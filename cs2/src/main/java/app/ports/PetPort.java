package app.ports;

import app.domain.models.Pet;

import java.util.List;
import java.util.Optional;

public interface PetPort {
    Optional<Pet> findById(Long id);
    List<Pet> findAll();
    void save(Pet pet);
    void delete(String id);

}

