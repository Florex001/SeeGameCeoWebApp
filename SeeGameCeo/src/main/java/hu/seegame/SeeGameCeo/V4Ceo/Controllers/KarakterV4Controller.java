package hu.seegame.SeeGameCeo.V4Ceo.Controllers;

import hu.seegame.SeeGameCeo.SGSUser.Others.Encrypt;
import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import hu.seegame.SeeGameCeo.V4Ceo.Services.KarakterV4Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v4")
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class KarakterV4Controller {

    @Autowired
    private KarakterV4Service karakterV4Service;

    @PostMapping("/createcaracter")
    public ResponseEntity<Object> createCharacter(@RequestBody KarakterV4 karakterV4, HttpServletRequest request){

        System.out.println(karakterV4);
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
    }
}
