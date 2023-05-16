package hu.seegame.SeeGameCeo.V4Ceo.Repositories;

import hu.seegame.SeeGameCeo.V4Ceo.Models.WorkshopV4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopV4Repository extends JpaRepository<WorkshopV4, Integer> {

    List<WorkshopV4> findByMuhelyid(int muhelyid);//keresés műhely id alapján

    List<WorkshopV4> findByTulajNev(String icnev);//keresés tulajid alapján

    List<WorkshopV4> findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(String dolgozo1, String dolgozo2, String dolgozo3, String dolgozo4, String dolgozo5, String dolgozo6, String dolgozo7, String dolgozo8, String dolgozo9, String dolgozo10, String dolgozo11, String dolgozo12);//keresi az összes olyan műhelyt amiben dolgozó1 től 12 ig szerepel az ő ic neve

}
