package app.adapters.pets.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "pets")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private long idPet;

    @Column(name = "owner_id", nullable = false)
    private long idOwner;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age")
    private int agePet;

    @Column(name = "species", nullable = false, length = 50)
    private String species;

    @Column(name = "race", length = 50)
    private String race;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "size", length = 20)
    private String size;

    @Column(name = "weight")
    private Float weight;

}
