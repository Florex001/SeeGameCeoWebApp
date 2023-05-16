package hu.seegame.SeeGameCeo;

import hu.seegame.SeeGameCeo.SGSUser.Controllers.UserController;
import hu.seegame.SeeGameCeo.SGSUser.Models.User;
import hu.seegame.SeeGameCeo.V4Ceo.Controllers.KarakterV4Controller;
import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SeeCeoBackendApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private KarakterV4Controller karakterV4Controller;

	@Test
	void contextLoads() {
	}

	@Test
	void registeredUser(){
		User user = new User();
		user.setEmail("test@test.test");
		user.setPassword("teszt");
		user.setUsername("teszt");
		ResponseEntity<Object> createuser =userController.registration(user);
		System.out.println(createuser);
	}

	@Test
	void createcaracter(){
		KarakterV4 karakter = new KarakterV4();
		karakter.setIcnev("teszt teszt");
		//karakterV4Controller.createCharacter(karakter);
	}
}
