package com.example.car.industry.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
