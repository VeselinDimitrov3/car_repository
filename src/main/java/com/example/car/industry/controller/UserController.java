package com.example.car.industry.controller;

import com.example.car.industry.convertor.UserConvertor;
import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.dto.UserPasswordUpdate;
import com.example.car.industry.exception.EmailDoublingException;
import com.example.car.industry.exception.PassportIdDoublingException;
import com.example.car.industry.exception.PhoneNumberDoublingException;
import com.example.car.industry.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserConvertor userConvertor;

    @GetMapping(path = "/{id}")
    public ResponseEntity<RegisterResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userConvertor.toResponse(userService.findById(id)));
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<RegisterResponse> getByEmail(@RequestParam String email) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userConvertor.toResponse(userService.findByEmail(email)));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<RegisterResponse> addUser(@RequestBody @Valid RegisterRequest newUser) throws PhoneNumberDoublingException, EmailDoublingException, PassportIdDoublingException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(newUser));
    }

    @PutMapping(path = "/change_password")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UserPasswordUpdate userPasswordUpdate) {
        userService.updatePassword(userPasswordUpdate);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Password was changed");
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User has been deleted successfully");
    }




}
