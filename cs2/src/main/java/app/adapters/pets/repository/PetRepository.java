package app.adapters.pets.repository;

import app.adapters.pets.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findAll();
    Optional<PetEntity> findById(Long id);
    List<PetEntity> findByIdOwner(Long idOwner);
    @Query("SELECT p FROM PetEntity p WHERE LOWER(p.species) = LOWER(:species)")
    List<PetEntity> findBySpeciesIgnoreCase(String species);
}