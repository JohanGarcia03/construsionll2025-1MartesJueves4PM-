package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor


//Encabezado de facturas

public class InvoiceOrder extends Pet{
    private long idFacture;
    private long idPet;
    private long dniOwner;
    private long idOrder;
    private String productName;
    private float value;
    private int amount;
    private Date date;

    public InvoiceOrder(long idPet, long cedulaOnwer, String name, int agePet, String species, String race, String color, String size, Float weight, long idFacture, long idPet1, long dniOwner, long idOrder, String productName, float value, int amount, Date date) {
        super(idPet, cedulaOnwer, name, agePet, species, race, color, size, weight);
        this.idFacture = idFacture;
        this.idPet = idPet1;
        this.dniOwner = dniOwner;
        this.idOrder = idOrder;
        this.productName = productName;
        this.value = value;
        this.amount = amount;
        this.date = date;
    }

    public long getIdFacture() {
        return idFacture;
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
