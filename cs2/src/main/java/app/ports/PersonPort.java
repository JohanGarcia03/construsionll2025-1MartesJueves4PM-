package app.ports;

import app.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface PersonPort {
  boolean PersonExist(long person);
  void savePersonport(User user);
  Optional<User> findById(Long id);
  List<User> getAllUsers();
}
