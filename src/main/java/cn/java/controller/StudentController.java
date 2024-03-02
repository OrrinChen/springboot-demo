package cn.java.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/getAllStudents.do")
	public @ResponseBody List<Map<String,Object>> getAllStudent(){
		return studentService.getAllStudents();
	}
	
	/**
	 * 数据有变动时调用此方法清空redis缓存数据(controller访问地址)
	 */
	@RequestMapping("/cleanRedis.do")
	public void cleanRedis() {
		studentService.cleanRedis();
	}
	
	@RequestMapping("/insertStudent.do")
	public void insertStudent() {
		String name = "王八蛋";
		String age = "999";
		studentService.insertStudent(name, age);
	}
	
	@RequestMapping("/insertManyStudent.do")
	public @ResponseBody String insertManyStudent(int i) {
		long begin = System.currentTimeMillis();
		for (int j = 0; j < i; j++) {
			String name = "王八蛋";
			String age = "999";
			studentService.insertStudent(name+j, age+j);
		}
		long end = System.currentTimeMillis();
		long times = end - begin;
		
		String tip = "MySql插入【"+i+"】条数据完成...消耗时间为:【"+ times+"】毫秒";
		
		System.out.println(tip);
		
		return tip;
	}

}
