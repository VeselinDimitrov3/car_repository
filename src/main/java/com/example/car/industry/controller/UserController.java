package com.example.car.industry.controller;

import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.dto.UserPasswordUpdate;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.RecordNotFoundException;
import com.example.car.industry.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) throws RecordNotFoundException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(registerRequest));

    }

    @PutMapping(path = "/update")
    ResponseEntity<String> updateClient(@RequestBody @Valid UserPasswordUpdate userPasswordUpdate) throws RecordNotFoundException{
        userService.updateUser(userPasswordUpdate);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(String.format("Password is successfully changed"));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteClient (@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity
                .ok()
                .body(String.format("Client successfully deleted", id));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<RegisterResponse> getById(@PathVariable Long id) throws RecordNotFoundException{
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userService.getById(id));
    }
    @GetMapping(path = "/getEmail/{id}")
    ResponseEntity<Users> findByEmail(@PathVariable String email) throws RecordNotFoundException{
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userService.findByEmail(email));
    }




}
