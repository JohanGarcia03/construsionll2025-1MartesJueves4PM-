package app.controller;
import app.adapters.clinicalHistories.entity.ClinicalHistoryEntity;
import app.adapters.MedicineOrder.entity.OrderMedicineEntity;
import app.domain.models.OrderMedicine;
import app.domain.models.User;
import app.domain.services.VeterinarianServices;
import app.ports.UserPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/veterinaries")
public class VeterinarianController {

    private final UserPort userPort;
    private final VeterinarianServices veterinarianServices;

    public VeterinarianController(UserPort userPort, VeterinarianServices veterinarianServices) {
        this.userPort = userPort;
        this.veterinarianServices = veterinarianServices;
    }

    // 📥 Registrar historia clínica
    @PostMapping("/registerClinicalHistory")
    public ResponseEntity<?> RegisterClinicalHistoryPet(
            @RequestBody ClinicalHistoryEntity historia,
            @RequestParam long cedulaVeterinario
    ) {
        try {
            User veterinario = new User();
            veterinario.setCedula(cedulaVeterinario);
            veterinario.setRole("veterinario"); // Validación mínima por ahora

            String respuesta = veterinarianServices.registerClinicalHistory(historia, veterinario);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }

    // 📥 Crear orden médica
    @PostMapping("/createOrderMedical")
    public ResponseEntity<?> CreateOrderMedical(
            @RequestBody OrderMedicineEntity orden,
            @RequestParam long cedulaVeterinario
    ) {
        try {
            User veterinario = new User();
            veterinario.setCedula(cedulaVeterinario);
            veterinario.setRole("veterinario");

            String respuesta = veterinarianServices.createOrder(orden, veterinario);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }

    // ❌ Anular orden médica
    @DeleteMapping("/order-medical/{orderId}")
    public ResponseEntity<String> annulledOrderMedical(
            @PathVariable String orderId,
            @RequestParam long cedulaVeterinario
    ) {
        try {
            User veterinario = new User();
            veterinario.setCedula(cedulaVeterinario);
            veterinario.setRole("veterinario");

            String respuesta = veterinarianServices.cancelOrder(orderId, veterinario);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }
    @GetMapping("/history/{petId}")
    public ResponseEntity<?> consultarHistoria(
            @PathVariable Long petId,
            @RequestParam long cedulaVeterinario
    ) {
        try {
            List<ClinicalHistoryEntity> History = veterinarianServices.getHistoryByPetId(petId, cedulaVeterinario);
            return ResponseEntity.ok(History);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }
    //LISTA ORDENES
    @GetMapping("/ListInvoiceOrders")
    public ResponseEntity<?>ListInvoiceOrders(@RequestParam long cedulaVeterinario) {
        try {
            User user = userPort.findByCedula(cedulaVeterinario)
                    .orElseThrow(() -> new Exception("❌ Usuario no encontrado"));

            List<OrderMedicine> orders = veterinarianServices.getAllMedicalOrders(user);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }

}
