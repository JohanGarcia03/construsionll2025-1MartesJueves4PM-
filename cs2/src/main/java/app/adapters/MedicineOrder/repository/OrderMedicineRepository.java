package app.adapters.MedicineOrder.repository;

import app.adapters.MedicineOrder.entity.OrderMedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderMedicineRepository extends JpaRepository<OrderMedicineEntity, Long> {

    Optional<OrderMedicineEntity> findByOrderId(Long orderId);

    List<OrderMedicineEntity> findByPetId(long petId);

    List<OrderMedicineEntity> findByCancelled(boolean cancelled);

    List<OrderMedicineEntity> findByDniVeterinarian(long vetDni);
    @Query("SELECT o FROM OrderMedicineEntity o WHERE o.dateGeneration BETWEEN :startDate AND :endDate")
    List<OrderMedicineEntity> findByDateRange(Date startDate, Date endDate);

}
