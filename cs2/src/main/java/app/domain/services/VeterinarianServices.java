package app.domain.services;

import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.*;

import java.util.Optional;

public class VeterinarianServices {
    private final ClinicalHistoryPort clinicalHistory;
    private final MedicineOrderPort medicineOrders;
    private final PetPort petPort;
    private final PersonPort personPort;

    public VeterinarianServices(ClinicalHistoryPort clinicalHistory, MedicineOrderPort medicineOrders, PetPort petPort, PersonPort personPort) throws Exception {
        this.clinicalHistory = clinicalHistory;
        this.medicineOrders = medicineOrders;
        this.petPort = petPort;
        this.personPort = personPort;

    }

    public Optional<Pet> registrarHistoriaClinica(Pet pet) throws Exception {
        Optional<Pet> petFound = petPort.FindByIdPet(String.valueOf(pet.getIdPet()));
        if (petFound.isEmpty()) throw new Exception("La mascota no existe");

        return petFound;
    }

    // Se busca un due;o registrado con esa mascota si existe pasa si no tira esa excepcion
    public Optional <Pet> getOwnerByPet(Pet pet)throws Exception{
        Optional <User> personByPet = personPort.findById((pet.getCedulaOnwer()));
        if(personByPet.isEmpty()) throw new Exception("No hay una mascota registrada con esa cedula:");

        return Optional.empty();
    }

}
