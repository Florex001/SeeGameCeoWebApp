package hu.seegame.SeeGameCeo.SGSUser.Services;

import hu.seegame.SeeGameCeo.SGSUser.Models.User;
import hu.seegame.SeeGameCeo.SGSUser.Others.Encrypt;
import hu.seegame.SeeGameCeo.SGSUser.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
        User talaltemail = userRepository.findByEmail(user.getEmail());

        if (talaltusername != null){
            return new ResponseEntity<>(Collections.singletonMap("error", "Már létezik ilyen felhasználónévvel fiók."), HttpStatus.OK);
        }

        if (talaltemail != null){
            return new ResponseEntity<>(Collections.singletonMap("error", "Már létezik ilyen emailel fiók."), HttpStatus.OK);
        }

        userRepository.save(user);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres regisztráció."), HttpStatus.OK);

    }  //Felhasználó regisztráció

    public ResponseEntity<Object> loginUser(String username, String password){

        if (username.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Mezők kitöltése kötelező."), HttpStatus.CONFLICT);
        }//Ha üres akkor nem enged tovább

        User user = userRepository.findByUsername(username);

        if (user == null){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nem létezik ilyen felhasználónévvel fiók."), HttpStatus.CONFLICT);
        }//Nem létezik ilyen fiók

        String visszafejtettPass = Encrypt.decrypt(user.getPassword());

        if (user.getUsername().equals(username) && visszafejtettPass.equals(password)){
            return new ResponseEntity<>(Collections.singletonMap("bejelentkezett", "Sikeres bejelentkezés."), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Collections.singletonMap("error", "Rossz felhasználónév és jelszó kombináció."), HttpStatus.CONFLICT);
        }

    } //felhasználó bejelentkezés

}
