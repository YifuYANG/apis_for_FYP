package service.scenario_1.entity;


import lombok.Data;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@Data
@Entity
public class UploadedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private double currentLatitude;
    @Column
    private double currentLongitude;
    @Column
    private double targetLatitude;
    @Column
    private double targetLongitude;
    @Column
    private double distance;
}
