package com.nick.cassandra;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

@Service
public class UserRepository {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserRepository.class);

	@Autowired
	@Qualifier("cassandraTemplate")
	private CassandraOperations cassandraOperations;

	public User getUser(String id) {

		return cassandraOperations.selectOneById(User.class, id);

	}

	public List<User> getByFirstName(String firstName) {

		// CreateIndexSpecification indexSpec = new CreateIndexSpecification()
		// .tableName("user").columnName("firstName")
		// .name("IDX_FIRST_NAME").ifNotExists().createIndex();
		// cassandraOperations.execute(indexSpec);

		Select s = QueryBuilder.select().from("user");
		s.where(QueryBuilder.eq("firstName", firstName));

		List<User> users = cassandraOperations.queryForList(s, User.class);

		LOGGER.info("getByFirstName count: " + users.size());

		return users;
	}

	public List<User> getAll() {

		List<User> users = cassandraOperations.selectAll(User.class);

		LOGGER.info("users count: " + users.size());

		return users;

	}

	@PostConstruct
	public void init() {
		List<User> users = new ArrayList<User>();

		try {

			for (int i = 1; i < 1000; i++) {
				users.add(new User("ida" + i, "Nick", "Boen"));
				users.add(new User("idb" + i, "Diane", "Boen"));
				users.add(new User("idc" + i, "Riley", "Boen"));
				users.add(new User("idd" + i, "Avery", "Boen"));
				users.add(new User("ide" + i, "Dozer", "Boen"));
			}

			CqlIdentifier tableName = cassandraOperations
					.getTableName(User.class);

			LOGGER.info("\n\nTable Name: " + tableName.toCql());

			cassandraOperations
					.execute("create table user (id text primary key, firstName text, lastName text)");

			cassandraOperations.insert(users);
		} catch (Exception e) {
		}// Hack

	}

}
