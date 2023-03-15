package com.example.car.industry.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailDoublingException extends SQLIntegrityConstraintViolationException {
    public EmailDoublingException(String ex) {
        super(ex);
    }
}
