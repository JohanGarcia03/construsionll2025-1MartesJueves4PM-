package app.ports;

import app.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    boolean existUserName(String username);
    void save(User user);
    void delete(Long id);
    Optional<User> findById(Long id);
    List<User> findAll();

}
