package com.tobalino.doodle.domain.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doo_app")
@SequenceGenerator(name = "doo_app_sq", sequenceName = "doo_app_sq", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class Application {

    @Id
    @Column(name = "app_id")
    @GeneratedValue(generator = "doo_app_sq", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "app_name", nullable = false)
    private String name;

    @Column(name = "app_created_date")
    private Date createdDate;
}
