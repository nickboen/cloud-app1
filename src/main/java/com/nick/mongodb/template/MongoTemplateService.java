package com.nick.mongodb.template;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoTemplateService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MongoTemplateService.class);

	private final MongoTemplate mongoTemplate;

	@Autowired
	public MongoTemplateService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<ZipCode> getAll() {
		return mongoTemplate.findAll(ZipCode.class);
	}

	@PostConstruct
	public void init() {

		try {
			// Create the collection
			mongoTemplate.createCollection(ZipCode.class);

			ZipCode zc1 = new ZipCode("55340", "Corcoran", "MN");
			ZipCode zc2 = new ZipCode("55346", "Eden Prairie", "MN");

			List<ZipCode> coll = new ArrayList<ZipCode>();
			coll.add(zc1);
			coll.add(zc2);

			mongoTemplate.insert(coll, ZipCode.class);

			List<ZipCode> mongoList = mongoTemplate.findAll(ZipCode.class);

			for (ZipCode zc : mongoList) {
				LOGGER.info(zc.toString());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}
}