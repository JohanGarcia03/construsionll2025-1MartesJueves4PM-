package app.ports;

import app.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface PersonPort {
  boolean PersonExist(long person);
  void savePersonport(User user);
  void deletePersonport(Long id);
  Optional<User> findById(Long id);
  List<User> findAllPersonport();
}
