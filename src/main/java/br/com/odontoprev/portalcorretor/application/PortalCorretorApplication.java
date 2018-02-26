package br.com.odontoprev.portalcorretor.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.odontoprev.portalcorretor")
public class PortalCorretorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalCorretorApplication.class, args);
	}
}
