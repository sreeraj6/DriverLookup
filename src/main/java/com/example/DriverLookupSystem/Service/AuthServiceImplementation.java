package com.example.DriverLookupSystem.Service;

import com.example.DriverLookupSystem.Dto.SignupRequestDto;
import com.example.DriverLookupSystem.Dto.SignupResponseDto;
import com.example.DriverLookupSystem.Model.User;
import com.example.DriverLookupSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImplementation implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Override
    public SignupResponseDto registerUser(SignupRequestDto signupRequestDto) {
        if(userRepository.existsByEmail(signupRequestDto.getEmail())){
            return new SignupResponseDto(HttpStatus.FOUND.value(),false,"User already Exist",null,null);
        }
        var newUser = User
                .builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getEmail())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build();

        userRepository.save(newUser);

        Map<String, Object> hm = new HashMap<>();
        hm.put("userid", newUser.getUid());
        hm.put("name", newUser.getName());

        return new SignupResponseDto(newUser,jwtService.generateToken(hm, newUser));
    }
}
