package app.domain.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import app.domain.models.Species;

@Getter
@Setter
@NoArgsConstructor
public class Pet {
    private long idPet;
    private String name;
    private long dniOwner;
    private int AgePet;
    private String Species;
    private String race;
    private String color;
    private String size;
    private Float weight;

    public Pet(long idPet, String name, long dniOwner, int agePet, String species, String race, String color, String size, Float weight) {
        this.idPet = idPet;
        this.name = name;
        this.dniOwner = dniOwner;
        AgePet = agePet;
        Species = species;
        this.race = race;
        this.color = color;
        this.size = size;
        this.weight = weight;
    }
}
