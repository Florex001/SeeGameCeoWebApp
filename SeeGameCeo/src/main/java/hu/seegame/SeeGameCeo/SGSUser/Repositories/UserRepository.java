package hu.seegame.SeeGameCeo.SGSUser.Repositories;

import hu.seegame.SeeGameCeo.SGSUser.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);//keresés felhasználónév alapján
    User findByEmail(String email);//keresés email alapján

}
