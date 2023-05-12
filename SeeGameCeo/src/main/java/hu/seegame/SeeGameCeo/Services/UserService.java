package hu.seegame.SeeGameCeo.Services;

import hu.seegame.SeeGameCeo.Models.User;
import hu.seegame.SeeGameCeo.Others.Encrypt;
import hu.seegame.SeeGameCeo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> registrationUser(User user){

        String username = user.getUsername();

        String password = user.getPassword();
        String titkositottPass = Encrypt.encrypt(password);

        user.setPassword(titkositottPass);

        User talaltusername = userRepository.findByUsername(username);
        User talaltaccid = userRepository.findByAccountid(user.getAccountid());

        if (talaltusername != null && talaltusername.getUsername().equals(username)){
            return new ResponseEntity<>(Collections.singletonMap("message", "Már létezik ilyen felhasználónévvel fiók."), HttpStatus.CONFLICT);
        }

        if (talaltaccid != null && talaltaccid.getAccountid() == user.getAccountid()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Az AccounID már regisztrálva van."), HttpStatus.CONFLICT);
        }

        userRepository.save(user);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres regisztráció."), HttpStatus.OK);

    }  //Felhasználó regisztráció

    public ResponseEntity<Object> loginUser(String username, String password){

        if (username.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Mezők kitöltése kötelező."), HttpStatus.CONFLICT);
        }//Ha üres akkor nem enged tovább

        User user = userRepository.findByUsername(username);

        if (user == null){
            return new ResponseEntity<>(Collections.singletonMap("message", "Nem létezik ilyen felhasználónévvel fiók."), HttpStatus.CONFLICT);
        }//Nem létezik ilyen fiók

        String visszafejtettPass = Encrypt.decrypt(user.getPassword());

        if (user.getUsername().equals(username) && visszafejtettPass.equals(password)){
            return new ResponseEntity<>(Collections.singletonMap("bejelentkezett", "Sikeres bejelentkezés."), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Collections.singletonMap("message", "Rossz felhasználónév és jelszó kombináció."), HttpStatus.CONFLICT);
        }

    } //felhasználó bejelentkezés

}
