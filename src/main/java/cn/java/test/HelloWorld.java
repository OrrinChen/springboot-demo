package cn.java.test;

import java.sql.*;

import com.alibaba.fastjson.JSON;

public class HelloWorld {
    private static Connection dbConn = null;

    public static void main(String[] args) {
        String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ecology8";//数据库路径
        dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ecology8";//数据库路径
        dbURL = "jdbc:sqlserver://192.168.1.34;DatabaseName=ecology";//数据库路径
        //9230:后台报错
        //七月 11, 2022 10:32:08 上午 com.microsoft.sqlserver.jdbc.SQLServerConnection Prelogin
        //警告: ConnectionID:1 ClientConnectionId: f511d7e6-3217-4b61-a3e9-898562a6f042 Prelogin error: host 172.16.1.2 port 9230 Error reading prelogin response: Connection reset ClientConnectionId:f511d7e6-3217-4b61-a3e9-898562a6f042
        dbURL = "jdbc:sqlserver://172.16.1.2:14333;DatabaseName=DB_DSBA";//数据库路径
        String name = "sa";                                                            //数据库账号
        String password = "ciscotom1!";//数据库密码
        String select = "select count(*) as COUNT from [MF_POS]";//简单查询语句
//        String select = "select * from [Formtable_main_3]";
        String update = "update [wryStudent3] set Ssex='女' where Sno='9512110'";
        String insert="insert into Course(Cno,Cname,Ccredit,XKLB) values('X02','English','5','必修')";
        String delete="delete  from [Course] where Cno='X02'";
        int i = 0;
        try {
            //1.加载驱动
            //Class.forName方法的作用,就是初始化给定的类.而我们给定的MySQL的Driver类中,
            // 它在静态代码块中通过JDBC的DriverManager注册了一下驱动.我们也可以直接使用JDBC的驱动管理器注册mysql驱动.
            // 从而代替使用Class.forName.
        	System.out.println("i=="+ i++);
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //2.连接
            dbConn = DriverManager.getConnection(dbURL, name, password);
            System.out.println("连接数据库成功！");
            PreparedStatement statement = null;

            statement = dbConn.prepareStatement(select);

            ResultSet res = null;
            res = statement.executeQuery();
            //当查询下一行有记录时：res.next()返回值为true，反之为false
            while (res.next()) {
//                String OS_NO = res.getString("OS_NO");
//                String QT_NO = res.getString("QT_NO");
//                System.out.println("OS_NO：" + OS_NO + "  QT_NO：" + QT_NO );
            	String count = res.getString("COUNT");
                System.out.println(i++ +"=="+ count);
            }

            //修改
//            statement = dbConn.prepareStatement(update);
//            int res1 = statement.executeUpdate();
//            System.out.println(res1);

            //添加
//            statement = dbConn.prepareStatement(insert);
//            int res2=statement.executeUpdate();
//            System.out.println(res2);

            //删除
//            statement = dbConn.prepareStatement(delete);
//            int res3=statement.executeUpdate();
//            System.out.println(res3);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
    }
 }
