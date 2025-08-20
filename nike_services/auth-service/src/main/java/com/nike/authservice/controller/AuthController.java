package com.nike.authservice.controller;

import com.nike.authservice.dto.AuthRequest;
import com.nike.authservice.dto.AuthResponse;
import com.nike.authservice.dto.response.ApiResponse;
import com.nike.authservice.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)


public class AuthController {
    AuthService authService;
    @PostMapping("/log-in")
    ApiResponse<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        System.out.println(">> LOGIN REQUEST: " + request.getUsername());
        var result = authService.authenticate(request);
        return ApiResponse.<AuthResponse>builder()
                .result(result)
                .message("Log-in successfully!")
                .build();
    }
}
