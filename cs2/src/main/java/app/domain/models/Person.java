package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {

    private long cedula;
    private String name;
    private int age;

    public Person(){

    }

}


