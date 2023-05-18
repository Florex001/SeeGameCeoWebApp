package hu.seegame.SeeGameCeo.V4Ceo.Repositories;

import hu.seegame.SeeGameCeo.V4Ceo.Models.FolyamatTMPV4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolyamatTMPV4Repository extends JpaRepository<FolyamatTMPV4, Integer> {

    List<FolyamatTMPV4> findByMunkaid(int jobid);

}
