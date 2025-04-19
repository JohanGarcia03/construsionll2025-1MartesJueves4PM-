package app.domain.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Pet {

    private long idPet;
    private long IdOwner;
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

    public Pet(long idPet, long IdOwner, String name, int agePet, String species, String race, String color, String size, Float weight) {
        this.idPet = idPet;
        this.IdOwner = IdOwner;
        this.name = name;
        AgePet = agePet;
        Species = species;
        this.race = race;
        this.color = color;
        this.size = size;
        this.weight = weight;
    }

}
