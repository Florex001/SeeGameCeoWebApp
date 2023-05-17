package hu.seegame.SeeGameCeo.V4Ceo.Controllers;

import hu.seegame.SeeGameCeo.SGSUser.Others.Encrypt;
import hu.seegame.SeeGameCeo.V4Ceo.Models.JobV4;
import hu.seegame.SeeGameCeo.V4Ceo.Services.JobV4Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/user/v4")
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class JobV4Controller {

    @Autowired
    private JobV4Service jobV4Service;

    @PostMapping("/createjob/{id}")
    public ResponseEntity<Object> createJob(@PathVariable int id, @RequestBody JobV4 job, HttpServletRequest request){
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

                        ResponseEntity<Object> hozzadas = jobV4Service.createJob(id, job, Integer.parseInt(userid));

                        return hozzadas;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.UNAUTHORIZED);
    }//az adott műhelyhez létre hoz egy munkát. egyszerre 2 aktiv munka lehet egy műhelyben

    @GetMapping("/getworkbyworkshop/{id}")
    public ResponseEntity<Object> getWorkByWorkshop(@PathVariable int id, HttpServletRequest request){
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

                        ResponseEntity<Object> munkak = jobV4Service.getJobByMuhelyAktiv(id, Integer.parseInt(userid));

                        return munkak;
                    }
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.UNAUTHORIZED);
    }//Műhelyhez tartozó munkák le kérdezése

}
