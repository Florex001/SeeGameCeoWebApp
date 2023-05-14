package hu.seegame.SeeGameCeo.Services;

import hu.seegame.SeeGameCeo.Models.Job;
import hu.seegame.SeeGameCeo.Models.Workshop;
import hu.seegame.SeeGameCeo.Repositories.JobRepository;
import hu.seegame.SeeGameCeo.Repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    public ResponseEntity<Object> createJob(int muhelyid, Job job, String icnev, int userid){

        List<Workshop> muhelybedolgozik = workshopRepository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev);
        Optional<Workshop> workshop = workshopRepository.findById(muhelyid);
        List<Workshop> ove = workshopRepository.findByTulajId(userid);

        List<Job> munkak = jobRepository.findByMuhelyId(muhelyid);


        if (workshop.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs ilyen műhely."), HttpStatus.OK);
        }

        Workshop muhelyben = null;

        if (!muhelybedolgozik.isEmpty()){
            for (Workshop elem : muhelybedolgozik){
                if (elem.getId() == muhelyid){
                    muhelyben = elem;
                }
            }
        }

        if (muhelyben != null && muhelyben.getId() == muhelyid && muhelyben.getStatus().equals("aktiv")){

            int szamlalo = 0;

            if (munkak != null){
                for (Job elem : munkak){
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
            job.setStatus("aktiv");
            jobRepository.save(job);
            return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen létrehozta az aktuális munkát."), HttpStatus.OK);
        }

        Workshop sajat = null;

        if (ove != null){
            for (Workshop elem : ove){
                if (elem.getStatus().equals("aktiv")){
                    sajat = elem;
                }
            }
        }


        if (sajat != null && sajat.getId() == muhelyid && sajat.getStatus().equals("aktiv")){

            int szamlalo = 0;

            if (munkak != null){
                for (Job elem : munkak){
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
            job.setStatus("aktiv");
            jobRepository.save(job);
            return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen létrehozta az aktuális munkát."), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozol a műhelyben, vagy a műhely törölve lett."), HttpStatus.OK);

    }//Fényező munka hozzáadása

}
