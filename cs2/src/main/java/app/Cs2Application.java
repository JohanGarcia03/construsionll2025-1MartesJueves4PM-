package app;

import app.domain.models.User;
import app.domain.services.AdminService;
import app.domain.services.InmemoryDefaultAdapter;
import app.domain.services.InmemoryPersonAdapter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Cs2Application {
	public static void main(String[] args) {
		InmemoryPersonAdapter personAdapter = new InmemoryPersonAdapter();
		InmemoryDefaultAdapter adapter = new InmemoryDefaultAdapter(personAdapter);
		AdminService adminService = new AdminService();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Usuario: ");
		String username = scanner.next();
		System.out.print("Contraseña: ");
		String password = scanner.next();

		Optional<User> loggedUser = adapter.login(username, password);

		if (loggedUser.isPresent()) {
			System.out.println("Bienvenido " + loggedUser.get().getUsername() + " - " + loggedUser.get().getRole());
		} else {
			System.out.println("Error: Usuario o contraseña incorrectos.");
		}
	}
}
