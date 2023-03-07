package com.example.car.industry.service;

import com.example.car.industry.dto.ReservationRequest;
import com.example.car.industry.dto.ReservationResponse;
import com.example.car.industry.dto.ReservationUpdate;
import com.example.car.industry.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {
    ReservationResponse addReservation(ReservationRequest reservationRequest);
    ReservationResponse getById(Long id);
    ReservationResponse findByUser(Users user);
    ReservationResponse findReservedCar(String reservedCar);
    ReservationResponse getByBeginningDate(String dateOfBeginning);
    ReservationResponse getByEndDate(String endDateOfReservation);
    void deleteReservation(Long id);
    void updateReservationCar(ReservationUpdate reservationUpdate);
    void updateBeginningDate(ReservationUpdate reservationUpdate);
    void updateReservationEndDate(ReservationUpdate reservationUpdate);


}
