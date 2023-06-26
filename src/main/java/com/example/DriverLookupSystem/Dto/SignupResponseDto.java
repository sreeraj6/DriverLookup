package com.example.DriverLookupSystem.Dto;

import com.example.DriverLookupSystem.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponseDto {
    private int status;
    private boolean success;
    private String message;
    private UserDataDto data;
    private String token;

    public SignupResponseDto(User user,String message, String token) {
        status = HttpStatus.CREATED.value();
        success = true;
        this.message = message;
        this.data = new UserDataDto(user.getUid(),user.getName(),user.getUsername());
        this.token = token;
    }
}
