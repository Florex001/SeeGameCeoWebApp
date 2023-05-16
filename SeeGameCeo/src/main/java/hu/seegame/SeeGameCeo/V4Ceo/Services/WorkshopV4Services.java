package hu.seegame.SeeGameCeo.V4Ceo.Services;

import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.WorkshopV4;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.KarakterV4Repository;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.WorkshopV4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WorkshopV4Services {

    @Autowired
    private WorkshopV4Repository workshopV4Repository;

    @Autowired
    private KarakterV4Repository karakterV4Repository;


    public ResponseEntity<Object> createWorkshop(WorkshopV4 workshopV4, int userid){

        KarakterV4 karakter = karakterV4Repository.findByUserid(userid);

        workshopV4.setTulajNev(karakter.getIcnev());
        workshopV4.setStatus("aktiv");

        List<WorkshopV4> muhelyidvan = workshopV4Repository.findByMuhelyid(workshopV4.getMuhelyid());
        List<WorkshopV4> tulajid = workshopV4Repository.findByTulajNev(workshopV4.getTulajNev());


        if (muhelyidvan != null) {
            for (WorkshopV4 elem : muhelyidvan) {
                if (elem.getStatus().equals("aktiv")){
                    return new ResponseEntity<>(Collections.singletonMap("error", "Már létezik ezzel a műhelyid-val műhely."), HttpStatus.OK);
                }
            }
        }


        boolean volt = false;

        if (tulajid != null){
            for (WorkshopV4 elem : tulajid){
                if (elem.getStatus().equals("aktiv")){
                    volt = true;
                }
            }
        }


        if (volt){
            return new ResponseEntity<>(Collections.singletonMap("error", "Már van egy műhely a tulajdonodban."), HttpStatus.OK);

        }

        workshopV4Repository.save(workshopV4);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres műhely hozzáadás."), HttpStatus.OK);

    }//Műhely létrehozása

    public ResponseEntity<Object> getMyWorkshop(int tulajid) {

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(tulajid);

        List<WorkshopV4> workshop = workshopV4Repository.findByTulajNev(karakterV4.getIcnev());

        if (workshop != null) {

            List<WorkshopV4> muhelyek = new ArrayList<>();

            for (WorkshopV4 elem : workshop) {
                if (elem.getStatus().equals("aktiv")) {
                    muhelyek.add(elem);
                }
            }

            if (muhelyek.isEmpty()) {
                return new ResponseEntity<>(Collections.singletonMap("error", "Nincs aktív műhelye."), HttpStatus.OK);
            }

            return new ResponseEntity<>(Collections.singletonMap("message", muhelyek), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs műhelye"), HttpStatus.OK);
        }
    }//lekérdezi azt a műhelyt aminek a felhasználó a tulajdonosa

    public ResponseEntity<Object> getWorkshopiworkin(int userid){

        KarakterV4 felhasznalo = karakterV4Repository.findByUserid(userid);

        String ignev = felhasznalo.getIcnev();

        List<WorkshopV4> ittdolgozik = workshopV4Repository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev, ignev);

        if (ittdolgozik.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozik sehol."), HttpStatus.OK);
        }

        List<WorkshopV4> workshops = new ArrayList<>();

        for (WorkshopV4 elem : ittdolgozik){
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
