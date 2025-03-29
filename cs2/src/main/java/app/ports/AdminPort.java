package app.ports;

import app.domain.models.Person;

public interface AdminPort {
    boolean existPerson(long personId );
    void savePerson (Person person);
    Person findById(long personId);
}
