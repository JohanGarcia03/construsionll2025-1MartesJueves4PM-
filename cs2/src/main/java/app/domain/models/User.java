package app.domain.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends Person{
    private String user;
    private String password;
    private String rol;

    public User(String user, String password, String rol) {
        this.user = user;
        this.password = password;
        this.rol = rol;
    }

    public User(long personId, String name, int age, String user, String password, String rol) {
        super(personId, name, age);
        this.user = user;
        this.password = password;
        this.rol = rol;
    }
}
