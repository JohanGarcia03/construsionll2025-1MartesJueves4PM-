package app.ports;

import app.adapters.clinicalHistories.entity.ClinicalHistoryEntity;
import app.domain.models.ClinicalHistory;

import java.util.List;
import java.util.Optional;

public interface ClinicalHistoryPort {
    ClinicalHistory save(ClinicalHistoryEntity history);
    Optional<ClinicalHistory> findById(long id);
    List<ClinicalHistoryEntity> findByPetId(Long petId);
    void saveVaccination(Long id, String vacuna);

    void saveAllergy(Long id, String alergia);
}
