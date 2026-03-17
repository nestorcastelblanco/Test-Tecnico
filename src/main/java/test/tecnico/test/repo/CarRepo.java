package test.tecnico.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.tecnico.test.model.Car;
import test.tecnico.test.model.User;

import java.util.List;

public interface CarRepo extends JpaRepository<Car, Long> {

    List<Car> findByUser(User user);

}
