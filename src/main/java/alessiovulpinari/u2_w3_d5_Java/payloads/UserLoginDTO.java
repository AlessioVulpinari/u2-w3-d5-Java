package alessiovulpinari.u2_w3_d5_Java.payloads;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(@NotBlank String email, @NotBlank String password) { }
