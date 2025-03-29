package app.domain.services;

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

    public void registerUser(User user) throws Exception {

        validateUser(user);
        Optional<User> userFound = personPort.findById(user.getCedula());
        if (userFound.isPresent()) {
            throw new Exception("Ya existe un usuario con ese ID: " + userFound.get().getCedula());
        }
        if (userPort.existUserName(user.getUsername())) {
            throw new Exception("El nombre de usuario '" + user.getUsername() + "' ya está en uso.");
        }
        userPort.save(user);
        user.setRole("");
        personPort.savePerson(user);
        userPort.save(user);
        personPort.personExist(user.getCedula());
    }

    private void validateUser(User user) throws IllegalArgumentException {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("El usuario debe tener un rol asignado: VETERINARIAN o SELLER.");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
    }

    public Optional<User> getUserById(long id) throws Exception {
        if (id == 0) {
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

    public void removeUser(long id) throws Exception {
        Optional<User> user = userPort.findById(id);
        if (user.isEmpty()) {
            throw new UserPrincipalNotFoundException("No se puede eliminar, usuario con ID " + id + " no encontrado.");
        }
        userPort.delete(id);
    }
}
