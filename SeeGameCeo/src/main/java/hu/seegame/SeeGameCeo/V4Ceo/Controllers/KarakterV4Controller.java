package hu.seegame.SeeGameCeo.V4Ceo.Controllers;

import hu.seegame.SeeGameCeo.SGSUser.Others.Encrypt;
import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import hu.seegame.SeeGameCeo.V4Ceo.Services.KarakterV4Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api/user/v4")
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class KarakterV4Controller {

    @Autowired
    private KarakterV4Service karakterV4Service;

    @PostMapping("/createcharacter")
    public ResponseEntity<Object> createCharacter(@RequestBody KarakterV4 karakterV4, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 3) {
                        cookie.setHttpOnly(true);
                        String id = parts[0];

                        karakterV4.setUserid(Integer.parseInt(id));

                        return karakterV4Service.createCharacter(karakterV4);
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//karakter létrehozása

    @PostMapping("/chooseserver")
    public ResponseEntity<Object> chooseServer(HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 3) {
                        cookie.setHttpOnly(true);
                        String id = parts[0];

                        KarakterV4 karakterV4 = karakterV4Service.chooseServer(Integer.parseInt(id));

                        if (karakterV4 == null){
                            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs karaktered."), HttpStatus.OK);
                        }

                        String value = "v4";


                        return new ResponseEntity<>(Collections.singletonMap("server", value), HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.UNAUTHORIZED);
    }//server sütit küld vissza ha kiválasztja a szervert és van karaktere különben létre kell hoznia egy karaktert hogy meg kapja a sütit

    @GetMapping("/getallicname")
    public ResponseEntity<Object> getAllIcnev(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 3) {
                        cookie.setHttpOnly(true);
                        String id = parts[0];

                        return karakterV4Service.getAllIcname(Integer.parseInt(id));
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.UNAUTHORIZED);
    }//ki listázza jsonba az összes ic nevet amivel regisztráltak
}
