package app.ports;

import app.domain.models.User;

import java.util.Optional;

public interface PersonPort {
  void personExist(long person);
  void savePerson(User user);
  Optional<User> findById(long id);
}
