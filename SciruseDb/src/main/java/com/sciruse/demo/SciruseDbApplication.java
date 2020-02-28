package com.sciruse.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EntityScan( basePackages = {"com.sciruse.models"} )
@ComponentScan( basePackages = {"com.sciruse.contoler"} )
@EnableJpaRepositories("com.sciruse.repository")

public class SciruseDbApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(SciruseDbApplication.class, args);
				
	}

	
	
}
