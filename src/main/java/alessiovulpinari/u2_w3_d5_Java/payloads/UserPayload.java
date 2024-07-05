package alessiovulpinari.u2_w3_d5_Java.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPayload(@NotBlank(message = "Lo username è un dato obbligatorio!")
                          @Size(min = 5, max = 15, message = "Inserire uno username compreso tra i 5 e i 15 caratteri") String username,
                          @NotBlank(message = "Il nome è un dato obbligatorio!")
                          @Size(min = 3, max = 20, message = "Inserire un nome compreso tra i 3 e i 20 caratteri")String name,
                          @NotBlank(message = "Il cognome è un dato obbligatorio!")
                          @Size(min = 3, max = 20, message = "Inserire un cognome compreso tra i 3 e i 20 caratteri")String surname,
                          @NotBlank(message = "La password è un dato obbligatorio!") String password,
                          @Size(min = 8, message = "Inserire una password di almeno 8 caratteri")
                          @NotBlank(message = "La mail è un dato obbligatorio!")
                          @Email(message = "Mail non valida!") String email) {
}
