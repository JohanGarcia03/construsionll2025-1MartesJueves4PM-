package app.ports;

import app.domain.models.OrderMedicine;

import java.util.List;
import java.util.Optional;

public interface MedicineOrderPort {
    OrderMedicine createMedicineOrder(OrderMedicine order);
    void annulOrder(Long orderId, String reason);
    List<OrderMedicine> getActiveOrdersByVet(String dniVeterinarian);
    List<OrderMedicine> getOrdersByPet(Long petId);
    Optional<OrderMedicine> findOrderById(Long orderId);
}