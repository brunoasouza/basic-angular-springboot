package com.example.demo.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.bean.Hero;
import com.example.demo.application.service.HeroService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/heroes")
public class HeroController {

	@Autowired
	private HeroService heroService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Hero> allUsers() {
		return heroService.getHeroes();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addHero(@RequestBody Hero hero) {
		heroService.addHero(hero);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public void update(@RequestBody Hero hero) {
		heroService.addHero(hero);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void findById(@PathVariable Long id) {
		heroService.removeHero(id);
	}

}
