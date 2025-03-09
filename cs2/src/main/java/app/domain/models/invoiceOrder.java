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

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public String getIdPet() {
        return idPet;
    }

    public void setIdPet(String idPet) {
        this.idPet = idPet;
    }

    public String getDniOwner() {
        return dniOwner;
    }

    public void setDniOwner(String dniOwner) {
        this.dniOwner = dniOwner;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
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
