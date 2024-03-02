package cn.java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * OaErp交互数据工具类
 * @author ds16
 *
 */
public class OaErpTool {
	public static String message = "";
	public static int status = 0; //数据交换状态： 1=成功  0=失败
//	public static Connection dbConn = OaErpTool.getConnection();
	/**
	 * 获取数据连接
	 * @return
	 */
	public static Connection getConnection() {
		//1.加载驱动
        //Class.forName方法的作用,就是初始化给定的类.而我们给定的MySQL的Driver类中,
        // 它在静态代码块中通过JDBC的DriverManager注册了一下驱动.我们也可以直接使用JDBC的驱动管理器注册mysql驱动.
        // 从而代替使用Class.forName.
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection dbconn = null;
//		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ecology8";//数据库路径
		String dbURL = "jdbc:sqlserver://192.168.1.34:1433;DatabaseName=ssm";//数据库路径
        String name = "sa";//数据库账号
        String password = "123456";//数据库密码
        try {
        	dbconn = DriverManager.getConnection(dbURL, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取Connection数据库连接失败！");
		}
		return dbconn;
	}
	
	/**
	 * 
	 * @param datestr：日期字符串，需要和format参数格式保持一致
	 * @param format：日期格式，如yyyy-MM-dd|yyyy-MM-dd HH:mm:ss等
	 * @param m:+数代表指定时间往后m个月，-负数代表指定日期往前m个月
	 * @return 指定日期字符串往后或往前m个月的第一天
	 */
	public static String getFirstDayofAppointMonth(String datestr, String format, int m){ 
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse (datestr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime (date);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			calendar.add(Calendar.MONTH, m);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
