package cn.java.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.java.entity.User;
import cn.java.service.UserService;
import cn.java.utils.JwtToken22;

//@Controller
@RestController
@RequestMapping("/user")
public class UserController {
	
	Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	private final String USERNAME = "admin";
    private final String PASSWORD = "123";
    
    //login方法
    @GetMapping("/login")
    public User login(User user){
    	System.out.println("123");
    	
    	System.out.println(user.toString());
    	
        if(USERNAME.equals(user.getUsername()) && PASSWORD.equals(user.getPassword())){
            //添加token
            user.setToken(JwtToken22.createToken());
            logger.info("user.token2=="+user.getToken());
            return user;
        }
        return null;
    }
    
    @GetMapping("/login2")
    public String login2(String username) {
//    	System.out.println("username:"+user.getUsername());
    	System.out.println("username:"+username);
    	return "login3";
    }
    
    //校验token
    @GetMapping("/checkToken")
    //接收前端请求过来的header中的token，然后去jwtoken校验方法里校验这个token
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        logger.info("token:"+token);
        return JwtToken22.checkToken(token);
    }

	@RequestMapping("/getUser.do")
	public @ResponseBody List<Map<String, Object>> getUser(
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		System.out.println("pageNum=" + pageNum + "&pageSize=" + pageSize);
		// PageHerlper后台分页核心代码
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> userList = userService.getUser();
		return userList;
	}

	@RequestMapping("/getUsers.do")
	public String getUsers(
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize, 
			Model model) {
		System.out.println("pageNum=" + pageNum + "&pageSize=" + pageSize);
		// PageHerlper后台分页核心代码
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> userList = userService.getUser();
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(userList);
		model.addAttribute("pageInfo",pageInfo);//bootstrp根据此数据分页
		model.addAttribute("userList", userList);
		return "fornt/fenye.jsp";
	}

}
