package service.scenario_3.entity;


import lombok.Data;
import service.scenario_3.constant.UserLevel;


import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String trustdvice;
    @Column
    private String password;
    @Column
    UserLevel userLevel;
}
