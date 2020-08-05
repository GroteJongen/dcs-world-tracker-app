package com.biszczak.unilowski.marek.dcsworldtrackerapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DcsWorldTrackerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcsWorldTrackerAppApplication.class, args);
		//TODO create quick test data loaders
		//TODO add exceptions and encapsulate all data save/read
		//TODO Start thinking of implementing spring security into this project
		//TODO Make also stats calculator for each player etc how many kills, deaths and stuff.

	}
}
