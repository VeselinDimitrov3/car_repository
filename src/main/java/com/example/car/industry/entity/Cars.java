package com.example.car.industry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private Double pricePerDay;
    @Column
    private Integer seats;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car")
    private Set<Reservation> reservationHashSet = new HashSet<>();
}
