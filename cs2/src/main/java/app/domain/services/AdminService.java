package app.domain.services;

import app.domain.models.Person;
import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.UserPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class AdminService {
    private PersonPort personPort;
    private UserPort userPort;

    @Autowired
    public AdminService(PersonPort personPort, UserPort userPort) {
        this.personPort = personPort;
        this.userPort = userPort;
    }

    public void registerVeterinarian(User user) throws Exception {
        // Validar existencia por cédula
        if (personPort.existsByCedula(user.getCedula())) {
            throw new Exception("Ya existe una persona con cédula: " + user.getCedula());
        }

        // Validar username
        if (userPort.existUserName(user.getUsername())) {
            throw new Exception("El nombre de usuario '" + user.getUsername() + "' ya está en uso.");
        }

        // 1️⃣ Crear y guardar persona primero
        Person persona = new Person();
        persona.setCedula(user.getCedula());
        persona.setName(user.getName());
        persona.setAge(user.getAge());

        personPort.savePerson(persona);

        // 2️⃣ Volver a obtener la persona para asegurar que esté en BD
        Person savePerson = personPort.findByCedula(user.getCedula())
                .orElseThrow(() -> new Exception("Error al recuperar persona guardada."));
        user.setPerson(savePerson);


        // 3️⃣ Asociar la persona al usuario y rol
        user.setPerson(savePerson);
        user.setRole("veterinario");

        // 4️⃣ Guardar usuario ya enlazado
        userPort.save(user);
    }

    public void registerSeller(User user) throws Exception {
        // Verificar cédula existente
        if (personPort.existsByCedula(user.getCedula())) {
            throw new Exception("Ya existe una persona con cédula: " + user.getCedula());
        }

        // Verificar si el username ya existe
        if (userPort.existUserName(user.getUsername())) {
            throw new Exception("El nombre de usuario '" + user.getUsername() + "' ya está en uso.");
        }

        // Crear y guardar la persona primero
        Person persona = new Person();
        persona.setCedula(user.getCedula());
        persona.setName(user.getName());
        persona.setAge(user.getAge());

        personPort.savePerson(persona);

        // Recuperar la persona para asociarla al usuario
        Person personaGuardada = personPort.findByCedula(user.getCedula())
                .orElseThrow(() -> new Exception("Error al recuperar persona guardada."));

        // Configurar y guardar el usuario
        user.setPerson(personaGuardada);
        user.setRole("vendedor");

        userPort.save(user);
    }



}
