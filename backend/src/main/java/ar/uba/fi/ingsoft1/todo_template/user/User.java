package ar.uba.fi.ingsoft1.todo_template.user;
import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
public class User implements UserDetails, UserCredentials {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(unique= true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String zone;

    @Column(nullable = false)
    private String password;

    private String photo;

    @Column (columnDefinition = "ENUM('Male', 'Female', 'Other')")
    private String gender;

    @Column (nullable = false)
    private Short age;

    private final String role = "USER";

    public User() {}

    public User(String username, String name, String lastname, String email, String zone, String password, String gender, String photo, Short age) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.zone = zone;
        this.password = password;
        this.gender = gender;
        this.photo = photo;
        this.age = age;
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getName() { return this.name;}

    public String getLastname() { return this.lastname;}

    public String getEmail() { return this.email;}

    public String getZone() { return this.zone;}

    @Override
    public String getPassword() { return this.password;}

    public String getGender() { return this.gender;}

    public String getPhoto() { return this.photo;}

    public String getRole() { return this.role;}

    public Short getAge() { return this.age;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
}
