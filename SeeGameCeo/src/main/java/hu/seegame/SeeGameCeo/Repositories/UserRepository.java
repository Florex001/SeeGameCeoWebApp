package hu.seegame.SeeGameCeo.Repositories;

import hu.seegame.SeeGameCeo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);//keresés felhasználónév alapján

    User findByAccountid(int accid);//keresés accountid alapján

    User findByIcnev(String name);//keresés ic név alapján
}
