package app.ports;

import app.domain.models.Person;
import app.domain.models.User;

import java.util.Optional;

public interface DefaulPort {
    Optional<User> login(String username, String password);


}
