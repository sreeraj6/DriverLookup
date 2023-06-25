package com.example.DriverLookupSystem.Controller;

import com.example.DriverLookupSystem.Dto.SignupRequestDto;
import com.example.DriverLookupSystem.Dto.SignupResponseDto;
import com.example.DriverLookupSystem.Service.AuthService;
import com.example.DriverLookupSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public SignupResponseDto addUser(@RequestBody SignupRequestDto requestDto) {
        return authService.registerUser(requestDto);
    }
}
