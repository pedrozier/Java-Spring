package com.standart.springproject;

import com.standart.springproject.services.ServiceTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringprojectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringprojectApplication.class, args);
		ServiceTest serviceTest = context.getBean(ServiceTest.class);
		serviceTest.teste();
	}

}
