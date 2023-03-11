package com.example.car.industry.service;

import com.example.car.industry.dto.ReservationRequest;
import com.example.car.industry.dto.ReservationResponse;
import com.example.car.industry.dto.ReservationUpdate;
import com.example.car.industry.entity.Reservation;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.RecordNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {
    ReservationResponse addReservation(ReservationRequest reservationRequest);
    ReservationResponse getById(Long id);
    Reservation findByUser(Users user);
    Reservation findReservedCar(String reservedCar) throws RecordNotFoundException;
    Reservation findDateOfBeginning(String dateOfBeginning) throws RecordNotFoundException;
    Reservation findDateOfEnding(String endDate) throws RecordNotFoundException;
    void deleteReservation(Long id);
    void updateReservationCar(ReservationUpdate reservationUpdate);
    void updateBeginningDate(ReservationUpdate reservationUpdate);
    void updateReservationEndDate(ReservationUpdate reservationUpdate);


}
