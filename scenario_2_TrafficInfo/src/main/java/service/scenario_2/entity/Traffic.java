package service.scenario_2.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Traffic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String road;
    @Column
    private Boolean isbussy;
}
