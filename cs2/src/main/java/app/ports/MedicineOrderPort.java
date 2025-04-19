package app.ports;

import app.domain.models.ClinicalHistory;
import app.domain.models.OrderMedicine;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Optional;

public interface MedicineOrderPort {
    OrderMedicine createMedicineOrder(OrderMedicine orderMedicine);
    void annulOrder(Long orderId, String reason);
    List<OrderMedicine> getActiveOrdersByVet(long dniVeterinarians);
    List<OrderMedicine> getOrdersByPet(long petId);
     Optional<OrderMedicine> findOrderById(long idOrder);

}