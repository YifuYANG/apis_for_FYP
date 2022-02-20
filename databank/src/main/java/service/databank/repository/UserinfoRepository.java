package service.databank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.databank.entity.UserInfo;

import java.util.List;

@Repository
public interface UserinfoRepository extends JpaRepository<UserInfo, Integer> {
    @Query("select u from UserInfo u where u.driverlicense = :driverlicense")
    List<UserInfo> findByDriverLicense(@Param("driverlicense") String driverlicense);
}
