package app.adapters.users;

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
    public User findByUserName(User user) {
        UserEntity entity = userRepository.findByUserName(user.getUsername());
        return entity != null ? convertToDomain(entity) : null;
    }

    // Métodos de conversión
    private UserEntity convertToEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUserId(user.getIdUser());
        entity.setUserName(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        return entity;
    }

    private User convertToDomain(UserEntity entity) {
        User user = new User();
        user.setIdUser(entity.getUserId());
        user.setUsername(entity.getUserName());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());
        return user;
    }
}