package app.domain.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends Person{
    private Long userId;
    private String password;
    private String rol;

    public User(Long user, String password, String rol) {
        this.userId = user;
        this.password = password;
        this.rol = rol;
    }

    public User(long personId, String name, int age, Long user, String password, String rol) {
        super(personId, name, age);
        this.userId = user;
        this.password = password;
        this.rol = rol;
    }
}
