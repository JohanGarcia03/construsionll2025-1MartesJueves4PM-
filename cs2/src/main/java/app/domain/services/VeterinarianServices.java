package app.domain.services;

import app.adapters.clinicalHistories.entity.ClinicalHistoryEntity;
import app.adapters.MedicineOrder.entity.OrderMedicineEntity;
import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.models.MedicineOrder;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.ClinicalHistoryPort;
import app.ports.MedicineOrderPort;
import app.ports.PetPort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static app.domain.utils.RoleValidator.validateRole;

@Service
public class VeterinarianServices {

    private final ClinicalHistoryPort clinicalHistoryPort;
    private final MedicineOrderPort medicineOrderPort;
    private final PetPort petPort;

    public VeterinarianServices(
            ClinicalHistoryPort clinicalHistoryPort,
            MedicineOrderPort medicineOrderPort,
            PetPort petPort
    ) {
        this.clinicalHistoryPort = clinicalHistoryPort;
        this.medicineOrderPort = medicineOrderPort;
        this.petPort = petPort;
    }

    // REGISTRAR HISTORIA CLÍNICA
    public String registerClinicalHistory(ClinicalHistoryEntity entity, User veterinarian) throws Exception {
        validateRole(veterinarian, "veterinario");

        Pet pet = petPort.FindByIdPet(entity.getPetId())
                .orElseThrow(() -> new NotFoundException("🐾 Mascota no encontrada con ID: " + entity.getPetId()));

        String species = pet.getSpecies().toLowerCase();

        if (entity.getVaccinations() != null && !entity.getVaccinations().isEmpty()) {
            if (!species.equals("perro") && !species.equals("gato")) {
                throw new BusinessException("❌ Solo perros y gatos pueden registrar historial de vacunación.");
            }
        }

        if (entity.getOrderId() != null && !entity.getOrderId().isBlank()) {
            long parsedId = Long.parseLong(entity.getOrderId());
            medicineOrderPort.findOrderById(parsedId)
                    .orElseThrow(() -> new NotFoundException("❌ Orden médica con ID " + parsedId + " no existe."));
        }

        entity.setVeterinarianId(veterinarian.getCedula());
        entity.setDate(new java.util.Date());
        entity.setOrderAnnulled(false);

        clinicalHistoryPort.save(entity);

        if (entity.getVaccinations() != null) {
            for (String vacuna : entity.getVaccinations()) {
                clinicalHistoryPort.saveVaccination(entity.getId(), vacuna);
            }
        }

        if (entity.getAllergyMedications() != null) {
            for (String alergia : entity.getAllergyMedications()) {
                clinicalHistoryPort.saveAllergy(entity.getId(), alergia);
            }
        }

        return "✅ Historia clínica registrada con éxito ";
    }

    // CREAR ORDEN MÉDICA
    public String createOrder(OrderMedicineEntity order, User veterinarian) throws Exception {
        if (!isVeterinarian(veterinarian)) {
            throw new BusinessException("🔒 Solo un veterinario puede crear una orden médica.");
        }

        petPort.FindByIdPet(order.getPetId())
                .orElseThrow(() -> new NotFoundException("🐾 Mascota no encontrada con ID: " + order.getPetId()));

        order.setDniVeterinarian(veterinarian.getCedula());
        order.setDateGeneration(new Date(System.currentTimeMillis()));
        order.setCancelled(false);

        MedicineOrder domainOrder = new MedicineOrder();
        domainOrder.setPetId(order.getPetId());
        domainOrder.setDniOwner(order.getDniOwner());
        domainOrder.setDniVeterinarian(order.getDniVeterinarian());
        domainOrder.setMedications(order.getMedications());
        domainOrder.setDose(order.getDose());
        domainOrder.setDateGeneration(order.getDateGeneration());
        domainOrder.setCancelled(order.isCancelled());

        MedicineOrder savedOrder = medicineOrderPort.save(domainOrder);

        return "✅ Orden médica creada con ID: " + savedOrder.getIdOrder();
    }

    // ANULAR ORDEN MÉDICA
    public String cancelOrder(String orderId, User veterinarian) throws Exception {
        if (!isVeterinarian(veterinarian)) {
            throw new BusinessException("🔒 Solo un veterinario puede anular una orden médica.");
        }

        Optional<MedicineOrder> optOrder = medicineOrderPort.findOrderById(orderId);

        if (optOrder.isEmpty()) {
            throw new NotFoundException("⚠️ Orden médica no encontrada con ID: " + orderId);
        }

        MedicineOrder order = optOrder.get();
        if (order.isCancelled()) {
            return "⚠️ La orden ya está anulada.";
        }

        order.setCancelled(true);
        medicineOrderPort.update(order);

        return "✅ Orden médica anulada correctamente.";
    }

    // CONSULTAR HISTORIA CLÍNICA
    public List<ClinicalHistoryEntity> getHistoryByPetId(Long petId, long cedulaVeterinario) throws Exception {
        User vet = new User();
        vet.setCedula(cedulaVeterinario);
        vet.setRole("veterinario");
        validateRole(vet, "veterinario");

        petPort.FindByIdPet(petId)
                .orElseThrow(() -> new NotFoundException("🐾 Mascota no encontrada con ID: " + petId));

        return clinicalHistoryPort.findByPetId(petId);
    }

    public List<MedicineOrder> getAllMedicalOrders(User user) throws Exception {
        if (!user.isVeterinarian() && !user.isSeller()) {
            throw new BusinessException("❌ Solo veterinarios o vendedores pueden consultar órdenes médicas.");
        }
        return medicineOrderPort.findAllOrders();
    }

    private boolean isVeterinarian(User user) {
        return user.getRole() != null && user.getRole().equalsIgnoreCase("veterinario");
    }

    public void updatePartialPet(long id, Map<String, Object> updates) throws Exception {
        Pet pet = petPort.FindByIdPet(id)
                .orElseThrow(() -> new NotFoundException("🐾 Mascota no encontrada con ID: " + id));

        if (updates.containsKey("name")) {
            pet.setName(updates.get("name").toString());
        }
        if (updates.containsKey("color")) {
            pet.setColor(updates.get("color").toString());
        }
        if (updates.containsKey("weight")) {
            pet.setWeight(Float.parseFloat(updates.get("weight").toString()));
        }

        petPort.SavePet(pet);
    }
}
