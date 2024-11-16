package com.iud.supermercado;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupermercadoApplication {
	private static final Dotenv dotenv = Dotenv.load();

	static {
		// Setting environment variables as system properties
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("USER_DB", dotenv.get("USER_DB"));
		System.setProperty("PASSWORD_DB", dotenv.get("PASSWORD_DB"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SupermercadoApplication.class, args);
	}

}
