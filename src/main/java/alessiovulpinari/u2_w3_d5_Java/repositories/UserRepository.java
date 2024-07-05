package alessiovulpinari.u2_w3_d5_Java.repositories;

import alessiovulpinari.u2_w3_d5_Java.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
