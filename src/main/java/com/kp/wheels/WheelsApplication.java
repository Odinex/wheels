package com.kp.wheels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class WheelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WheelsApplication.class, args);
	}

}
