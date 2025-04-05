package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter

@Setter
public class OrderMedicine extends Pet{

    private long idOrder;
    private long petId;
    private long dniOwner;
    private long dniVeterinarian;
    private String medications;
    private String dose;
    private Date dateGeneration;
    private boolean voidedOrder;

    public OrderMedicine(long idPet, long cedulaOnwer, String name, int agePet, String species, String race, String color, String size, Float weight, long idOrder, long petId, long dniOwner, long dniVeterinarian, String medications, String dose, Date dateGeneration, boolean voidedOrder) {
        super(idPet, cedulaOnwer, name, agePet, species, race, color, size, weight);
        this.idOrder = idOrder;
        this.petId = petId;
        this.dniOwner = dniOwner;
        this.dniVeterinarian = dniVeterinarian;
        this.medications = medications;
        this.dose = dose;
        this.dateGeneration = dateGeneration;
        this.voidedOrder = voidedOrder;
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
