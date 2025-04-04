package app.domain.services;

import app.domain.models.ClinicalHistory;
import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.PetPort;
import app.ports.UserPort;
import app.ports.VeterinarianPort;

import java.util.List;

public class VeterinarianServices {
    public final VeterinarianPort veterinarianPort;
    public final PetPort petPort;

    public VeterinarianServices(VeterinarianPort veterinarianPort, PetPort petPort) {
        this.veterinarianPort = veterinarianPort;
        this.petPort = petPort;
    }

    public void registerClinicalHistory(String petId, User veterinarian, String reason, String symptoms,
                                        String diagnosis, String procedure, String medication, String dosage,
                                        List<String> vaccinationHistory, List<String> allergyMedications,
                                        String procedureDetails) throws Exception {
        // Validar que el usuario sea veterinario
        if (!"veterinario".equals(veterinarian.getRole())) {
            throw new Exception("Acceso denegado. Solo los médicos veterinarios pueden registrar historias clínicas.");
        }
    }
}
