package com.example.DriverLookupSystem.Service;

import com.example.DriverLookupSystem.Dto.LoginRequestDto;
import com.example.DriverLookupSystem.Dto.SignupRequestDto;
import com.example.DriverLookupSystem.Dto.SignupResponseDto;
import com.example.DriverLookupSystem.Model.User;
import com.example.DriverLookupSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
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
    @Autowired
    private AuthenticationManager authenticationManager;

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

        return new SignupResponseDto(newUser,"User Signedup Successfully",jwtService.generateToken(hm, newUser));
    }

    //login user
    @Override
    public SignupResponseDto loginUser(LoginRequestDto loginRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));
            var user = userRepository.findByEmail(loginRequestDto.getEmail()).orElse(null);
            System.out.println(user);
            Map<String, Object> hm = new HashMap<>();
            hm.put("userid", user.getUid());
            hm.put("name", user.getName());
            var token = jwtService.generateToken(hm,user);
            return new SignupResponseDto(user,"User Login Successfully",jwtService.generateToken(hm, user));
        }
        catch (Exception e) {
            return new SignupResponseDto(HttpStatus.BAD_REQUEST.value(), false, e.getMessage(), null,null);
        }
    }
}
