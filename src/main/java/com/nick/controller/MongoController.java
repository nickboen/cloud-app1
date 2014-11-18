package com.nick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.mongodb.template.MongoTemplateService;
import com.nick.mongodb.template.ZipCode;

@RestController
public class MongoController {

	@Autowired
	MongoTemplateService mongoService;

	@RequestMapping("/zips")
	public List<ZipCode> getCustomers() {

		return mongoService.getAll();
	}

}
