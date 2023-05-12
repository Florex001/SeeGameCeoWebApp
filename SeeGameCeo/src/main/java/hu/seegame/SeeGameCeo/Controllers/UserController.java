package hu.seegame.SeeGameCeo.Controllers;

import hu.seegame.SeeGameCeo.Models.User;
import hu.seegame.SeeGameCeo.Others.Encrypt;
import hu.seegame.SeeGameCeo.Repositories.UserRepository;
import hu.seegame.SeeGameCeo.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registration")
    public String registration(@RequestBody User user){
        return userService.registrationUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response){
        String username = user.getUsername();
        String password = user.getPassword();

        String bejelentkezett = userService.loginUser(username, password);

        User felhasznalo = userRepository.findByUsername(username);

        if (bejelentkezett.equals("loggedin")){
            String jogosultsag = felhasznalo.getPermission();
            int id = felhasznalo.getId();
            String usernamebydb = felhasznalo.getUsername();
            String value = id + "-" + usernamebydb + "-" + jogosultsag;

            String encrypCookie = Encrypt.encrypt(value);

            Cookie cookie = new Cookie("user", encrypCookie);
            response.addCookie(cookie);
            return String.valueOf(value);
        }

        return bejelentkezett;
    }

}
