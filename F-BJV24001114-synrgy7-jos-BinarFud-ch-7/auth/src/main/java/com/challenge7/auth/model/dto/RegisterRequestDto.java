package com.challenge7.auth.model.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;

    private String password;

    private String fullname;

    private String role;
}
