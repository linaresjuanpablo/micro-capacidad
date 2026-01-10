package com.example.capacidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(
		basePackages = "com.example.capacidad.infra.output.r2dbc.repository"
)
public class CapacidadApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapacidadApplication.class, args);
	}

}
