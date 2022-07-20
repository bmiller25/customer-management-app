package com.benjaminjmiller.customermanagementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("file:services.xml")
public class SpringStarter {

	public static void main(String[] args) {
		SpringApplication.run(SpringStarter.class, args);
	}

}
