package com.example.car.industry.convertor;

import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.entity.Cars;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CarsConvertor {

//    public Cars carsRequest (CarRequest carRequest) {
//        return Cars.builder()
//                .brand(carRequest.getBrand())
//                .model(carRequest.getModel())
//                .pricePerDay(carRequest.getPricePerDay())
//                .seats(carRequest.getSeats())
//                .build();
//    }

    public CarResponse responseCar (Cars cars) {
        return CarResponse.builder()
                .id(cars.getId())
                .brand(cars.getBrand())
                .model(cars.getModel())
                .pricePerDay(cars.getPricePerDay())
                .seats(cars.getSeats())
                .build();
    }

}
