package com.example.car.industry.convertor;

import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.dto.RegisterResponse;
import com.example.car.industry.dto.ReservationRequest;
import com.example.car.industry.dto.ReservationResponse;
import com.example.car.industry.entity.Cars;
import com.example.car.industry.entity.Reservation;
import com.example.car.industry.entity.Users;
import com.example.car.industry.impl.CarsServiceImpl;
import com.example.car.industry.impl.UserServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@Component
public class ReservationConvertor {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CarsServiceImpl carService;

    public Reservation requestReservation(ReservationRequest reservationRequest) {
        Users user = userService.findByEmail(reservationRequest.getUserEmail());
        Cars car = carService.findById(Long.parseUnsignedLong(reservationRequest.getCarId()));
        LocalDate startingDate = LocalDate.parse(reservationRequest.getStartDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(reservationRequest.getEndDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Reservation.builder()
                .user(user)
                .car(car)
                .startingDate(startingDate)
                .endDate(endDate)
                .totalPrice(ChronoUnit.DAYS.between(startingDate, endDate) * car.getPricePerDay())
                .build();
    }

    public ReservationResponse responseReservation(Reservation reservation) {
        return ReservationResponse.builder()
                .user(RegisterResponse.builder()
                        .firstName(reservation.getUser().getFirstName())
                        .lastName(reservation.getUser().getLastName())
                        .email(reservation.getUser().getEmail())
                        .phoneNumber(reservation.getUser().getPhoneNumber())
                        .build())
                .car(CarResponse.builder()
                        .brand(reservation.getCar().getBrand())
                        .model(reservation.getCar().getModel())
                        .pricePerDay(String.valueOf(reservation.getCar().getPricePerDay()))
                        .seats(reservation.getCar().getSeats())
                        .build())
                .reservationDate(reservation.getStartingDate().toString())
                .returnDate(reservation.getEndDate().toString())
                .totalPrice(reservation.getTotalPrice())
                .build();

    }

}
