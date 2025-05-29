package app.adapters.clinicalHistories.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "clinical_history")
public class ClinicalHistoryEntity {

    @Column(name = "procedure_type") // nombre válido
    private String procedureType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "pet_id", nullable = false)
    private Long petId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "veterinarian_id", nullable = false)
    private Long veterinarianId;

    private String reason;

    private String symptoms;

    @Column(length = 1000)
    private String diagnostic;

    private String medication;

    private String dosage;

    @Column(name = "order_id", unique = true)
    private String orderId;

    @ElementCollection
    @CollectionTable(name = "clinical_history_vaccinations", joinColumns = @JoinColumn(name = "history_id"))
    @Column(name = "vaccine")
    private List<String> vaccinations;

    @ElementCollection
    @CollectionTable(name = "clinical_history_allergies", joinColumns = @JoinColumn(name = "history_id"))
    @Column(name = "allergy")
    private List<String> allergyMedications;

    @ManyToOne
    @JoinColumn(name = "history_id", referencedColumnName = "id")
    private ClinicalHistoryEntity clinicalHistory;

    @Column(length = 1000)
    private String procedureDetails;

    private boolean orderAnnulled;


}