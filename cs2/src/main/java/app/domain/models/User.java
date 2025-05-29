package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User extends Person {
    private Person person;
    private long idUser;
    private String username;
    private String password;
    private String role;

    public User(long idUser, String username, String password, String role) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean isVeterinarian() {
        return "veterinario".equalsIgnoreCase(role);
    }

    public boolean isSeller() {
        return "vendedor".equalsIgnoreCase(role);
    }

}
