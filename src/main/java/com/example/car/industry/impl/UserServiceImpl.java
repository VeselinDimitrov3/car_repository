package com.example.car.industry.impl;

import com.example.car.industry.convertor.UserConvertor;
import com.example.car.industry.dto.UserPasswordUpdate;
import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.RecordNotFoundException;
import com.example.car.industry.repository.UserRepository;
import com.example.car.industry.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserConvertor userConvertor;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(UserConvertor userConvertor, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userConvertor = userConvertor;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        Users userToBeSaved = userConvertor.toUser(registerRequest);
        return userConvertor.toResponse(userRepository.save(userToBeSaved));
    }
    @Override
    public void updateUser(UserPasswordUpdate client) throws RecordNotFoundException{
        Optional<Users> user = userRepository.findById(client.getId());
        if (user.isEmpty()) {
            throw new RecordNotFoundException("User not found or invalid credentials");
        } else if (!passwordEncoder.matches(
                client.getPassword(),
                user.get().getPassword())) {
            throw new RecordNotFoundException("User not found or password is wrong");
        } else {
            user.get().setPassword(client.getNewPassword());
        }
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public RegisterResponse getById(Long id) throws RecordNotFoundException{
        return userConvertor.toResponse(userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Id %s not found", id))));
    }
    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("User not found"));
    }
}
