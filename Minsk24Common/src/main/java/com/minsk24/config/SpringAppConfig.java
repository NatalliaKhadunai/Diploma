package com.minsk24.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Configuration
public class SpringAppConfig {
    @Bean
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("DIPLOMA").createEntityManager();
    }
}
