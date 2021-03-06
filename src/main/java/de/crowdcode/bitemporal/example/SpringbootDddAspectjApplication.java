package de.crowdcode.bitemporal.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@EnableSpringConfigured
@SpringBootApplication
public class SpringbootDddAspectjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDddAspectjApplication.class, args);
	}

}
