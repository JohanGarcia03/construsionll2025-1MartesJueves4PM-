package app.ports;

import app.domain.models.ClinicalHistory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ClinicalHistoryPort {
    // Registro médico
    ClinicalHistory registerMedicalRecord(ClinicalHistory record);

    // Consultas
    Map<Date, ClinicalHistory> getFullHistoryByPet(Long petId);
    ClinicalHistory getRecordByDate(Long petId, Date date);

    // Ordenes médicas
    void markOrderAsAnnulled(long orderId, String reason);

    // Validación
    boolean isValidVeterinarianForPet(String dniVeterinarian, Long petId);
}
