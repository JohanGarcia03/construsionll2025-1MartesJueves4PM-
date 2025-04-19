package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter

@Setter
public class OrderMedicine{

    private long idOrder;
    private long petId;
    private long dniOwner;
    private long dniVeterinarian;
    private String medications;
    private String dose;
    private Date dateGeneration;
    private boolean voidedOrder;

    public OrderMedicine() {
    }

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

    public long getDniVeterinarian() {
        return dniVeterinarian;
    }

    public void setDniVeterinarian(long dniVeterinarian) {
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
}
