package app.adapters.clinicalHistories.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clinical_history")
public class ClinicalHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "date")
    private Date date;

    @Column(name = "veterinarian_id")
    private Long veterinarianId;

    @Column(name = "diagnostic", length = 1000)
    private String diagnostic;

    @ElementCollection
    @CollectionTable(name = "clinical_history_medications", joinColumns = @JoinColumn(name = "history_id"))
    @Column(name = "medication")
    private List<String> medications;

    public String getReason() {
        return this.getReason();
    }

    public List<String> getAllergies() {
        return this.getAllergies();
    }

    public String getDetails() {
        return this.getDetails();
    }

    public boolean isOrderAnnulled() {
        return this.isOrderAnnulled();
    }

    public Long getOrderId() {
        return this.getOrderId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setVeterinarianId(Long veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }


    public void setReason(String reason) {
        this.getReason();
    }

    public void setSymptoms(String symptoms) {
        this.setSymptoms("");
    }

    public void setProcedure(String procedure) {
        this.setProcedure("");
    }

    public long getPetId() {
        this.getPetId();
        return 0;
    }

    public java.sql.Date getDate() {
        return this.getDate();
    }

    public String getVeterinarianId() {
        return this.getVeterinarianId();
    }

    public String getSymptoms() {
        return this.getSymptoms();
    }

    public String getDiagnostic() {
        return this.getDiagnostic();
    }

    public String getProcedure() {
        return this.getProcedure();
    }

    public String getDose() {
        return this.getDose();
    }

    public List<String> getVaccines() {
        return this.getVaccines();
    }
}