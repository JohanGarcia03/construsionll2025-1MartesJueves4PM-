package app;

import app.domain.models.Pet;
import app.domain.models.User;
import app.domain.services.AdminService;
import app.domain.services.InmemoryDefaultAdapter;
import app.domain.services.MemoryPersonAdapter;
import app.domain.services.PetServices;
import app.ports.PersonPort;
import app.ports.PetPort;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.util.Optional;


@SpringBootApplication
public class Cs2Application {
	public static void main(String[] args) {





		/*PetPort petPort = new InmemoryDefaultAdapter(petport);
		PersonPort personPort = petPort; //
		PetServices petServices = new PetServices(petPort, personPort);*/
		///////////////////////////////////////////////////////////////
		MemoryPersonAdapter personAdapter = new MemoryPersonAdapter();
		InmemoryDefaultAdapter adapter = new InmemoryDefaultAdapter(personAdapter);

		while (true) {
			String username = "";
			while (username.trim().isEmpty()) {
				username = JOptionPane.showInputDialog("Ingrese el usuario por favor:");
				if (username == null) {
					System.out.println("Saliendo...");
					return;
				}
				if (username.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El usuario no puede estar vacío. Inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			String password = "";
			while (password.trim().isEmpty()) {
				JPasswordField passwordField = new JPasswordField();
				int option = JOptionPane.showConfirmDialog(null, passwordField, "Ingrese su contraseña:",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (option != JOptionPane.OK_OPTION) {
					System.out.println("Saliendo...");
					return;
				}
				password = new String(passwordField.getPassword());

				if (password.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía. Inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			Optional<User> loggedUser = adapter.login(username, password);
			if (loggedUser.isPresent()) {
				System.out.println("Bienvenido " + loggedUser.get().getUsername() + " - " + loggedUser.get().getRole());
				break;
			} else {
				System.out.println("Error: Usuario o contraseña incorrectos. Intente de nuevo.");
			}

		}


	}

}
