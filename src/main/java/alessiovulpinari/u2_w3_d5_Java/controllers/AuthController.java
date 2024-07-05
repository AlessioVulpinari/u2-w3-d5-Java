package alessiovulpinari.u2_w3_d5_Java.controllers;

import alessiovulpinari.u2_w3_d5_Java.payloads.UserLoginDTO;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserLoginResponseDTO;
import alessiovulpinari.u2_w3_d5_Java.services.AuthService;
import alessiovulpinari.u2_w3_d5_Java.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(authService.authenticationAndTokenGeneration(body));
    }
}
