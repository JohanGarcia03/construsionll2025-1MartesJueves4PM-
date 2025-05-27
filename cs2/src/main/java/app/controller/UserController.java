package app.controller;

import app.domain.models.User;
import app.ports.UserPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserPort userPort;

    public UserController(UserPort userPort) {
        this.userPort = userPort;
    }

    // 🔸 Registrar nuevo usuario

    @PostMapping("/RegisterUser")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userPort.save(user);
            return ResponseEntity.ok("✅ Usuario registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al registrar usuario: " + e.getMessage());
        }
    }

    // 🔸 Consultar todos los usuarios
    @GetMapping("/AllUsers")
    public ResponseEntity<List<User>> getAllByUsers() {
        return ResponseEntity.ok(userPort.findAll());
    }

    // 🔸 Buscar usuario por cédula
    @GetMapping("/{SearchUserByID}")
    public ResponseEntity<?> getAllUserByIdOwner(@PathVariable Long cedula) {
        return userPort.findById(cedula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
