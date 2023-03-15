package com.example.car.industry.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CarRequest {
    private String brand;
    private String model;
    private String pricePerDay;
    private int seats;


}
