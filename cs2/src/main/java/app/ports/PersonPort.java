package app.ports;

import app.domain.models.Person;
import app.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface PersonPort {
  boolean PersonExist(long person);
}
