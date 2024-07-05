package alessiovulpinari.u2_w3_d5_Java.controllers;

import alessiovulpinari.u2_w3_d5_Java.exceptions.BadRequestException;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserLoginDTO;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserLoginResponseDTO;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserPayload;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserRegistrationResponseDTO;
import alessiovulpinari.u2_w3_d5_Java.services.AuthService;
import alessiovulpinari.u2_w3_d5_Java.services.ManagerService;
import alessiovulpinari.u2_w3_d5_Java.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    @PostMapping("/user/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(authService.authenticationAndTokenGeneration(body));
    }

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistrationResponseDTO registerUser(@RequestBody @Validated UserPayload body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }

        return new UserRegistrationResponseDTO(this.userService.saveUser(body).getUserId());
    }

    @PostMapping("/manager/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistrationResponseDTO registerManager(@RequestBody @Validated UserPayload body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }

        return new UserRegistrationResponseDTO(this.managerService.saveUser(body).getUserId());
    }

}
