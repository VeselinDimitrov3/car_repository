package com.example.car.industry.service;


import com.example.car.industry.dto.UserPasswordUpdate;
import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.EmailDoublingException;
import com.example.car.industry.exception.PassportIdDoublingException;
import com.example.car.industry.exception.PhoneNumberDoublingException;

public interface UserService {
    Users findById(Long id);

    Users findByEmail(String email);

    RegisterResponse register(RegisterRequest registerRequest) throws EmailDoublingException, PhoneNumberDoublingException, PassportIdDoublingException;

    void updatePassword(UserPasswordUpdate userPasswordUpdate);

    void deleteById(Long id);


}
