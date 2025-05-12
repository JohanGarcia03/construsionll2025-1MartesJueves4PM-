package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class OrderMedicineValidator extends SimpleValidator {

    public String medicationsValidator(String meds) throws Exception {
        String validated = stringValidator(meds, "medicamentos");
        if (validated.split(",").length > 10) {
            throw new Exception("Máximo 10 medicamentos por orden");
        }
        return validated;
    }

    public long veterinarianDniValidator(String dni) throws Exception {
        long value = longValidator(dni, "DNI de veterinario");
        if (value < 1_000_000) {
            throw new Exception("El DNI debe tener mínimo 7 dígitos");
        }
        return value;
    }

    public void validateOrderDates(String generationDate, String expirationDate) throws Exception {
        if (generationDate.compareTo(expirationDate) > 0) {
            throw new Exception("La fecha de generación no puede ser posterior a la de expiración");
        }
    }
}