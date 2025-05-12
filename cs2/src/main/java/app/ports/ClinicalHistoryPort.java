package app.ports;

import app.domain.models.ClinicalHistory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClinicalHistoryPort {
    ClinicalHistory save(ClinicalHistory history);
    Optional<ClinicalHistory> findById(long id);
    List<ClinicalHistory> findByPetId(long petId);
}
