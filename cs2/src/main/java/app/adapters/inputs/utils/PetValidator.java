package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class PetValidator extends SimpleValidator {

    public String nameValidator(String name) throws Exception {
        String validated = stringValidator(name, "nombre de mascota");
        if (validated.length() > 100) {
            throw new Exception("El nombre no puede exceder 100 caracteres");
        }
        return validated;
    }

    public int ageValidator(String age) throws Exception {
        try {
            int ageValue = Integer.parseInt(stringValidator(age, "edad"));
            if (ageValue < 0 || ageValue > 30) {
                throw new Exception("La edad debe estar entre 0 y 30 años");
            }
            return ageValue;
        } catch (NumberFormatException e) {
            throw new Exception("La edad debe ser un número entero");
        }
    }

    public String speciesValidator(String species) throws Exception {
        String validated = stringValidator(species, "especie");
        if (!validated.matches("^[a-zA-ZáéíóúñÁÉÍÓÚÑ\\s-]+$")) {
            throw new Exception("La especie solo puede contener letras y guiones");
        }
        return validated;
    }

    public Float weightValidator(String weight) throws Exception {
        try {
            Float weightValue = Float.parseFloat(stringValidator(weight, "peso"));
            if (weightValue <= 0 || weightValue > 200) {
                throw new Exception("El peso debe estar entre 0.1 y 200 kg");
            }
            return weightValue;
        } catch (NumberFormatException e) {
            throw new Exception("El peso debe ser un número decimal válido");
        }
    }
}