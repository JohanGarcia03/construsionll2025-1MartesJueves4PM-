
package app.controller;
import app.adapters.users.entity.UserEntity;
import app.domain.models.User;
import app.domain.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            UserEntity userAuth = authService.login(user);
            return ResponseEntity.ok("✅ Login exitoso: " + userAuth.getUserName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error: " + e.getMessage());
        }
    }

}
