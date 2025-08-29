package com.keep.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Notes Service Spring Boot application.
 * Starts the embedded server and initializes the application context.
 */
@SpringBootApplication
public class NotesServiceApplication {

	/**
	 * Application entry point.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(NotesServiceApplication.class, args);
	}

}
