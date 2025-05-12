package app.ports;

import app.domain.models.Person;
import app.domain.models.User;

import java.util.Optional;

public interface PersonPort {
  // Implementación para Person
  void savePerson(Person person);

  Optional<Person> findByCedula(long cedula);

  boolean existsByCedula(long cedula);

  void savePerson(User user);
  Optional<User> findById(long id);
}
