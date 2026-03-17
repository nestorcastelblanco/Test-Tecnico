package test.tecnico.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import test.tecnico.test.dto.CarRequest;
import test.tecnico.test.model.Car;
import test.tecnico.test.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public Car createCar(@RequestBody CarRequest request, Authentication auth) {

        return carService.createCar(request, auth.getName());
    }

    @GetMapping
    public List<Car> getCars(Authentication auth) {

        return carService.getCars(auth.getName());
    }

}