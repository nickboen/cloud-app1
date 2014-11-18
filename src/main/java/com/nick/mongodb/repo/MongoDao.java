package com.nick.mongodb.repo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;

//@Service
public class MongoDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MongoDao.class);

	@Autowired
	@Qualifier("myMongo")
	private MongoOperations mongoOperations;

	// @Autowired
	// private CustomerRepository repository;

	@PostConstruct
	public void init() {

		try {
			// save a couple of customers

			mongoOperations.insert(new Customer("asmith", "Alice", "Smith"),
					MongoConfiguration.DB_NAME);
			mongoOperations.insert(new Customer("bsmith", "Bob", "Smith"),
					MongoConfiguration.DB_NAME);

			// fetch all customers
			// LOGGER.info("Customers found with findAll():");
			LOGGER.info("-------------------------------");

			for (Customer customer : mongoOperations.findAll(Customer.class)) {
				LOGGER.info(customer.toString());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	public List<Customer> findAll() {
		return mongoOperations.findAll(Customer.class);
	}

}
