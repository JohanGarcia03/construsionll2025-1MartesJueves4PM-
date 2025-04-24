package app.adapters.persons;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.persons.repository.PersonRepository;
import app.adapters.users.UserAdapter;
import app.domain.models.Person;
import app.domain.models.User;
import app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonAdapter implements PersonPort {

    private final PersonRepository personRepository;
    private final UserAdapter userAdapter; // Para manejar User

    @Autowired
    public PersonAdapter(PersonRepository personRepository,
                         UserAdapter userAdapter) {
        this.personRepository = personRepository;
        this.userAdapter = userAdapter;
    }

    // Implementación para Person
    @Override
    public void savePerson(Person person) {
        PersonEntity entity = convertToEntity(person);
        personRepository.save(entity);
    }

    @Override
    public Optional<Person> findByCedula(long cedula) {
        return personRepository.findByCedula(cedula)
                .map(this::convertToDomain);
    }

    @Override
    public boolean existsByCedula(long cedula) {
        return personRepository.existsByCedula(cedula);
    }

    // Implementación para User (manteniendo compatibilidad)
    @Override
    public void savePerson(User user) {
        userAdapter.save(user); // Delegamos al UserAdapter
    }

    @Override
    public Optional<User> findById(long id) {
        return userAdapter.findById(id); // Delegamos al UserAdapter
    }

    // Métodos de conversión
    private PersonEntity convertToEntity(Person person) {
        PersonEntity entity = new PersonEntity();
        entity.setCedula(person.getCedula());
        entity.setName(person.getName());
        entity.setAge(person.getAge());
        return entity;
    }

    private Person convertToDomain(PersonEntity entity) {
        Person person = new Person();
        person.setCedula(entity.getCedula());
        person.setName(entity.getName());
        person.setAge(entity.getAge());
        return person;
    }
}