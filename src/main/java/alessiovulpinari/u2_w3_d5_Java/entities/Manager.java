package alessiovulpinari.u2_w3_d5_Java.entities;

import alessiovulpinari.u2_w3_d5_Java.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Manager extends GenericUser {

    @OneToMany(mappedBy = "manager")
    private List<Event> events;

    public Manager(String username, String name, String surname, String password, String email) {
        super(username, name, surname, password, email);
        super.setUserRole(UserRole.EVENT_MANAGER);
        this.events = new ArrayList<>();
    }

}
