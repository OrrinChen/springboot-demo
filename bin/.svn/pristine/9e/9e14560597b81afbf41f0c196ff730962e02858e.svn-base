package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	
	@Select(value = "select count(*) from users where username=#{arg0} and password=#{arg1}")
	int login(String username, String password);
	
	@Select(value = "SELECT * from users")
	List<Map<String, Object>> getUser();
}