package cn.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.java.mapper.StudentMapper;
import cn.java.service.StudentService;

@Service
@CacheConfig(cacheNames = {"cache1"})
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentMapper studentMapper;
	
	//注意键要用单引号'括起来如'aaa'
	@Cacheable(key = "'aaa'")
	@Override
	public List<Map<String, Object>> getAllStudents() {
		return studentMapper.getAllStudents();
	}
	
	/**
	 * 数据有变动时调用此方法清空redis缓存数据(service服务接口方法)
	 */
	@CacheEvict(key = "'aaa'")
	public void cleanRedis() {
		System.out.println("Redis数据被清空了...");
	}
	
	/**
	 * 插入数据（students学生表）
	 * @param name 学生名字
	 * @param age 学生年龄
	 */
	public void insertStudent(String name, String age) {
		studentMapper.inserInsertStudent(name, age);
	}

}
