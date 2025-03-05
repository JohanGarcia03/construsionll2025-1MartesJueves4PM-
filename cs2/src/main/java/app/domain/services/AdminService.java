package app.domain.services;

import app.domain.models.Person;
import app.domain.models.User;
import app.ports.PersonPort;
import lombok.*;
import app.ports.UserPort;

public class AdminService {

    private UserPort username;
    private PersonPort personPort;

    @SneakyThrows
    public void RegisterVeter(Person persona) throws Exception {
        if (PersonPort.PersonExist(persona.getPersonId())){
            throw new Exception("Ya existe una persona con ese ID");
        }

    }
    public void RegisterUs(User username) throws Exception {
        if (username.existUserName((username.getUser()))) {
            throw new Exception("Ya existe una persona con ese usuario");
            }
        }
    }
