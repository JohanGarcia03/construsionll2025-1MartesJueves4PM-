package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Person {

    private long PersonId;
    private String Name;
    private int Age;

    public Person(long personId, String name, int age) {
        PersonId = personId;
        Name = name;
        Age = age;
    }

}


