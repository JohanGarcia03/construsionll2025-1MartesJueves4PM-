package app.domain.models;

public class PetOwner extends Person{
    public PetOwner() {
    }

    public PetOwner(Long personId, String name, int age) {
        super(personId, name, age);
    }
}
