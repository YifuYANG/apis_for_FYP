package service.scenario_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import service.scenario_1.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.trustdvice = :trustdvice")
    User findByUserBytrustdvice(@Param("trustdvice") String trustdvice);
}
