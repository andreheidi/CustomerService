package br.com.gasit.enderecador;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
		"br.com.gasit.enderecador.entity",
		"br.com.gasit.enderecador.repository",
		"br.com.gasit.enderecador.repository.impl",
		"br.com.gasit.enderecador.service.impl"
		}
)
public class Enderecador {
	
	private final Logger log = Logger.getLogger(Enderecador.class);
	
	@Bean
	public ApplicationContext configure() {
		log.info("Carregando configuracao do Spring");
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
		
		log.info("Registrando classe Enderecador");
		config.register(Enderecador.class);
		
		log.info("Configuracao iniciada com sucesso");
		return config;
	}

}
