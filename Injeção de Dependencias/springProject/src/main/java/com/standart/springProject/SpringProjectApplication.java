package com.standart.springProject;

import com.standart.springProject.service.CarVehicleService;
import com.standart.springProject.service.VehicleService;
import com.standart.springProject.service.VehicleTestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringProjectApplication.class, args);
		// VehicleService vehicleService = context.getBean(CarVehicleService.class);
		// vehicleService.startEngine();
		// vehicleService.move();
		// vehicleService.stopEngine();

		VehicleTestService vehicleTestService = context.getBean(VehicleTestService.class);
		vehicleTestService.testVehicle();
	}

}
