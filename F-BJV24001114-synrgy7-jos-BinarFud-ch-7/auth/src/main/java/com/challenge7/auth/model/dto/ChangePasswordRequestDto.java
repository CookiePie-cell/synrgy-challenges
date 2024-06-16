package com.challenge7.auth.model.dto;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    private String email;
    private String newPassword;
    private String newConfirmPassword;
    private String otp;
}
