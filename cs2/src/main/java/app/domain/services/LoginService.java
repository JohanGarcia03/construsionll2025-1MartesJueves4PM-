package app.domain.services;

import app.ports.UserPort;
import app.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LoginService {

    @Autowired
    private UserPort userPort;


    public User login(User user) throws Exception {
        User userValidate = userPort.findByUserName(user);
        if(userValidate == null) {
            throw new Exception("Usuario o contraseña incorrecta");
        }
        if(user.getPassword().equals(userValidate.getPassword())) {
            throw new Exception("Usuario o contraseña incorrecta");
        }
        return user;
    }
}