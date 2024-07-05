package alessiovulpinari.u2_w3_d5_Java.controllers;

import alessiovulpinari.u2_w3_d5_Java.entities.Event;
import alessiovulpinari.u2_w3_d5_Java.entities.GenericUser;
import alessiovulpinari.u2_w3_d5_Java.exceptions.BadRequestException;
import alessiovulpinari.u2_w3_d5_Java.payloads.EventPayload;
import alessiovulpinari.u2_w3_d5_Java.payloads.EventPostResponseDTO;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserPayload;
import alessiovulpinari.u2_w3_d5_Java.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
    public EventPostResponseDTO postNewEvent(@RequestBody @Validated EventPayload body, BindingResult bindingResult, @AuthenticationPrincipal GenericUser currentUser) {
        if (bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors());
        return new EventPostResponseDTO(this.eventService.saveEvent(body, currentUser).getEventId());
    }

    @PutMapping("/me/{eventId}")
    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
    public Event findByIdAndUpdate(@PathVariable UUID eventId, @RequestBody EventPayload body, @AuthenticationPrincipal GenericUser currentUser) {
        return this.eventService.findByIdAndUpdate(eventId, body, currentUser);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasAuthority('EVENT_MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID eventId, @AuthenticationPrincipal GenericUser currentUser) {
        this.eventService.findByIdAndDelete(eventId, currentUser);
    }

}
