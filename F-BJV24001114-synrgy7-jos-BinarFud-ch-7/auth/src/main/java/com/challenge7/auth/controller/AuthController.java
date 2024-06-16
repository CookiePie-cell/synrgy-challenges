package com.challenge7.auth.controller;

import com.challenge7.auth.model.ApiResponse;
import com.challenge7.auth.model.dto.LoginRequestDto;
import com.challenge7.auth.model.dto.LoginResponseDto;
import com.challenge7.auth.model.dto.RegisterRequestDto;
import com.challenge7.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = {"/register", "/register/"})
    public ResponseEntity<ApiResponse<String>> registerManual(@RequestBody RegisterRequestDto request) {
        String message = authService.register(request, false);

        return ResponseEntity
                .ok(ApiResponse.<String>builder()
                        .statusCode(201)
                        .data(message)
                        .build());
    }

    @PostMapping(path = {"/login", "/login/"})
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto loginResponse = authService.login(request);

        return ResponseEntity
                .ok(ApiResponse.<LoginResponseDto>builder()
                        .statusCode(200)
                        .data(loginResponse)
                        .build());
    }
}
