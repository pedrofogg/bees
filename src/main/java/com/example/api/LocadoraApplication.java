package com.example.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocadoraApplication {

	  private static final Logger logger = LogManager.getLogger(LocadoraApplication.class);
	
	public static void main(String[] args) {
		logger.debug("Bem vindo");
		SpringApplication.run(LocadoraApplication.class, args);
	}

}
