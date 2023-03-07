package com.example.car.industry.impl;

import com.example.car.industry.config.JjwtService;
import com.example.car.industry.convertor.UserConvertor;
import com.example.car.industry.dto.UserPasswordUpdate;
import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.RecordNotFoundException;
import com.example.car.industry.repository.UserRepository;
import com.example.car.industry.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserConvertor userConvertor;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JjwtService jwtService;

    public RegisterResponse register(RegisterRequest registerRequest) throws RecordNotFoundException{
        Users user = Users.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();


        Users savedUser = userRepository.save(user);
        String token = jwtService.generateJwt(savedUser);
        return RegisterResponse.builder().token(token).build();
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
    public Users findByEmail(String email) throws RecordNotFoundException{
        return userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("User not found"));
    }
}
