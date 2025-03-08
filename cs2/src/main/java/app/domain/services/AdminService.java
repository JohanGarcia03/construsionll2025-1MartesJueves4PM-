package app.domain.services;

import app.domain.models.Person;
import app.domain.models.Role;
import app.domain.models.User;
import app.ports.PersonPort;
import lombok.*;
import app.ports.UserPort;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class AdminService {
    private PersonPort personPort;
    private UserPort usernamePort;
    private User Userlogued;

    void RegisterUser(User user) throws Exception {
        if (personPort.PersonExist(user.getPersonId())){
            throw new Exception("Ya existe una persona con ese ID");
        }
        if (usernamePort.existUserName(user.getUsername())) {
            throw new Exception("Ya existe una persona con ese usuario");
        }
        if ((user.getRole() == Role.VETERINARIAN || user.getRole() == Role.SELLER)
                && Userlogued.getRole() != Role.ADMIN) {
            throw new SecurityException("No tienes permisos para registrar este usuario.");
        }
        usernamePort.save(user);
    }
    public Optional<User> getPersonId(Long id) {
        return usernamePort.findById(id);
    }
    public List<User> Getallpersons() {
        return usernamePort.findAll();
    }
    public void removeUser(Long id){
        usernamePort.delete(id);

    }

}


