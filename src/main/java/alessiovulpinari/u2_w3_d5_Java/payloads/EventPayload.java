package alessiovulpinari.u2_w3_d5_Java.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record EventPayload(@NotBlank String title, @NotBlank String description, @NotNull LocalDate date,
                           @NotBlank String location, @NotNull int maxParticipants, UUID managerId) {
}
