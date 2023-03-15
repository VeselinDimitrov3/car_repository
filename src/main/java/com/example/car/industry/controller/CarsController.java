package com.example.car.industry.controller;

import com.example.car.industry.convertor.CarsConvertor;
import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.CarResponse;
import com.example.car.industry.dto.CarsUpdate;
import com.example.car.industry.entity.Cars;
import com.example.car.industry.exception.RecordNotFoundException;
import com.example.car.industry.impl.CarsServiceImpl;
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
    private CarsServiceImpl carService;

    @Autowired
    private CarsConvertor carConvertor;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(carConvertor.responseCar(carService.findById(id)));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<CarResponse> addCar(@RequestBody @Valid CarRequest carRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.addCar(carRequest));
    }

    @PutMapping(path = "/change")
    public ResponseEntity<CarResponse> updatePrice(@RequestBody @Valid CarsUpdate carUpdate) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(carService.updatePrice(carUpdate));
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Car has been deleted successfully");
    }


}
