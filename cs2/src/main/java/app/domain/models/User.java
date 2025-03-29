package app.domain.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
@NoArgsConstructor
public class User extends Person{
    private long userId;
    private String username;
    private String password;
    private Role role;

    public User(long userId, String username, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Long personId, String name, int age, long userId, String username, String password, Role role) {
        super(personId, name, age);
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}


