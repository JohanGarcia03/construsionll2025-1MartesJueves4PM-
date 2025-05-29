package app.domain.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pet {

    private long idPet;
    private long IdOwner;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private int agePet;
    @Setter
    @Getter
    private String species;
    @Setter
    @Getter
    private String race;
    @Setter
    @Getter
    private String color;
    @Setter
    @Getter
    private String size;
    private Float weight;

    public Pet(long idPet, long IdOwner, String name, int agePet, String species, String race, String color, String size, Float weight) {
        this.idPet = idPet;
        this.IdOwner = IdOwner;
        this.name = name;
        this.agePet = agePet;
        this.species = species;
        this.race = race;
        this.color = color;
        this.size = size;
        this.weight = weight;
    }

}
