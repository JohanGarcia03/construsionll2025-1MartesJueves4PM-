package app.ports;

import app.domain.models.Person;
import app.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface PersonPort {
  void savePerson(Person person);

  Optional<Person> findByCedula(long cedula);

  boolean existsByCedula(long cedula);

  // Implementación para User (manteniendo compatibilidad)
  void savePerson(User user);

  Optional<User> findById(long id);

  List<Person> getAll();
}
