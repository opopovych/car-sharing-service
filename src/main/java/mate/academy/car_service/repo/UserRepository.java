package mate.academy.car_service.repo;

import java.util.Optional;
import mate.academy.car_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}