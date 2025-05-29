package app.controller;

import app.adapters.MedicineOrder.entity.OrderMedicineEntity;
import app.adapters.clinicalHistories.entity.ClinicalHistoryEntity;
import app.domain.exceptions.BusinessException;
import app.domain.exceptions.InputsException;
import app.domain.exceptions.NotFoundException;
import app.domain.models.MedicineOrder;
import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.VeterinarianServices;
import app.ports.PetPort;
import app.ports.UserPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/veterinaries")
public class VeterinarianController {

    private final UserPort userPort;
    private final PetPort petPort;
    private final VeterinarianServices veterinarianServices;

    public VeterinarianController(UserPort userPort, PetPort petPort, VeterinarianServices veterinarianServices) {
        this.userPort = userPort;
        this.petPort = petPort;
        this.veterinarianServices = veterinarianServices;
    }



    // 🔸 Registrar nueva mascota
    @PostMapping("/registerPets")
    public ResponseEntity<?> registerPets(@RequestBody Pet pet) {
        try {
            petPort.SavePet(pet);
            return ResponseEntity.ok("✅ Mascota registrada correctamente.");
        } catch (InputsException e) {
            return ResponseEntity.status(422).body("⚠️ Entrada inválida: " + e.getMessage());
        } catch (NotFoundException | BusinessException e) {
            return ResponseEntity.status(404).body("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("⛔ Error inesperado: " + e.getMessage());
        }
    }

    // 🔸 Consultar mascota por ID
    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getPetsById(@PathVariable Long id) {
        return petPort.FindByIdPet(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔸 Listar todas las mascotas
    @GetMapping
    public ResponseEntity<List<Pet>> listPets() {
        return ResponseEntity.ok(petPort.PetAll());
    }

    @GetMapping("/by-owner/{cedula}")
    public ResponseEntity<?> listPetsByIdOwner(@PathVariable(required = true) Long cedula) {
        try {
            if (cedula == null || cedula <= 0) {
                return ResponseEntity.badRequest().body("❌ La cédula del dueño no puede estar vacía o inválida.");
            }
            List<Pet> pets = petPort.FindByIdOwner(cedula);
            if (pets.isEmpty()) {
                return ResponseEntity.ok("⚠️ No se encontraron mascotas registradas para esta cédula.");
            }
            return ResponseEntity.ok(pets);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("⛔ Error inesperado: " + e.getMessage());
        }
    }


    // 🔸 Actualizar mascota parcialmente
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartialPet(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates,
            @RequestParam Long cedulaVeterinario
    ) {
        try {
            User vet = userPort.findByCedula(cedulaVeterinario)
                    .orElseThrow(() -> new NotFoundException("❌ Veterinario no encontrado"));

            if (!"veterinario".equalsIgnoreCase(vet.getRole())) {
                return ResponseEntity.badRequest().body("❌ Acceso denegado: solo veterinarios pueden modificar mascotas");
            }

            veterinarianServices.updatePartialPet(id, updates);
            return ResponseEntity.ok("✅ Mascota actualizada con éxito.");
        } catch (InputsException | BusinessException | NotFoundException e) {
            return ResponseEntity.badRequest().body("❌ Error al actualizar mascota: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("⛔ Error inesperado: " + e.getMessage());
        }
    }
    // 📥 Registrar historia clínica
    @PostMapping("/registerClinicalHistory")
    public ResponseEntity<?> registerClinicalHistory(
            @RequestBody ClinicalHistoryEntity history,
            @RequestParam long cedulaVeterinario
    ) {
        try {
            User veterinario = new User();
            veterinario.setCedula(cedulaVeterinario);
            veterinario.setRole("veterinario");

            String respuesta = veterinarianServices.registerClinicalHistory(history, veterinario);
            return ResponseEntity.ok(respuesta);

        } catch (InputsException | BusinessException | NotFoundException e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error inesperado: " + e.getMessage());
        }
    }

    // 💊 Crear orden médica
    @PostMapping("/createMedicalOrder")
    public ResponseEntity<?> createMedicalOrder(
            @RequestBody OrderMedicineEntity orden,
            @RequestParam Long cedulaVeterinario
    ) {
        try {
            User veterinario = new User();
            veterinario.setCedula(cedulaVeterinario);
            veterinario.setRole("veterinario");

            String respuesta = veterinarianServices.createOrder(orden, veterinario);
            return ResponseEntity.ok(respuesta);

        } catch (InputsException | BusinessException | NotFoundException e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error inesperado: " + e.getMessage());
        }
    }



    // ❌ Anular orden médica
    @DeleteMapping("/order-medical/{orderId}")
    public ResponseEntity<?> annulledOrderMedical(
            @PathVariable String orderId,
            @RequestParam long cedulaVeterinario
    ) {
        try {
            User veterinario = new User();
            veterinario.setCedula(cedulaVeterinario);
            veterinario.setRole("veterinario");

            String respuesta = veterinarianServices.cancelOrder(orderId, veterinario);
            return ResponseEntity.ok(respuesta);

        } catch (InputsException | BusinessException | NotFoundException e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error inesperado: " + e.getMessage());
        }
    }

    // 📄 Consultar historia clínica por mascota
    @GetMapping("/history/{petId}")
    public ResponseEntity<?> consultarHistoria(
            @PathVariable Long petId,
            @RequestParam long cedulaVeterinario
    ) {
        try {
            List<ClinicalHistoryEntity> history = veterinarianServices.getHistoryByPetId(petId, cedulaVeterinario);
            return ResponseEntity.ok(history);

        } catch (NotFoundException | InputsException | BusinessException e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error inesperado: " + e.getMessage());
        }
    }

    // 📋 Listar todas las órdenes médicas
    @GetMapping("/orders")
    public ResponseEntity<?> listarOrdenes(@RequestParam long cedulaUsuario) {
        try {
            User user = new User();
            user.setCedula(cedulaUsuario);

            List<MedicineOrder> ordenes = veterinarianServices.getAllMedicalOrders(user);
            return ResponseEntity.ok(ordenes);

        } catch (NotFoundException | InputsException | BusinessException e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error inesperado: " + e.getMessage());
        }
    }
}
