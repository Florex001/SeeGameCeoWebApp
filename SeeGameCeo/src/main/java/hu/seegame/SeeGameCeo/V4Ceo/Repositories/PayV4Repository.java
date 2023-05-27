package hu.seegame.SeeGameCeo.V4Ceo.Repositories;

import hu.seegame.SeeGameCeo.V4Ceo.Models.PayV4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayV4Repository extends JpaRepository<PayV4, Integer> {

    List<PayV4> findByMunkaid(int id);

}
