package com.example.car.industry.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarsUpdate {

    @NotNull
    @Size(min = 1, max = 3)
    private String id;

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
