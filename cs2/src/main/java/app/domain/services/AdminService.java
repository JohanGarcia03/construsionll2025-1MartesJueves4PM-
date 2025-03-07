package app.domain.services;

import app.domain.models.Person;
import app.domain.models.User;
import app.ports.PersonPort;
import lombok.*;
import app.ports.UserPort;

@Getter
@Setter
@NoArgsConstructor
public class AdminService {

    private PersonPort personPort;
    private UserPort usernamePort;

    public void RegisterUser(User user) throws Exception {
        if (personPort.PersonExist(user.getPersonId())){
            throw new Exception("Ya existe una persona con ese ID");
        }
        if (usernamePort.existUserName(user.getUserId())) {
            throw new Exception("Ya existe una persona con ese usuario");
        }

    }

}
