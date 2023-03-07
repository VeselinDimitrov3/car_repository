package com.example.car.industry.dto;

import com.example.car.industry.entity.Users;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private String dateOfBeginning;
    private String endDateOfReservation;
    private String reservedCar;
    private Users user;


}
