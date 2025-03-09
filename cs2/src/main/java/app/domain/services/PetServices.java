package app.domain.services;

import app.domain.models.Pet;
import app.ports.Petport;
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
    private Petport petport;
    private UserPort userport;

    public PetServices(Petport petPort, UserPort userport) {
        this.petport = petPort;
        this.userport = userport;
    }

    public void registerPet(Long idPet,String name, Long dniOwner,int AgePet, String species, String race, String color, String size, Float weight) {
        if (userport.findById(dniOwner).isEmpty()) {
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
