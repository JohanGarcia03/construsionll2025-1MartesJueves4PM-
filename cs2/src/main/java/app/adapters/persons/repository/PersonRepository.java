package app.adapters.persons.repository;

import app.adapters.persons.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByCedula(long cedula);
    boolean existsByCedula(long cedula);

    // Para la relación con User
    Optional<PersonEntity> findByUser_Id(Long userId);
}