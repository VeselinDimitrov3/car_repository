package com.example.car.industry.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RegisterRequest {
    private Long id;
    @Size(min = 2, message = "User first name should contain more than 2 characters")
    private String firstName;
    @Size(min = 3, message = "User last name should contain more than 3 characters")
    private String lastName;
    @Email(message = "Email should have proper email format.")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$", message = "" +
            "At least one upper case English letter\n" +
            "At least one lower case English letter\n" +
            "At least one digit\n" +
            "At least one special character\n" +
            "Minimum eight in length")
    private String password;
    @Size(min = 10, max = 10, message = "Phone number should contain 10 numbers")
    private String phoneNumber;
    private String address;
}
