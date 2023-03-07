package com.example.car.industry.impl;

import com.example.car.industry.convertor.ReservationConvertor;
import com.example.car.industry.dto.ReservationRequest;
import com.example.car.industry.dto.ReservationResponse;
import com.example.car.industry.dto.ReservationUpdate;
import com.example.car.industry.entity.Reservation;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.RecordNotFoundException;
import com.example.car.industry.repository.ReservationRepository;
import com.example.car.industry.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationConvertor reservationConvertor;


    @Override
    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = Reservation.builder()
                .dateOfBeginning(reservationRequest.getDateOfBeginning())
                .endDateOfReservation(reservationRequest.getEndDateOfReservation())
                .reservedCar(reservationRequest.getReservedCar())
                .build();

        return reservationConvertor.responseReservation(reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponse getById(Long id) {
        return reservationConvertor.responseReservation(reservationRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Id %s not found", id))));
    }

    @Override
    public ReservationResponse findByUser(Users user) {
        return reservationConvertor.responseReservation(reservationRepository.findByUser(user)
                .orElseThrow(() -> new RecordNotFoundException(String.format("User %s not found", user))));
    }

    @Override
    public ReservationResponse findReservedCar(String reservedCar) {
        return reservationConvertor.responseReservation(reservationRepository.findReservedCar(reservedCar)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Car %s not found", reservedCar))));
    }

    @Override
    public ReservationResponse getByBeginningDate(String dateOfBeginning) {
        return reservationConvertor.responseReservation(reservationRepository.findByDateOfBeginning(dateOfBeginning)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Date %s not found", dateOfBeginning))));
    }

    @Override
    public ReservationResponse getByEndDate(String endDateOfReservation) {
        return reservationConvertor.responseReservation(reservationRepository.findEndDateOfReservation(endDateOfReservation)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Date %s not found", endDateOfReservation))));
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void updateReservationCar(ReservationUpdate reservationUpdate) {
        Optional<Reservation> car = reservationRepository.updateReservationCar(reservationUpdate.getReservedCar());
        if (car.isEmpty()) {
            throw new RecordNotFoundException("Car not found or invalid credentials");
        } else {
            car.get().setId(car.get().getId());
            car.get().setReservedCar(reservationUpdate.getNewReservedCar());
        }
    }


    @Override
    public void updateBeginningDate(ReservationUpdate reservationUpdate) {
        Optional<Reservation> start = reservationRepository.updateBeginningDate(reservationUpdate.getDateOfBeginning());
        if (start.isEmpty()) {
            throw new RecordNotFoundException("Date not found or invalid credentials");
        } else {
            start.get().setId(start.get().getId());
            start.get().setDateOfBeginning(reservationUpdate.getDateOfBeginning());
        }
    }

    @Override
    public void updateReservationEndDate(ReservationUpdate reservationUpdate) {
        Optional<Reservation> end = reservationRepository.updateReservationEndDate(reservationUpdate.getEndDateOfReservation());
        if (end.isEmpty()) {
            throw new RecordNotFoundException("Date not found or invalid credentials");
        } else {
            end.get().setId(end.get().getId());
            end.get().setEndDateOfReservation(reservationUpdate.getEndDateOfReservation());
        }
    }
}