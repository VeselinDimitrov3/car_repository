package com.example.car.industry.dto;

import lombok.*;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponse {
    private CarResponse car;
    private RegisterResponse user;
    private String reservationDate;
    private String returnDate;
    private Double totalPrice;

}

