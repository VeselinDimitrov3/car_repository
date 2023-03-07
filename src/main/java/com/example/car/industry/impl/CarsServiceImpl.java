package com.example.car.industry.impl;

import com.example.car.industry.convertor.CarsConvertor;
import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.dto.CarsUpdate;
import com.example.car.industry.entity.Cars;
import com.example.car.industry.exception.RecordNotFoundException;
import com.example.car.industry.repository.CarRepository;
import com.example.car.industry.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CarsServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarsConvertor carConvertor;


    @Override
    public CarResponse addCar(CarRequest carRequest) {
        Cars car = Cars.builder()
                .brand(carRequest.getBrand())
                .model(carRequest.getModel())
                .pricePerDay(carRequest.getPricePerDay())
                .seats(carRequest.getSeats())
                .build();

        return carConvertor.responseCar(carRepository.save(car));
    }

    @Override
    public CarResponse getCar(Long id) {
        return carConvertor.responseCar(carRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Id %s not found", id))));
    }

    @Override
    public void updateCar(CarsUpdate carsUpdate) {
        Optional<Cars> car = carRepository.findById(carsUpdate.getId());
        if (car.isEmpty()) {
            throw new RecordNotFoundException("User not found or invalid credentials");
        } else {
            car.get().setId(carsUpdate.getId());
            car.get().setBrand(carsUpdate.getNewBrand());
            car.get().setModel(carsUpdate.getNewModel());
            car.get().setPricePerDay(carsUpdate.getNewPrice());
            car.get().setSeats(carsUpdate.getNewSeats());
        }
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
