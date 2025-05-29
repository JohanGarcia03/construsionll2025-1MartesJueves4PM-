package app.adapters.MedicineOrder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "order_medicine")

public class OrderMedicineEntity {

    // Getters y setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private Long petId;
    private Long dniOwner;
    private String medications;
    private String dose;
    private Long dniVeterinarian;
    private java.sql.Date dateGeneration;
    private boolean cancelled;

}
