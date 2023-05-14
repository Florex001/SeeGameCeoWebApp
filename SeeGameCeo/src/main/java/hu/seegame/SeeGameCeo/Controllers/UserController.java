package hu.seegame.SeeGameCeo.Controllers;

import hu.seegame.SeeGameCeo.Models.User;
import hu.seegame.SeeGameCeo.Others.Encrypt;
import hu.seegame.SeeGameCeo.Repositories.UserRepository;
import hu.seegame.SeeGameCeo.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
            String icnev = felhasznalo.getIcnev();
            String value = id + "-" + usernamebydb + "-" + jogosultsag + "-" + icnev;

            String encrypCookie = Encrypt.encrypt(value);

            Cookie cookie = new Cookie("user", encrypCookie);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres bejelentkezés!"), HttpStatus.OK);
        }

        return bejelentkezett;
    }//bejelentkezés

    @GetMapping("/getallicname")
    public ResponseEntity<Object> getAllIcnev(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 4) {
                        cookie.setHttpOnly(true);
                        String id = parts[0];
                        String icnev = parts[3];

                        return userService.getAllIcname(icnev);
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//ki listázza jsonba az összes ic nevet amivel regisztráltak

}
