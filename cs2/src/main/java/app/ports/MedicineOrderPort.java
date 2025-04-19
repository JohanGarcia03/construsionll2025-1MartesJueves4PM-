package app.ports;

import app.domain.models.ClinicalHistory;
import app.domain.models.OrderMedicine;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MedicineOrderPort {
    OrderMedicine save(OrderMedicine order);
    Optional<OrderMedicine> findOrderById(long id);
    List<OrderMedicine> findOrdersByVeterinarian(long vetId);
    List<ClinicalHistory> findByPetId(long petId);
    List<ClinicalHistory> findByDateRange(Date startDate, Date endDate);
}