package com.nick.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.jpa.City;
import com.nick.jpa.CityDTO;
import com.nick.jpa.CityService;

@RestController
public class JpaController {

	@Autowired
	CityService cityService;

	@RequestMapping("/cities")
	public List<City> getCities() {

		return cityService.findAll();
	}

	@PostConstruct
	public String createCities() {

		for (int i = 0; i < 300; i++) {
			CityDTO created = new CityDTO();
			created.setName("Name" + i);
			created.setState("MN");
			created.setCountry("USA");

			cityService.create(created);
		}

		return "Done";
	}
}
