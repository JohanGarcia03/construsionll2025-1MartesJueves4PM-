package app.adapters.invoiceOrders.repository;

import app.adapters.invoiceOrders.entity.InvoiceOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceOrdersRepository extends JpaRepository<InvoiceOrdersEntity, Long> {
    Optional<InvoiceOrdersEntity> findByIdFacture(long idFacture);
    List<InvoiceOrdersEntity> findByIdPet(long idPet);
    List<InvoiceOrdersEntity> findByIdOrder(long idOrder);
    List<InvoiceOrdersEntity> findByDniOwner(long dniOwner);
    List<InvoiceOrdersEntity> findAll();
}