package com.example.FBJV24001114synrgy7josBinarFudch5.service;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UserRequestDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UserCUDResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.UserResponseDto;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.User;
import com.example.FBJV24001114synrgy7josBinarFudch5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserResponseDto getById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::mapToUserResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("AuthUser tidak ditemukan"));

    }

    private UserResponseDto mapToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .emai(user.getEmail())
                .build();
    }

    @Override
    public UserCUDResponseDto save(UserRequestDto request) {
        String username = userRepository.saveUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        return UserCUDResponseDto.builder().username(username).build();
    }

    @Override
    public UserCUDResponseDto update(UUID userId, UserRequestDto updatedUser) {
        String username = userRepository.updateUser(
                userId,
                updatedUser.getUsername(),
                updatedUser.getEmail(),
                updatedUser.getPassword()
        );
        return UserCUDResponseDto.builder().username(username).build();
    }

    @Override
    public void deleteById(UUID userId) {
        userRepository.deleteUser(userId);
    }
}
