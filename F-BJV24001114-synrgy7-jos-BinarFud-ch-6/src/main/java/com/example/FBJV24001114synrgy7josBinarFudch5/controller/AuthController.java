package com.example.FBJV24001114synrgy7josBinarFudch5.controller;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.ApiResponse;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.LoginRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.LoginResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.RegisterRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

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
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto request) {
        LoginResponseDto loginResponse = authService.login(request);

        return ResponseEntity
                .ok(ApiResponse.<LoginResponseDto>builder()
                        .statusCode(200)
                        .data(loginResponse)
                        .build());
    }
}
