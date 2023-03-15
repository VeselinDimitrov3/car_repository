package com.example.car.industry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String address;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column(unique = true)
    private String passportId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Reservation> reservationsList = new ArrayList<>();


}
