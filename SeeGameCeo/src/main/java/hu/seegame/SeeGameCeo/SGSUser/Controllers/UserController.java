package hu.seegame.SeeGameCeo.SGSUser.Controllers;

import hu.seegame.SeeGameCeo.SGSUser.Models.User;
import hu.seegame.SeeGameCeo.SGSUser.Others.Encrypt;
import hu.seegame.SeeGameCeo.SGSUser.Repositories.UserRepository;
import hu.seegame.SeeGameCeo.SGSUser.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registration")
    public ResponseEntity<Object> registration(@RequestBody User user){
        return userService.registrationUser(user);
    }//felhasználó regisztrálás

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpServletResponse response){
        String username = user.getUsername();
        String password = user.getPassword();

        ResponseEntity<Object> bejelentkezett = userService.loginUser(username, password);

        User felhasznalo = userRepository.findByUsername(username);

        if (bejelentkezett.getStatusCode() == HttpStatus.OK){
            String jogosultsag = felhasznalo.getPermission();
            int id = felhasznalo.getId();
            String usernamebydb = felhasznalo.getUsername();
            String value = id + "-" + usernamebydb + "-" + jogosultsag;

            System.out.println(value);

            String encrypCookie = Encrypt.encrypt(value);

            Cookie cookie = new Cookie("user", encrypCookie);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres bejelentkezés!"), HttpStatus.OK);
        }

        return bejelentkezett;
    }//bejelentkezés


}
