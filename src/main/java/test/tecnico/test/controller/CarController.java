package test.tecnico.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import test.tecnico.test.dto.AuthResponse;
import test.tecnico.test.dto.CarRequest;
import test.tecnico.test.dto.CarResponse;
import test.tecnico.test.model.Car;
import test.tecnico.test.model.User;
import test.tecnico.test.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<AuthResponse> createCar(@RequestBody CarRequest request,
                                                  @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(carService.createCar(request, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,
                                         @RequestBody CarRequest request,
                                         @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(carService.updateCar(id, request, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id,
                                            @AuthenticationPrincipal User user) {
        carService.deleteCar(id, user);
        return ResponseEntity.ok("Auto eliminado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getCars(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Integer anio
    ) {
        return ResponseEntity.ok(carService.searchCars(user, search, marca, anio));
    }
}