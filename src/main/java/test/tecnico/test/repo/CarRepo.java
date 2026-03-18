package test.tecnico.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.tecnico.test.model.Car;
import test.tecnico.test.model.User;

import java.util.List;
import java.util.Optional;

public interface CarRepo extends JpaRepository<Car, Long> {

    List<Car> findByUser(User user);

    Optional<Car> findByIdAndUser(Long id, User user);

    boolean existsByPlaca(String placa);

    Optional<Car> findByPlaca(String placa);

    @Query("""
        SELECT c FROM Car c
        WHERE c.user = :user
        AND (
            :search IS NULL OR :search = '' OR
            LOWER(c.placa) LIKE LOWER(CONCAT('%', :search, '%')) OR
            LOWER(c.modelo) LIKE LOWER(CONCAT('%', :search, '%'))
        )
        AND (
            :marca IS NULL OR :marca = '' OR
            LOWER(c.marca) = LOWER(:marca)
        )
        AND (
            :anio IS NULL OR c.anio = :anio
        )
    """)
    List<Car> searchCars(
            @Param("user") User user,
            @Param("search") String search,
            @Param("marca") String marca,
            @Param("anio") Integer anio
    );
}