package app.adapters.users;

import app.adapters.persons.entity.PersonEntity;
import app.adapters.users.entity.UserEntity;
import app.adapters.users.repository.UserRepository;
import app.domain.models.User;
import app.ports.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAdapter implements UserPort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean existUserName(String username) {
        return userRepository.existsByUserName(username);
    }


    @Override
    public void save(User user) {
        UserEntity entity = convertToEntity(user);
        userRepository.save(entity);
    }

    @Override
    public Optional<User> findByCedula(Long cedula) {
        return userRepository.findByCedula(cedula)
                .map(this::convertToDomain);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id)
                .map(this::convertToDomain);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    // Métodos de conversión
    private UserEntity convertToEntity(User user) {
        UserEntity entity = new UserEntity();
        if (user.getIdUser() != 0) {
            entity.setId(user.getIdUser());
        }
        entity.setUserName(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());

        if (user.getPerson() != null) {
            PersonEntity personEntity = new PersonEntity();
            personEntity.setCedula(user.getPerson().getCedula());
            personEntity.setName(user.getPerson().getName());
            personEntity.setAge(user.getPerson().getAge());
            entity.setPerson(personEntity);
        }

        return entity;
    }


    private User convertToDomain(UserEntity entity) {
        User user = new User();
        user.setIdUser(entity.getId());
        user.setUsername(entity.getUserName());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());
        return user;
    }
}