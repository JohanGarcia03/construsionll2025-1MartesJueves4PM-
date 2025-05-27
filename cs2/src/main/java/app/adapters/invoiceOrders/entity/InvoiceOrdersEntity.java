package app.adapters.invoiceOrders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "invoice_orders")
public class InvoiceOrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facture")
    private long idFacture;

    @Column(name = "id_pet", nullable = false)
    private long idPet;

    @Column(name = "dni_owner", nullable = false)
    private long dniOwner;

    @Column(name = "id_order")
    private long idOrder;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "value", nullable = false)
    private float value;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "date", nullable = false)
    private Date date;

    public InvoiceOrdersEntity(long idFacture,
                               long idPet, long dniOwner,
                               long idOrder, String productName,
                               float value, int amount, Date date) {

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