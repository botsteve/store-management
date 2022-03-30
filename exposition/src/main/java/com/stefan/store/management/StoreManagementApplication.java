package com.stefan.store.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.stefan.store.management.entities")
@EnableJpaRepositories("com.stefan.store.management.repositories")
public class StoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);
	}
}