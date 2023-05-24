package hu.seegame.SeeGameCeo.V4Ceo.Services;

import hu.seegame.SeeGameCeo.V4Ceo.Models.FolyamatTMPV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.JobV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.WorkshopV4;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.FolyamatTMPV4Repository;
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
public class FolyamatTMPV4Service {

    @Autowired
    private FolyamatTMPV4Repository folyamatTMPV4Repository;

    @Autowired
    private JobV4Repository jobV4Repository;

    @Autowired
    private KarakterV4Repository karakterV4Repository;

    @Autowired
    private WorkshopV4Repository workshopV4Repository;

    public ResponseEntity<Object> createProcess(int jobid, FolyamatTMPV4 folyamatTMPV4, int userid){

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(userid);

        String icnev = karakterV4.getIcnev();

        List<WorkshopV4> muhelybedolgozik = workshopV4Repository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev);
        List<WorkshopV4> ove = workshopV4Repository.findByTulajNev(icnev);
        Optional<JobV4> jobV4 = jobV4Repository.findById(jobid);

        if (jobV4.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs ilyen munka."), HttpStatus.OK);
        }

        if (!muhelybedolgozik.isEmpty()){
            for (WorkshopV4 elem : muhelybedolgozik){
                if (elem.getId() == jobV4.get().getMuhelyId() && elem.getStatus().equals("aktiv")){
                    folyamatTMPV4.setMunkaid(jobid);

                    folyamatTMPV4Repository.save(folyamatTMPV4);
                    return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen hozzáadott folyamat."), HttpStatus.OK);
                }
            }
        }

        if (ove != null){
            for (WorkshopV4 elem : ove){
                if (elem.getStatus().equals("aktiv") && elem.getId() == jobV4.get().getMuhelyId() && elem.getTulajNev().equals(icnev)){
                    folyamatTMPV4.setMunkaid(jobid);

                    folyamatTMPV4Repository.save(folyamatTMPV4);
                    return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen hozzáadott folyamat."), HttpStatus.OK);
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("message", "Nem dolgozol a műhelyben ezért nem tudsz folyamatot hozzáadni."), HttpStatus.OK);

    }//Folyamatok ideiglenes eltárolása véglegesítésig

    public ResponseEntity<Object> getAllProcessByJob(int jobid, int userid){

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(userid);

        List<FolyamatTMPV4> folyamatok = folyamatTMPV4Repository.findByMunkaid(jobid);

        String icnev = karakterV4.getIcnev();

        List<WorkshopV4> muhelybedolgozik = workshopV4Repository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev);
        List<WorkshopV4> ove = workshopV4Repository.findByTulajNev(icnev);
        Optional<JobV4> jobV4 = jobV4Repository.findById(jobid);

        if (folyamatok.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Adj hozzá folyamatot."), HttpStatus.OK);
        }

        if (!muhelybedolgozik.isEmpty()){
            for (WorkshopV4 elem : muhelybedolgozik){
                if (elem.getId() == jobV4.get().getMuhelyId() && elem.getStatus().equals("aktiv")){
                    return new ResponseEntity<>(Collections.singletonMap("message", folyamatok), HttpStatus.OK);
                }
            }
        }

        if (ove != null){
            for (WorkshopV4 elem : ove){
                if (elem.getStatus().equals("aktiv") && elem.getId() == jobV4.get().getMuhelyId() && elem.getTulajNev().equals(icnev)){
                    return new ResponseEntity<>(Collections.singletonMap("message", folyamatok), HttpStatus.OK);
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozol a műhelyben."), HttpStatus.OK);

    }//Munkához tartozó folyamatok lekérése

    public ResponseEntity<Object> deleteProcess(int id, int userid){

        Optional<FolyamatTMPV4> folyamatTMPV4 = folyamatTMPV4Repository.findById(id);

        KarakterV4 karakterV4 = karakterV4Repository.findByUserid(userid);

        Optional<FolyamatTMPV4> folyamatok = folyamatTMPV4Repository.findById(id);

        if (!folyamatok.isEmpty()){
            int jobid = folyamatok.get().getMunkaid();

            String icnev = karakterV4.getIcnev();

            List<WorkshopV4> muhelybedolgozik = workshopV4Repository.findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev, icnev);
            List<WorkshopV4> ove = workshopV4Repository.findByTulajNev(icnev);
            Optional<JobV4> jobV4 = jobV4Repository.findById(jobid);

            System.out.println(icnev);

            if (folyamatTMPV4.isEmpty()){
                return new ResponseEntity<>(Collections.singletonMap("error", "Nem törölheted mert nincs ilyen munka folyamat."), HttpStatus.OK);
            }

            if (!muhelybedolgozik.isEmpty()){
                for (WorkshopV4 elem : muhelybedolgozik){
                    if (elem.getId() == jobV4.get().getMuhelyId() && elem.getStatus().equals("aktiv")){
                        folyamatTMPV4Repository.deleteById(id);

                        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen eltávolította a folyamatot."), HttpStatus.OK);
                    }
                }
            }

            if (ove != null){
                for (WorkshopV4 elem : ove){
                    if (elem.getStatus().equals("aktiv") && elem.getId() == jobV4.get().getMuhelyId() && elem.getTulajNev().equals(icnev)){
                        folyamatTMPV4Repository.deleteById(id);

                        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen eltávolította a folyamatot."), HttpStatus.OK);
                    }
                }
            }
        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nem dolgozol a műhelyben."), HttpStatus.OK);

    }//munkafolyamat törlés

}
