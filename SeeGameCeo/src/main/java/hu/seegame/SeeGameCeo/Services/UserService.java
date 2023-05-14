package hu.seegame.SeeGameCeo.Services;

import hu.seegame.SeeGameCeo.Models.User;
import hu.seegame.SeeGameCeo.Others.Encrypt;
import hu.seegame.SeeGameCeo.Repositories.UserRepository;
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
        User talaltaccid = userRepository.findByAccountid(user.getAccountid());
        User talalticnev = userRepository.findByIcnev(user.getIcnev());

        if (talalticnev != null){
            return new ResponseEntity<>(Collections.singletonMap("error", "Már létezik ilyen ic névvel fiók."), HttpStatus.OK);
        }

        if (talaltusername != null && talaltusername.getUsername().equals(username)){
            return new ResponseEntity<>(Collections.singletonMap("error", "Már létezik ilyen felhasználónévvel fiók."), HttpStatus.OK);
        }

        if (talaltaccid != null && talaltaccid.getAccountid() == user.getAccountid()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Az AccounID már regisztrálva van."), HttpStatus.OK);
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

    public ResponseEntity<Object> getAllIcname(String icnev){
        List<User> felhasznalok = userRepository.findAll();

        List<Map<String, String>> icnevList = new ArrayList<>();

        for (User elem : felhasznalok){
            if (!icnev.equals(elem.getIcnev())){
                Map<String, String> icnevMap = new HashMap<>();
                icnevMap.put("label", elem.getIcnev());
                icnevMap.put("value", elem.getIcnev());
                icnevList.add(icnevMap);
            }

        }

        return new ResponseEntity<>(icnevList, HttpStatus.OK);

    }//Összes ic név lekérése

}
