package com.example.car.industry.repository;

import com.example.car.industry.entity.Cars;
import com.example.car.industry.entity.Reservation;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUser(User user);

    Optional<Reservation> findByCar(Cars car);



}
