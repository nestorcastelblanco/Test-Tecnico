package test.tecnico.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import test.tecnico.test.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
