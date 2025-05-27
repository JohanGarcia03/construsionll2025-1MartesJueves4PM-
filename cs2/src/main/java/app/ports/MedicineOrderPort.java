package app.ports;

import app.domain.models.OrderMedicine;

import java.util.List;
import java.util.Optional;

public interface MedicineOrderPort {
    OrderMedicine save(OrderMedicine order);

    Optional<OrderMedicine> findOrderById(long idOrder); // 👈 aquí

    OrderMedicine update(OrderMedicine order);
    List<OrderMedicine> findAllOrders();

    Optional<OrderMedicine> findOrderById(String idOrder);
}
