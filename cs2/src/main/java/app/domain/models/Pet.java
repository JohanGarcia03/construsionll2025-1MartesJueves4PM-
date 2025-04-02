package app.domain.models;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter

public class Pet extends ClinicalHistory {

    private long idPet;
    private long cedulaOnwer;
    @Getter
    private String name;
    @Getter
    private int AgePet;
    @Getter
    private String Species;
    @Getter
    private String race;
    @Getter
    private String color;
    @Getter
    private String size;
    @Getter
    private Float weight;

    public Pet(long idPet, long cedulaOnwer, String name, int agePet, String species, String race, String color, String size, Float weight) {
        this.idPet = idPet;
        this.cedulaOnwer = cedulaOnwer;
        this.name = name;
        AgePet = agePet;
        Species = species;
        this.race = race;
        this.color = color;
        this.size = size;
        this.weight = weight;
    }

    public Pet(Date date, String users, String reason, String symptoms, String diagnostic, String procedure, String dose, long idOrder, List<String> vaccine, List<String> allergies, String detailsProcess, boolean orderAnnulled, long idPet, long cedulaOnwer, String name, int agePet, String species, String race, String color, String size, Float weight) {
        super(date, users, reason, symptoms, diagnostic, procedure, dose, idOrder, vaccine, allergies, detailsProcess, orderAnnulled);
        this.idPet = idPet;
        this.cedulaOnwer = cedulaOnwer;
        this.name = name;
        AgePet = agePet;
        Species = species;
        this.race = race;
        this.color = color;
        this.size = size;
        this.weight = weight;
    }
}
