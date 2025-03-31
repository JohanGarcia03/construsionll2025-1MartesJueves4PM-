package app.domain.models;


import lombok.Getter;
import lombok.Setter;

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

    public Pet(Long idPet, List<Register> registers, long idPet1, long cedulaOnwer, String name, int agePet, String species, String race, String color, String size, Float weight) {
        super(idPet, registers);
        this.idPet = idPet1;
        this.cedulaOnwer = cedulaOnwer;
        this.name = name;
        AgePet = agePet;
        Species = species;
        this.race = race;
        this.color = color;
        this.size = size;
        this.weight = weight;
    }

    public long getIdPets() {
        return idPet;
    }

    public void setIdPet(long idPet) {
        this.idPet = idPet;
    }

    public long getCedulaOwner() {
        return cedulaOnwer;
    }

    public void setCedulaOnwer(long cedulaOnwer) {
        this.cedulaOnwer = cedulaOnwer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgePet(int agePet) {
        AgePet = agePet;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
