package alessiovulpinari.u2_w3_d5_Java.repositories;

import alessiovulpinari.u2_w3_d5_Java.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
