package com.smarfat.webapp.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.smarfat.webapp.gym.system.Main;

import javafx.application.Application;

@SpringBootApplication
public class GymApplication {

	public static void main(String[] args) {
		Application.launch(Main.class, args);

		SpringApplication.run(GymApplication.class, args);
	}

}
