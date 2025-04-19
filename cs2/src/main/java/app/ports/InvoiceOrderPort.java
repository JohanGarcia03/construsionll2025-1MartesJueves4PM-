package app.ports;

import app.domain.models.InvoiceOrder;
import java.util.List;
import java.util.Optional;

public interface InvoiceOrderPort {
    InvoiceOrder save(InvoiceOrder invoice);
    Optional<InvoiceOrder> findById(long id);
    List<InvoiceOrder> findByPetId(long petId);
    List<InvoiceOrder> findByOrderId(long orderId);
    List<InvoiceOrder> findAll();
}