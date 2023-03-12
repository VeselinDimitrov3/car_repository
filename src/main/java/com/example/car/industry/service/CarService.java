package com.example.car.industry.service;

import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.dto.CarsUpdate;
import com.example.car.industry.entity.Cars;
import com.example.car.industry.exception.RecordNotFoundException;


public interface CarService {
    CarResponse addCar(CarRequest carRequest);
    CarResponse getCar(Long id);
    void updateCar(CarsUpdate car) throws RecordNotFoundException;
    void deleteCar(Long id);

}
