package app.domain.services;

import app.domain.models.*;
import app.ports.*;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import app.domain.models.User;

import java.util.Optional;
@NoArgsConstructor

public class VeterinarianServices {
    private  MedicineOrderPort medicineOrdersPort;
    private  PetPort petPort;
    private  PersonPort personPort;


    /// que si trae orden que si exista la orde
    /// El veterinario que si sea veterinario


    public Optional<Pet> registerClinicalHistory(ClinicalHistory clinicalHistory) throws Exception {
        // 1. Verificar si la mascota existe
        Optional<Pet> petFound = petPort.FindByIdPet(clinicalHistory.getIdPet());
        if (petFound.isEmpty()) {
            throw new Exception("La mascota no existe");
        }

        // 2. Verificar si el dueño de la mascota existe
        Optional<User> personByPet = personPort.findById(petFound.get().getIdOwner());
        if (personByPet.isEmpty()) {
            throw new Exception("No hay una persona asociada a esa mascota");
        }

        // 3. Verificar si la orden médica existe
        Optional<OrderMedicine> petFindByOrder = medicineOrdersPort.findOrderById(clinicalHistory.getIdOrder());
        if (petFindByOrder.isEmpty()) {
            throw new Exception("La orden médica con ese ID no existe");
        }

        // 4. Guardar la historia clínica
        medicineOrdersPort.createMedicineOrder(petFound.get().getIdPet(), clinicalHistory);

        // 5. Confirmar con retorno de la mascota
        return petFound;
    }


}

