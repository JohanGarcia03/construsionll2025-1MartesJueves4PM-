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

    Person(){

    }
    public Person(long cedula, String name, int age) {
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

}


