package app.adapters.clinicalHistories;

import app.adapters.clinicalHistories.entity.ClinicalHistoryEntity;
import app.adapters.clinicalHistories.repository.ClinicalHistoryRepository;
import app.domain.models.ClinicalHistory;
import app.ports.ClinicalHistoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ClinicalHistoryAdapter implements ClinicalHistoryPort {

    @Autowired
    private ClinicalHistoryRepository repository;

    @Override
    public ClinicalHistory save(ClinicalHistoryEntity history) {
        ClinicalHistoryEntity entity = convertToEntity(history);
        ClinicalHistoryEntity saved = repository.save(entity);
        return convertToDomain(saved);
    }

    private ClinicalHistoryEntity convertToEntity(ClinicalHistoryEntity history) {
        return history;
    }

    @Override
    public List<ClinicalHistoryEntity> findByPetId(Long petId) {
        return repository.findByPetId(petId);
    }


    @Override
    public Optional<ClinicalHistory> findById(long id) {
        return repository.findById(id)
                .map(this::convertToDomain);
    }


    @Override
    public void saveVaccination(Long id, String vacuna) {

    }

    @Override
    public void saveAllergy(Long id, String alergia) {

    }

    private ClinicalHistory convertToDomain(ClinicalHistoryEntity entity) {
        return new ClinicalHistory();
    }

    private ClinicalHistoryEntity convertToEntity(ClinicalHistory domain) {
        ClinicalHistoryEntity entity = new ClinicalHistoryEntity();
        entity.setDate(domain.getDate());
        entity.setVeterinarianId(Long.parseLong(domain.getUsers()));
        entity.setPetId(domain.getIdPet());
        entity.setReason(domain.getReason());
        entity.setSymptoms(domain.getSymptoms());
        entity.setDiagnostic(domain.getDiagnostic());
        entity.setProcedureType(domain.getProcedure());
        entity.setMedication(domain.getMedication());
        entity.setDosage(domain.getDose());
        entity.setOrderId(String.valueOf(domain.getIdOrder()));
        entity.setVaccinations(domain.getVaccine());
        entity.setAllergyMedications(domain.getAllergies());
        entity.setProcedureDetails(domain.getDetailsProcess());
        entity.setOrderAnnulled(domain.isOrderAnnulled());
        return entity;
    }
}
