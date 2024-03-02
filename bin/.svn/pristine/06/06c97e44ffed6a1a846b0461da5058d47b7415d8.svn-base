package cn.java.test;

import javax.annotation.PostConstruct; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON; 
/**
 * 
 * @Description: 配置常量类——根据不同的spring-profile加载不同的配置
 * @author: eric.zhang
 * @date: 2018年7月20日 上午10:59:24
 */
//注意：没有包名，不能被其它类所引用
@Component
public class ConfigConstant {
  @Autowired
  private Environment env;  
  public static String url;
  public static String username;
  public static String password;
 
  @PostConstruct
  public void readConfig() {
	  System.out.println("env=="+JSON.toJSON(env));
	  url = env.getProperty("erp.url");
	  username = env.getProperty("erp.username");
	  password = env.getProperty("password");
	  System.out.println("url=2="+url);
  }
}