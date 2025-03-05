package app.ports;

import app.domain.models.User;

public interface UserPort {
    boolean existUserName(User user);

}
