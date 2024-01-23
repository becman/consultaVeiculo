package com.example.consultaVeiculos;

import com.example.consultaVeiculos.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultaVeiculosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaVeiculosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();

		principal.exibeMenu();
	}
}
