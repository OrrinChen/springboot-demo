package cn.java.utils;

import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties; 
/**
 * 
 * @Description: 配置常量类——根据不同的spring-profile加载不同的配置
 *               变量名把配置文件的key中的"."替换成"_"命名
 * @author: eric.zhang
 * @date: 2018年7月20日 上午10:59:24
 */
public class ConfigConstant { 
  public static String CONFIG_URL;
  public static String CONFIG_NAME;
  
  static {
    try {
      Properties props = new Properties();
      props.load(new InputStreamReader(ConfigConstant.class.getClassLoader().getResourceAsStream("application.properties"),"UTF-8"));
//      String profile = props.getProperty("spring.profiles.active");
//      String envFile = "application-" + profile + ".properties";
      String envFile = "application.properties";
      Properties envProps = new Properties();
      
      envProps.load(new InputStreamReader(ConfigConstant.class.getClassLoader().getResourceAsStream(envFile), "UTF-8"));
      Field[] fields = ConfigConstant.class.getFields();
      for (Field field : fields) {
        field.set(null, envProps.getProperty(field.getName().replace("_", ".").toLowerCase()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}