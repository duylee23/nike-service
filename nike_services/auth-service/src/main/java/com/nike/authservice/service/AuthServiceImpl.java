package com.nike.authservice.service;

import com.nike.authservice.dto.AuthRequest;
import com.nike.authservice.dto.AuthResponse;
import com.nike.authservice.dto.request.IntrospectRequest;
import com.nike.authservice.dto.response.IntrospectResponse;
import com.nike.authservice.dto.response.UserResponse;
import com.nike.authservice.entity.User;
import com.nike.authservice.exception.AppException;
import com.nike.authservice.exception.ErrorCode;
import com.nike.authservice.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthServiceImpl implements AuthService {

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    UserRepository userRepository;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        var user = this.userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_CREDENTIALS));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(authRequest.getPassword(), user.getPassword());
        if (!matches) {
            throw new AppException(ErrorCode.PASSWORD_INVALID);
        }

        var token = generateToken(user);

        var userResponse = UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRoles())
                .username(user.getUsername())
                .build();

        return AuthResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .user(userResponse)
                .build();
    }

    private String generateToken(User user) {
        //generate header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        //generate claim set
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(3600, ChronoUnit.MINUTES).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        //convert the claimsSet to Json, wrap it inside a payload object
        Payload payload = new Payload(claimsSet.toJSONObject());

        //combine header and payload
        JWSObject jwsObject = new JWSObject(header, payload);
        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot sign JWT", e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_"+ role.toString());
                if(!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> {
                        stringJoiner.add("PERMISSION_"+ permission.getName());
                    });
                }
            });
        }
        return stringJoiner.toString();
    }

    public IntrospectResponse introspect(IntrospectRequest req) throws JOSEException, ParseException
    {
        var token = req.getToken();
        boolean isValid = true;
        try{
            verifyToken(token, false);

        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectResponse.builder()
                .isValid(isValid)
                .build();
    }


    private SignedJWT verifyToken(String token, boolean isRefreshToken) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        //parse token into signedJwt to get information
        SignedJWT jwt = SignedJWT.parse(token);

        Date expireTime = (isRefreshToken) ? new Date
                (jwt.getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                )
                : jwt.getJWTClaimsSet().getExpirationTime();

        var isVerified = jwt.verify(verifier);
        if (!isVerified || expireTime.before(new Date())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return jwt;
    }
}
