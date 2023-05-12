package hu.seegame.SeeGameCeo;

import hu.seegame.SeeGameCeo.Controllers.UserController;
import hu.seegame.SeeGameCeo.Models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeeCeoBackendApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
	}

	@Test
	void registeredUser(){
		User user = new User();
		user.setAccountid(1234);
		user.setEmail("test@test.test");
		user.setPassword("teszt");
		user.setUsername("teszt");
		userController.registration(user);
		System.out.println("Regisztráció sikeresen le futott.");
	}

}
