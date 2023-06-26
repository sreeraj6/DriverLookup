package com.example.DriverLookupSystem.Service;

import com.example.DriverLookupSystem.Dto.LoginRequestDto;
import com.example.DriverLookupSystem.Dto.SignupRequestDto;
import com.example.DriverLookupSystem.Dto.SignupResponseDto;

public interface AuthService {
    SignupResponseDto registerUser(SignupRequestDto signupRequestDto);

    SignupResponseDto loginUser(LoginRequestDto loginRequestDto);
}
