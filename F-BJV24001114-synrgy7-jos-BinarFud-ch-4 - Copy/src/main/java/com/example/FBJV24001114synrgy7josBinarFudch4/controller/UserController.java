package com.example.FBJV24001114synrgy7josBinarFudch4.controller;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.ApiResponse;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UserRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UserCUDResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UserResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(
            path = "/api/users/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<UserResponseDto>> findById(@PathVariable("userId") UUID userId) {
        UserResponseDto user = userService.getById(userId);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<UserResponseDto>builder()
                        .statusCode(200)
                        .data(user)
                        .build());
    }

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<UserCUDResponseDto>> save(@RequestBody UserRequestDto request) {
        UserCUDResponseDto response = userService.save(request);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<UserCUDResponseDto>builder()
                        .statusCode(201)
                        .data(response)
                        .build());
    }

    @PutMapping(
            path = "/api/users/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<UserCUDResponseDto>> update(@PathVariable("userId") UUID userId, @RequestBody UserRequestDto request) {
        UserCUDResponseDto response = userService.update(userId, request);
        return ResponseEntity
                .ok()
                .body(ApiResponse.<UserCUDResponseDto>builder()
                        .statusCode(200)
                        .data(response)
                        .build());
    }

    @DeleteMapping(
            path = "/api/users/{userId}"
    )
    public ResponseEntity<ApiResponse<Map<String, String>>> delete(@PathVariable("userId") UUID userId) {
        userService.deleteById(userId);
        Map<String, String> response = new HashMap<>(){{
            put("status", "Success");
        }};
        return ResponseEntity
                .ok()
                .body(ApiResponse.<Map<String, String>>builder()
                        .statusCode(200)
                        .data(response)
                        .build());
    }
}
