package app.domain.services;

import app.domain.models.User;
import app.ports.DefaulPort;

public class DefaulUser {
    private DefaulPort defaulPort;

    public User login(String username, String password) {
        return defaulPort.login(username, password)
                .orElseThrow(() -> new SecurityException("Usuario o contraseña incorrectos"));
    }
}
