package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerRestApiApplication {

	public static void main(String[] args)
	{
		System.out.println("This is my customer rest api");
		SpringApplication.run(CustomerRestApiApplication.class, args);
	}

}
