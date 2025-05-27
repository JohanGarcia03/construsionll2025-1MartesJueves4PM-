package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Setter
@Getter

//Encabezado de facturas
public class InvoiceOrder {
    private long idFacture;
    private long idPet;
    private long dniOwner;
    private Long idOrder;
    private String productName;
    private float value;
    private int amount;
    private Date date;

    public InvoiceOrder() {
    }

}