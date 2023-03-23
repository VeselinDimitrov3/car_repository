package com.example.car.industry.controller;


import com.example.car.industry.convertor.ReservationConvertor;
import com.example.car.industry.dto.ReservationCarChange;
import com.example.car.industry.dto.ReservationPeriodChange;
import com.example.car.industry.dto.ReservationRequest;
import com.example.car.industry.dto.ReservationResponse;
import com.example.car.industry.impl.CarsServiceImpl;
import com.example.car.industry.impl.ReservationServiceImpl;
import com.example.car.industry.impl.UserServiceImpl;
import com.example.car.industry.repository.ReservationRepository;
import com.example.car.industry.util.VerUtil;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {
    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private ReservationConvertor reservationConvertor;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CarsServiceImpl carService;
    @Autowired
    private ReservationRepository reservationRepository;


    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationConvertor.responseReservation(reservationService.findById(id)));
    }

    @GetMapping(path = "/get_with_user_email")
    public ResponseEntity<List<ReservationResponse>> getByUserEmail(@RequestParam String userEmail){
        List<ReservationResponse> reservationResponses = reservationService.findByUser(userService.findByEmail(userEmail))
                .stream()
                .map(reservationConvertor::responseReservation)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationResponses);
    }

    @GetMapping(path = "/get_with_car_id")
    public ResponseEntity<List<ReservationResponse>> getByCarId(@RequestParam Long carId) {
        List<ReservationResponse> reservationResponses = reservationService.findByCar(carService.findById(carId))
                .stream()
                .map(reservationConvertor::responseReservation)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(reservationResponses);
    }

    @GetMapping(path = "/get_with_period")
    public ResponseEntity<List<ReservationResponse>> getByPeriod(@RequestParam String startingDate, @RequestParam String endDate) {
        if (!(VerUtil.isDateFormatOk(startingDate) && (VerUtil.isDateFormatOk(endDate)))) {
            throw new RuntimeException("Date format invalid! Provide date format DD/MM/YYYY.");
        } else {
            List<ReservationResponse> reservationResponses = reservationService.findByPeriod(startingDate, endDate)
                    .stream()
                    .map(reservationConvertor::responseReservation)
                    .collect(Collectors.toList());
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(reservationResponses);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody @Valid ReservationRequest reservationRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservationService.save(reservationRequest));
    }

    @PutMapping(path = "/{id}/change_car")
    public ResponseEntity<ReservationResponse> changeCar(@PathVariable Long id, @RequestBody @Valid ReservationCarChange carChange) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(reservationService.changeCar(id, carService.findById(Long.parseLong(carChange.getCarId()))));
    }

    @PutMapping(path = "{id}/change_period")
    public ResponseEntity<ReservationResponse> changePeriod(@PathVariable Long id, @RequestBody @Valid ReservationPeriodChange periodChange) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(reservationService.changePeriod(id, periodChange.getNewEndDate()));
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Reservation has been deleted successfully");
    }


}