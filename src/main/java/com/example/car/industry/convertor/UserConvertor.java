package com.example.car.industry.convertor;

import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Users toUser (RegisterRequest registerRequest) {
        return Users.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .address(registerRequest.getAddress())
                .build();
    }

    public RegisterResponse toResponse (Users user){
        return RegisterResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
