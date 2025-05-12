package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {

    private long cedula;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private int age;

    public Person(){

    }

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
}


