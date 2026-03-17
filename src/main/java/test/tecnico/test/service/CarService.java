package test.tecnico.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.tecnico.test.dto.CarRequest;
import test.tecnico.test.model.Car;
import test.tecnico.test.model.User;
import test.tecnico.test.repo.CarRepo;
import test.tecnico.test.repo.UserRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepo carRepository;
    private final UserRepo userRepository;

    public Car createCar(CarRequest request, String username) {

        User user = userRepository.findByUsername(username).orElseThrow();

        Car car = Car.builder()
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .anio(request.getAnio())
                .placa(request.getPlaca())
                .color(request.getColor())
                .user(user)
                .build();

        return carRepository.save(car);
    }

    public List<Car> getCars(String username) {

        User user = userRepository.findByUsername(username).orElseThrow();

        return carRepository.findByUser(user);
    }

}