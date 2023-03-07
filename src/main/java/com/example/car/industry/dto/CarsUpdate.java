package com.example.car.industry.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarsUpdate {

    @NotNull
    private Long id;

    @NotNull
    private String brand;

    @NotNull
    private String newBrand;

    @NotNull
    private String model;

    @NotNull
    private String newModel;

    @NotNull
    private Double pricePerDay;

    @NotNull
    private Double newPrice;

    @NotNull
    private int seats;

    @NotNull
    private int newSeats;

}
