package service.scenario_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import service.scenario_2.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = :username")
    User findByUserbyusername(@Param("username") String username);

    @Query("select u from User u where u.id = :id")
    User findByUserbyuserid(@Param("id") int id);
}
