package alessiovulpinari.u2_w3_d5_Java.services;

import alessiovulpinari.u2_w3_d5_Java.entities.User;
import alessiovulpinari.u2_w3_d5_Java.exceptions.UnathorizedException;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserLoginDTO;
import alessiovulpinari.u2_w3_d5_Java.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticationAndTokenGeneration(UserLoginDTO body) {

        User found = this.userService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), found.getPassword())) return jwtTools.createToken(found);
        else throw new UnathorizedException("Credenziali non corette");
    }
}
