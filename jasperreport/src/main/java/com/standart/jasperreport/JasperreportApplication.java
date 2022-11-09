package com.standart.jasperreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JasperreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasperreportApplication.class, args);
	}

}
