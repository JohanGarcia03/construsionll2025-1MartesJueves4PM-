package app.ports;

import app.domain.models.Pet;
import app.domain.models.PetOwner;

import java.util.List;
import java.util.Optional;

public interface OwnerPort {
    Optional<PetOwner> findById(Long id);
    List<PetOwner> findAll();
    void save(PetOwner owner);
    void delete(String id);
}
