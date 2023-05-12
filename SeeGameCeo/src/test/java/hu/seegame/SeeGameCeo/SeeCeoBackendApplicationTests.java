package hu.seegame.SeeGameCeo;

import hu.seegame.SeeGameCeo.Controllers.UserController;
import hu.seegame.SeeGameCeo.Controllers.WorkshopController;
import hu.seegame.SeeGameCeo.Models.User;
import hu.seegame.SeeGameCeo.Models.Workshop;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
class SeeCeoBackendApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private WorkshopController workshopController;

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

	@Test
	void createWorkshop(){
		Workshop workshop = new Workshop();

		workshop.setTulajId(1003);
		workshop.setMuhelyid(15267);
		workshop.setMuhelynev("fasz");
		workshop.setDolgozo1("Mia Khalifa");
		workshop.setDolgozo2("Thakur Aazar");
		workshop.setDolgozo3("");
		workshop.setDolgozo4("");
		workshop.setDolgozo5("");
		workshop.setDolgozo6("");
		workshop.setDolgozo7("");
		workshop.setDolgozo8("");
		workshop.setDolgozo9("");
		workshop.setDolgozo10("");
		workshop.setDolgozo11("");
		workshop.setDolgozo12("");
		workshop.setJog1("csiszolasalapozas");
		workshop.setJog2("csiszolas");
		workshop.setJog3("");
		workshop.setJog4("");
		workshop.setJog5("");
		workshop.setJog6("");
		workshop.setJog7("");
		workshop.setJog8("");
		workshop.setJog9("");
		workshop.setJog10("");
		workshop.setJog11("");
		workshop.setJog12("");
		workshop.setLejarat(LocalDateTime.of(2023, Month.MAY, 12, 19, 0, 0));
		Cookie cookie = new Cookie("user", "6kc8KIRwI0F29lK5Xlk6qQ%3D%3D");
		//workshopController.createWorkshop(workshop);
	}

}
