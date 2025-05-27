package app.domain.services;

import app.adapters.users.entity.UserEntity;
import app.ports.UserPort;
import app.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserPort userPort;

    public UserEntity login(User user) throws Exception {
        return userPort.findByUsername(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .orElseThrow(() -> new Exception("❌ Usuario o contraseña incorrecta"));
    }
}
