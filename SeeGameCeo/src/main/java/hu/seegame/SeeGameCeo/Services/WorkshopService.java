package hu.seegame.SeeGameCeo.Services;

import hu.seegame.SeeGameCeo.Models.Workshop;
import hu.seegame.SeeGameCeo.Repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    public ResponseEntity<Object> createWorkshop(Workshop workshop, int tulaj_id){

        workshop.setTulajId(tulaj_id);
        workshop.setStatus("aktiv");

        Workshop muhelyidvan = workshopRepository.findByMuhelyid(workshop.getMuhelyid());
        Workshop tulajid = workshopRepository.findByTulajId(workshop.getTulajId());

        if (muhelyidvan != null){
            return new ResponseEntity<>(Collections.singletonMap("message", "Már létezik ezzel a műhelyid-val műhely."), HttpStatus.CONFLICT);
        }

        if (tulajid != null){
            return new ResponseEntity<>(Collections.singletonMap("message", "Már van egy műhely a tulajdonodban."), HttpStatus.CONFLICT);

        }

        workshopRepository.save(workshop);
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeres műhely hozzáadás."), HttpStatus.OK);

    }

}
