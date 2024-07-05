package alessiovulpinari.u2_w3_d5_Java.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "eventi")
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id_evento", nullable = false)
    private UUID eventId;

    @Column(name = "titolo", nullable = false)
    private String title;

    @Column(name = "descrizione", nullable = false)
    private String description;

    @Column(name = "data_evento", nullable = false)
    private LocalDate date;

    @Column(name = "luogo", nullable = false)
    private String location;

    @Column(name = "numero_massimo_partecipanti", nullable = false)
    private int maxParticipants;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Manager manager;

    public Event(String title, String description, LocalDate date, String location, int maxParticipants, Manager manager) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.manager = manager;
    }
}
