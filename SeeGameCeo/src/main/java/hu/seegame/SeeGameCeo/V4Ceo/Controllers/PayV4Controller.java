package hu.seegame.SeeGameCeo.V4Ceo.Controllers;

import hu.seegame.SeeGameCeo.SGSUser.Others.Encrypt;
import hu.seegame.SeeGameCeo.V4Ceo.Services.PayV4Service;
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
public class PayV4Controller {

    @Autowired
    private PayV4Service payV4Service;

    @PostMapping("/pay/{jobid}")
    public ResponseEntity<Object> fizetesSzamolas(@PathVariable int jobid, HttpServletRequest request){
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

                        ResponseEntity<Object> fizetes = payV4Service.fizetesSzamolas(jobid, Integer.parseInt(userid));

                        return fizetes;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//ki számolja a munkán dolgozók fizetését

    @GetMapping("/pay/{jobid}")
    public ResponseEntity<Object> fizetesekAMunkahoz(@PathVariable int jobid, HttpServletRequest request){
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

                        ResponseEntity<Object> fizetesek = payV4Service.getPay(jobid);

                        return fizetesek;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//ki listázza az adott munkához tartozó fizetéseket

    @PostMapping("/ispay/{fizetesid}")
    public ResponseEntity<Object> fizetvevane(@PathVariable int fizetesid, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    String encryptedValue = cookie.getValue();
                    String decryptedValue = Encrypt.decrypt(encryptedValue);
                    String[] parts = decryptedValue.split("-");
                    if (parts.length == 3) {
                        cookie.setHttpOnly(true);

                        ResponseEntity<Object> fizetesek = payV4Service.fizetveVanE(fizetesid);

                        return fizetesek;
                    }
                }
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("error", "Jelentkezz be."), HttpStatus.OK);
    }//ha rá kattint a ki listázottra akkor a fizetése true vagy false lesz

}
