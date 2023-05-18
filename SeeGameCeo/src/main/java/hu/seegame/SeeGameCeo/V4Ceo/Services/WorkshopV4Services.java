package hu.seegame.SeeGameCeo.V4Ceo.Services;

import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.WorkshopV4;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.KarakterV4Repository;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.WorkshopV4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

        LocalDateTime datum = workshopV4.getLejarat();

        if (LocalDateTime.now().isAfter(datum)){
            return new ResponseEntity<>(Collections.singletonMap("error", "A dátum nem lehet a mai napnál hamarabbi."), HttpStatus.OK);
        }

        LocalDateTime maxdatum = LocalDateTime.now().plusWeeks(1);

        if (maxdatum.isBefore(datum)){
            return new ResponseEntity<>(Collections.singletonMap("error", "A mai dátumtol maximum 1 hét lehet a lejárat."), HttpStatus.OK);
        }

        workshopV4Repository.save(workshopV4);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres műhely hozzáadás."), HttpStatus.OK);

    }//Műhely létrehozása

    public ResponseEntity<Object> getMyWorkshop(int tulajid) {

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(tulajid);

        List<WorkshopV4> workshop = workshopV4Repository.findByTulajNev(karakterV4.getIcnev());

        WorkshopV4 sajat = new WorkshopV4();

        for (WorkshopV4 elem : workshop) {
            if (elem.getStatus().equals("aktiv")) {
                sajat.setTulajNev(elem.getTulajNev());
                sajat.setStatus(elem.getStatus());
                sajat.setId(elem.getId());
                sajat.setMuhelynev(elem.getMuhelynev());
                sajat.setMuhelyid(elem.getMuhelyid());
                sajat.setLejarat(elem.getLejarat());
                sajat.setDolgozo1(elem.getDolgozo1());
                sajat.setDolgozo2(elem.getDolgozo2());
                sajat.setDolgozo3(elem.getDolgozo3());
                sajat.setDolgozo4(elem.getDolgozo4());
                sajat.setDolgozo5(elem.getDolgozo5());
                sajat.setDolgozo6(elem.getDolgozo6());
                sajat.setDolgozo7(elem.getDolgozo7());
                sajat.setDolgozo8(elem.getDolgozo8());
                sajat.setDolgozo9(elem.getDolgozo9());
                sajat.setDolgozo10(elem.getDolgozo10());
                sajat.setDolgozo11(elem.getDolgozo11());
                sajat.setDolgozo12(elem.getDolgozo12());
            }
        }

        try {

            LocalDateTime sajatlejarat = sajat.getLejarat();

            if (sajatlejarat != null && LocalDateTime.now().isAfter(sajatlejarat)) {
                sajat.setStatus("deaktiv");
                workshopV4Repository.save(sajat);
                return new ResponseEntity<>(Collections.singletonMap("lejart", "Lejárt a műhelyed."), HttpStatus.OK);
            }

        } catch (NullPointerException e) {
            System.out.println("Lejárt a műhely.");
        }

        if (!workshop.isEmpty()){

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
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nincs műhelye."), HttpStatus.OK);

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
                if (!LocalDateTime.now().isAfter(elem.getLejarat())){
                    workshops.add(elem);
                }
            }
        }

        if (workshops.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs aktív műhely amiben dolgozol."),  HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("message", workshops), HttpStatus.OK);

    }//Felhasználó műhelye amiben dolgozik

    public ResponseEntity<Object> getWorkerByWorkshop(int id){
        Optional<WorkshopV4> workshopV4 = workshopV4Repository.findById(id);

        if (workshopV4.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs ilyen műhely"), HttpStatus.OK);
        }

        if (workshopV4.get().getStatus().equals("aktiv")){
            ArrayList<String> dolgozokestulajnev = new ArrayList<>();
            dolgozokestulajnev.add(workshopV4.get().getDolgozo1());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo2());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo3());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo4());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo5());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo6());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo7());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo8());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo9());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo10());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo11());
            dolgozokestulajnev.add(workshopV4.get().getDolgozo12());
            dolgozokestulajnev.add(workshopV4.get().getTulajNev());

            ArrayList<Map<String, String>> lista = new ArrayList<>();

            for (String elem : dolgozokestulajnev){
                if (elem != null){
                    Map<String, String> dolgozokMap = new HashMap<>();
                    dolgozokMap.put("label", elem);
                    dolgozokMap.put("value", elem);
                    lista.add(dolgozokMap);
                }
            }

            return new ResponseEntity<>(Collections.singletonMap("message", lista), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nem aktív a műhely."), HttpStatus.OK);

    }//Munkások lekérdezése az adott műhelyhez

}
