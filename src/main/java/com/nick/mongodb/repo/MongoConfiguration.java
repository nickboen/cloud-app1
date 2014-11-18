package com.nick.mongodb.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@PropertySource(value = { "classpath:mongodb.properties" })
@EnableMongoRepositories(basePackages = { "com.nick.mongodb" })
public class MongoConfiguration {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MongoConfiguration.class);

	public static final String DB_NAME = "mydb";
	public static final String CUSTOMER_COLLECTION = "Customers";

	@Autowired
	private Environment env;

	@Bean
	public MongoClient mongo() throws Exception {
		String host = env.getProperty("spring.data.mongodb.host");
		int port = Integer
				.parseInt(env.getProperty("spring.data.mongodb.port"));

		LOGGER.info("\n\nTrying in instantiate Mongo: " + host + ":" + port);

		return new MongoClient(host, port);
	}

	@Bean(name = "myMongo")
	public MongoOperations mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), DB_NAME);
	}

}
