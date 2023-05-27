package hu.seegame.SeeGameCeo.V4Ceo.Services;

import hu.seegame.SeeGameCeo.V4Ceo.Models.JobV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.WorkshopV4;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.JobV4Repository;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.KarakterV4Repository;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.WorkshopV4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JobV4Service {

    @Autowired
    private JobV4Repository jobV4Repository;

    @Autowired
    private WorkshopV4Repository workshopV4Repository;

    @Autowired
    private KarakterV4Repository karakterV4Repository;

    public ResponseEntity<Object> createJob(int muhelyid, JobV4 job, int userid){

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(userid);

        String icnev = karakterV4.getIcnev();

        List<WorkshopV4> muhelybedolgozik = workshopV4Repository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev);
        Optional<WorkshopV4> workshop = workshopV4Repository.findById(muhelyid);
        List<WorkshopV4> ove = workshopV4Repository.findByTulajNev(icnev);

        List<JobV4> munkak = jobV4Repository.findByMuhelyId(muhelyid);


        if (workshop.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs ilyen műhely."), HttpStatus.OK);
        }

        WorkshopV4 muhelyben = null;

        if (!muhelybedolgozik.isEmpty()){
            for (WorkshopV4 elem : muhelybedolgozik){
                if (elem.getId() == muhelyid && elem.getStatus().equals("aktiv")){
                    muhelyben = elem;
                }
            }
        }

        if (muhelyben != null && muhelyben.getId() == muhelyid && muhelyben.getStatus().equals("aktiv")){

            int szamlalo = 0;

            if (munkak != null){
                for (JobV4 elem : munkak){
                    if (elem.getStatus().equals("aktiv")){
                        szamlalo++;
                    }
                }
            }

            if (szamlalo >= 2){
                return new ResponseEntity<>(Collections.singletonMap("error", "Csak két darab elválalt autó lehet a műhelyben."), HttpStatus.OK);
            }

            int autoar = job.getAutoar();

            int anyagkoltseg = (int) (autoar * 0.1);

            int osszfizetes = autoar - anyagkoltseg;


            job.setAnyagkoltseg(anyagkoltseg);
            job.setMuhelyId(muhelyid);
            job.setOsszfizetes(osszfizetes);
            job.setElvalalta(icnev);
            job.setStatus("aktiv");
            jobV4Repository.save(job);
            return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen létrehozta az aktuális munkát."), HttpStatus.OK);
        }

        WorkshopV4 sajat = null;

        if (ove != null){
            for (WorkshopV4 elem : ove){
                if (elem.getStatus().equals("aktiv")){
                    sajat = elem;
                }
            }
        }


        if (sajat != null && sajat.getId() == muhelyid && sajat.getStatus().equals("aktiv")){

            int szamlalo = 0;

            if (munkak != null){
                for (JobV4 elem : munkak){
                    if (elem.getStatus().equals("aktiv")){
                        szamlalo++;
                    }
                }
            }

            if (szamlalo >= 2){
                return new ResponseEntity<>(Collections.singletonMap("error", "Csak két darab elválalt autó lehet a műhelyben."), HttpStatus.OK);
            }

            int autoar = job.getAutoar();

            int anyagkoltseg = (int) (autoar * 0.1);

            int osszfizetes = autoar - anyagkoltseg;


            job.setAnyagkoltseg(anyagkoltseg);
            job.setMuhelyId(muhelyid);
            job.setOsszfizetes(osszfizetes);
            job.setElvalalta(icnev);
            job.setStatus("aktiv");
            jobV4Repository.save(job);
            return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen létrehozta az aktuális munkát."), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozol a műhelyben, vagy a műhely törölve lett."), HttpStatus.OK);

    }//Fényező munka hozzáadása

    public ResponseEntity<Object> getJobByMuhelyAktiv(int muhelyid, int userid){

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(userid);

        String icnev = karakterV4.getIcnev();

        List<WorkshopV4> muhelybedolgozik = workshopV4Repository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev);
        Optional<WorkshopV4> workshop = workshopV4Repository.findById(muhelyid);
        List<WorkshopV4> ove = workshopV4Repository.findByTulajNev(icnev);
        List<JobV4> talalt = jobV4Repository.findByMuhelyId(muhelyid);


        if (workshop.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs ilyen műhely."), HttpStatus.OK);
        }

        WorkshopV4 muhelyben = null;

        if (!muhelybedolgozik.isEmpty()){
            for (WorkshopV4 elem : muhelybedolgozik){
                if (elem.getId() == muhelyid && elem.getStatus().equals("aktiv")){
                    muhelyben = elem;
                }
            }
        }

        if (muhelyben != null && muhelyben.getId() == muhelyid && muhelyben.getStatus().equals("aktiv")) {

            List<JobV4> aktivmunkak = new ArrayList<>();

            for (JobV4 elem: talalt){
                if (elem.getStatus().equals("aktiv")){
                    aktivmunkak.add(elem);
                }
            }


            return new ResponseEntity<>(Collections.singletonMap("message", aktivmunkak), HttpStatus.OK);
        }

        WorkshopV4 sajat = null;

        if (ove != null){
            for (WorkshopV4 elem : ove){
                if (elem.getStatus().equals("aktiv")){
                    sajat = elem;
                }
            }
        }


        if (sajat != null && sajat.getId() == muhelyid && sajat.getStatus().equals("aktiv")){

            List<JobV4> aktivmunkak = new ArrayList<>();

            for (JobV4 elem: talalt){
                if (elem.getStatus().equals("aktiv")){
                    aktivmunkak.add(elem);
                }
            }


            return new ResponseEntity<>(Collections.singletonMap("message", aktivmunkak), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozol a műhelyben, vagy a műhely törölve lett."), HttpStatus.OK);
    }//Műhelyhez tartozó munkák lekérdezése

    public ResponseEntity<Object> getJobByMuhelyDeAktiv(int muhelyid, int userid){

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(userid);

        String icnev = karakterV4.getIcnev();

        List<WorkshopV4> muhelybedolgozik = workshopV4Repository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev);
        Optional<WorkshopV4> workshop = workshopV4Repository.findById(muhelyid);
        List<WorkshopV4> ove = workshopV4Repository.findByTulajNev(icnev);
        List<JobV4> talalt = jobV4Repository.findByMuhelyId(muhelyid);


        if (workshop.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs ilyen műhely."), HttpStatus.OK);
        }

        WorkshopV4 muhelyben = null;

        if (!muhelybedolgozik.isEmpty()){
            for (WorkshopV4 elem : muhelybedolgozik){
                if (elem.getId() == muhelyid && elem.getStatus().equals("aktiv")){
                    muhelyben = elem;
                }
            }
        }

        if (muhelyben != null && muhelyben.getId() == muhelyid && muhelyben.getStatus().equals("aktiv")) {

            List<JobV4> deaktivmunkak = new ArrayList<>();

            for (JobV4 elem: talalt){
                if (elem.getStatus().equals("deaktiv")){
                    deaktivmunkak.add(elem);
                }
            }


            return new ResponseEntity<>(Collections.singletonMap("message", deaktivmunkak), HttpStatus.OK);
        }

        WorkshopV4 sajat = null;

        if (ove != null){
            for (WorkshopV4 elem : ove){
                if (elem.getStatus().equals("aktiv")){
                    sajat = elem;
                }
            }
        }


        if (sajat != null && sajat.getId() == muhelyid && sajat.getStatus().equals("aktiv")){

            List<JobV4> deaktivmunkak = new ArrayList<>();

            for (JobV4 elem: talalt){
                if (elem.getStatus().equals("deaktiv")){
                    deaktivmunkak.add(elem);
                }
            }


            return new ResponseEntity<>(Collections.singletonMap("message", deaktivmunkak), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozol a műhelyben, vagy a műhely törölve lett."), HttpStatus.OK);
    }//Műhelyhez tartozó munkák lekérdezése

}
