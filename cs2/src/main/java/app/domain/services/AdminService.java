package app.domain.services;

import app.domain.models.Role;
import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.UserPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class AdminService {
    private PersonPort personPort;
    private UserPort userPort;
    private User loggedUser;

    public void registerUser(User user) throws Exception {

        validateUser(user);
        Optional<User> userFound = personPort.findById(user.getPersonId());

        if (userFound.isPresent()) {
            throw new Exception("Ya existe un usuario con ese ID: " + userFound.get().getPersonId());
        }
        if (userPort.existUserName(user.getUsername())) {
            throw new Exception("El nombre de usuario '" + user.getUsername() + "' ya está en uso.");
        }
        if (!canRegisterUser(user)) {
            throw new SecurityException("No tienes permisos para registrar este usuario.");
        }
        userPort.save(user);
    }

    private void validateUser(User user) throws IllegalArgumentException {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("El usuario debe tener un rol asignado: VETERINARIAN o SELLER.");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
    }

    private boolean canRegisterUser(User user) {
        return loggedUser != null && loggedUser.getRole() == Role.ADMIN;
    }

    public Optional<User> getUserById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        Optional<User> user = userPort.findById(id);
        if (user.isEmpty()) {
            throw new UserPrincipalNotFoundException("No se encontró usuario con ID: " + id);
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userPort.findAll();
    }

    public void removeUser(Long id) throws Exception {
        Optional<User> user = userPort.findById(id);
        if (user.isEmpty()) {
            throw new UserPrincipalNotFoundException("No se puede eliminar, usuario con ID " + id + " no encontrado.");
        }
        userPort.delete(id);
    }
}
