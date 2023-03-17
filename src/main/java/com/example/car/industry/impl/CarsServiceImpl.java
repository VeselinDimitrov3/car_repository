package com.example.car.industry.impl;

import com.example.car.industry.convertor.CarsConvertor;
import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.dto.CarsUpdate;
import com.example.car.industry.entity.Cars;
import com.example.car.industry.exception.CarNotFoundException;
import com.example.car.industry.exception.RecordNotFoundException;
import com.example.car.industry.repository.CarRepository;
import com.example.car.industry.service.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CarsServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarsConvertor carConvertor;

    @Autowired
    public CarsServiceImpl(CarRepository carRepository, CarsConvertor carConvertor) {
        this.carRepository = carRepository;
        this.carConvertor = carConvertor;
    }

    @Override
    @Transactional
    public CarResponse addCar(CarRequest carRequest) {
        Cars newCar = carRepository.save(carConvertor.carsRequest(carRequest));
        return carConvertor.responseCar(newCar);
    }

    @Override
    public Cars findById(Long id) {
        return carRepository.findById(id).orElseThrow
                (() -> new CarNotFoundException
                        (String.format("Car with id %d not found", id.intValue())));
    }

    @Override
    @Transactional
    public CarResponse updatePrice(CarsUpdate carUpdate) {
        Cars car = carRepository.findById(Long.parseUnsignedLong(carUpdate.getId())).orElseThrow(() ->
                new CarNotFoundException(String.format("Car with id %s not found", carUpdate.getId())));
        car.setPricePerDay((Double.parseDouble(carUpdate.getNewPrice())));
        carRepository.save(car);
        return carConvertor.responseCar(car);
    }

    @Override
    public void deleteCar(Long id) {
        if (carRepository.findById(id).isEmpty()) {
            throw new CarNotFoundException(String.format("Car with id %d not found", id.intValue()));
        } else {
            carRepository.deleteById(id);
        }
    }
}
