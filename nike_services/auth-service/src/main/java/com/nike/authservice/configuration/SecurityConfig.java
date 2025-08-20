package com.nike.authservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomJwtDecoder customJwtDecoder ;
    private final String[] PUBLIC_ENDPOINTS = {
            "/users",
            "/auth/**"
    };

//    @Value("${jwt.signerKey}")
//    private String SIGNER_KEY;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                        // there're 2 ways to authorize user: endpoint authorization or method authorization
//                        .requestMatchers(HttpMethod.GET, "/users").hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwtConfigurer -> jwtConfigurer
                                .decoder(customJwtDecoder)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        ));
        return http.build();
    }

    //decode token
//    @Bean
//    JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS256");
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS256)
//                .build();
//    }

    //convert role prefix
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter () {
        JwtGrantedAuthoritiesConverter authorConvert = new JwtGrantedAuthoritiesConverter();
        authorConvert.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter authenticationConvert = new JwtAuthenticationConverter();
        authenticationConvert.setJwtGrantedAuthoritiesConverter(authorConvert);
        return authenticationConvert;
    }

    //encrypt
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
