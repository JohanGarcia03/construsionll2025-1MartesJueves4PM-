package app.adapters.clinicalHistories;

import app.adapters.clinicalHistories.entity.ClinicalHistoryEntity;
import app.adapters.clinicalHistories.repository.ClinicalHistoryRepository;
import app.domain.models.ClinicalHistory;
import app.ports.ClinicalHistoryPort;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ClinicalHistoryAdapter implements ClinicalHistoryPort {
    @Autowired
    private ClinicalHistoryRepository repository;

    @Override
    public ClinicalHistory save(ClinicalHistory history) {
        ClinicalHistoryEntity entity = convertToEntity(history);
        ClinicalHistoryEntity saved = repository.save(entity);
        return convertToDomain(saved);
    }

    @Override
    public Optional<ClinicalHistory> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<ClinicalHistory> findByPetId(long petId) {
        return List.of();
    }
    public List<ClinicalHistory> findByPetId(Long petId) {
        return repository.findByPetId(petId).stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    private ClinicalHistoryEntity convertToEntity(ClinicalHistory domain) {
        ClinicalHistoryEntity entity = new ClinicalHistoryEntity();

        // Mapeo de campos básicos
        entity.setPetId(domain.getIdPet());
        entity.setDate(domain.getDate());
        entity.setDiagnostic(domain.getDiagnostic());
        entity.setMedications(domain.getMedications());
        entity.setReason(domain.getReason());
        entity.setSymptoms(domain.getSymptoms());
        entity.setProcedure(domain.getProcedure());

        return entity;
    }

    private ClinicalHistory convertToDomain(ClinicalHistoryEntity entity) {
        ClinicalHistory domain = new ClinicalHistory();

        // Mapeo completo de campos
        domain.setIdPet(entity.getPetId());
        domain.setDate(entity.getDate());
        domain.setUsers("Vet-" + entity.getVeterinarianId());
        domain.setReason(entity.getReason());
        domain.setSymptoms(entity.getSymptoms());
        domain.setDiagnostic(entity.getDiagnostic());
        domain.setProcedure(entity.getProcedure());
        domain.setDose(entity.getDose());
        domain.setVaccine(entity.getVaccines());
        domain.setAllergies(entity.getAllergies());
        domain.setDetailsProcess(entity.getDetails());
        domain.setOrderAnnulled(entity.isOrderAnnulled());

        // Asignar ID de orden si existe
        if (entity.getOrderId() != null) {
            domain.setIdOrder(entity.getOrderId());
        }

        return domain;
    }
}
