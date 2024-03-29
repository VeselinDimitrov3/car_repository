package com.example.car.industry.dto;


import com.example.car.industry.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    @NotNull
    @Size(min = 1, max = 3)
    @Pattern(regexp = "\\d", message = "Car ID must be only with max 3 digit")
    private String carId;

    @NotNull
    @Email(message = "Invalid user email!")
    private String userEmail;

    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^(?:(?:31(\\/)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$",
            message = "Date format invalid, You have to provide date format DD/MM/YYYY (day, month, year)")
    private String startDate;

    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^(?:(?:31(\\/)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$",
            message = "Date format invalid, You have to provide date format DD/MM/YYYY. (day, month, year)")
    private String endDate;

}
