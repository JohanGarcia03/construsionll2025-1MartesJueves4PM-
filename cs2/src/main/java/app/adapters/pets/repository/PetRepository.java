package app.adapters.pets.repository;

import app.adapters.pets.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    // Métodos básicos
    List<PetEntity> findAll();
    Optional<PetEntity> findById(Long id);

    // Métodos adicionales
    List<PetEntity> findByOwnerId(Long ownerId);

    @Query("SELECT p FROM PetEntity p WHERE LOWER(p.species) = LOWER(:species)")
    List<PetEntity> findBySpeciesIgnoreCase(String species);

    List<PetEntity> findByRaceContainingIgnoreCase(String race);

    @Query("SELECT p FROM PetEntity p WHERE p.agePet BETWEEN :minAge AND :maxAge")
    List<PetEntity> findByAgeRange(int minAge, int maxAge);
}