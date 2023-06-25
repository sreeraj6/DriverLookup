package com.example.DriverLookupSystem.Service;

import com.example.DriverLookupSystem.Dto.SignupRequestDto;
import com.example.DriverLookupSystem.Dto.SignupResponseDto;

public interface AuthService {
    SignupResponseDto registerUser(SignupRequestDto signupRequestDto);
}
