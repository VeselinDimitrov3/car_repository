package com.example.car.industry.dto;


import com.example.car.industry.entity.Users;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ReservationRequest {
    private Users user;
    private String dateOfBeginning;
    private String endDateOfReservation;
    private String reservedCar;

}
