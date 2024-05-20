package mate.academy.carservice.repo;

import java.util.Optional;
import mate.academy.carservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
