package hu.seegame.SeeGameCeo.Controllers;

import hu.seegame.SeeGameCeo.Models.Workshop;
import hu.seegame.SeeGameCeo.Others.Encrypt;
import hu.seegame.SeeGameCeo.Services.WorkshopService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class WorkshopController {

    @Autowired
    private WorkshopService workshopService;

    @PostMapping("/createworkshop")
    public ResponseEntity<Object> createWorkshop(@RequestBody Workshop workshop, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 4) {
                        String id = parts[0];
                        cookie.setHttpOnly(true);

                        ResponseEntity<Object> hozzadas = workshopService.createWorkshop(workshop, Integer.parseInt(id));

                        return hozzadas;
                    }
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("message", "Jelentkezz be."), HttpStatus.BAD_REQUEST);

    }//műhely létrehozás

    @GetMapping("/getmyworkshop")
    public ResponseEntity<Object> getMyWorkshop(HttpServletRequest request){

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

                        ResponseEntity<Object> sajatmuhelye = workshopService.getMyWorkshop(Integer.parseInt(id));

                        return sajatmuhelye;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//felhasználó saját műhelye

    @GetMapping("/workshopiworkin")
    public ResponseEntity<Object> getWorkshopiworkin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 4) {
                        cookie.setHttpOnly(true);
                        String icname = parts[3];

                        ResponseEntity<Object> ittdolgozik = workshopService.getWorkshopiworkin(icname);

                        return ittdolgozik ;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//felhasználó műhelye amiben dolgozik

}
