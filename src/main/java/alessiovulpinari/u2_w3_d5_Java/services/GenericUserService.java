package alessiovulpinari.u2_w3_d5_Java.services;

import alessiovulpinari.u2_w3_d5_Java.entities.GenericUser;
import alessiovulpinari.u2_w3_d5_Java.exceptions.NotFoundException;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserPayload;
import alessiovulpinari.u2_w3_d5_Java.repositories.GenericUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenericUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GenericUserRepository userRepository;

    public Page<GenericUser> getUsers(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

//    public GenericUser saveUser(UserPayload body) {
//        this.userRepository.findByEmail(body.email()).ifPresent(user ->
//        {throw new BadRequestException("Esiste già un utente con questa email: " + body.email());});
//
//        this.userRepository.findByUsername(body.username()).ifPresent(user -> {
//            throw new BadRequestException("Esiste già un utente con questo username: " + body.username());});
//
//        GenericUser newUser = new GenericUser(body.username(), body.name(), body.surname(), passwordEncoder.encode(body.password()), body.email());
//        return userRepository.save(newUser);
//    }

    public GenericUser findByIdAndUpdate(UUID userId, UserPayload body)
    {
        GenericUser found = findById(userId);
        found.setEmail(body.email());
        found.setUsername(body.username());
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setPassword(passwordEncoder.encode(body.password()));
        return userRepository.save(found);
    }

    public void findByIdAndDelete(UUID userId) {
        GenericUser found = findById(userId);
        this.userRepository.delete(found);
    }

    public GenericUser findById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public GenericUser findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

}
