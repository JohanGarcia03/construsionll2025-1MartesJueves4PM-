package app.controller;

import app.domain.models.Pet;
import app.ports.PetPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetPort petPort;

    public PetController(PetPort petPort) {
        this.petPort = petPort;
    }

    // 🔸 Registrar nueva mascota
    @PostMapping("/registerPets")
    public ResponseEntity<?> registerPets(@RequestBody Pet pet) {
        try {
            petPort.SavePet(pet);
            return ResponseEntity.ok("✅ Mascota registrada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al registrar la mascota: " + e.getMessage());
        }
    }

    // 🔸 Consultar mascota por ID
    @GetMapping("/{SearchPetsById}")
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

    // 🔸 Listar mascotas por ID del dueño
    @GetMapping("/Owner/{idOwner}")
    public ResponseEntity<List<Pet>> listPetsByIdOwner(@PathVariable Long idDueno) {
        return ResponseEntity.ok(petPort.FindByIdOwner(idDueno));
    }

}
