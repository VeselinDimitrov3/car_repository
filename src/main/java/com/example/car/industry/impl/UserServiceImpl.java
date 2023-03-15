package com.example.car.industry.impl;


import com.example.car.industry.convertor.UserConvertor;
import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.dto.UserPasswordUpdate;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.EmailDoublingException;
import com.example.car.industry.exception.PassportIdDoublingException;
import com.example.car.industry.exception.PhoneNumberDoublingException;
import com.example.car.industry.exception.RecordNotFoundException;
import com.example.car.industry.repository.UserRepository;
import com.example.car.industry.service.UserService;
import com.example.car.industry.util.VerUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.example.car.industry.util.VerUtil.isUserSaveOk;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserConvertor userConvertor;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserConvertor userConvertor) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConvertor = userConvertor;
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(String.format("User with id %d not found", id.intValue())));
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RecordNotFoundException(String.format("User with email %s not found", email)));
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest userRequest) throws EmailDoublingException, PhoneNumberDoublingException, PassportIdDoublingException {
        Users newUser = null;
        if (isUserSaveOk(userRepository.findAll(),
                userRequest.getEmail(),
                userRequest.getPhoneNumber(),
                userRequest.getPassportId())) {
            newUser = userRepository.save(userConvertor.toUser(userRequest, bCryptPasswordEncoder));
        }
        return userConvertor.toResponse(Objects.requireNonNull(newUser));
    }

    @Override
    @Transactional
    public void updatePassword(UserPasswordUpdate userPasswordUpdate) {
        if (VerUtil.login(userRepository.findAll(),
                userPasswordUpdate.getEmail(),
                bCryptPasswordEncoder,
                userPasswordUpdate.getPassword())) {
            Users modelingUser = findByEmail(userPasswordUpdate.getEmail());
            modelingUser.setPassword(bCryptPasswordEncoder.encode(userPasswordUpdate.getNewPassword()));
            userRepository.save(modelingUser);
        } else throw new RecordNotFoundException("User not found or invalid password");
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RecordNotFoundException("User not found");
        } else userRepository.deleteById(id);
    }
}
