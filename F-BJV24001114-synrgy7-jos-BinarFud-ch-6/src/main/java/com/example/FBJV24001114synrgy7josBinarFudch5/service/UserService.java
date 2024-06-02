package com.example.FBJV24001114synrgy7josBinarFudch5.service;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UserRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UserCUDResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UserResponseDto;

import java.util.UUID;

public interface UserService {
    UserResponseDto getById(UUID id);
    UserCUDResponseDto save(UserRequestDto user);
    UserCUDResponseDto update(UUID userId, UserRequestDto user);
    void deleteById(UUID userId);

}