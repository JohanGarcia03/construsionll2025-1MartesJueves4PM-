package app.domain.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends Person{
    private Long Username;
    private String password;
    private Role Role;

    public User(Long username, String password, app.domain.models.Role role) {
        Username = username;
        this.password = password;
        Role = role;
    }

    public User(long personId, String name, int age, String username, String password, Long username1, String password1, app.domain.models.Role role) {
        super(personId, name, age, username, password);
        Username = username1;
        this.password = password1;
        Role = role;
    }
}
