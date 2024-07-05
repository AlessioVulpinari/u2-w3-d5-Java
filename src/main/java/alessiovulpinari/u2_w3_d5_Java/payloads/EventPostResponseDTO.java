package alessiovulpinari.u2_w3_d5_Java.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EventPostResponseDTO(@NotNull UUID eventID) {
}
