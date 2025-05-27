package app.domain.services;

import app.adapters.clinicalHistories.entity.ClinicalHistoryEntity;
import app.adapters.MedicineOrder.entity.OrderMedicineEntity;
import app.domain.models.OrderMedicine;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.ClinicalHistoryPort;
import app.ports.MedicineOrderPort;
import app.ports.PetPort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
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
                .orElseThrow(() -> new Exception("🐾 Mascota no encontrada con ID: " + entity.getPetId()));

        String species = pet.getSpecies().toLowerCase();

        // Validar vacunas ANTES de guardar
        if (entity.getVaccinations() != null && !entity.getVaccinations().isEmpty()) {
            if (!species.equals("perro") && !species.equals("gato")) {
                throw new Exception("❌ Solo perros y gatos pueden registrar historial de vacunación.");
            }
        }

        // Validar orden médica si existe
        if (entity.getOrderId() != null && !entity.getOrderId().isBlank()) {
            long parsedId = Long.parseLong(entity.getOrderId());
            medicineOrderPort.findOrderById(parsedId)
                    .orElseThrow(() -> new Exception("❌ Orden médica con ID " + parsedId + " no existe."));
        }

        // Set campos base
        entity.setVeterinarianId(veterinarian.getCedula());
        entity.setDate(new java.util.Date());
        entity.setOrderAnnulled(false);

        clinicalHistoryPort.save(entity);

        // Guardar vacunaciones
        if (entity.getVaccinations() != null) {
            for (String vacuna : entity.getVaccinations()) {
                clinicalHistoryPort.saveVaccination(entity.getId(), vacuna);
            }
        }

        // Guardar alergias
        if (entity.getAllergyMedications() != null) {
            for (String alergia : entity.getAllergyMedications()) {
                clinicalHistoryPort.saveAllergy(entity.getId(), alergia);
            }
        }

        return "✅ Historia clínica registrada con éxito (validación aplicada)";
    }

    // 💊 CREAR ORDEN MÉDICA
    public String createOrder(OrderMedicineEntity order, User veterinarian) throws Exception {
        if (!isVeterinarian(veterinarian)) {
            throw new Exception("🔒 Solo un veterinario puede crear una orden médica.");
        }

        petPort.FindByIdPet(order.getPetId())

                .orElseThrow(() -> new Exception("🐾 Mascota no encontrada con ID: " + order.getPetId()));

        order.setDniVeterinarian(veterinarian.getCedula());
        order.setDateGeneration(new Date(System.currentTimeMillis()));
        order.setCancelled(false);

        // Convertimos a modelo sin ID manual
        OrderMedicine domainOrder = new OrderMedicine();
        domainOrder.setPetId(order.getPetId());
        domainOrder.setDniOwner(order.getDniOwner());
        domainOrder.setDniVeterinarian(order.getDniVeterinarian());
        domainOrder.setMedications(order.getMedications());
        domainOrder.setDose(order.getDose());
        domainOrder.setDateGeneration(order.getDateGeneration());
        domainOrder.setCancelled(order.isCancelled());

        OrderMedicine savedOrder = medicineOrderPort.save(domainOrder);

        return "✅ Orden médica creada con ID: " + savedOrder.getIdOrder();
    }

    // ❌ ANULAR ORDEN MÉDICA
    public String cancelOrder(String orderId, User veterinarian) throws Exception {
        if (!isVeterinarian(veterinarian)) {
            throw new Exception("🔒 Solo un veterinario puede anular una orden médica.");
        }

        Optional<OrderMedicine> optOrder = medicineOrderPort.findOrderById(orderId);

        if (optOrder.isEmpty()) {
            throw new Exception("⚠️ Orden médica no encontrada con ID: " + orderId);
        }

        OrderMedicine order = optOrder.get();
        if (order.isCancelled()) {
            return "⚠️ La orden ya está anulada.";
        }

        order.setCancelled(true);
        medicineOrderPort.update(order); // Guardar la orden como anulada

        return "✅ Orden médica anulada correctamente.";
    }
    // 📍 Consulta historia clínica por ID de mascota
    public List<ClinicalHistoryEntity> getHistoryByPetId(Long petId, long cedulaVeterinario) throws Exception {
        // 1. Validar que quien consulta sea veterinario
        User vet = new User();
        vet.setCedula(cedulaVeterinario);
        vet.setRole("veterinario");
        validateRole(vet, "veterinario");

        // 2. Verificar que la mascota existe
        petPort.FindByIdPet(petId)
                .orElseThrow(() -> new Exception("🐾 Mascota no encontrada con ID: " + petId));

        // 3. Buscar historias clínicas por ID de mascota
        return clinicalHistoryPort.findByPetId(petId);
    }

    public List<OrderMedicine> getAllMedicalOrders(User user) throws Exception {
        if (!user.isVeterinarian() && !user.isSeller()) {
            throw new Exception("❌ Solo veterinarios o vendedores pueden consultar órdenes médicas.");
        }
        return medicineOrderPort.findAllOrders();
    }

    // 🛡️ Validación de rol
    private boolean isVeterinarian(User user) {
        return user.getRole() != null && user.getRole().equalsIgnoreCase("veterinario");
    }
}
