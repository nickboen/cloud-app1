package com.nick.mongodb.repo;

import java.util.List;

public interface CustomerRepository {
	// extends MongoRepository<Customer, String>
	public Customer findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);

}
