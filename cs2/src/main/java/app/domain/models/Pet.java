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

    public long getIdPet() {
        return idPet;
    }

    public void setIdPet(long idPet) {
        this.idPet = idPet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDniOwner() {
        return dniOwner;
    }

    public void setDniOwner(long dniOwner) {
        this.dniOwner = dniOwner;
    }

    public int getAgePet() {
        return AgePet;
    }

    public void setAgePet(int agePet) {
        AgePet = agePet;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
