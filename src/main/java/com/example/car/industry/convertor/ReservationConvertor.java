package com.example.car.industry.convertor;

import com.example.car.industry.dto.ReservationRequest;
import com.example.car.industry.dto.ReservationResponse;
import com.example.car.industry.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationConvertor {

    public Reservation requestReservation(ReservationRequest reservationRequest) {
        return Reservation.builder()
                .user(reservationRequest.getUser())
                .dateOfBeginning(reservationRequest.getDateOfBeginning())
                .endDateOfReservation(reservationRequest.getEndDateOfReservation())
                .reservedCar(reservationRequest.getReservedCar())
                .build();
    }


    public ReservationResponse responseReservation (Reservation reservation) {
        return ReservationResponse.builder()
                .dateOfBeginning(reservation.getDateOfBeginning())
                .endDateOfReservation(reservation.getEndDateOfReservation())
                .reservedCar(reservation.getReservedCar())
                .build();
    }
}
