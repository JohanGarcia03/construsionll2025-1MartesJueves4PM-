package app.adapters.persons.entity;

import app.adapters.users.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "persons")
@Getter
@Setter
public class PersonEntity {

    @Id
    @Column(name = "cedula")
    private long cedula;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    // Relación opcional con User si es necesario
    @OneToOne(mappedBy = "person")
    @JsonIgnore // evita loops al serializar en JSON
    @ToString.Exclude // evita loops al imprimir con Lombok
    private UserEntity user;

}