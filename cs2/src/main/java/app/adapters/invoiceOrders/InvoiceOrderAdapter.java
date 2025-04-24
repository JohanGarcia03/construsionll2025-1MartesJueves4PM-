package app.adapters.invoiceOrders;

import app.adapters.invoiceOrders.entity.InvoiceOrdersEntity;
import app.adapters.invoiceOrders.repository.InvoiceOrdersRepository;
import app.domain.models.InvoiceOrder;
import app.ports.InvoiceOrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceOrderAdapter implements InvoiceOrderPort {

    private final InvoiceOrdersRepository invoiceOrdersRepository;

    @Autowired
    public InvoiceOrderAdapter(InvoiceOrdersRepository invoiceOrdersRepository) {
        this.invoiceOrdersRepository = invoiceOrdersRepository;
    }

    @Override
    public InvoiceOrder save(InvoiceOrder invoice) {
        InvoiceOrdersEntity entity = convertToEntity(invoice);
        InvoiceOrdersEntity savedEntity = invoiceOrdersRepository.save(entity);
        return convertToDomain(savedEntity);
    }

    @Override
    public Optional<InvoiceOrder> findById(long id) {
        return invoiceOrdersRepository.findByIdFacture(id)
                .map(this::convertToDomain);
    }

    @Override
    public List<InvoiceOrder> findByPetId(long petId) {
        return invoiceOrdersRepository.findByIdPet(petId).stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceOrder> findByOrderId(long orderId) {
        return invoiceOrdersRepository.findByIdOrder(orderId).stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceOrder> findAll() {
        return invoiceOrdersRepository.findAll().stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    public List<InvoiceOrder> findByOwnerId(long ownerId) {
        return invoiceOrdersRepository.findByDniOwner(ownerId).stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    private InvoiceOrdersEntity convertToEntity(InvoiceOrder domain) {
        return new InvoiceOrdersEntity(
                domain.getIdFacture(),
                domain.getIdPet(),
                domain.getDniOwner(),
                domain.getIdOrder(),
                domain.getProductName(),
                domain.getValue(),
                domain.getAmount(),
                domain.getDate()
        );
    }

    private InvoiceOrder convertToDomain(InvoiceOrdersEntity entity) {
        InvoiceOrder domain = new InvoiceOrder();
        domain.setIdFacture(entity.getIdFacture());
        domain.setIdPet(entity.getIdPet());
        domain.setDniOwner(entity.getDniOwner());
        domain.setIdOrder(entity.getIdOrder());
        domain.setProductName(entity.getProductName());
        domain.setValue(entity.getValue());
        domain.setAmount(entity.getAmount());
        domain.setDate(entity.getDate());
        return domain;
    }
}