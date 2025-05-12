package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class InvoiceValidator extends SimpleValidator {

    public float valueValidator(String value) throws Exception {
        try {
            float val = Float.parseFloat(stringValidator(value, "valor"));
            if (val <= 0) {
                throw new Exception("El valor debe ser mayor a 0");
            }
            return val;
        } catch (NumberFormatException e) {
            throw new Exception("El valor debe ser un número decimal válido");
        }
    }

    public int amountValidator(String amount) throws Exception {
        try {
            int amt = Integer.parseInt(stringValidator(amount, "cantidad"));
            if (amt <= 0 || amt > 1000) {
                throw new Exception("La cantidad debe estar entre 1 y 1000");
            }
            return amt;
        } catch (NumberFormatException e) {
            throw new Exception("La cantidad debe ser un número entero");
        }
    }

    public String productNameValidator(String name) throws Exception {
        String validated = stringValidator(name, "nombre de producto");
        if (validated.length() > 150) {
            throw new Exception("El nombre no puede exceder 150 caracteres");
        }
        return validated;
    }
}