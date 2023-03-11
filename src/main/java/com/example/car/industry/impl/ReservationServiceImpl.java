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
import jakarta.transaction.Transactional;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationConvertor reservationConvertor;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationConvertor reservationConvertor) {
        this.reservationRepository = reservationRepository;
        this.reservationConvertor = reservationConvertor;
    }
    @Override
    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        Reservation reservationToBeSaved = reservationConvertor.requestReservation(reservationRequest);
        return reservationConvertor.responseReservation(reservationRepository.save(reservationToBeSaved));
    }

    @Override
    public ReservationResponse getById(Long id) {
        return reservationConvertor.responseReservation(reservationRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Id %s not found", id))));
    }

    @Override
    public Reservation findByUser(Users user) {
        return reservationRepository.findByUser(user)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found"));
    }

    @Override
    public Reservation findReservedCar(String reservedCar) throws RecordNotFoundException {
//        return reservationConvertor.responseReservation(reservationRepository.findReservedCar(reservedCar)
//                .orElseThrow(() -> new RecordNotFoundException(String.format("Car %s not found", reservedCar))));
        return reservationRepository.findReservedCar(reservedCar).orElseThrow(()
                -> new RecordNotFoundException("Reserved car not found"));
    }

    @Override
    @Query
    public Reservation findDateOfBeginning(String dateOfBeginning) throws RecordNotFoundException{
        return reservationRepository.findByDateOfBeginning(dateOfBeginning).orElseThrow(()
                -> new RecordNotFoundException("Date not found"));

    }

    @Override
    @Query
    public Reservation findDateOfEnding(String endDate) throws RecordNotFoundException{
//        return reservationConvertor.responseReservation(reservationRepository.findEndDateOfReservation(endDateOfReservation)
//                .orElseThrow(() -> new RecordNotFoundException(String.format("Date %s not found", endDateOfReservation))));
        return reservationRepository.findEndDateOfReservation(endDate).orElseThrow(()
                -> new RecordNotFoundException("Date not found"));
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateReservationCar(ReservationUpdate reservationUpdate) {
        Optional<Reservation> car = reservationRepository.findById(reservationUpdate.getId());
        if (car.isEmpty()) {
            throw new UsernameNotFoundException("Car not found or invalid credentials");
        } else {
            car.get().setId(car.get().getId());
            car.get().setReservedCar(reservationUpdate.getNewReservedCar());
        }
    }
    @Override
    @Transactional
    public void updateBeginningDate(ReservationUpdate reservationUpdate) {
        Optional<Reservation> start = reservationRepository.findById(reservationUpdate.getId());
        if (start.isEmpty()) {
            throw new UsernameNotFoundException("Date not found or invalid credentials");
        } else {
            start.get().setId(start.get().getId());
            start.get().setDateOfBeginning(reservationUpdate.getNewDateOfBeginning());
        }
    }

    @Override
    @Transactional
    public void updateReservationEndDate(ReservationUpdate reservationUpdate) {
        Optional<Reservation> end = reservationRepository.findById(reservationUpdate.getId());
        if (end.isEmpty()) {
            throw new UsernameNotFoundException("Date not found or invalid credentials");
        } else {
            end.get().setId(end.get().getId());
            end.get().setEndDateOfReservation(reservationUpdate.getNewEndDateOfReservation());
        }
    }
}
