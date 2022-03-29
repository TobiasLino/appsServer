package com.tobalino.doodle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = "com.tobalino.doodle.domain", enableDefaultTransactions = false)
public class JpaTestConfig {

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return adapter;
    }

    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter,
                                                                           JpaDialect jpaDialect,
                                                                           DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setDataSource(dataSource);
        entityManager.setJpaDialect(jpaDialect);
        entityManager.setPackagesToScan("com.tobalino.doodle.domain");

        var properties = new Properties();
        properties.putAll(jpaVendorAdapter.getJpaPropertyMap());

        entityManager.setJpaProperties(properties);
        return entityManager;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(JpaDialect jpaDialect,
                                                         EntityManagerFactory factory,
                                                         DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setJpaDialect(jpaDialect);
        transactionManager.setEntityManagerFactory(factory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
