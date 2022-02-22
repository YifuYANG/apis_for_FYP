package service.scenario_2.entity;


import lombok.Data;
import service.scenario_2.constant.UserLevel;


import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    UserLevel userLevel;
}
