package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

@Setter
@Getter
@NoArgsConstructor
public class Person {

    private long PersonId;
    private String Name;
    private int Age;
    private String username;
    private String password;

    public Person(long personId, String name, int age, String username, String password) {
        PersonId = personId;
        Name = name;
        Age = age;
        this.username = username;
        this.password = password;
    }
}


