package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class Mainveterinarian {
    //Guarda toda la información en memoria*/

    private List<User> users;
    private List<PetOwner> petOwners;
    private List<Pet> pets;
    private List<OrderMedicine> orderMedicines;
    private List<invoiceOrder>invoiceOrders;

}
