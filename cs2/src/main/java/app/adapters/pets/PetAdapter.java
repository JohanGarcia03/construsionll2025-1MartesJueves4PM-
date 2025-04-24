package app.adapters.pets;

import app.adapters.pets.entity.PetEntity;
import app.adapters.pets.repository.PetRepository;
import app.domain.models.Pet;
import app.ports.PetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PetAdapter implements PetPort {

    private final PetRepository petRepository;

    @Autowired
    public PetAdapter(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void SavePet(Pet pet) throws Exception {
        try {
            validatePet(pet);
            PetEntity entity = convertToEntity(pet);
            petRepository.save(entity);
            pet.setIdPet(entity.getIdPet()); // Actualizar ID generado
        } catch (Exception e) {
            throw new Exception("Error al guardar mascota: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Pet> PetAll() {
        return petRepository.findAll()
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pet> FindByIdPet(long id) {
        return petRepository.findById(id)
                .map(this::convertToDomain);
    }

    // Nuevos métodos adicionales
    public List<Pet> findByOwner(long ownerId) {
        return petRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    public List<Pet> findBySpecies(String species) {
        return petRepository.findBySpeciesIgnoreCase(species)
                .stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    private void validatePet(Pet pet) throws Exception {
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new Exception("El nombre de la mascota es requerido");
        }
        if (pet.getSpecies() == null || pet.getSpecies().trim().isEmpty()) {
            throw new Exception("La especie de la mascota es requerida");
        }
        if (pet.getIdOwner() <= 0) {
            throw new Exception("Debe especificar un dueño válido");
        }
    }

    private PetEntity convertToEntity(Pet pet) {
        PetEntity entity = new PetEntity();
        entity.setIdPet(pet.getIdPet());
        entity.setIdOwner(pet.getIdOwner());
        entity.setName(pet.getName());
        entity.setAgePet(pet.getAgePet());
        entity.setSpecies(pet.getSpecies());
        entity.setRace(pet.getRace());
        entity.setColor(pet.getColor());
        entity.setSize(pet.getSize());
        entity.setWeight(pet.getWeight());
        return entity;
    }

    private Pet convertToDomain(PetEntity entity) {
        return new Pet(
                entity.getIdPet(),
                entity.getIdOwner(),
                entity.getName(),
                entity.getAgePet(),
                entity.getSpecies(),
                entity.getRace(),
                entity.getColor(),
                entity.getSize(),
                entity.getWeight()
        );
    }
}