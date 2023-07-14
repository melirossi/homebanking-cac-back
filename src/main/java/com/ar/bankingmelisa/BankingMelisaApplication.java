package com.ar.bankingmelisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
The @SpringBootApplication annotation is a combination of multiple annotations:
 - @SpringBootConfiguration: Specifies that this class is a configuration class for Spring Boot.
 - @EnableAutoConfiguration: Enables autoconfiguration of the Spring application context.
 - @ComponentScan: Enables component scanning to discover and register beans within the specified packages.
 */
@SpringBootApplication
/*
The @EnableJpaRepositories annotation enables JPA repositories in the application.
It scans the specified packages for repositories and configures them to work with the Spring Data JPA framework.
 */
@EnableJpaRepositories
public class BankingMelisaApplication {
	public static void main(String[] args) {
		// Main method that starts the Spring Boot application:
		SpringApplication.run(BankingMelisaApplication.class, args);
	}
}





