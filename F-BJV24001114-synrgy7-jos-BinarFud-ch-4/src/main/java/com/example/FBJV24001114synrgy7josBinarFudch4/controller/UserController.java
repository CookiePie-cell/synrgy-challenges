package com.example.FBJV24001114synrgy7josBinarFudch4.controller;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UserRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UserCUDResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.UserResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public UserResponseDto findById(@PathVariable("userId") UUID userId) {
        return userService.getById(userId);
    }

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserCUDResponseDto save(@RequestBody UserRequestDto request) {
        return userService.save(request);
    }

    @PutMapping(
            path = "/api/users/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserCUDResponseDto update(@PathVariable("userId") UUID userId, @RequestBody UserRequestDto request) {
        return userService.update(userId, request);
    }

    @DeleteMapping(
            path = "/api/users/{userId}"
    )
    public Map<String, String> delete(@PathVariable("userId") UUID userId) {
        userService.deleteById(userId);
        return new HashMap<>(){{
            put("status", "Success");
        }};
    }
}
