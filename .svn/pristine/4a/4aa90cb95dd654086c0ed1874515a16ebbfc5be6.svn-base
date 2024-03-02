package cn.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import cn.java.entity.Person;
import cn.java.service.MongoDBService;

@Service
public class MongoDBServiceImpl implements MongoDBService {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void insertData(Person person) {
		mongoTemplate.save(person);
	}

}
