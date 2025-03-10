package app.domain.services;

import app.domain.models.Pet;
import app.ports.PersonPort;
import app.ports.PetPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Getter

@Setter
public class PetServices {
    private PetPort petport;
    private PersonPort personPort;

    public PetServices() {
    }

    public PetServices(PetPort petPort, PersonPort personPort) {
        this.petport = petPort;
        this.personPort = personPort;
    }

    public void registerPet(Long idPet,String name, Long dniOwner,int AgePet, String species, String race, String color, String size, Float weight) {
        if (personPort.findById(dniOwner).isEmpty()) {
            throw new IllegalArgumentException("El dueño no está registrado.");
        }

        String petId = UUID.randomUUID().toString();
        Pet pet = new Pet(idPet, name, dniOwner, AgePet, species, race, color, size, weight);
        petport.save(pet);
    }

    public List<Pet> getAllPets() {
        return petport.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petport.findById(id);
    }
}
