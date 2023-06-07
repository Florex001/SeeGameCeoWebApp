package hu.seegame.SeeGameCeo.V4Ceo.Controllers;

import hu.seegame.SeeGameCeo.SGSUser.Others.Encrypt;
import hu.seegame.SeeGameCeo.V4Ceo.Models.WorkshopV4;
import hu.seegame.SeeGameCeo.V4Ceo.Services.WorkshopV4Services;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/user/v4")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class WorkshopV4Controller {

    @Autowired
    private WorkshopV4Services workshopV4Services;

    @PostMapping("/createworkshop")
    public ResponseEntity<Object> createWorkshop(@RequestBody WorkshopV4 workshopV4, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 3) {
                        String id = parts[0];
                        cookie.setHttpOnly(true);

                        ResponseEntity<Object> hozzadas = workshopV4Services.createWorkshop(workshopV4, Integer.parseInt(id));

                        return hozzadas;
                    }
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("message", "Jelentkezz be."), HttpStatus.OK);

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
                    if (parts.length == 3) {
                        cookie.setHttpOnly(true);
                        String id = parts[0];

                        ResponseEntity<Object> sajatmuhelye = workshopV4Services.getMyWorkshop(Integer.parseInt(id));

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
                    if (parts.length == 3) {
                        cookie.setHttpOnly(true);
                        String id = parts[0];

                        ResponseEntity<Object> ittdolgozik = workshopV4Services.getWorkshopiworkin(Integer.parseInt(id));

                        return ittdolgozik ;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//felhasználó műhelye amiben dolgozik

    @GetMapping("/getworkerbyworkshop/{id}")
    public ResponseEntity<Object> getWorkerByWorkshop(@PathVariable int id, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 3) {
                        String userid = parts[0];
                        cookie.setHttpOnly(true);

                        ResponseEntity<Object> muhelybendolgozok = workshopV4Services.getWorkerByWorkshop(id);

                        return muhelybendolgozok;
                    }
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//Munkások lekérdezése az adott műhelyhez

    @PostMapping("/workshopextension/{id}")
    public ResponseEntity<Object> workshopExtension(@PathVariable int id, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 3) {
                        String userid = parts[0];
                        cookie.setHttpOnly(true);

                        ResponseEntity<Object> muhelyhosszabbitas = workshopV4Services.workshopExtension(id);

                        return muhelyhosszabbitas;
                    }
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }


}
