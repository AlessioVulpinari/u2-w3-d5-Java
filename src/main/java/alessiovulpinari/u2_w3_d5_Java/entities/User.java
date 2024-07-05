package alessiovulpinari.u2_w3_d5_Java.entities;

import alessiovulpinari.u2_w3_d5_Java.enums.UserRole;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class User extends GenericUser
{
    @ManyToMany
    @JoinTable(name = "utente_evento", joinColumns = @JoinColumn(name = "id_utente"),
            inverseJoinColumns = @JoinColumn(name = "id_evento"))
    private List<Event> events;

    public User(String username, String name, String surname, String password, String email) {
        super(username, name, surname, password, email);
        super.setUserRole(UserRole.USER);
        this.events = new ArrayList<>();
    }

    public void participateEvent(Event event) {
        this.events.add(event);
    }

    public void revokeEvent(Event event) {
        this.events.remove(event);
    }

}
