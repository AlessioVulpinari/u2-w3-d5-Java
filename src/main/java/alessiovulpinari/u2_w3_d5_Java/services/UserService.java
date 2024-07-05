package alessiovulpinari.u2_w3_d5_Java.services;

import alessiovulpinari.u2_w3_d5_Java.entities.User;
import alessiovulpinari.u2_w3_d5_Java.exceptions.BadRequestException;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserPayload;
import alessiovulpinari.u2_w3_d5_Java.repositories.GenericUserRepository;
import alessiovulpinari.u2_w3_d5_Java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GenericUserRepository genericUserRepository;

    public User saveUser(UserPayload body) {
        this.genericUserRepository.findByEmail(body.email()).ifPresent(user ->
        {throw new BadRequestException("Esiste già un utente con questa email: " + body.email());});

        this.genericUserRepository.findByUsername(body.username()).ifPresent(user -> {
            throw new BadRequestException("Esiste già un utente con questo username: " + body.username());});

        User newUser = new User(body.username(), body.name(), body.surname(), passwordEncoder.encode(body.password()), body.email());
        return userRepository.save(newUser);
    }
}
