package hu.seegame.SeeGameCeo.Services;

import hu.seegame.SeeGameCeo.Models.User;
import hu.seegame.SeeGameCeo.Models.Workshop;
import hu.seegame.SeeGameCeo.Repositories.UserRepository;
import hu.seegame.SeeGameCeo.Repositories.WorkshopRepository;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<Workshop> muhelyidvan = workshopRepository.findByMuhelyid(workshop.getMuhelyid());
        List<Workshop> tulajid = workshopRepository.findByTulajId(workshop.getTulajId());


        if (muhelyidvan != null) {
            for (Workshop elem : muhelyidvan) {
                if (elem.getStatus().equals("aktiv")){
                    return new ResponseEntity<>(Collections.singletonMap("error", "Már létezik ezzel a műhelyid-val műhely."), HttpStatus.OK);
                }
            }
        }


        boolean volt = false;

        if (tulajid != null){
            for (Workshop elem : tulajid){
                if (elem.getStatus().equals("aktiv")){
                    volt = true;
                }
            }
        }


        if (volt){
            return new ResponseEntity<>(Collections.singletonMap("error", "Már van egy műhely a tulajdonodban."), HttpStatus.OK);

        }

        workshopRepository.save(workshop);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres műhely hozzáadás."), HttpStatus.OK);

    }//Műhely létrehozása

    public ResponseEntity<Object> getMyWorkshop(int tulajid){

        List<Workshop> workshop = workshopRepository.findByTulajId(tulajid);

        if (workshop != null){

            List<Workshop> muhelyek = new ArrayList<>();

            for (Workshop elem : workshop){
                if (elem.getStatus().equals("aktiv")){
                    muhelyek.add(elem);
                }
            }

            if (muhelyek.isEmpty()){
                return  new ResponseEntity<>(Collections.singletonMap("error", "Nincs aktív műhelye."), HttpStatus.OK);
            }

            return new ResponseEntity<>(Collections.singletonMap("message", muhelyek), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs műhelye"), HttpStatus.OK);
        }


    }//A felhasználó saját műhelye

    public ResponseEntity<Object> getWorkshopiworkin(String icname){

        User felhasznalo = userRepository.findByIcnev(icname);

        String ignev = felhasznalo.getIcnev();

        List<Workshop> ittdolgozik = workshopRepository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev);

        if (ittdolgozik.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozik sehol."), HttpStatus.OK);
        }

        List<Workshop> workshops = new ArrayList<>();

        for (Workshop elem : ittdolgozik){
            if (elem.getStatus().equals("aktiv")){
                workshops.add(elem);
            }
        }

        if (workshops.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs aktív műhely amiben dolgozol."),  HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("message", workshops), HttpStatus.OK);

    }//Felhasználó műhelye amiben dolgozik

}
