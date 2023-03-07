package com.example.car.industry.controller;

import com.example.car.industry.dto.*;
import com.example.car.industry.entity.Reservation;
import com.example.car.industry.entity.Users;
import com.example.car.industry.service.CarService;
import com.example.car.industry.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/reservations")
@RequiredArgsConstructor
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    ResponseEntity<ReservationResponse> addReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservationService.addReservation(reservationRequest));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ReservationResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationService.getById(id));
    }

    @GetMapping(path = "/user/{id}")
    ResponseEntity<ReservationResponse> findByUser(@PathVariable Users user) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationService.findByUser(user));
    }

    @GetMapping(path = "/car/{id}")
    ResponseEntity<ReservationResponse> findReservedCar(@PathVariable String reservedCar) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationService.findReservedCar(reservedCar));
    }

    @GetMapping(path = "/beginning/{id}")
    ResponseEntity<ReservationResponse> getByBeginningDate(@PathVariable String dateOfBeginning) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationService.getByBeginningDate(dateOfBeginning));
    }

    @GetMapping(path = "/end/{id}")
    ResponseEntity<ReservationResponse> getByEndDate(@PathVariable String endDateOfReservation) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationService.getByEndDate(endDateOfReservation));
    }

    @DeleteMapping(path = "/delete")
    ResponseEntity<String> deleteReservation(Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity
                .ok()
                .body(String.format("Reservation successfully deleted", id));
    }

    @PutMapping(path = "/car/update")
    ResponseEntity<String> updateReservationCar (@RequestBody @Valid ReservationUpdate reservationUpdate) {
        reservationService.updateReservationCar(reservationUpdate);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(String.format("Car reservation is successfully updated"));
    }

    @PutMapping(path = "/date/update")
    ResponseEntity<String> updateBeginningDate (@RequestBody @Valid ReservationUpdate dateOfBeginning) {
        reservationService.updateBeginningDate(dateOfBeginning);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(String.format("Date reservation is successfully updated"));
    }
    @PutMapping(path = "/date/update")
    ResponseEntity<String> updateReservationEndDate (@RequestBody @Valid ReservationUpdate endDateOfReservation) {
        reservationService.updateReservationEndDate(endDateOfReservation);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(String.format("Date reservation is successfully updated"));
    }

}
