package app.domain.services;

import app.domain.models.Pet;
import app.domain.models.PetOwner;
import app.ports.OwnerPort;
import app.ports.PetPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@NoArgsConstructor
public class OnwerServices {
    private OwnerPort ownerPort;
    private PetPort petPort;

    public void registerOwner(PetOwner owner) throws Exception {
        if (owner == null || owner.getPersonId() == null) {
            throw new IllegalArgumentException("El dueño no puede ser nulo y debe tener una cédula válida.");
        }
        Optional<PetOwner> ownerFound = ownerPort.findById(owner.getPersonId());
        if (ownerFound.isPresent()) {
            throw new Exception("Ya existe un dueño registrado con la cédula: " + owner.getPersonId());
        }
        ownerPort.save(owner);
    }

    public void registerPet(Pet pet) throws Exception {
        if (pet == null || pet.getPersonId() == null) {
            throw new IllegalArgumentException("La mascota debe tener un dueño asociado.");
        }
        Optional<PetOwner> owner = ownerPort.findById(pet.getPersonId());
        if (owner.isEmpty()) {
            throw new Exception("El dueño con cédula " + pet.getDniOwner() + " no está registrado.");
        }
        pet.setIdPet(generatePetId());
        petPort.save(pet);
    }

     Optional<Pet> getPetsByOwner(Long dniOwner) {
        return petPort.findById(dniOwner);
    }

    private Long generatePetId() {
        return System.currentTimeMillis();
    }
}
