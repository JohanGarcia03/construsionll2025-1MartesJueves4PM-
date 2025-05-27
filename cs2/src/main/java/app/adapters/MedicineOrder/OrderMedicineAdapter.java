package app.adapters.MedicineOrder;

import app.adapters.MedicineOrder.entity.OrderMedicineEntity;
import app.adapters.MedicineOrder.repository.OrderMedicineRepository;
import app.domain.models.OrderMedicine;
import app.ports.MedicineOrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderMedicineAdapter implements MedicineOrderPort {

    @Autowired
    private OrderMedicineRepository repository;

    @Override
    public OrderMedicine save(OrderMedicine order) {
        OrderMedicineEntity entity = toEntity(order);
        OrderMedicineEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<OrderMedicine> findAllOrders() {
        return repository.findAll()
                .stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<OrderMedicine> findOrderById(long idOrder) {
        return repository.findById(idOrder).map(this::toDomain);
    }

    @Override
    public OrderMedicine update(OrderMedicine order) {
        return save(order);
    }

    @Override
    public Optional<OrderMedicine> findOrderById(String idOrder) {
        return repository.findById(Long.valueOf(idOrder)).map(this::toDomain);
    }

    // === Conversión ===
    private OrderMedicine toDomain(OrderMedicineEntity entity) {
        OrderMedicine domain = new OrderMedicine();
        domain.setIdOrder(entity.getOrderId());
        domain.setPetId(entity.getPetId());
        domain.setDniOwner(entity.getDniOwner());
        domain.setDniVeterinarian(entity.getDniVeterinarian());
        domain.setMedications(entity.getMedications());
        domain.setDose(entity.getDose());
        domain.setDateGeneration(entity.getDateGeneration());
        domain.setCancelled(entity.isCancelled());
        return domain;
    }

    private OrderMedicineEntity toEntity(OrderMedicine domain) {
        OrderMedicineEntity entity = new OrderMedicineEntity();

        if (domain.getIdOrder() != null) {
            entity.setOrderId(domain.getIdOrder());
        }

        entity.setPetId(domain.getPetId());
        entity.setDniOwner(domain.getDniOwner());
        entity.setDniVeterinarian(domain.getDniVeterinarian());
        entity.setMedications(domain.getMedications());
        entity.setDose(domain.getDose());
        entity.setDateGeneration(domain.getDateGeneration());
        entity.setCancelled(domain.isCancelled());

        return entity;
    }

}