package com.example.car.industry.controller;

import com.example.car.industry.convertor.CarsConvertor;
import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.dto.CarsUpdate;
import com.example.car.industry.entity.Cars;
import com.example.car.industry.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cars")
@RequiredArgsConstructor
public class CarsController {
    @Autowired
    CarService carService;

    @PostMapping
    ResponseEntity<CarResponse> addCar(@RequestBody @Valid CarRequest carRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.addCar(carRequest));

        //        Cars savedCar = carService.addCar(
//                convertor.carsRequest(carRequest));
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(convertor.responseCar(savedCar));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CarResponse> getCar(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(carService.getCar(id));
    }

    @PutMapping(path = "/update")
    ResponseEntity<String> updateCar (@RequestBody @Valid CarsUpdate carsUpdate) {
        carService.updateCar(carsUpdate);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(String.format("Car is successfully updated", carsUpdate.getId()));
    }
    @DeleteMapping(path = "/delete")
    ResponseEntity<String> deleteCar (@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity
                .ok()
                .body(String.format("Car successfully deleted", id));
    }


}
