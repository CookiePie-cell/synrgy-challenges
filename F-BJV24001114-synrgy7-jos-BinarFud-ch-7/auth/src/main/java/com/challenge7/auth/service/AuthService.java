package com.challenge7.auth.service;


import com.challenge7.auth.model.dto.*;

public interface AuthService {
    String register(RegisterRequestDto request, Boolean isEnabled);

    LoginResponseDto login(LoginRequestDto request);

    void confirmOTP(String otp);

    void forgetPassword(ForgetPasswordRequestDto request);

    void changePassword(ChangePasswordRequestDto request);

}
