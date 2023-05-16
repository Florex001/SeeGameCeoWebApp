package hu.seegame.SeeGameCeo.V4Ceo.Services;

import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.KarakterV4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KarakterV4Service {

    @Autowired
    private KarakterV4Repository karakterV4Repository;

    public ResponseEntity<Object> createCharacter(KarakterV4 karakterV4){

        KarakterV4 karakterByUser = karakterV4Repository.findByUserid(karakterV4.getUserid());
        KarakterV4 karakter = karakterV4Repository.findByIcnev(karakterV4.getIcnev());

        if (karakterByUser != null){
            return new ResponseEntity<>(Collections.singletonMap("message", "Már van egy karaktered."), HttpStatus.OK);
        }

        if (karakter != null){
            return new ResponseEntity<>(Collections.singletonMap("message", "Már létezik egy ilyen karakter."), HttpStatus.OK);
        }

        karakterV4Repository.save(karakterV4);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres létrehozás."), HttpStatus.OK);
    }//karakter létrehozása

    public KarakterV4 chooseServer(int userid){

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(userid);

        return karakterV4;
    }//lekérdezi hogy van e a felhasználónak az adott szerveren karaktere ha van vissza adja az értéket ha nincs akkor null

}
