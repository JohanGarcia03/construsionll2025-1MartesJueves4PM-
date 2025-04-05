package app.domain.models;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter

public class Pet {

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

}
