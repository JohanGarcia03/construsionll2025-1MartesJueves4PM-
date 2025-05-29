package app.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MedicineOrder {

    @Setter
    private Long idOrder;             // ID único (UUID)
    private long petId;                // ID de la mascota
    private long dniOwner;             // Cédula del dueño
    private long dniVeterinarian;      // Cédula del veterinario
    private String medications;        // Nombre del medicamento recetado
    private String dose;               // Dosis del medicamento
    private Date dateGeneration;       // Fecha de generación
    private boolean cancelled;         // ¿Orden anulada?

}