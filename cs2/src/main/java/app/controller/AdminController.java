package app.controller;

import app.domain.models.User;
import app.domain.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    //REGISTRAR VETERINARIOS
    @PostMapping("/veterinarios")
    public ResponseEntity<String> RegisterVeterinarian(@RequestBody User user) {
        try {
            adminService.registerVeterinarian(user);
            return ResponseEntity.ok("✅ Veterinario registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al registrar veterinario: " + e.getMessage());
        }
    }
//REGISTRAR VENDEDORES
    @PostMapping("/vendedores")
    public ResponseEntity<String> RegisterSeller(@RequestBody User user) {
        try {
            adminService.registerSeller(user);
            return ResponseEntity.ok("✅ Vendedor registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al registrar vendedor: " + e.getMessage());
        }
    }


}
