package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {

    private Long personId;
    private String name;
    private int age;

    Person(){

    }
    public Person(Long personId, String name, int age) {
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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


