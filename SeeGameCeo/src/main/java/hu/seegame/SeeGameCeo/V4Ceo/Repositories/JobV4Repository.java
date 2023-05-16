package hu.seegame.SeeGameCeo.V4Ceo.Repositories;

import hu.seegame.SeeGameCeo.V4Ceo.Models.JobV4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobV4Repository extends JpaRepository<JobV4, Integer> {

    List<JobV4> findByMuhelyId(int id);//műhely munkák között keres a műhelyid alapján

}
