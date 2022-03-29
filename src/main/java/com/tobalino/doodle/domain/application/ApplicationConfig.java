package com.tobalino.doodle.domain.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "doo_config")
@SequenceGenerator(name = "doo_config_sq", sequenceName = "doo_config_sq", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class ApplicationConfig {

    @Id
    @Column(name = "config_id")
    @GeneratedValue(generator = "doo_config_sq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "config_name", nullable = false)
    private String name;

    @Column(name = "config_value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private Application application;
}
