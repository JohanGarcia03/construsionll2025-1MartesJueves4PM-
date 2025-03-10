package app.domain.services;

import app.domain.models.User;
import app.ports.AdminPort;

import java.util.Optional;

public class InmemoryDefaultAdapter implements AdminPort {
    private final MemoryPersonAdapter personAdapter;

    public InmemoryDefaultAdapter(MemoryPersonAdapter personAdapter) {
        this.personAdapter = personAdapter;
    }

    @Override
    public Optional<User> login(String username, String password) {
        return personAdapter.findAll().stream().filter(user -> user.getUsername()
                .equals(username) && user.getPassword().equals(password)).findFirst();
    }
}
