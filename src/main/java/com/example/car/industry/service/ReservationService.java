package com.example.car.industry.service;

import com.example.car.industry.dto.ReservationRequest;
import com.example.car.industry.dto.ReservationResponse;
import com.example.car.industry.entity.Cars;
import com.example.car.industry.entity.Reservation;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    ReservationResponse save(ReservationRequest reservationRequest) throws RecordNotFoundException;

    Reservation findById(Long id);

    List<Reservation> findByUser(Users user);

    List<Reservation> findByCar(Cars car);

    List<Reservation> findByPeriod(String startingDate, String endDate);

    void deleteById(Long id);

    ReservationResponse changeCar(Long id, Cars car);

    ReservationResponse changePeriod(Long id, String newEndDate);


}
