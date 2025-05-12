package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class UserValidator extends SimpleValidator {

    public String usernameValidator(String username) throws Exception {
        String validated = stringValidator(username, "nombre de usuario");
        if (validated.length() < 4 || validated.length() > 20) {
            throw new Exception("El usuario debe tener entre 4 y 20 caracteres");
        }
        if (!validated.matches("^[a-zA-Z0-9_]+$")) {
            throw new Exception("Solo se permiten letras, números y guiones bajos");
        }
        return validated;
    }

    public String passwordValidator(String password) throws Exception {
        String validated = stringValidator(password, "contraseña");
        if (validated.length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres");
        }
        return validated;
    }

    public String roleValidator(String role) throws Exception {
        String validated = stringValidator(role, "rol");
        if (!validated.matches("^(VETERINARIAN|SELLER|ADMIN)$")) {
            throw new Exception("Rol inválido. Debe ser VETERINARIAN, SELLER o ADMIN");
        }
        return validated;
    }
}