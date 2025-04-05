package app.ports;

import app.domain.models.Pet;

import java.util.List;
import java.util.Optional;

public interface PetPort {
    void SavePet(Pet pet) throws Exception;
    List<Pet> PetAll();
    Optional<Pet> FindByIdPet(String id);
    boolean ExistPetById(String id);
}