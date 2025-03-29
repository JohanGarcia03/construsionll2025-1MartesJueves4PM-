package app.domain.services;

import app.domain.models.Person;
import app.domain.models.User;
import app.ports.AdminPort;
import app.ports.UserPort;

public class AdminService {
    private AdminPort adminPort;
    private UserPort userPort ;

    public void registerPerson(User user) throws Exception {
        if (adminPort.existPerson(user.getPersonId())) {
            throw new Exception("El usuario ya existe con esta ID.");

        }
        if(userPort.existUserName(user.getName())) {
            throw new Exception("Ya existe un username con ese usuario");
    }
        // Si no existe, registrar el usuario
        user.getRole();
        user.setPersonId(user.getPersonId());
        user.getName();
        adminPort.savePerson(user);
        userPort.saveUser(user);
    }
}
