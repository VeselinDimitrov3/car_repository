package com.example.car.industry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPasswordUpdate {

    @NotNull
    private Long id;

    @NotNull
    private String password;

    @NotNull
    private String newPassword;

}
