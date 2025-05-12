package app.adapters.orderMedicines.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Table(name = "order_medicines")
@Getter @Setter
public class OrderMedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long idOrder;

    @Column(name = "pet_id", nullable = false)
    private long petId;

    @Column(name = "owner_dni", nullable = false)
    private long dniOwner;

    @Column(name = "vet_dni", nullable = false)
    private long dniVeterinarian;

    @Column(name = "medications", nullable = false, length = 500)
    private String medications;

    @Column(name = "dose", nullable = false, length = 100)
    private String dose;

    @Column(name = "generation_date", nullable = false)
    private Date dateGeneration;

    @Column(name = "is_voided", nullable = false)
    private boolean voidedOrder = false;

    @Column(name = "cancellation_reason", length = 200)
    private String cancellationReason;

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

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}