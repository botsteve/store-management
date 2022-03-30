package com.stefan.store.management;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.stefan.store.management.repositories")
@EntityScan(basePackages = "com.stefan.store.management.entities")
@EnableJpaRepositories(basePackages = "com.stefan.store.management.repositories")
public class TestContextConfiguration {
}
