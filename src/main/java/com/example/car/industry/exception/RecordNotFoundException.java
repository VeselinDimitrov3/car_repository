package com.example.car.industry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;

@ResponseStatus(HttpStatus.ACCEPTED)
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(String message) {
        super(message);
    }

}
