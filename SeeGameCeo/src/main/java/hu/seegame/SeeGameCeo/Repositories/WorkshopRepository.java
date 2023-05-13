package hu.seegame.SeeGameCeo.Repositories;

import hu.seegame.SeeGameCeo.Models.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

    Workshop findByMuhelyid(int muhelyid);//keresés műhely id alapján

    Workshop findByTulajId(int id);//keresés tulajid alapján

    List<Workshop> findByDolgozo1OrDolgozo2OrDolgozo3OrDolgozo4OrDolgozo5OrDolgozo6OrDolgozo7OrDolgozo8OrDolgozo9OrDolgozo10OrDolgozo11OrDolgozo12(String dolgozo1, String dolgozo2, String dolgozo3, String dolgozo4, String dolgozo5, String dolgozo6, String dolgozo7, String dolgozo8, String dolgozo9, String dolgozo10, String dolgozo11, String dolgozo12);//keresi az összes olyan műhelyt amiben dolgozó1 től 12 ig szerepel az ő ic neve

}
