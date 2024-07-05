package alessiovulpinari.u2_w3_d5_Java.services;

import alessiovulpinari.u2_w3_d5_Java.entities.Event;
import alessiovulpinari.u2_w3_d5_Java.entities.GenericUser;
import alessiovulpinari.u2_w3_d5_Java.entities.Manager;
import alessiovulpinari.u2_w3_d5_Java.exceptions.ForbiddenException;
import alessiovulpinari.u2_w3_d5_Java.exceptions.NotFoundException;
import alessiovulpinari.u2_w3_d5_Java.payloads.EventPayload;
import alessiovulpinari.u2_w3_d5_Java.payloads.UserPayload;
import alessiovulpinari.u2_w3_d5_Java.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(EventPayload body) {

        Event event = new Event(body.title(), body.description(), body.date(),
                body.location(), body.maxParticipants(), managerService.findById(body.managerId()));

        return eventRepository.save(event) ;
    }

    public Event findById(UUID eventId) {
        return this.eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
    }

    public Event findByIdAndUpdate(UUID eventId, EventPayload body, GenericUser currentUser)
    {
        if (!currentUser.getUserId().equals(body.managerId())) throw new ForbiddenException("Non è un tuo evento non puoi modificarlo!");
        Event eventFound = this.findById(eventId);
        eventFound.setTitle(body.title());
        eventFound.setDescription(body.description());
        eventFound.setDate(body.date());
        eventFound.setLocation(body.location());
        eventFound.setMaxParticipants(body.maxParticipants());

        return eventRepository.save(eventFound);
    }

    public void findByIdAndDelete(UUID eventId, GenericUser currentUser) {
        if (!currentUser.getUserId().equals(findById(eventId).getManager().getUserId())) throw new ForbiddenException("Non è un tuo evento non puoi cancellarlo!");
        Event found = findById(eventId);
        this.eventRepository.delete(found);
    }

}
