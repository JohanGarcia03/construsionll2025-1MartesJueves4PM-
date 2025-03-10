package app.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;

@Setter
@Getter
public class Person {

    private Long PersonId;
    private String Name;
    private int Age;

    Person(){

    }
    public Person(Long personId, String name, int age) {
    }

    public Long getPersonId() {
        return PersonId;
    }

    public void setPersonId(Long personId) {
        PersonId = personId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}


