package hu.seegame.SeeGameCeo.V4Ceo.Controllers;

import hu.seegame.SeeGameCeo.SGSUser.Others.Encrypt;
import hu.seegame.SeeGameCeo.V4Ceo.Models.FolyamatTMPV4;
import hu.seegame.SeeGameCeo.V4Ceo.Services.FolyamatTMPV4Service;
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
public class FolyamatTMPV4Controller {

    @Autowired
    private FolyamatTMPV4Service folyamatTMPV4Service;

    @PostMapping("/createprocess/{jobid}")
    public ResponseEntity<Object> createProcess(@PathVariable int jobid, @RequestBody FolyamatTMPV4 folyamatTMPV4, HttpServletRequest request){
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

                        ResponseEntity<Object> hozzadas = folyamatTMPV4Service.createProcess(jobid, folyamatTMPV4);

                        return hozzadas;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//Folyamatok ideiglenes eltárolása véglegesítésig

    @GetMapping("/getprocess/{jobid}")
    public ResponseEntity<Object> getProcessByJob(@PathVariable int jobid, HttpServletRequest request){
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

                        ResponseEntity<Object> folyamatok = folyamatTMPV4Service.getAllProcessByJob(jobid);

                        return folyamatok;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//Munkához tartozó folyamatok lekérése

    @DeleteMapping("/deleteprocess/{processid}")
    private ResponseEntity<Object> deleteProcess(@PathVariable int processid, HttpServletRequest request){
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

                        ResponseEntity<Object> deleteProcess = folyamatTMPV4Service.deleteProcess(processid);

                        return deleteProcess;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//munkafolyamat törlése

}
