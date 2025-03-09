package app.domain.services;

import app.domain.models.User;
import app.ports.DefaulPort;

import java.util.Optional;

public class InmemoryDefaultAdapter implements DefaulPort {
    private final InmemoryPersonAdapter personAdapter;

    public InmemoryDefaultAdapter(InmemoryPersonAdapter personAdapter) {
        this.personAdapter = personAdapter;
    }

    @Override
    public Optional<User> login(String username, String password) {
        return personAdapter.findAll().stream().filter(user -> user.getUsername()
                .equals(username) && user.getPassword().equals(password)).findFirst();
    }
}
