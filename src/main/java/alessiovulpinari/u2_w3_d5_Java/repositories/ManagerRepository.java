package alessiovulpinari.u2_w3_d5_Java.repositories;

import alessiovulpinari.u2_w3_d5_Java.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {
}
