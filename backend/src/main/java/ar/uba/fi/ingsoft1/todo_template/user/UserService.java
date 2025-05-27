package ar.uba.fi.ingsoft1.todo_template.user;

import ar.uba.fi.ingsoft1.todo_template.config.security.JwtService;
import ar.uba.fi.ingsoft1.todo_template.config.security.JwtUserDetails;
import ar.uba.fi.ingsoft1.todo_template.user.refresh_token.RefreshToken;
import ar.uba.fi.ingsoft1.todo_template.user.refresh_token.RefreshTokenService;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    UserService(
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RefreshTokenService refreshTokenService
    ) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;

        UserCreateDTO userDTO = new UserCreateDTO("CarlosCrack", "contraseña1234", "juan@gmail.com", "Carlos", "Castro", LocalDate.of(2003, 4, 23), "Male");
        UserCreateDTO adminDTO = new UserCreateDTO("Male", "malena999", "malena@outlook.com", "Malena", "Rodriguez", LocalDate.of(2001, 2, 27), "Female");
        var user = userDTO.asUser(passwordEncoder::encode);
        var admin = adminDTO.asUser(passwordEncoder::encode);
        admin.setAdmin();
        userRepository.save(user);
        userRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> {
                    var msg = String.format("Username '%s' not found", username);
                    return new UsernameNotFoundException(msg);
                });
    }

    Optional<UserProfileDTO> getUserProfile(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() ? Optional.ofNullable(new UserProfileDTO(user.get())) : Optional.empty();
    }

    Optional<TokenDTO> createUser(UserCreateDTO data) {
        if (userRepository.findByUsername(data.username()).isPresent()) {
            return loginUser(data);
        } else {
            var user = data.asUser(passwordEncoder::encode);
            userRepository.save(user);
            return Optional.of(generateTokens(user));
        }
    }


    User updateUserProfile(UserProfileDTO userProfileDTO) throws UsernameNotFoundException {
        return userRepository.findByUsername(userProfileDTO.username())
            .map(user -> {
                if (userProfileDTO.name() != null) user.setName(userProfileDTO.name());
                if (userProfileDTO.lastname() != null) user.setLastname(userProfileDTO.lastname());
                if (userProfileDTO.birthday() != null) user.setBirthday(userProfileDTO.birthday());
                if (userProfileDTO.gender() != null) user.setGender(userProfileDTO.gender());

                userRepository.save(user);
                return user;
            })
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<User> updateAdmin(String username, UserCreateDTO data) {
        return userRepository.findByUsername(username)
                .filter(user -> Objects.equals(user.getRole(), "ADMIN"))
                .map(user -> {
                    if (data.username() != null) user.setUsername(data.username());
                    if (data.password() != null) user.setPassword(passwordEncoder.encode(data.password()));
                    if (data.email() != null) user.setEmail(data.email());
                    if (data.name() != null) user.setName(data.name());
                    if (data.lastname() != null) user.setLastname(data.lastname());
                    if (data.birthday() != null) user.setBirthday(data.birthday());
                    if (data.gender() != null) user.setGender(data.gender());

                    return userRepository.save(user);
                });
    }



    Optional<TokenDTO> createAdmin(UserCreateDTO data) {
        if (userRepository.findByUsername(data.username()).isPresent()) {
            return loginUser(data);
        } else {
            var user = data.asUser(passwordEncoder::encode);
            user.setAdmin();
            userRepository.save(user);
            return Optional.of(generateTokens(user));
        }
    }

    Optional<TokenDTO> loginUser(UserCredentials data) {
        Optional<User> maybeUser = userRepository.findByUsername(data.username());
        return maybeUser
                .filter(user -> passwordEncoder.matches(data.password(), user.getPassword()))
                .map(this::generateTokens);
    }

    @Transactional
    public boolean deleteAdmin(String username) {
        return userRepository.findByUsername(username)
                .filter(user -> Objects.equals(user.getRole(), "ADMIN"))
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }

    Optional<TokenDTO> refresh(RefreshDTO data) {
        return refreshTokenService.findByValue(data.refreshToken())
                .map(RefreshToken::user)
                .map(this::generateTokens);
    }

    private TokenDTO generateTokens(User user) {
        String accessToken = jwtService.createToken(new JwtUserDetails(
                user.getUsername(),
                user.getRole()
        ));
        RefreshToken refreshToken = refreshTokenService.createFor(user);
        return new TokenDTO(accessToken, refreshToken.value());
    }


    public boolean deleteUser(String username) {
        return userRepository.findByUsername(username)
                .filter(user -> Objects.equals(user.getRole(), "USER"))
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean manejoSolicitudesSeguimiento(Long userId, Long solicitanteId, boolean aceptar) {
        Optional<User> maybeUser = userRepository.findById(userId);

        Optional<User> maybeSolicitante = userRepository.findById(solicitanteId);

        if (maybeUser.isEmpty() || maybeSolicitante.isEmpty()) {
            return false ;
        }

        User user = maybeUser.get();
        User userSolicitante = maybeSolicitante.get();

        if (!user.getSolicitudesSeguimiento().contains(userSolicitante)) {
            return false;
        }

        if (aceptar) {
            user.aceptarSolicitudesSeguimiento(userSolicitante);
        }else{
            user.rechazarSolicitudesSeguimiento(userSolicitante);
        }
        userRepository.save(user);
        return true;

    }

    public List<UserProfileDTO> getSeguidores(String username){
        return userRepository.findByUsername(username)
                .map(user -> user.getSeguidores().stream()
                        //.map(UserLoginDTO::from)
                        .map(seguidor -> new UserProfileDTO(seguidor.getUsername(), seguidor.getName(), seguidor.getLastname(), seguidor.getBirthday(),seguidor.getGender()))
                        .collect(Collectors.toList()))
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }

    public List<UserProfileDTO> getSeguidos(String username){
        return userRepository.findByUsername(username)
                .map(user -> user.getSeguidos().stream()
                        //.map(UserLoginDTO::from)
                        .map(seguidor -> new UserProfileDTO(seguidor.getUsername(), seguidor.getName(), seguidor.getLastname(), seguidor.getBirthday(),seguidor.getGender()))
                        .collect(Collectors.toList()))
                .orElseThrow(()-> new UsernameNotFoundException("USer Not Found"));

    }


}
