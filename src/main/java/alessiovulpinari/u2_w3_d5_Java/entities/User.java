package alessiovulpinari.u2_w3_d5_Java.entities;

import alessiovulpinari.u2_w3_d5_Java.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"password", "userRole", "authorities", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
@Entity
@Table(name = "utenti")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "id_utente", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String username;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "cognome", nullable = false)
    private String surname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "ruolo", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String username, String name, String surname, String password, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.userRole = UserRole.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.userRole.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
