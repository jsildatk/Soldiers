package org.soldiers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.soldiers.repository")
public class SoldiersApplication {
	public static void main(String[] args) {
		SpringApplication.run(SoldiersApplication.class, args);
	}
}
