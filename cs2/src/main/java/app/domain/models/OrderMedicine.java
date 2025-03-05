package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@NoArgsConstructor
@Setter
public class OrderMedicine {
    private String idOrder;
    private String dniOwner;
    private String dniVeterinarian;
    private String medications;
    private String dose;
    private Date dateGeneration;
    private boolean voidedOrder;

    public OrderMedicine(String idOrder, String dniOwner, String dniVeterinarian, String medications, String dose, Date dateGeneration, boolean voidedOrder) {
        this.idOrder = idOrder;
        this.dniOwner = dniOwner;
        this.dniVeterinarian = dniVeterinarian;
        this.medications = medications;
        this.dose = dose;
        this.dateGeneration = dateGeneration;
        this.voidedOrder = voidedOrder;
    }
}
