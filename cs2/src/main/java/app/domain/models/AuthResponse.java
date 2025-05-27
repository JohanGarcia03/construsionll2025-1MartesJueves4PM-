package app.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    private String username;
    private String role;
    private long cedula;

}
