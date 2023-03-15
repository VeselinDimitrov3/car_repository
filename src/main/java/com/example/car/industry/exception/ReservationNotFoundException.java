package com.example.car.industry.exception;

import java.util.NoSuchElementException;

public class ReservationNotFoundException extends NoSuchElementException {
    public ReservationNotFoundException(String ex) {
        super(ex);
    }
}
