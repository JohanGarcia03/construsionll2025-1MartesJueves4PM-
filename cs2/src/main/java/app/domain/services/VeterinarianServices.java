package app.domain.services;

import app.domain.models.*;
import app.ports.*;
import java.util.Optional;
import app.domain.models.ClinicalHistory;

public class VeterinarianServices {
    private final ClinicalHistoryPort clinicalHistoryPort;
    private final MedicineOrderPort medicineOrderPort;
    private final PetPort petPort;
    private final UserPort userPort;

    // Inyección de dependencias
    public VeterinarianServices(ClinicalHistoryPort clinicalHistoryPort, MedicineOrderPort medicineOrderPort, PetPort petPort, UserPort userPort) {
        this.clinicalHistoryPort = clinicalHistoryPort;
        this.medicineOrderPort = medicineOrderPort;
        this.petPort = petPort;
        this.userPort = userPort;
    }

    public ClinicalHistory registerClinicalHistory(long veterinarianId, ClinicalHistory history) throws Exception {
        // Validar veterinario
        User vet = userPort.findById(veterinarianId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (!vet.isVeterinarian()) {
            throw new Exception("Solo veterinarios pueden registrar historias clínicas");
        }

        //Validar mascota
        Pet pet = petPort.FindByIdPet(history.getIdPet())
                .orElseThrow(() -> new Exception("Mascota no encontrada"));

        //Si hay orden médica, validarla
        if (history.getIdOrder() != 0) {
            medicineOrderPort.findOrderById(history.getIdOrder())
                    .orElseThrow(() -> new Exception("Orden médica no válida"));
        }

        //Completar datos
        history.setUsers(vet.getName());
        history.setDate(new java.sql.Date(System.currentTimeMillis()));
        return clinicalHistoryPort.save(history);
    }

    public OrderMedicine createMedicalOrder(long veterinarianId, OrderMedicine order) throws Exception {
        return medicineOrderPort.save(order);

    }
}