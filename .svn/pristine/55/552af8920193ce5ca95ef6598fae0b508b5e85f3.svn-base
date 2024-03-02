package cn.java.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.java.service.LoginService;
import cn.java.utils.MD5Tool;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login.do")
	public String login(String username, String password, HttpSession session) throws Exception {
		String finalMD5Password = MD5Tool.finalMD5(password);//MD5加密密码
		int login = loginService.login(username, finalMD5Password);
		if(login>0) {//登录成功
			session.setAttribute("username", username);//用于判断是否登录
			return "fornt/main.jsp";
		}
		// 登录失败
		session.invalidate();
		return "fornt/login.jsp";
	}

}
