package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
	
	@Select(value = "SELECT * FROM students")
	List<Map<String, Object>> getAllStudents();
	
	@Insert(value = "INSERT students(name,age) VALUE(#{arg0},#{arg1});")
	void inserInsertStudent(String name, String age);

}
