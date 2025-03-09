package app.domain.services;

import app.domain.models.Role;
import app.domain.models.User;
import app.ports.PersonPort;
import lombok.*;
import app.ports.UserPort;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class AdminService {
    private PersonPort personPort;
    private UserPort userPort;
    private User loggedUser;

    public void registerUser(User user) throws Exception {
        if(user.getRole()== null){
            throw new IllegalArgumentException("El usuario debe de tener un rol asignado: VETERINARIAN O SELLER." );
        }
        if(user.getUsername() == null || user.getUsername().isEmpty()){
            throw new Exception ("El nombre de usuario no puede estar vacio: ");
        }
        Optional<User> UserFound = personPort.findById(user.getPersonId());
        if (UserFound.isPresent()){
            throw new Exception("Ya existe un usuario con ese ID:" + UserFound.get().getPersonId());
        }
        if (userPort.existUserName(user.getUsername())) {
            throw new Exception("El nombre de usuario'"+ user.getUsername()+ "'Ya se encuentra en uso:  :) ");
        }
        if ((user.getRole() == Role.VETERINARIAN || user.getRole() == Role.SELLER)
                && (loggedUser.getRole() == null || loggedUser.getRole() != Role.ADMIN)) {
            throw new SecurityException("No tienes permisos para registrar este usuario.");
        }

        userPort.save(user);
    }
    public Optional<User> getPersonId(Long id) throws Exception {
        if(id == null){
            throw new Exception("El id no puede ser nulo..");
        }
        Optional<User> user = userPort.findById(id);
        if(user.isEmpty()) {
            throw new UserPrincipalNotFoundException("No se encontro usaurio con ese ID:" + id);
        }
        return user;
    }
    public List<User> getallPersons() {
        return userPort.findAll();
    }
    public void removeUser(Long id)throws Exception{
        Optional<User> user = userPort.findById(id);
        if(user.isEmpty()){
            throw new UserPrincipalNotFoundException("No se puede eliminar, usuario con ID"+ id +"no encontrado.");
        }
        userPort.delete(id);
    }

}


