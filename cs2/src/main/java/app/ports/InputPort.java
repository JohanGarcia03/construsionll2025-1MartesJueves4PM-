package app.ports;

import app.domain.models.User;

import java.util.Optional;

public interface InputPort {
    Optional<User> login(String username, String password);
}
