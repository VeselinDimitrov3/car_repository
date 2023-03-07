package com.example.car.industry.dto;


import lombok.*;

@Data
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RegisterRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
