package app.controller;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.InputsException;
import app.domain.exceptions.NotFoundException;
import app.domain.models.Person;
import app.domain.models.User;
import app.domain.services.AdminService;
import app.ports.PersonPort;
import app.ports.UserPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final PersonPort personPort;
    private final AdminService adminService;
    private final UserPort userPort;

    public AdminController(PersonPort personPort, AdminService adminService, UserPort userPort) {
        this.personPort = personPort;
        this.adminService = adminService;
        this.userPort = userPort;
    }

    // 👨‍⚕️ Registrar Veterinario
    @PostMapping("/registerVeterinarian")
    public ResponseEntity<?> registerVeterinarian(@RequestBody User user) {
        try {
            adminService.registerVeterinarian(user);
            return ResponseEntity.ok("✅ Veterinario registrado correctamente.");
        } catch (BusinessException | NotFoundException e) {
            return ResponseEntity.badRequest().body("❌ Error al registrar veterinario: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("⛔ Error inesperado: " + e.getMessage());
        }
    }

    // 🧑‍💼 Registrar Vendedor
    @PostMapping("/registerSeller")
    public ResponseEntity<?> registerSeller(@RequestBody User user) {
        try {
            adminService.registerSeller(user);
            return ResponseEntity.ok("✅ Vendedor registrado correctamente.");
        } catch (BusinessException | NotFoundException e) {
            return ResponseEntity.badRequest().body("❌ Error al registrar vendedor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("⛔ Error inesperado: " + e.getMessage());
        }
    }

    // 🔸 Registrar usuario directo
    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userPort.save(user);
            return ResponseEntity.ok("✅ Usuario registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("⛔ Error inesperado: " + e.getMessage());
        }
    }

    // 🔍 Obtener usuario por cédula
    @GetMapping("/users/{cedula}")
    public ResponseEntity<?> getUserByCedula(@PathVariable Long cedula) {
        return userPort.findByCedula(cedula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🛠️ Actualizar usuario parcialmente
    @PatchMapping("/users/{cedula}")
    public ResponseEntity<?> updatePartialUser(
            @PathVariable Long cedula,
            @RequestBody Map<String, Object> updates
    ) {
        try {
            adminService.updatePartialUser(cedula, updates);
            return ResponseEntity.ok("✅ Usuario actualizado parcialmente.");
        } catch (InputsException | BusinessException | NotFoundException e) {
            return ResponseEntity.badRequest().body("❌ Error al actualizar usuario: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("⛔ Error inesperado: " + e.getMessage());
        }
    }

    // 👤 Registrar nueva persona
    @PostMapping("/registerPerson")
    public ResponseEntity<?> registerPerson(@RequestBody Person persona) {
        try {
            personPort.savePerson(persona);
            return ResponseEntity.ok("✅ Persona registrada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al registrar persona: " + e.getMessage());
        }
    }

    // 🔍 Obtener persona por cédula
    @GetMapping("/persons/{cedula}")
    public ResponseEntity<?> getPersonByCedula(@PathVariable Long cedula) {
        return personPort.findByCedula(cedula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 📋 Listar personas
    @GetMapping("/persons")
    public ResponseEntity<List<Person>> listPersons() {
        return ResponseEntity.ok(personPort.getAll());
    }

    // 📋 Listar usuarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userPort.findAll());
    }

}
