package app.adapters.invoiceOrders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Table(name = "invoice_orders")
@Getter @Setter
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

    public long getIdFacture() {
        return idFacture;
    }

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

    public void setIdFacture(long idFacture) {
        this.idFacture = idFacture;
    }

    public long getIdPet() {
        return idPet;
    }

    public void setIdPet(long idPet) {
        this.idPet = idPet;
    }

    public long getDniOwner() {
        return dniOwner;
    }

    public void setDniOwner(long dniOwner) {
        this.dniOwner = dniOwner;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}