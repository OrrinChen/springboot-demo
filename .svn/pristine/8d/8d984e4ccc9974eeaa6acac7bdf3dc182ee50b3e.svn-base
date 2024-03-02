package cn.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.entity.Person;
import cn.java.service.MongoDBService;

@Controller
public class MongoDBController {
	
	@Autowired
	private MongoDBService mongoDBService;
	
	@RequestMapping("/insertData.do")
	public void insertData() {
		Person person = new Person("张三","男","18");
		mongoDBService.insertData(person);
	}
	
	@RequestMapping("/insertManyData.do")
	public @ResponseBody String insertManyData(int i) {
		long begin = System.currentTimeMillis();
		for (int j = 0; j < i; j++) {
			Person person = new Person("王二麻子"+j, "男", "0"+j);
			mongoDBService.insertData(person);
		}
		long end = System.currentTimeMillis();
		
		long times = end - begin;
		
		String tip = "Mongodb插入【"+i+"】条数据完成...消耗时间为:【"+ times+"】毫秒";
		
		System.out.println(tip);
		
		return tip;
		
	}
	
	

}
