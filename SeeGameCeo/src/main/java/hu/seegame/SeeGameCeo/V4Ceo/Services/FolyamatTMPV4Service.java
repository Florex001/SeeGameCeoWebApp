package hu.seegame.SeeGameCeo.V4Ceo.Services;

import hu.seegame.SeeGameCeo.V4Ceo.Models.FolyamatTMPV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.JobV4;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.FolyamatTMPV4Repository;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.JobV4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FolyamatTMPV4Service {

    @Autowired
    private FolyamatTMPV4Repository folyamatTMPV4Repository;

    @Autowired
    private JobV4Repository jobV4Repository;

    public ResponseEntity<Object> createProcess(int jobid, FolyamatTMPV4 folyamatTMPV4){
        Optional<JobV4> jobV4 = jobV4Repository.findById(jobid);

        if (jobV4.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nincs ilyen munka."), HttpStatus.OK);
        }

        folyamatTMPV4.setMunkaid(jobid);

        System.out.println(folyamatTMPV4);

        folyamatTMPV4Repository.save(folyamatTMPV4);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen hozzáadott folyamat."), HttpStatus.OK);
    }//Folyamatok ideiglenes eltárolása véglegesítésig

    public ResponseEntity<Object> getAllProcessByJob(int jobid){

        List<FolyamatTMPV4> folyamatok = folyamatTMPV4Repository.findByMunkaid(jobid);

        if (folyamatok.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Adj hozzá folyamatot."), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.singletonMap("message", folyamatok), HttpStatus.OK);

    }//Munkához tartozó folyamatok lekérése

    public ResponseEntity<Object> deleteProcess(int id){

        Optional<FolyamatTMPV4> folyamatTMPV4 = folyamatTMPV4Repository.findById(id);

        if (folyamatTMPV4.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("error", "Nem törölheted mert nincs ilyen munka folyamat."), HttpStatus.OK);
        }

        folyamatTMPV4Repository.deleteById(id);

        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen eltávolította a folyamatot."), HttpStatus.OK);

    }//munkafolyamat törlés

}
