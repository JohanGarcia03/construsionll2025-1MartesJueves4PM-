package app.ports;

import app.domain.models.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PetPort {
    void SavePet(Pet pet) throws Exception;
    List<Pet> PetAll();
    Optional<Pet> FindByIdPet(Long id);

    List<Pet> FindByIdOwner(long idOwner);

}