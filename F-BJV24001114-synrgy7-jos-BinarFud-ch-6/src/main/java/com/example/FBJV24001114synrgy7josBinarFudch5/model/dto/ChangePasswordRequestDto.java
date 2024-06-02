package com.example.FBJV24001114synrgy7josBinarFudch5.model.dto;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    private String email;
    private String newPassword;
    private String newConfirmPassword;
    private String otp;
}
