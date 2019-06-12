package com.cts.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@SpringBootApplication
@ComponentScan
public class ProjectManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagerApplication.class, args);
	}

}