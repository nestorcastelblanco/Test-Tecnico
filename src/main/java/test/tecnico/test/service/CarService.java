package test.tecnico.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.tecnico.test.dto.AuthResponse;
import test.tecnico.test.dto.CarRequest;
import test.tecnico.test.dto.CarResponse;
import test.tecnico.test.model.Car;
import test.tecnico.test.model.User;
import test.tecnico.test.repo.CarRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepo carRepo;

    public AuthResponse createCar(CarRequest request, User user) {

        if ( request.getMarca().isBlank()) {
            throw new RuntimeException("Es necesario ingresar la marca");
        }

        if ( request.getPlaca().isBlank()) {
            throw new RuntimeException("Es necesario ingresar la placa");
        }

        if ( request.getModelo().isBlank()) {
            throw new RuntimeException("Es necesario ingresar el modelo");
        }

        if ( request.getColor().isBlank()) {
            throw new RuntimeException("Es necesario ingresar el color");
        }

        if (request.getAnio() < 1900 || request.getAnio() > 2100) {
            throw new RuntimeException("El año del vehículo no es válido");
        }

        if ( !validarPlaca(request.getPlaca()) ) {
            throw new RuntimeException("La placa que se ingreso no es valida");
        }

        if (carRepo.existsByPlaca(request.getPlaca())) {
            throw new RuntimeException("El vehiculo ya esta registrado");
        }

        Car car = Car.builder()
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .anio(request.getAnio())
                .placa(request.getPlaca())
                .color(request.getColor())
                .foto(request.getFoto())
                .user(user)
                .build();

        carRepo.save(car);

        return new AuthResponse(null, "Vehiculo registrado correctamente");
    }

    public List<Car> getCarsByUser(User user) {
        return carRepo.findByUser(user);
    }

    public Car updateCar(Long id, CarRequest request, User user) {

        if ( request.getMarca().isBlank()) {
            throw new RuntimeException("Es necesario ingresar la marca");
        }

        if ( request.getPlaca().isBlank()) {
            throw new RuntimeException("Es necesario ingresar la placa");
        }

        if ( request.getModelo().isBlank()) {
            throw new RuntimeException("Es necesario ingresar el modelo");
        }

        if ( request.getColor().isBlank()) {
            throw new RuntimeException("Es necesario ingresar el color");
        }

        if (request.getAnio() < 1900 || request.getAnio() > 2100) {
            throw new RuntimeException("El año del vehículo no es válido");
        }

        if ( !validarPlaca(request.getPlaca()) ) {
            throw new RuntimeException("La placa que se ingreso no es valida");
        }

        Car car = carRepo.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("No se encontró el auto para editar"));

        car.setMarca(request.getMarca());
        car.setModelo(request.getModelo());
        car.setAnio(request.getAnio());
        car.setPlaca(request.getPlaca());
        car.setColor(request.getColor());
        car.setFoto(request.getFoto());

        return carRepo.save(car);
    }

    public void deleteCar(Long id, User user) {
        Car car = carRepo.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("No se encontró el auto para eliminar"));
        carRepo.delete(car);
    }

    public boolean validarPlaca (String placa){
        String regex = "^[A-Z]{3}[0-9]{3}$";
        return placa.matches(regex);
    }

    public List<CarResponse>    searchCars(User user, String search, String marca, Integer anio) {
        return carRepo.searchCars(user, search, marca, anio)
                .stream()
                .map(car -> new CarResponse(
                        car.getId(),
                        car.getMarca(),
                        car.getModelo(),
                        car.getAnio(),
                        car.getPlaca(),
                        car.getColor(),
                        car.getFoto()
                ))
                .toList();
    }
}