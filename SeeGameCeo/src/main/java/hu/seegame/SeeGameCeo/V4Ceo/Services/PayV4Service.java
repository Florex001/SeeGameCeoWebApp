package hu.seegame.SeeGameCeo.V4Ceo.Services;

import hu.seegame.SeeGameCeo.V4Ceo.Models.FolyamatTMPV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.JobV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import hu.seegame.SeeGameCeo.V4Ceo.Models.PayV4;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.FolyamatTMPV4Repository;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.JobV4Repository;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.KarakterV4Repository;
import hu.seegame.SeeGameCeo.V4Ceo.Repositories.PayV4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PayV4Service {

    @Autowired
    private PayV4Repository payV4Repository;

    @Autowired
    private JobV4Repository jobV4Repository;

    @Autowired
    private FolyamatTMPV4Repository folyamatTMPV4Repository;

    @Autowired
    private KarakterV4Repository karakterV4Repository;

    public ResponseEntity<Object> fizetesSzamolas(int munkaid, int userid){

        KarakterV4 karakter = karakterV4Repository.findByUserid(userid);
        String icnev = karakter.getIcnev();

        Optional<JobV4> munka = jobV4Repository.findById(munkaid);

        int cs = 0;
        int a = 0;
        int f = 0;

        int CSK = 1;
        int AK = 2;
        int FK = 3;

        double CSSZ = 0.33;
        double ASZ = 0.285;
        double FSZ = 0.285;

        if (munka.isPresent() && munka.get().getStatus().equals("aktiv")){

            List<FolyamatTMPV4> folyamatok = folyamatTMPV4Repository.findByMunkaid(munka.get().getId());

            int munkaosszFizetes = munka.get().getAutoar();

            double CSTMP = munkaosszFizetes * CSSZ;
            double ATMP = munkaosszFizetes * ASZ;
            double FTMP = munkaosszFizetes * FSZ;

            for (FolyamatTMPV4 elem : folyamatok){
                if (elem.getFolyamat() == CSK){
                    cs++;
                }else if (elem.getFolyamat() == AK){
                    a++;
                }else if (elem.getFolyamat() == FK){
                    f++;
                }
            }//Kiszámoljuk hányan csináltak 1 folyamatot

            if (cs != 0 && a !=0 && f != 0){

                int csiszFiz = (int) (CSTMP / cs);
                int alapFiz = (int) (ATMP / a);
                int fenyFiz = (int) (FTMP / f);

                for (FolyamatTMPV4 elem : folyamatok){
                    if (elem.getFolyamat() == CSK){
                        PayV4 csiszolo = new PayV4();
                        csiszolo.setMunkaid(elem.getMunkaid());
                        csiszolo.setIcnev(elem.getIcnev());
                        csiszolo.setFolyamat(elem.getFolyamat());
                        csiszolo.setFizetes(csiszFiz);
                        csiszolo.setFizetve(false);
                        payV4Repository.save(csiszolo);
                        folyamatTMPV4Repository.deleteById(elem.getId());
                    }

                    if (elem.getFolyamat() == AK){
                        PayV4 alapozo = new PayV4();
                        alapozo.setMunkaid(elem.getMunkaid());
                        alapozo.setIcnev(elem.getIcnev());
                        alapozo.setFolyamat(elem.getFolyamat());
                        alapozo.setFizetes(alapFiz);
                        alapozo.setFizetve(false);
                        payV4Repository.save(alapozo);
                        folyamatTMPV4Repository.deleteById(elem.getId());
                    }

                    if (elem.getFolyamat() == FK){
                        PayV4 fenyezo = new PayV4();
                        fenyezo.setMunkaid(elem.getMunkaid());
                        fenyezo.setIcnev(elem.getIcnev());
                        fenyezo.setFolyamat(elem.getFolyamat());
                        fenyezo.setFizetes(fenyFiz);
                        fenyezo.setFizetve(false);
                        payV4Repository.save(fenyezo);
                        folyamatTMPV4Repository.deleteById(elem.getId());
                    }

                }

                JobV4 frissitett = munka.get();

                frissitett.setLeadta(icnev);
                frissitett.setStatus("deaktiv");
                jobV4Repository.save(frissitett);

                return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen elvégeztétek a munkát."), HttpStatus.OK);

            }else {
                return new ResponseEntity<>(Collections.singletonMap("error", "Nincs befejezve a munka."), HttpStatus.OK);
            }

        }

        return new ResponseEntity<>(Collections.singletonMap("error", "Nincs ilyen munka."), HttpStatus.OK);
    }//kiszámolja a fizetését azoknak akik dolgoztak az adott munkán

    public ResponseEntity<Object> getPay(int munkaid){

        List<PayV4> fizetesek = payV4Repository.findByMunkaid(munkaid);

        return new ResponseEntity<>(Collections.singletonMap("message", fizetesek), HttpStatus.OK);
    }//ki listázza az adott munkához tartozó fizetéseket

    public ResponseEntity<Object> fizetveVanE(int payid){

        Optional<PayV4> fizetes = payV4Repository.findById(payid);

        if (fizetes.isPresent()){
            PayV4 fizetesObj = fizetes.get();
            if (fizetesObj.isFizetve()){
                fizetesObj.setFizetve(false);
                payV4Repository.save(fizetesObj);
            }else {
                fizetesObj.setFizetve(true);
                payV4Repository.save(fizetesObj);
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("message", "Sikeresen módosítva."), HttpStatus.OK);

    }//ha rá kattint a ki listázottra akkor a fizetése true vagy false lesz

}
