package hu.seegame.SeeGameCeo.V4Ceo.Repositories;

import hu.seegame.SeeGameCeo.V4Ceo.Models.KarakterV4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarakterV4Repository extends JpaRepository<KarakterV4, Integer> {

    KarakterV4 findByUserid(int userid);

    KarakterV4 findByIcnev(String icnev);

}
