package com.example.car.industry.service;

import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.dto.CarsUpdate;
import com.example.car.industry.entity.Cars;


public interface CarService {
    CarResponse addCar(CarRequest carRequest);
    CarResponse getCar(Long id);
    void updateCar(CarsUpdate car);
    void deleteCar(Long id);

}
