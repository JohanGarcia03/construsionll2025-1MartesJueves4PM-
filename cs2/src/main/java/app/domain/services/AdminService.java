package app.domain.services;

import app.domain.models.Person;
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


    public void registerVeterinarian(User user) throws Exception {
        Optional<User> userFound = personPort.findById(user.getCedula());
        if (userFound.isPresent()) {
            throw new Exception("Ya existe un usuario con esa Cedula: " + userFound.get().getCedula());
        }

        if (userPort.existUserName(user.getUsername())) {
            throw new Exception("El nombre de usuario '" + user.getUsername() + "' ya está en uso.");
        }
        user.setRole("VETERINARIAN");
        personPort.savePerson(user);
        userPort.save(user);
    }
    public Optional<User> getVendorById(long id) throws Exception {
        Optional<User> user = userPort.findById(id);
        if (user.isEmpty()) {
            throw new UserPrincipalNotFoundException("No se encontró usuario con ID: " + id);
        }
        return user;
    }

    public List<User> getAllVendors() {
        return userPort.findAll();
    }

    public void registerSeller(User user) throws Exception {
        Optional<User> userFound = personPort.findById(user.getCedula());
        if (userFound.isPresent()) {
            throw new Exception("Ya existe un usuario con esa Cedula: " + userFound.get().getCedula());
        }
        if (userPort.existUserName(user.getUsername())) {
            throw new Exception("El nombre de usuario '" + user.getUsername() + "' ya está en uso.");
        }
        user.setRole("SELLER");
        personPort.savePerson(user);
        userPort.save(user);
    }
    public Optional<User> getSellerById(long id) throws Exception {
        Optional<User> user = userPort.findById(id);
        if (user.isEmpty()) {
            throw new UserPrincipalNotFoundException("No se encontró un vendedor con ese ID: " + id);
        }
        return user;
    }
    public void registerOwner(User user) throws Exception {
        Optional<User> userFound = personPort.findById(user.getCedula());
        if (userFound.isPresent()) {
            throw new Exception("Ya existe un usuario con esa Cedula: " + userFound.get().getCedula());
        }
        user.setRole("OWNER");
        personPort.savePerson(user);
        userPort.save(user);
    }

    public List<User> getAllSellers() {
        return userPort.findAll();
    }

}
