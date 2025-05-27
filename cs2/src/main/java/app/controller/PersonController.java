package app.controller;

import app.domain.models.Person;
import app.ports.PersonPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("personApiController")
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonPort personPort;

    public PersonController(PersonPort personPort) {
        this.personPort = personPort;
    }

    // 🔸 Registrar nueva persona
    @PostMapping
    public ResponseEntity<?> registrarPersona(@RequestBody Person persona) {
        try {
            personPort.savePerson(persona);
            return ResponseEntity.ok("✅ Persona registrada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al registrar persona: " + e.getMessage());
        }
    }

    // 🔸 Obtener persona por cédula
    @GetMapping("/{DNIPerson}")
    public ResponseEntity<?> GetIdByPerson(@PathVariable Long cedula) {
        return personPort.findByCedula(cedula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔸 Listar todas las personas
    @GetMapping
    public ResponseEntity<List<Person>> ListPerson() {
        return ResponseEntity.ok(personPort.getAll());
    }
}
