package com.example.car.industry.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Component
public class CarRequest {
    private String brand;
    private String model;
    private String pricePerDay;
    private int seats;


}
