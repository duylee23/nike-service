package com.nike.authservice.service;


import com.nike.authservice.dto.AuthRequest;
import com.nike.authservice.dto.AuthResponse;
import com.nike.authservice.dto.request.IntrospectRequest;
import com.nike.authservice.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
    IntrospectResponse introspect(IntrospectRequest req) throws JOSEException, ParseException;
}
