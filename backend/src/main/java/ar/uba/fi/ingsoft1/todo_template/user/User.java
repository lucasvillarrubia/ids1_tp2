package ar.uba.fi.ingsoft1.todo_template.user;
import ar.uba.fi.ingsoft1.todo_template.ratings.Rating;
import jakarta.persistence.*;
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
    private String password;

    @Column(unique= true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column (nullable = false)
    private String lastname;

    @Column (nullable = false)
    private LocalDate birthday;

    @Column (nullable = false)
    private String gender;

    @Column(nullable = false)
    private String role;

    @ManyToMany
    private List<User> solicitudesSeguimiento = new ArrayList<>();
    @ManyToMany
    private List<User> seguidores = new ArrayList<>();
    @ManyToMany
    private List<User> seguidos = new ArrayList<>();




    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    public User() {}

    public User(String username, String password, String email, String name, String lastname, LocalDate birthday, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.role = "USER";
        this.ratings = new ArrayList<>();
        this.solicitudesSeguimiento = new ArrayList<>();
        this.seguidores = new ArrayList<>();
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

    @Override
    public String getPassword() {
        return this.password;
    }

    public Boolean isAdmin() {
        return this.role.equals("ADMIN");
    }

    public void setAdmin(){
        this.role = "ADMIN";
    }

    public String getRole() {
        return this.role;
    }

    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public String getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }

    public List<Rating> getRatings() { return ratings; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<User>  getSolicitudesSeguimiento() { return solicitudesSeguimiento;}
    public List<User>  getSeguidores() {return seguidores;}
    public List<User> getSeguidos() {return seguidos;}


    public void aceptarSolicitudesSeguimiento(User solicitante) {
        if(solicitudesSeguimiento.remove(solicitante)) {
            solicitudesSeguimiento.add(solicitante);
            solicitante.getSeguidos().add(this);
        }

    }

    public void rechazarSolicitudesSeguimiento(User solicitante) {
        solicitudesSeguimiento.remove(solicitante);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
}
