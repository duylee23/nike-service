package com.nike.authservice.controller;


import com.nike.authservice.service.AuthService;
import com.nike.authservice.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final AuthService productService;
    private final UploadFileService uploadFileService;

}
