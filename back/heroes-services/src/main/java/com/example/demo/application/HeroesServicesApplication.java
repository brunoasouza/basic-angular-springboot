package com.example.demo.application;

import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.application.bean.Hero;
import com.example.demo.application.repository.HeroRepository;

@SpringBootApplication
public class HeroesServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroesServicesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(HeroRepository heroRepository) {
		return args -> {
			Stream.of("Dr Nice", "Narco", "Bombasto", "Celeritas", "Magneta").forEach(name -> {
				Hero hero = new Hero(name);
				heroRepository.save(hero);
			});
			heroRepository.findAll().forEach(System.out::println);
		};
	}
}
