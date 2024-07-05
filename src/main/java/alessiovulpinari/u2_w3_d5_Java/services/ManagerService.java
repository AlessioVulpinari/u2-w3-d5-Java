package alessiovulpinari.u2_w3_d5_Java.services;

import alessiovulpinari.u2_w3_d5_Java.entities.Manager;
import alessiovulpinari.u2_w3_d5_Java.entities.User;
import alessiovulpinari.u2_w3_d5_Java.exceptions.BadRequestException;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserPayload;
import alessiovulpinari.u2_w3_d5_Java.repositories.GenericUserRepository;
import alessiovulpinari.u2_w3_d5_Java.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GenericUserRepository genericUserRepository;

    public Manager saveUser(UserPayload body) {
        this.genericUserRepository.findByEmail(body.email()).ifPresent(user ->
        {throw new BadRequestException("Esiste già un utente con questa email: " + body.email());});

        this.genericUserRepository.findByUsername(body.username()).ifPresent(user -> {
            throw new BadRequestException("Esiste già un utente con questo username: " + body.username());});

        Manager newUser = new Manager(body.username(), body.name(), body.surname(), passwordEncoder.encode(body.password()), body.email());
        return managerRepository.save(newUser);
    }
}
