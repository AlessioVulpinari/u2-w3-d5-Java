package alessiovulpinari.u2_w3_d5_Java.controllers;

import alessiovulpinari.u2_w3_d5_Java.entities.GenericUser;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserPayload;
import alessiovulpinari.u2_w3_d5_Java.services.GenericUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private GenericUserService userService;

    @GetMapping("/me")
    public GenericUser getMyProfile(@AuthenticationPrincipal GenericUser currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public GenericUser updateMyProfile(@AuthenticationPrincipal GenericUser currentUser, @RequestBody UserPayload body) {
        return this.userService.findByIdAndUpdate(currentUser.getUserId(), body);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<GenericUser> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size)
    {
        return this.userService.getUsers(page, size);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericUser findById(@PathVariable UUID userId)
    {
        return this.userService.findById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GenericUser findByIdAndUpdate(@PathVariable UUID userId, @RequestBody UserPayload body) {
        return this.userService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID userId) {
        this.userService.findByIdAndDelete(userId);
    }
}
