package service.databank.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String driverlicense;
    @Column
    private String carplate;

}
