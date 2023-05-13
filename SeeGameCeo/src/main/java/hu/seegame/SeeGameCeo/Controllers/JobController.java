package hu.seegame.SeeGameCeo.Controllers;

import hu.seegame.SeeGameCeo.Models.Job;
import hu.seegame.SeeGameCeo.Others.Encrypt;
import hu.seegame.SeeGameCeo.Services.JobService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/createjob/{id}")
    public ResponseEntity<Object> createJob(@PathVariable int id, @RequestBody Job job, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 4) {
                        String userid = parts[0];
                        String icnev = parts[3];
                        cookie.setHttpOnly(true);

                        ResponseEntity<Object> hozzadas = jobService.createJob(id, job, icnev, Integer.parseInt(userid));

                        return hozzadas;
                    }
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("message", "Jelentkezz be."), HttpStatus.BAD_REQUEST);
    }

}
