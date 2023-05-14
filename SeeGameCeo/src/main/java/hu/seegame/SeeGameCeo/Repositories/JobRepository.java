package hu.seegame.SeeGameCeo.Repositories;

import hu.seegame.SeeGameCeo.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByMuhelyId(int id);//műhely munkák között keres a műhelyid alapján

}
