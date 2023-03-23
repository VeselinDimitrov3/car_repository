package com.example.car.industry.runner;

import com.example.car.industry.dto.CarRequest;
import com.example.car.industry.dto.RegisterRequest;
import com.example.car.industry.dto.ReservationRequest;
import com.example.car.industry.service.CarService;
import com.example.car.industry.service.ReservationService;
import com.example.car.industry.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final CarService carService;
    private final UserService userService;
    private final ReservationService reservationService;

    public Runner(CarService carService, UserService userService, ReservationService reservationService) {
        this.carService = carService;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @Override
    public void run(String... args) throws Exception {

        CarRequest car = new CarRequest();
        car.setBrand("Audi");
        car.setModel("A5");
        car.setPricePerDay("200");
        car.setSeats(5);

        carService.addCar(car);

        RegisterRequest user = new RegisterRequest();
        user.setFirstName("Borislav");
        user.setLastName("Georgiev");
        user.setEmail("fdgdfg325@abv.bg");
        user.setPhoneNumber("3563626634");
        user.setPassword("Sduighosdf243");

        userService.register(user);


        ReservationRequest reservation = new ReservationRequest();
        reservation.setCarId("1");
        reservation.setStartDate("22/02/2022");
        reservation.setEndDate("22/02/2023");
        reservation.setUserEmail("fdgdfg325@abv.bg");

        reservationService.save(reservation);

    }
}
