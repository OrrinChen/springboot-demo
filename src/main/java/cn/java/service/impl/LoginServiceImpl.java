package cn.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.mapper.UserMapper;
import cn.java.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int login(String username, String password) {
		return userMapper.login(username, password);
	}

}
