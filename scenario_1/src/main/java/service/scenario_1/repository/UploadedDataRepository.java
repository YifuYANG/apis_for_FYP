package service.scenario_1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.scenario_1.entity.UploadedData;

@Repository
public interface UploadedDataRepository extends JpaRepository<UploadedData, Integer> {
}
