package com.nike.authservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(404, "User not found! ", HttpStatus.NOT_FOUND),
    PASSWORD_INVALID(401, "User name or password is invalid", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(401, "Unauthenticated", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
