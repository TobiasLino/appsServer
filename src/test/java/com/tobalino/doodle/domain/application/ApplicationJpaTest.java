package com.tobalino.doodle.domain.application;

import com.tobalino.doodle.config.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

@JpaTest
public class ApplicationJpaTest {

    @Autowired
    private ApplicationRepository repository;

    @Autowired
    private ApplicationConfigRepository configRepository;

    @Test
    @Sql(value = "/com/tobalino/doodle/scripts/sprint1/clear_database.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    @Sql(value = "/com/tobalino/doodle/scripts/sprint1/insert_simple_applications.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    public void shouldReturnASingleApplication() {
        Application application = repository.findById(1L).orElseThrow(() -> new NullPointerException("sem aplicações"));

        assertThat(application.getId(), equalTo(1L));
        assertThat(application.getName(), equalTo("app1"));
    }

    @Test
    @Sql(value = "/com/tobalino/doodle/scripts/sprint1/clear_database.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    @Sql(value = "/com/tobalino/doodle/scripts/sprint1/insert_simple_applications.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    public void shouldReturnAllApplications() {
        List<Application> applications = repository.findAll();

        assertThat(applications.isEmpty(), equalTo(false));
    }

    @Test
    @Sql(value = "/com/tobalino/doodle/scripts/sprint1/clear_database.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    @Sql(value = "/com/tobalino/doodle/scripts/sprint1/insert_simple_applications.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    public void shouldReturnAllConfigsFromASingleApplication() {
        List<ApplicationConfig> configs = configRepository.findAllByApplicationId(1L);

        assertThat(configs.isEmpty(), equalTo(false));
        assertThat(configs.size(), equalTo(4));
    }
}
