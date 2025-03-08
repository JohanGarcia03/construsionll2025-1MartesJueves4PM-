package app.domain.services;

import app.domain.models.Pet;
import app.ports.PersonPort;
import app.ports.UserPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Getter
@NoArgsConstructor
@Setter
public class PetServices {
    private PetServices petPort;
    private UserPort userPort;

    public PetServices(PetServices petPort, UserPort userPort) {
        this.petPort = petPort;
        this.userPort = userPort;
    }

    public void registerPet(Long idPet,String name, Long dniOwner,int AgePet, String species, String race, String color, String size, Float weight) {
        if (userPort.findById(dniOwner).isEmpty()) {
            throw new IllegalArgumentException("El dueño no está registrado.");
        }

        String petId = UUID.randomUUID().toString();
        Pet pet = new Pet(idPet, name, dniOwner, AgePet, species, race, color, size, weight);
        petPort.save(pet);
    }

    public List<Pet> getAllPets() {
        return petPort.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petPort.findById(id);
    }
}
