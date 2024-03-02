package cn.java.utils;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
/**
 * 获取配置文件工具类
 * @author ds16
 *
 */
public class ConfigTool {
	
	Logger logger = Logger.getLogger(ConfigTool.class);

	@Autowired
	private Environment env;
	
	public static String url;
	public static String username;
	public static String password;

	@PostConstruct
	public void readConfig() {
		url = env.getProperty("erp.url");
		username = env.getProperty("erp.username");
		password = env.getProperty("erp.password");
		logger.info("ConfigTool.readConfig.url=="+url);
	}

//	public ConfigTool() {
//		super();
//		url = env.getProperty("erp.url");
//		username = env.getProperty("erp.username");
//		password = env.getProperty("erp.password");
//		System.out.println("ConfigTool.readConfig.url=="+url);
//	}
	
	

}
