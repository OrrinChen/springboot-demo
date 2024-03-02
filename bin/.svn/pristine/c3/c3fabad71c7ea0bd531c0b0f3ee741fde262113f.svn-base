package cn.java.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.utils.JwtToken22;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin//跨域
@Controller
public class LoginController2 {
	
	Logger logger = Logger.getLogger(LoginController2.class);
	
	@PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(String username,String password) {
//        log.info("username:{},password:{}",username,password);
        logger.info("username:{"+username+"},password:{"+password+"}");
        Map<String, Object> map = new HashMap<>();
        Map<String,Object> metaMap = new HashMap<String, Object>();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        // 模拟数据库，验证用户名和密码是否正确
        if (!"admin".equals(username) || !"123456".equals(password)) {
        	metaMap.put("msg", "用户名密码错误");
            metaMap.put("status", 401);
            map.put("meta", metaMap);
            return ResponseEntity.ok(map);
//            return "12";
        }
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", username);
        // 生成token，其中参数2是存活时间，单位是毫秒，暂定（8小时，因为token过期跳转功能还没做好）
        String jwtToken = jwtUtil.encode(username, 1000 * 60 * 60 * 8  , chaim);
        metaMap.put("msg", "登录成功");
        metaMap.put("status", 200);
        map.put("meta", metaMap);
        
        dataMap.put("token", jwtToken);
        dataMap.put("username", username);
        map.put("data", dataMap);
        return ResponseEntity.ok(map);
//        return "34";
    }
	
	//校验token
    @GetMapping("/checkToken")
    @ResponseBody
    //接收前端请求过来的header中的token，然后去jwtoken校验方法里校验这个token
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        logger.info("token:"+token);
        JwtUtil jwtUtil = new JwtUtil();
        logger.info("jwtUtil=="+jwtUtil.isVerify(token));
        boolean ret = jwtUtil.isVerify(token);
        logger.info("ret=="+ret);
        return ret;
//        return false;
    }
    
    @RequestMapping("/testdemo")
    public ResponseEntity<String> testdemo() {
        return ResponseEntity.ok("我爱蛋炒饭");
    }
}
