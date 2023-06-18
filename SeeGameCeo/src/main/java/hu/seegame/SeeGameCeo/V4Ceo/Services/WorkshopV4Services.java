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
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class WorkshopV4Services {

    @Autowired
    private WorkshopV4Repository workshopV4Repository;

    @Autowired
    private KarakterV4Repository karakterV4Repository;


    public ResponseEntity<Object> createWorkshop(WorkshopV4 workshopV4, int userid){

        KarakterV4 karakter = karakterV4Repository.findByUserid(userid);

        workshopV4.setTulajNev(String.valueOf(karakter.getId()));
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

        ArrayList<String> dologzok = new ArrayList<>();

        dologzok.addAll(Arrays.asList(
                workshopV4.getDolgozo1(),
                workshopV4.getDolgozo2(),
                workshopV4.getDolgozo3(),
                workshopV4.getDolgozo4(),
                workshopV4.getDolgozo5(),
                workshopV4.getDolgozo6(),
                workshopV4.getDolgozo7(),
                workshopV4.getDolgozo8(),
                workshopV4.getDolgozo9(),
                workshopV4.getDolgozo10(),
                workshopV4.getDolgozo11(),
                workshopV4.getDolgozo12()
        ));

        int index = 0;

        for (String elem : dologzok){
            if (elem != null){
                KarakterV4 kari =karakterV4Repository.findByIcnev(elem);
                if (kari != null){
                    dologzok.set(index, String.valueOf(kari.getId()));
                }
            }
            index++;
        }



        workshopV4.setDolgozo1(dologzok.get(0));
        workshopV4.setDolgozo2(dologzok.get(1));
        workshopV4.setDolgozo3(dologzok.get(2));
        workshopV4.setDolgozo4(dologzok.get(3));
        workshopV4.setDolgozo5(dologzok.get(4));
        workshopV4.setDolgozo6(dologzok.get(5));
        workshopV4.setDolgozo7(dologzok.get(6));
        workshopV4.setDolgozo8(dologzok.get(7));
        workshopV4.setDolgozo9(dologzok.get(8));
        workshopV4.setDolgozo10(dologzok.get(9));
        workshopV4.setDolgozo11(dologzok.get(10));
        workshopV4.setDolgozo12(dologzok.get(11));

        workshopV4Repository.save(workshopV4);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres műhely hozzáadás."), HttpStatus.OK);

    }//Műhely létrehozása

    public ResponseEntity<Object> getMyWorkshop(int tulajid) {

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(tulajid);

        List<WorkshopV4> workshop = workshopV4Repository.findByTulajNev(String.valueOf(karakterV4.getId()));

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

        String ignev = String.valueOf(felhasznalo.getId());

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
                    Optional<KarakterV4> kari = karakterV4Repository.findById(Integer.valueOf(elem));

                    String karakternev = kari.get().getIcnev();

                    Map<String, String> dolgozokMap = new HashMap<>();
                    dolgozokMap.put("label", karakternev);
                    dolgozokMap.put("value", karakternev);
                    lista.add(dolgozokMap);
                }
            }

            return new ResponseEntity<>(Collections.singletonMap("message", lista), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nem aktív a műhely."), HttpStatus.OK);

    }//Munkások lekérdezése az adott műhelyhez

    public ResponseEntity<Object> workshopExtension(int id){
        Optional<WorkshopV4> workshopV4 = workshopV4Repository.findById(id);
        WorkshopV4 muhely = workshopV4.get();

        LocalDateTime time = muhely.getLejarat();

        LocalDateTime now = LocalDateTime.now();

        if (now.until(time, ChronoUnit.DAYS) <= 1){
            LocalDateTime modositott = time.plusWeeks(1);
            muhely.setLejarat(modositott);
            workshopV4Repository.save(muhely);
            return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen meghosszabítottad a műhyelyedet."), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Még nem tudod meg hosszabítani a műhelyedet."), HttpStatus.OK);

    }

}
