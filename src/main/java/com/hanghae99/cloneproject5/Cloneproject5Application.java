package com.hanghae99.cloneproject5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Cloneproject5Application {

	public static void main(String[] args) {
		SpringApplication.run(Cloneproject5Application.class, args);
	}

}
