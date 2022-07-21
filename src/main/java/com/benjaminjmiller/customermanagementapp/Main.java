package com.benjaminjmiller.customermanagementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:services.xml")
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        CustomerManagementApp customerManagementApp = applicationContext.getBean(CustomerManagementApp.class);
        customerManagementApp.processCommand(args);
    }
}
