package hu.seegame.SeeGameCeo.Services;

import hu.seegame.SeeGameCeo.Models.User;
import hu.seegame.SeeGameCeo.Others.Encrypt;
import hu.seegame.SeeGameCeo.Repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registrationUser(User user){

        String username = user.getUsername();

        String password = user.getPassword();
        String titkositottPass = Encrypt.encrypt(password);

        user.setPassword(titkositottPass);

        User talaltusername = userRepository.findByUsername(username);
        User talaltaccid = userRepository.findByAccountid(user.getAccountid());

        if (talaltusername == null && talaltaccid == null){
            userRepository.save(user);
            return "Sikeres regisztráció.";
        }

        if (talaltusername != null && talaltusername.getUsername().equals(username)){
            return "Már létezik ilyen felhasználónévvel fiók.";
        }

        if (talaltaccid != null && talaltaccid.getAccountid() == user.getAccountid()){
            return "Ez az accountId már regisztrálva van.";
        }

        userRepository.save(user);
        return "Sikeres regisztráció.";

    }  //Felhasználó regisztráció

    public String loginUser(String username, String password){

        if (username.isEmpty() || password.isEmpty()){
            return "A mezők kitöltése kötelező.";
        }//Ha üres akkor nem enged tovább

        User user = userRepository.findByUsername(username);

        if (user == null){
            return "Nem létezik ilyen felhasználónévvel regisztrált fiók.";
        }//Nem létezik ilyen fiók

        String visszafejtettPass = Encrypt.decrypt(user.getPassword());

        if (user.getUsername().equals(username) && visszafejtettPass.equals(password)){
            return "loggedin"; //bejelentkezett
        }else {
           return "Rossz felhasználónév és jelszó kombináció.";
        }

    }

}
