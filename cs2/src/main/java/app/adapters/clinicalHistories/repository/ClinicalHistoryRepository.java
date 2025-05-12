package app.adapters.clinicalHistories.repository;
import app.adapters.clinicalHistories.entity.ClinicalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistoryEntity, Long> {
    List<ClinicalHistoryEntity> findByPetId(Long petId);
    List<ClinicalHistoryEntity> findByVeterinarianId(Long vetId);
}
