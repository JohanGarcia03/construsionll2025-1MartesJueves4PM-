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

    private List<User> Users;
    private List<PetOwner> Owners;
    private List<Pet> pets;
    private List<OrderMedicine> OrderMedicines;
    private List<invoiceOrder>invoiceOrders;

}
