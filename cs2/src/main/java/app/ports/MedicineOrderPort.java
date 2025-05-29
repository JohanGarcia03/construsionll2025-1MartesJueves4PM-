package app.ports;

import app.domain.models.MedicineOrder;

import java.util.List;
import java.util.Optional;

public interface MedicineOrderPort {
    MedicineOrder save(MedicineOrder order);

    Optional<MedicineOrder> findOrderById(long idOrder); // 👈 aquí

    MedicineOrder update(MedicineOrder order);
    List<MedicineOrder> findAllOrders();

    Optional<MedicineOrder> findOrderById(String idOrder);
}
