package hu.seegame.SeeGameCeo.Services;

import hu.seegame.SeeGameCeo.Models.User;
import hu.seegame.SeeGameCeo.Models.Workshop;
import hu.seegame.SeeGameCeo.Repositories.UserRepository;
import hu.seegame.SeeGameCeo.Repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> createWorkshop(Workshop workshop, int tulaj_id){

        workshop.setTulajId(tulaj_id);
        workshop.setStatus("aktiv");

        Workshop muhelyidvan = workshopRepository.findByMuhelyid(workshop.getMuhelyid());
        Workshop tulajid = workshopRepository.findByTulajId(workshop.getTulajId());

        if (muhelyidvan != null){
            return new ResponseEntity<>(Collections.singletonMap("message", "Már létezik ezzel a műhelyid-val műhely."), HttpStatus.OK);
        }

        if (tulajid != null){
            return new ResponseEntity<>(Collections.singletonMap("message", "Már van egy műhely a tulajdonodban."), HttpStatus.OK);

        }

        workshopRepository.save(workshop);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres műhely hozzáadás."), HttpStatus.OK);

    }//Műhely létrehozása

    public ResponseEntity<Object> getMyWorkshop(int tulajid){

        Workshop workshop = workshopRepository.findByTulajId(tulajid);

        if (workshop != null){
            return new ResponseEntity<>(Collections.singletonMap("message", workshop), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Collections.singletonMap("message", "Nincs műhelye"), HttpStatus.OK);
        }


    }//A felhasználó saját műhelye

    public ResponseEntity<Object> getWorkshopiworkin(String icname){

        User felhasznalo = userRepository.findByIcnev(icname);

        String ignev = felhasznalo.getIcnev();

        List<Workshop> ittdolgozik = workshopRepository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev);

        if (ittdolgozik.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozik sehol."), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("message", ittdolgozik), HttpStatus.OK);

    }//Felhasználó műhelye amiben dolgozik

}
