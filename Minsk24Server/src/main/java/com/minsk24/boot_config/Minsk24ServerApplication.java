package com.minsk24.boot_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.minsk24"})
public class Minsk24ServerApplication {

	public static void main(String[] args) {
		System.out.println("WORKING DIRECTORY = " +
				System.getProperty("user.dir"));
		SpringApplication.run(Minsk24ServerApplication.class, args);
	}
}
