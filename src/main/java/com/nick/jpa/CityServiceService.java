package com.nick.jpa;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceService implements CityService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CityServiceService.class);

	@Resource
	private CityRepository cityRepository;

	@Transactional
	@Override
	public City create(CityDTO created) {
		LOGGER.debug("Creating a new city with information: " + created);

		City city = new City(created.getName(), created.getState(),
				created.getCountry());

		return cityRepository.save(city);
	}

	@Transactional(readOnly = true)
	@Override
	public City delete(Long cityId) {
		LOGGER.debug("Deleting city with id: " + cityId);

		City deleted = cityRepository.findOne(cityId);

		if (deleted == null) {
			LOGGER.debug("No person found with id: " + cityId);
			throw new RuntimeException("City not found");
		}

		cityRepository.delete(deleted);
		return deleted;
	}

	@Transactional(readOnly = true)
	@Override
	public List<City> findAll() {
		LOGGER.debug("Finding all cities");
		return cityRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public City findById(Long id) {
		LOGGER.debug("Finding city by id: " + id);
		return cityRepository.findOne(id);
	}

	@Transactional
	@Override
	public City update(CityDTO updated) {
		LOGGER.debug("Updating city with information: " + updated);

		City city = cityRepository.findOne(updated.getId());

		if (city == null) {
			LOGGER.debug("No city found with id: " + updated.getId());
			throw new RuntimeException("City not found");
		}

		city.update(updated.getName(), updated.getState(), updated.getCountry());

		return city;
	}

}
