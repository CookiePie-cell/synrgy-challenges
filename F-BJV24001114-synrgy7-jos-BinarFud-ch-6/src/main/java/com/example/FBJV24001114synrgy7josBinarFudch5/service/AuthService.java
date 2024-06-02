package com.example.FBJV24001114synrgy7josBinarFudch5.service;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.*;

public interface AuthService {
    String register(RegisterRequestDto request, Boolean isEnabled);

    LoginResponseDto login(LoginRequestDto request);

    void confirmOTP(String otp);

    void forgetPassword(ForgetPasswordRequestDto request);

    void changePassword(ChangePasswordRequestDto request);

}
