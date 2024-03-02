package cn.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.mapper.UserMapper;
import cn.java.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<Map<String, Object>> getUser(){
		List<Map<String, Object>> userList = userMapper.getUser();
		return userList;
	}

}
