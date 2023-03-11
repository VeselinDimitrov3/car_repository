package com.example.car.industry.impl;

import com.example.car.industry.convertor.CarsConvertor;
import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.dto.CarsUpdate;
import com.example.car.industry.entity.Cars;
import com.example.car.industry.repository.CarRepository;
import com.example.car.industry.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CarsServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarsConvertor carConvertor;


    @Override
    public CarResponse addCar(CarRequest carRequest) {
        Cars reservationToBeSaved = carConvertor.carsRequest(carRequest);
        return carConvertor.responseCar(carRepository.save(reservationToBeSaved));
    }

    @Override
    public CarResponse getCar(Long id) {
        return carConvertor.responseCar(carRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Id %s not found", id))));
    }

    @Override
    public void updateCar(CarsUpdate carsUpdate) {
        Optional<Cars> car = carRepository.findById(carsUpdate.getId());
        if (car.isEmpty()) {
            throw new UsernameNotFoundException("User not found or invalid credentials");
        } else {
            car.get().setId(carsUpdate.getId());
            car.get().setModel(carsUpdate.getNewModel());
            car.get().setBrand(carsUpdate.getNewBrand());
            car.get().setSeats(carsUpdate.getSeats());
            car.get().setPricePerDay(carsUpdate.getNewPrice());
        }
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
