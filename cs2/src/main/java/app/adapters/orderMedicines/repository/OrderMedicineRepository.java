package app.adapters.orderMedicines.repository;

import app.adapters.orderMedicines.entity.OrderMedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderMedicineRepository extends JpaRepository<OrderMedicineEntity, Long> {
    Optional<OrderMedicineEntity> findByOrderId(long orderId);

    List<OrderMedicineEntity> findByPetId(long petId);

    List<OrderMedicineEntity> findByDniVeterinarian(long vetDni);

    @Query("SELECT o FROM OrderMedicineEntity o WHERE o.dateGeneration BETWEEN :startDate AND :endDate")
    List<OrderMedicineEntity> findByDateRange(Date startDate, Date endDate);

    List<OrderMedicineEntity> findByVoidedOrder(boolean isVoided);
}