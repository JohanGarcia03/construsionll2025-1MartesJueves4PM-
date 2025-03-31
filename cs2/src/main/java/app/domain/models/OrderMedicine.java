package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@NoArgsConstructor
@Setter
public class OrderMedicine {
    private long idOrder;
    private long petId;
    private long dniOwner;
    private String dniVeterinarian;
    private String medications;
    private String dose;
    private Date dateGeneration;
    private boolean voidedOrder;

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getDniOwner() {
        return dniOwner;
    }

    public void setDniOwner(long dniOwner) {
        this.dniOwner = dniOwner;
    }

    public String getDniVeterinarian() {
        return dniVeterinarian;
    }

    public void setDniVeterinarian(String dniVeterinarian) {
        this.dniVeterinarian = dniVeterinarian;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Date getDateGeneration() {
        return dateGeneration;
    }

    public void setDateGeneration(Date dateGeneration) {
        this.dateGeneration = dateGeneration;
    }

    public boolean isVoidedOrder() {
        return voidedOrder;
    }

    public void setVoidedOrder(boolean voidedOrder) {
        this.voidedOrder = voidedOrder;
    }

    public OrderMedicine(long idOrder, long petId, long dniOwner, String dniVeterinarian, String medications, String dose, Date dateGeneration, boolean voidedOrder) {
        this.idOrder = idOrder;
        this.petId = petId;
        this.dniOwner = dniOwner;
        this.dniVeterinarian = dniVeterinarian;
        this.medications = medications;
        this.dose = dose;
        this.dateGeneration = dateGeneration;
        this.voidedOrder = voidedOrder;


    }
}
