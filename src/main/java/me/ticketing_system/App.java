package me.ticketing_system;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.ticketing_system.cli.Cli;

@SpringBootApplication
public class App {
	private static Cli cli = new Cli();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
