package com.boilerr.coffeetablerest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoffeeTableRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeTableRestApplication.class, args);
	}

}
