package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Setter
@Getter
@NoArgsConstructor
public class invoiceOrder {
    private String idFacture;
    private String idPet;
    private String dniOwner;
    private String idOrder;
    private String productName;
    private float value;
    private int amount;
    private Date date;

    public invoiceOrder(String idFacture, String idPet, String dniOwner, String idOrder, String productName, float value, int amount, Date date) {
        this.idFacture = idFacture;
        this.idPet = idPet;
        this.dniOwner = dniOwner;
        this.idOrder = idOrder;
        this.productName = productName;
        this.value = value;
        this.amount = amount;
        this.date = date;
    }
}
