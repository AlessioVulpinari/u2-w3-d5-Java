package alessiovulpinari.u2_w3_d5_Java.services;

import alessiovulpinari.u2_w3_d5_Java.entities.Event;
import alessiovulpinari.u2_w3_d5_Java.payloads.EventPayload;
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
}
