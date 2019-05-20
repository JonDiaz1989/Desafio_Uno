package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.client"})
public class DemoRcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRcApplication.class, args);
		
		
	}

}
