package alessiovulpinari.u2_w3_d5_Java.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID uuid) {
        super("Oggetto con id: " + uuid + " non trovato!");
    }

    public NotFoundException(String dato) {super(dato.toUpperCase() + " non trovato!");}
}
