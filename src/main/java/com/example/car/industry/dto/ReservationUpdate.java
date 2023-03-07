package com.example.car.industry.dto;

import com.example.car.industry.entity.Reservation;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationUpdate {

    @NotNull
    private Long id;

    @NotNull
    private String dateOfBeginning;

    @NotNull
    private String newDateOfBeginning;

    @NotNull
    private String endDateOfReservation;

    @NotNull
    private String newEndDateOfReservation;

    @NotNull
    private String reservedCar;

    @NotNull
    private String newReservedCar;


}
