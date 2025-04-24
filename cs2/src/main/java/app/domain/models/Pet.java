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

    public long getIdPet() {
        return idPet;
    }

    public void setIdPet(long idPet) {
        this.idPet = idPet;
    }

    public long getIdOwner() {
        return IdOwner;
    }

    public void setIdOwner(long idOwner) {
        IdOwner = idOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
