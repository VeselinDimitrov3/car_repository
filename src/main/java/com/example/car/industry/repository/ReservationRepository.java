package com.example.car.industry.repository;

import com.example.car.industry.dto.ReservationResponse;
import com.example.car.industry.entity.Reservation;
import com.example.car.industry.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByDateOfBeginning(String dateOfBeginning);
    Optional<Reservation> findEndDateOfReservation(String endDateOfReservation);
    Optional<Reservation> findReservedCar(String reservedCar);
    Optional<Reservation> findByUser(Users user);



}
