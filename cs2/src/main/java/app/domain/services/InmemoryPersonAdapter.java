package app.domain.services;

import app.domain.models.Role;
import app.domain.models.User;
import app.ports.UserPort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InmemoryPersonAdapter implements UserPort {

    private final Map<Long, User> userDatabase = new HashMap<>();

    public InmemoryPersonAdapter (){
        User admin = new User(1L, "Admin", 30, "admin", "Pt3R0d4cT1l3s", Role.ADMIN);
        userDatabase.put(admin.getPersonId(), admin);
    }

    @Override
    public boolean existUserName(String username) {
        return userDatabase.values().stream().anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public void save(User user) {
        userDatabase.put(user.getPersonId(), user);
    }

    @Override
    public void delete(Long id) {
        userDatabase.values().removeIf(user -> user.getPersonId().equals(id));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDatabase.values().stream()
                .filter(user -> user.getPersonId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userDatabase.values());
    }
}

