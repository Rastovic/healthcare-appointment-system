package com.main.app;

import org.springframework.boot.SpringApplication;

public class TestHealthcareAppointmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(HealthcareAppointmentSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
