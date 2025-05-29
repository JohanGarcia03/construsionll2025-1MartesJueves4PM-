package app.adapters.pets;
import app.adapters.pets.entity.PetEntity;
import app.adapters.pets.repository.PetRepository;
import app.domain.exceptions.NotFoundException;
import app.domain.models.Pet;
import app.ports.PersonPort;
import app.ports.PetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Service
public  class PetAdapter implements PetPort {

    private final PersonPort personPort;
    private final PetRepository petRepository;

    @Autowired
    public PetAdapter(PersonPort personPort, PetRepository petRepository) {
        this.personPort = personPort;

        this.petRepository = petRepository;
    }

    @Override
    public void SavePet(Pet pet) throws Exception {
        try {
            validatePet(pet);
            PetEntity entity = convertToEntity(pet);
            PetEntity saved = petRepository.save(entity);
            pet.setIdPet(saved.getIdPet());
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new NotFoundException("❌ Error al guardar mascota: " + e.getMessage());
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
    public Optional<Pet> FindByIdPet(Long id) {
        return petRepository.findById(id)
                .map(this::convertToDomain);
    }

    @Override
    public List<Pet> FindByIdOwner(long idOwner) {
        return petRepository.findByIdOwner(idOwner)
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

    private void validatePet(Pet pet ) throws NotFoundException {
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new NotFoundException("❌ El nombre de la mascota es obligatorio.");
        }
        if (pet.getSpecies().isEmpty()) {
            throw new NotFoundException("❌ La especie de la mascota es obligatoria.");
        }
        if (!personPort.existsByCedula(pet.getIdOwner())) {
            throw new NotFoundException("❌ No existe un dueño registrado con cédula: " + pet.getIdOwner());
        }
        if (pet.getAgePet() <= 0) {
            throw new NotFoundException("❌ La edad de la mascota debe ser mayor que 0.");
        }
        if (pet.getWeight() <= 0){
            throw new NotFoundException(("❌ El peso de la mascota debe ser mayor a cero."));
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