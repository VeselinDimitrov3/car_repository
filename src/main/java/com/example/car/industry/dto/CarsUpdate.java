package com.example.car.industry.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    private String newBrand;

    @NotNull
    private String newModel;

    @NotNull
    @Pattern(regexp = "(\\d+(\\.\\d*)?)", message = "Price for day must be decimal")
    private String newPrice;

    @NotNull
    private int seats;

}
