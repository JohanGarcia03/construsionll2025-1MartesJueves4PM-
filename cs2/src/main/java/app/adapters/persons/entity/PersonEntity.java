package app.adapters.persons.entity;

import app.adapters.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persons")
@Getter @Setter
public class PersonEntity {
    @Id
    @Column(name = "cedula")
    private long cedula;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    // Relación opcional con User si es necesario
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private UserEntity user;

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}