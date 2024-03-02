package cn.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.entity.Formtable_main_3;
import cn.java.service.Main3Service;

@Controller
@RequestMapping("/main3")
public class Main3Controller {
	
	@Autowired
	private Main3Service main3Service;
	
	@RequestMapping("getMain3Data")
	@ResponseBody
	public Formtable_main_3 getMain3Data(int id) {
		Formtable_main_3 formtable_main_3 = main3Service.getDataById(id);
		return formtable_main_3;
	}
	
	@RequestMapping("getMain3DataAll")
	@ResponseBody
	public List getMain3DataAll() {
		List list = main3Service.getAllData();
		return list;
	}
	
	@RequestMapping("getMain3DataByCondition")
	@ResponseBody
	public List getMain3DataByCondition(int id) {
		return main3Service.getDataByConditon(id);
	}

}
