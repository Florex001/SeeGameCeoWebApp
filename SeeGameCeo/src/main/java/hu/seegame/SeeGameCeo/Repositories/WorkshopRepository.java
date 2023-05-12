package hu.seegame.SeeGameCeo.Repositories;

import hu.seegame.SeeGameCeo.Models.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

    Workshop findByMuhelyid(int muhelyid);

    Workshop findByTulajId(int id);

}
