package app.adapters.orderMedicines;

import app.adapters.orderMedicines.entity.OrderMedicineEntity;
import app.adapters.orderMedicines.repository.OrderMedicineRepository;
import app.domain.models.ClinicalHistory;
import app.domain.models.OrderMedicine;
import app.ports.MedicineOrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderMedicineAdapter implements MedicineOrderPort {

    private final OrderMedicineRepository orderRepository;

    @Autowired
    public OrderMedicineAdapter(OrderMedicineRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderMedicine save(OrderMedicine order) {
        OrderMedicineEntity entity = convertToEntity(order);
        OrderMedicineEntity savedEntity = orderRepository.save(entity);
        return convertToDomain(savedEntity);
    }

    @Override
    public Optional<OrderMedicine> findOrderById(long id) {
        return orderRepository.findByOrderId(id)
                .map(this::convertToDomain);
    }

    @Override
    public List<OrderMedicine> findOrdersByVeterinarian(long vetId) {
        return orderRepository.findByDniVeterinarian(vetId).stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClinicalHistory> findByPetId(long petId) {
        // Implementación depende de tu ClinicalHistoryAdapter
        throw new UnsupportedOperationException("Implementar mediante ClinicalHistoryAdapter");
    }

    @Override
    public List<ClinicalHistory> findByDateRange(java.util.Date startDate, java.util.Date endDate) {
        return List.of();
    }


    @Override
    public List<ClinicalHistory> findByDateRange(Date startDate, Date endDate) {
        // Implementación depende de tu ClinicalHistoryAdapter
        throw new UnsupportedOperationException("Implementar mediante ClinicalHistoryAdapter");
    }

    // Métodos adicionales útiles
    public void cancelOrder(long orderId, String reason) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setVoidedOrder(true);
            order.setCancellationReason(reason);
            orderRepository.save(order);
        });
    }

    public List<OrderMedicine> findActiveOrders() {
        return orderRepository.findByVoidedOrder(false).stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    private OrderMedicineEntity convertToEntity(OrderMedicine domain) {
        OrderMedicineEntity entity = new OrderMedicineEntity();
        entity.setIdOrder(domain.getIdOrder());
        entity.setPetId(domain.getPetId());
        entity.setDniOwner(domain.getDniOwner());
        entity.setDniVeterinarian(domain.getDniVeterinarian());
        entity.setMedications(domain.getMedications());
        entity.setDose(domain.getDose());
        entity.setDateGeneration(domain.getDateGeneration());
        entity.setVoidedOrder(domain.isVoidedOrder());
        return entity;
    }

    private OrderMedicine convertToDomain(OrderMedicineEntity entity) {
        OrderMedicine domain = new OrderMedicine();
        domain.setIdOrder(entity.getIdOrder());
        domain.setPetId(entity.getPetId());
        domain.setDniOwner(entity.getDniOwner());
        domain.setDniVeterinarian(entity.getDniVeterinarian());
        domain.setMedications(entity.getMedications());
        domain.setDose(entity.getDose());
        domain.setDateGeneration(entity.getDateGeneration());
        domain.setVoidedOrder(entity.isVoidedOrder());
        return domain;
    }
}