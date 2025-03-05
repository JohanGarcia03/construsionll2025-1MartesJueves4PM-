package app.ports;

import app.domain.models.Person;

public interface PersonPort {
  boolean existPerson(Person person);
}
