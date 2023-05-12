package hu.seegame.SeeGameCeo.Controllers;

import hu.seegame.SeeGameCeo.Models.Workshop;
import hu.seegame.SeeGameCeo.Others.Encrypt;
import hu.seegame.SeeGameCeo.Services.WorkshopService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
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
                    if (parts.length == 3) {
                        String id = parts[0];

                        ResponseEntity<Object> hozzadas = workshopService.createWorkshop(workshop, Integer.parseInt(id));

                        return hozzadas;
                    }
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("message", "Jelentkezz be."), HttpStatus.BAD_REQUEST);

    }

}
