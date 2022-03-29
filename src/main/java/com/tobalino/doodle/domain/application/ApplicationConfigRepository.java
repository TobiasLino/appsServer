package com.tobalino.doodle.domain.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationConfigRepository extends JpaRepository<ApplicationConfig, Long> {

    List<ApplicationConfig> findAllByApplicationId(Long applicationId);
}
