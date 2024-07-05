package alessiovulpinari.u2_w3_d5_Java.repositories;

import alessiovulpinari.u2_w3_d5_Java.entities.GenericUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GenericUserRepository extends JpaRepository<GenericUser, UUID> {
    Optional<GenericUser> findByEmail(String email);
    Optional<GenericUser> findByUsername(String username);
}
