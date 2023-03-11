package com.example.car.industry.service;


import com.example.car.industry.dto.UserPasswordUpdate;
import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.RecordNotFoundException;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest) ;
    void updateUser(UserPasswordUpdate user) throws RecordNotFoundException;
    void deleteUser(Long id);
    RegisterResponse getById(Long id) throws RecordNotFoundException;
    Users findByEmail(String email);


}
