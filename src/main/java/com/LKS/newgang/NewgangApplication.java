package com.LKS.newgang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NewgangApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewgangApplication.class, args);
	}
}
