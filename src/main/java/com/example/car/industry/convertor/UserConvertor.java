package com.example.car.industry.convertor;

import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    public Users toClient (RegisterRequest registerRequest) {
//        return Users.builder()
//                .firstName(registerRequest.getFirstName())
//                .lastName(registerRequest.getLastName())
//                .password(passwordEncoder.encode(registerRequest.getPassword()))
//                .email(registerRequest.getEmail())
//                .build();
//    }

    public RegisterResponse toResponse (Users user){
        return RegisterResponse.builder()
                .token(user.getToken())
                .build();
    }

}
