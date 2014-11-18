package com.nick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.cassandra.User;
import com.nick.cassandra.UserRepository;

@RestController
public class CassandraController {

	@Autowired
	UserRepository userRepo;

	@RequestMapping("/users")
	public List<User> getUsers() {

		return userRepo.getAll();
	}

	@RequestMapping("/user/{userId}")
	public User getUserById(@PathVariable String userId) {

		return userRepo.getUser(userId);

	}

	@RequestMapping("/user/firstname/{firstName}")
	public List<User> getUserByFirstName(@PathVariable String firstName) {

		return userRepo.getByFirstName(firstName);

	}

}
