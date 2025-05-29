package app.domain.services;

import app.domain.exceptions.NotFoundException;
import app.domain.models.Person;
import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.UserPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Service
public class AdminService {
    private PersonPort personPort;
    private UserPort userPort;

    @Autowired
    public AdminService(PersonPort personPort, UserPort userPort) {
        this.personPort = personPort;
        this.userPort = userPort;
    }

    public void registerVeterinarian(User user) throws Exception {
        // 🚫 Validaciones básicas
        if (user.getCedula() <= 0) {
            throw new NotFoundException("❌ La cédula no puede estar vacía o ser cero.");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new NotFoundException("❌ El nombre de usuario no puede estar vacío.");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new NotFoundException("❌ La contraseña no puede estar vacía.");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new NotFoundException("❌ El nombre no puede estar vacío.");
        }
        if (user.getAge() <= 0) {
            throw new NotFoundException("❌ La edad debe ser mayor a 0.");
        }

        // 🚫 Validaciones lógicas
        if (!personPort.existsByCedula(user.getCedula())) {
            throw new NotFoundException("❌ No existe una persona registrada con la cédula: " + user.getCedula());
        }

        if (userPort.findByCedula(user.getCedula()).isPresent()) {
            throw new NotFoundException("❌ Ya existe un usuario con cédula: " + user.getCedula());
        }

        if (userPort.existUserName(user.getUsername())) {
            throw new NotFoundException("❌ El nombre de usuario '" + user.getUsername() + "' ya está en uso.");
        }

        // ✅ Guardar usuario y persona
        user.setRole("veterinario");
        userPort.save(user);
    }

    public void registerSeller(User user) throws Exception {
        if (user.getCedula() <= 0) {
            throw new NotFoundException("❌ La cédula no puede estar vacía o ser cero.");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new NotFoundException("❌ El nombre de usuario no puede estar vacío.");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new NotFoundException("❌ La contraseña no puede estar vacía.");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new NotFoundException("❌ El nombre no puede estar vacío.");
        }
        if (user.getAge() <= 0) {
            throw new NotFoundException("❌ La edad debe ser mayor a 0.");
        }

        if (userPort.findByCedula(user.getCedula()).isPresent()) {
            throw new NotFoundException("❌ Ya existe un usuario con cédula: " + user.getCedula());
        }

        if (userPort.existUserName(user.getUsername())) {
            throw new NotFoundException("❌ El nombre de usuario '" + user.getUsername() + "' ya está en uso.");
        }

        // Si no existe persona, la crea
        if (!personPort.existsByCedula(user.getCedula())) {
            Person persona = new Person();
            persona.setCedula(user.getCedula());
            persona.setName(user.getName());
            persona.setAge(user.getAge());
            personPort.savePerson(persona);
        }

        Person personaGuardada = personPort.findByCedula(user.getCedula())
                .orElseThrow(() -> new NotFoundException("❌ Error al recuperar persona guardada."));

        user.setPerson(personaGuardada);
        user.setRole("vendedor");

        userPort.save(user);
    }

    public void updatePartialUser(Long cedula, Map<String, Object> updates) throws Exception {
        User user = userPort.findByCedula(cedula)
                .orElseThrow(() -> new Exception("Usuario no encontrado con cédula: " + cedula));

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            switch (entry.getKey()) {
                case "username":
                    user.setUsername(entry.getValue().toString());
                    break;
                case "password":
                    user.setPassword(entry.getValue().toString());
                    break;
                case "edad":
                    user.setAge(Integer.parseInt(entry.getValue().toString()));
                    break;
                // casos evitables
                case "cedula":
                case "role":
                    throw new Exception("❌ No se puede modificar la cédula ni el rol");
            }
        }

        userPort.save(user);
    }

}




