package me.ticketing_system;

import java.util.Scanner;

import me.ticketing_system.cli.CliUtilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.ticketing_system.cli.Cli;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		Cli.init();
		String option = CliUtilities.readStringInput("Would you like to run CLI (C) or SpringBoot (S)")
									.toUpperCase();
		if (option.equals("C")) {

		} else if (option.equals("S")) {
			SpringApplication.run(App.class, args);
		} else {
			System.out.println("Invalid option, please try again");
			System.exit(0);
		}
	}
}
