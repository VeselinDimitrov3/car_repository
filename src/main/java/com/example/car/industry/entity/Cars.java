package com.example.car.industry.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
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
    private String pricePerDay;
    @Column
    private int seats;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cars cars = (Cars) o;
        return id != null && Objects.equals(id, cars.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
