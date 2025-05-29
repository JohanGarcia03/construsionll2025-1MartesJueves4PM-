package app.ports;

import app.adapters.users.entity.UserEntity;
import app.domain.models.Person;
import app.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    boolean existUserName(String username);
    Optional<UserEntity> findByUsername(String userName);
    void save(User user);
    Optional<User> findByCedula(Long cedula);
    Optional<User> findById(long id);
    List<User> findAll();

}
