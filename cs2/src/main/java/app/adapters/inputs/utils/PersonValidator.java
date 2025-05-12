package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class PersonValidator extends SimpleValidator {

    public long cedulaValidator(String cedula) throws Exception {
        long value = longValidator(cedula, "cédula");
        if (value < 1_000_000 || value > 9_999_999_999L) {
            throw new Exception("La cédula debe tener entre 7 y 10 dígitos");
        }
        return value;
    }

    public String nameValidator(String name) throws Exception {
        String validated = stringValidator(name, "nombre");
        if (validated.length() < 2 || validated.length() > 100) {
            throw new Exception("El nombre debe tener entre 2 y 100 caracteres");
        }
        return validated;
    }

    public int ageValidator(String age) throws Exception {
        try {
            int ageValue = Integer.parseInt(stringValidator(age, "edad"));
            if (ageValue < 0 || ageValue > 120) {
                throw new Exception("La edad debe estar entre 0 y 120 años");
            }
            return ageValue;
        } catch (NumberFormatException e) {
            throw new Exception("La edad debe ser un número entero");
        }
    }
}