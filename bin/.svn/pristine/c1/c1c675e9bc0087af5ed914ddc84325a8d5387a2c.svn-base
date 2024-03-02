package cn.java.tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.java.entity.Formtable_main_3;
import cn.java.entity.Formtable_main_46WithBLOBs;
import cn.java.entity.Formtable_main_46_dt1;
import cn.java.entity.Workflow_requestbaseWithBLOBs;
import cn.java.service.Main3Service;
import cn.java.service.Main46Service;
import cn.java.service.OaerpService;

/**
 * OA-ERP数据交换定时任务类
 * @author Administrator
 *
 */
@Component
public class OaErpTask {
	
	private static Logger logger = Logger.getLogger(OaErpTask.class);
	
	// 数据库连接信息
	@Autowired
	private Environment env;//不能加static修饰，否则无法注入
	public static String url;
	public static String username;
	public static String password;
	
	//数据库连接
	private static Connection dbConn ;
	
	public static String message = "";
	public static int status = 0; //数据交换状态： 1=成功  0=失败
	
	@Autowired
	private OaerpService oaerpService;
	@Autowired
	private Main3Service main3Service;
	@Autowired
	private Main46Service main46Service;
	
	@PostConstruct
	public void readConfig() throws Exception {
		logger.info("OaErpTask。readConfig()被调用。。。");
		url = env.getProperty("erp.url");
		username = env.getProperty("erp.username");
		password = env.getProperty("erp.password");
		try {
        	logger.info("url=="+url);
        	dbConn = DriverManager.getConnection(url, username,password);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
	}
	
//	@Scheduled(cron = "* * * * * * *")
//	@Scheduled(cron = "1/5 * * * * *")
	public void ChangeData() {
		System.out.println("time:"+new Date());
		int maxSourceId = oaerpService.getMaxSourceId();//查找数据交换表中记录的源表最大sourceid值
		
		System.out.println("maxSourceId:"+maxSourceId);
		Formtable_main_3 main3 = main3Service.getDataById(maxSourceId);//
		
		//entity->json
		String main3Str = JSONObject.toJSONString(main3);
		System.out.println("main3Str:"+main3Str);
		
		//查找源表下一条记录
		Formtable_main_3 nextMaxData = main3Service.getNextMaxDataById(maxSourceId);
		List main3List = main3Service.getDataByConditon(maxSourceId);
		if(nextMaxData != null) {
			//往ERP数据库插入数据（采购订单）
			status = insertERP(nextMaxData);//状态码：用于记录交互状态（数据交换状态： 1=成功  0=失败）
			//往OA数据库插入数据（数据交换记录表）
			oaerpService.insertData(nextMaxData);
			//后台输出分隔符
//			System.out.println("后台输出分隔符*************************");
		} else {
			System.out.println("没有数据需要交互："+new Date());
			//后台输出分隔符
//			System.out.println("后台输出分隔符*************************");
		}
		System.out.println("main3="+main3.getId());
		//后台输出分隔符
		System.out.println("");
		System.out.println("后台输出分隔符*************************");
	}
	
	/**
	 * 新物料采购合同数据交换定时任务
	 */
	public void changeMain46Data() {
		//获取当前交换时间点
		Date maxTime = oaerpService.getMaxTime("Formtable_main_46");
		System.out.println("Date=="+maxTime);
		
		//通过当前交换时间获取已经办结的工作流 306:新采购物料合同
		Workflow_requestbaseWithBLOBs workflow = main46Service.selectWorkflowByIdAndTime(306,maxTime);
		System.out.println("workflow=="+JSON.toJSON(workflow));
		//获取requestid
		Integer requestid = workflow.getRequestid();
		System.out.println("requestid=="+requestid);
		
		//获取主表id
		Formtable_main_46WithBLOBs main46 = main46Service.selectMain46ByRequestid(requestid);
		System.out.println("main46=="+JSON.toJSON(main46));
		Integer mainid = main46.getId();
		System.out.println("mainid=="+mainid);
		
		//获取明细表List
		List<Formtable_main_46_dt1> main46dt1List = main46Service.selectMain46dt1ListByMainid(mainid);
		int i = 0;
		for (Formtable_main_46_dt1 main46dt1 : main46dt1List) {
			System.out.println("main46dt1="+ i++ +"="+JSON.toJSON(main46dt1));
		}
	}
	
	/**
	 * 获取数据连接
	 * @return
	 */
	@Deprecated
	private static Connection getInstance() {
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
        
        logger.info("OaErpTask被调用");
        
        try {
        	dbconn = DriverManager.getConnection(dbURL, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
		return dbconn;
	}

	//生成采购订单号，规则：PO+年份最后1位+1位月份（1-9ABC）+2位日（01-12）+4位排序（0001开始）
    public static String getAutoPoNo() {
    	PreparedStatement statement;
    	//获取当前年份最后1位
    	String date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(new Date());
    	String[] split = date.split("-");//分割年月日
    	String year = split[0].substring(3);//年份的最后1位
    	String month = split[1];//月份2位
    	switch (month) {//1位月份（1-9ABC）
			case "01":month = "1";break;
			case "02":month = "2";break;
			case "03":month = "3";break;
			case "04":month = "4";break;
			case "05":month = "5";break;
			case "06":month = "6";break;
			case "07":month = "7";break;
			case "08":month = "8";break;
			case "09":month = "9";break;
			case "10":month = "A";break;
			case "11":month = "B";break;
			case "12":month = "C";break;
		}
    	
    	String day = split[2];//2位日（01-12）
    	//当天采购订单号 前缀
    	String prePoNo = "PO" + year + month + day ;
//    	System.out.println("prePoNo="+prePoNo);
    	
    	//当前数据库中最的采购订单号 后缀
    	String maxSufString = "0001";
		try {//查询目标数据库中当天的采购订单后最大的订单号 后缀
			String select = "select * from MF_POS where OS_NO like '"+prePoNo+"%'";
			statement = dbConn.prepareStatement(select);
			ResultSet res=statement.executeQuery();
			//当查询下一行有记录时：res.next()返回值为true，反之为false
            while (res.next()) {//循环比较结束后，maxSufString为最新的订单号后缀
                String OS_NO = res.getString("OS_NO");
//                System.out.println("OS_NO：" + OS_NO);
                String substring = OS_NO.substring(6, 10);//采购订单号固定长度为10，获取最后4位，参与循环运算比较
                if(Integer.parseInt(substring) > Integer.parseInt(maxSufString)) {
                	maxSufString=substring;
                }
                int parseInt = Integer.parseInt(maxSufString);
                parseInt++;//当前采购订单号最大后缀+1,生成最新的订单号后缀
                maxSufString = String.format("%04d", parseInt);//int转string，不足4位，前面补0
            }
            statement.close();//关闭
//            dbConn.close();//关闭jdbc
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
		System.out.println("getAutoPoNo=="+prePoNo+maxSufString);
    	return prePoNo+maxSufString;//生成的最新采购订单号
    }
    /**
     * 生成WF_TEMPID表最新的ITM号
     * @return
     */
    public static String getNewMaxITM(){
    	PreparedStatement statement ;
    	String itm = "";
    	try {//获取WF_TEMPID表最大ITM号
			String select = "select max(ITM) ITM from WF_TEMPID";
			statement = dbConn.prepareStatement(select);
			ResultSet res=statement.executeQuery();
			//当查询下一行有记录时：res.next()返回值为true，反之为false
            while (res.next()) {//循环比较结束后，itm为当前最新的ITM号
                itm = res.getString("ITM");
                System.out.println("ITM:" + itm);
                if(StringUtils.isEmpty(itm)) {//null判断
                	itm = "0";
                }
                int parseInt = Integer.parseInt(itm+"");
                parseInt++;//当前ITM+1,生成最新的ITM号
                itm = parseInt+"";
            }
            //关闭statement
            statement.close();//关闭
//            dbConn.close();//关闭jdbc
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
    	System.out.println("getNewMaxITM=="+itm);
    	return itm;
    }
    
    /**
     * 生成TF_POS_Z表最新的ITM号
     * @return
     */
    public static String getTF_POS_ZNewMaxITM(){
    	PreparedStatement statement ;
    	String itm = "";
    	try {//获取WF_TEMPID表最大ITM号
    		String select = "select max(ITM) ITM from TF_POS_Z where OS_ID = 'PO' and OS_NO = '"+getAutoPoNo()+"'";
    		statement = dbConn.prepareStatement(select);
    		ResultSet res=statement.executeQuery();
    		//当查询下一行有记录时：res.next()返回值为true，反之为false
    		while (res.next()) {//循环比较结束后，itm为当前最新的ITM号
    			itm = res.getString("ITM");
//                System.out.println("ITM:" + itm);
    			//itm为null时，赋值为0参与运算
    			if(StringUtils.isEmpty(itm)) {
    				itm="0";
    			}
    			
    			int parseInt = Integer.parseInt(itm);
    			parseInt++;//当前ITM+1,生成最新的ITM号
    			itm = parseInt+"";
//    			System.out.println("itm="+itm);
    		}
    		//关闭statement
    		statement.close();//关闭
//            dbConn.close();//关闭jdbc
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		System.out.println("数据库连接失败！");
    	}
    	System.out.println("getTF_POS_ZNewMaxITM=="+itm);
    	return itm;
    }
    
    //校验数据，满足数据条件才往ERP数据库插入数据
    public boolean vailidata() {
    	//erp系统不能为空字段，主表：MF_POS
    	
    	//erp系统不能为空字段，明细表：TF_POS
    	
    	return false ;
    }
	
	//往ERP数据库插入数据（采购订单）
	public int insertERP(Formtable_main_3 main3) {
		int ret = 0;
        String select = "select * from [MF_POS]";//简单查询语句
        
        //必填字段
        //插入业务表 MF_POS
        String insertMF_POS="insert into MF_POS(OS_ID,OS_NO,QT_NO) values('PO','"+ getAutoPoNo() +"','SQ5C240001')";
        System.out.println("insertMF_POS=="+insertMF_POS);
        
        //插入业务表TP_POS 一条MF_POS表记录对应多条TP_POS记录
        String insertTF_POS = "insert into TF_POS (OS_ID,OS_NO,ITM,QT_NO,PRD_NO) values ('PO','"+getAutoPoNo()+"','4','SQ5C240003','WBGOBAL000000')";
        System.out.println("insertTF_POS=="+insertTF_POS);
        
        //插入业务表TP_POS_Z 一条TF_POS表记录对应一条TP_POS_Z记录
        String insertTF_POS_Z = "insert into TF_POS_Z (OS_ID,OS_NO,ITM,YT) values ('PO','"+getAutoPoNo()+"','"+getTF_POS_ZNewMaxITM()+"','用途') ;";
        System.out.println("insertTF_POS_Z=="+insertTF_POS_Z);
        
        //插入工作流WF_TEMPID表数据 //225668
        String insertWF_TEMPID = "insert into WF_TEMPID(TYPE,ITM,IS_USED) values('IN','"+getNewMaxITM()+"','T');";
        System.out.println("insertWF_TEMPID=="+insertWF_TEMPID);
        
        //插入工作流wf_flow_inst表数据 //225668
        StringBuffer insertWF_FLOW_INST = new StringBuffer();
        insertWF_FLOW_INST.append("insert into WF_FLOW_INST(INST_ID,TEMP_ID,BIL_NO,BIL_ID, BIL_DD, USR, DEP, REM,SH_STATE, CUR_NODE, BIL_ITM) ");
        insertWF_FLOW_INST.append("values                  ('"+getNewMaxITM()+"','10002','"+getAutoPoNo()+"','PO','2022-07-12 00:00:00.000','GZDS','0000','厂商名称:升谱光电半导体有限公司;币别:;单据类别：;采购1个货品;数量合计:1;金额合计:34.19;','0','10005','0')");
        System.out.println("insertWF_FLOW_INST=="+insertWF_FLOW_INST.toString());
		
        
        try {
            PreparedStatement statement = null;
            statement = dbConn.prepareStatement(select);
            ResultSet res = null;
            res = statement.executeQuery();
            //当查询下一行有记录时：res.next()返回值为true，反之为false
//            while (res.next()) {
//                String OS_NO = res.getString("OS_NO");
//                String QT_NO = res.getString("QT_NO");
//                System.out.println("OS_NO：" + OS_NO + "QT_NO：" + QT_NO );
//            }
            

            //修改
//		            statement = dbConn.prepareStatement(update);
//		            int res1 = statement.executeUpdate();
//		            System.out.println(res1);

            //添加MF_POS表数据
            statement = dbConn.prepareStatement(insertMF_POS);
            int mf_posFlag = statement.executeUpdate();
            System.out.println("MF_POS表数据添加："+mf_posFlag);
            statement.close();//关闭statement
            
            //添加TF_POS表数据
            statement = dbConn.prepareStatement(insertTF_POS);
            int tf_posFlag = statement.executeUpdate();
            System.out.println("TF_POS表数据添加："+tf_posFlag);
            statement.close();//关闭statement
            
            //添加TF_POS_Z表数据
            statement = dbConn.prepareStatement(insertTF_POS_Z);
            int tf_pos_zFlag = statement.executeUpdate();
            System.out.println("TF_POS_Z表数据添加："+tf_pos_zFlag);
            statement.close();//关闭statement
            
            //添加 WF_TEMPID表数据
            statement = dbConn.prepareStatement(insertWF_TEMPID);
//            boolean tempIdFlag = statement.execute();//execut()数据入库，返回还是false
            int wf_tepidFlag = statement.executeUpdate();
//            statement.executeUpdate(tempIdInsert);
            System.out.println("WF_TEMPID表数据添加："+wf_tepidFlag);
            statement.close();//关闭statement
            
            //添加WF_FLOW_INST表数据
            statement = dbConn.prepareStatement(insertWF_FLOW_INST.toString());
//            boolean instFlag = statement.execute();//execut()数据入库，返回还是false
            int wf_flow_instFlag = statement.executeUpdate();
            System.out.println("WF_FLOW_INST表数据添加："+wf_flow_instFlag);
            statement.close();//关闭statement
            
            //所有业务表和工作流表数据正常入库才返回成功标识1，否则为0
			if (mf_posFlag > 0 && tf_posFlag > 0 && tf_pos_zFlag > 0 && wf_tepidFlag > 0 && wf_flow_instFlag > 0) {
				ret = 1;
			}
            
            //
//            statement = dbConn.prepareStatement(instinsert.toString());
//          boolean execute = statement.execute();
//          int execute = statement.executeUpdate();
//          int execute = statement.executeUpdate(instinsert.toString());
//          System.out.println("添加wf_flow_inst表是否成功："+execute);
            

            //删除
//		            statement = dbConn.prepareStatement(delete);
//		            int res3=statement.executeUpdate();
//		            System.out.println(res3);
            
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
            System.out.println("数据库操作失败！");
        }
        return ret;
	}

}
