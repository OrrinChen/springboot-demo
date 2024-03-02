package cn.java.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import cn.java.entity.Formtable_main_46WithBLOBs;
import cn.java.entity.Formtable_main_46_dt1;
import cn.java.entity.Workflow_requestbaseWithBLOBs;
import cn.java.service.Main46Service;
import cn.java.service.OaerpService;
import cn.java.utils.OaErpTool;

/**
 * 新订购物料合同控制类
 * @author ds16
 *
 */
@Controller
@RequestMapping("/main46")
public class Main46Controller {
	
	private static Logger logger = Logger.getLogger(Main46Controller.class);
	
	//获取配置文件字段值,注意：通过@Value注解没法赋值给静态变量
//	@Value("${erp.url}")
//  private static String url;
//	@Value("${erp.username}")
//	private static  String username;
//	@Value("${erp.password}")
//	private static  String password;
	
	@Autowired
	private Environment env;//不能加static修饰，否则无法注入
	public static String url;
	public static String username;
	public static String password;
	public static Map usermap;//OA-ERP用户对应关系
	public static String cron;//定时任务表达式
//	public ConfigTool configTool = new ConfigTool();
	//数据库连接
//	private static Connection conn = Main46Controller.getConnection();
	private static Connection conn ;
	
	@PostConstruct
	public void readConfig() throws Exception {
		//获取配置文件字段值方法一
		url = env.getProperty("erp.url");
		username = env.getProperty("erp.username");
		password = env.getProperty("erp.password");
		usermap = (Map)JSON.parse(env.getProperty("erp.usermap"));
		try {
        	logger.info("url=="+url);
        	conn = DriverManager.getConnection(url, username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.info("数据库连接失败！"+e.getMessage());
			logger.error(e.getMessage());
//			logger.info(e.getMessage());
		}
		cron = env.getProperty("erp.cron");
		
		//获取配置文件字段值方法二：会将当前类的所有属性都赋值，且properties没有此属性时会空指针或者类型不对应会抛数据转换异常
//		String prefix = "erp.";
//	    Field[] fields = Main46Controller.class.getFields();
//	    logger.info("fields:"+fields);
//	    for(Field field : fields ){
//	    	logger.info("field1:"+field);
//	    	field.set(null, getProperty(prefix + field.getName()));
//	    	logger.info("field2:"+field);
//	    }
	}
//	private String getProperty(String key) throws UnsupportedEncodingException {
//		logger.info("property=="+env.getProperty(key));
//		return env.getProperty(key);
////	    return new String(env.getProperty(key).getBytes("ISO-8859-1"), "UTF-8");//返回为空
//	  }
	
	
	public static String message = "";
	public static String sourcejson= "";
	public static String targetjson = "";
	public static String targetid = "";
	public static int status = 0; //数据交换状态： 1=成功  0=失败
	private SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");//格式化日期
	
	@Autowired
	private Main46Service main46Service;
	@Autowired
	private OaerpService oaerpService;
	
	@RequestMapping("/getWorkflow")
	@ResponseBody
//	@Scheduled(cron = "1/5 * * * * *")
	public Workflow_requestbaseWithBLOBs getWorkflow() {
		logger.info("Main46Controller.getWorkflow()...");
		Workflow_requestbaseWithBLOBs workflow = main46Service.selectWorkflowByRequestid(71071);
		return workflow;
	}
	
	/**
	 * 交换数据定时任务
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping("/getChange")
	@ResponseBody
//	@Scheduled(cron = "1/2 * * * * *")
	@Scheduled(cron = "${erp.cron}")//能读取到application。properties配置文件的erp.cron的值
	public Date getChange() throws SQLException {
//		logger.info("cron=="+cron);//静态属性获取
//		logger.info("cron22=="+"${erp.cron}");//取不到值
		//获取当前交换时间点
		Date maxTime = oaerpService.getMaxTime("formtable_main_46;formtable_main_46_dt1");
		logger.info("maxTime=="+sdf.format(maxTime));
		logger.info("conn=="+conn);
//		logger.info("conn.isValid(0)=22="+conn.isValid(0));
		//不能作为conn连接是否有效判断条件，只有手动调用过close()方法，或者调用conn对象方法抛出异常后才为ture，正确方法使用isValid(0)作为条件
//		logger.info("conn.isClosed=="+conn.isClosed());
		//conn为空或者conn连接无效不可用时，重新获取conn，注意：conn实例存在不代表连接可用，而且isClosed()方法不能作为判断条件
		if(conn==null || !conn.isValid(0)) {//conn为空或者conn不用，特别注意：不能用conn。isClosed()作为判断条件
        	conn=getConnection();
        }
        logger.info("conn.isValid(0)=="+conn.isValid(0));

        //通过当前交换时间获取下一个已经办结的工作流对象 306:新采购物料合同
		Workflow_requestbaseWithBLOBs workflow = main46Service.selectWorkflowByIdAndTime(306,maxTime);
		logger.info("workflow=="+JSON.toJSON(workflow));
		logger.info("usermap=="+usermap);
		
		//数据交换操作
		if(workflow != null) {
			
			//获取requestid
			Integer requestid = workflow.getRequestid();
			logger.info("requestid=="+requestid);
			
			//获取主表id
			Formtable_main_46WithBLOBs main46 = main46Service.selectMain46ByRequestid(requestid);
			logger.info("main46=="+JSON.toJSON(main46));
			Integer mainid = main46.getId();
			logger.info("mainid=="+mainid);
			
			//获取明细表List
			List<Formtable_main_46_dt1> main46dt1List = main46Service.selectMain46dt1ListByMainid(mainid);
			int i = 0;
			for (Formtable_main_46_dt1 main46dt1 : main46dt1List) {
				logger.info("main46dt1="+ i++ +"="+JSON.toJSON(main46dt1));
			}
			
			//往ERP数据库插入数据（采购订单）
			status = insertERP(main46);//状态码：用于记录交互状态（数据交换状态： 1=成功  0=失败）
			//往OA数据库插入数据（数据交换记录表）
			String dateStr = workflow.getLastoperatedate()+" "+workflow.getLastoperatetime();
			logger.info("dateStr=="+dateStr);
			Date date = null;
			try {
				date = sdf.parse(dateStr);
				logger.info("date=="+date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.info("日期转换失败！");
				e.printStackTrace();
				logger.error(e.getMessage());
//				logger.info(e.getMessage());
			}
			//往OA数据库插入数据（记录交换日记）
			oaerpService.insertData(main46,date);
			//后台输出分隔符
//			logger.info("后台输出分隔符*************************");
		} else {
			logger.info("没有数据需要交互："+sdf.format(new Date()));
			//后台输出分隔符
//			logger.info("后台输出分隔符*************************");
		}
//		logger.info("main3="+main3.getId());
		//后台输出分隔符
//		logger.info("");
//		logger.info("后台输出分隔符*************************");
		
		return maxTime;
	}
	
	@RequestMapping("/getMain46dt1ListByMainid")
	@ResponseBody
	public List<Formtable_main_46_dt1> getMain46dt1ListByMainid() {
		logger.info("Main46Controller.getMain46dt1ListByMainid()...");
		List<Formtable_main_46_dt1> main46List = main46Service.selectMain46dt1ListByMainid(1329);
		for (Formtable_main_46_dt1 main_46_dt1 : main46List) {
			logger.info(main_46_dt1.toString());
			logger.info(JSON.toJSONString(main_46_dt1));
		}
		return main46List;
	}
	
	@RequestMapping("/getMain46dt1ById")
	@ResponseBody
	public Formtable_main_46_dt1 getMain46dt1ById() {
		logger.info("Main46Controller.getMain46dt1ById()...");
		return main46Service.selectMain46dt1ById(1329);
	}
	
	@RequestMapping("/getMain46ById")
	@ResponseBody
	public Formtable_main_46WithBLOBs getMain46ById() {
		Formtable_main_46WithBLOBs main46 = main46Service.selectMain46ById(1329);
		return main46;
	}
	
	/**
	 * 获取数据连接
	 * @return
	 */
	private static Connection getConnection() {
		//1.加载驱动
        //Class.forName方法的作用,就是初始化给定的类.而我们给定的MySQL的Driver类中,
        // 它在静态代码块中通过JDBC的DriverManager注册了一下驱动.我们也可以直接使用JDBC的驱动管理器注册mysql驱动.
        // 从而代替使用Class.forName.
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection dbconn = null;
//		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=ecology8";//数据库路径
//		String dbURL = "jdbc:sqlserver://192.168.1.34:1433;DatabaseName=ssm";//数据库路径
		// 2015账套
//		String dbURL = "" ;//数据库路径
//        String name = "";//数据库账号
//        String password = "";//数据库密码
        try {
        	logger.info("url:=22="+url);
//        	logger.info("username:=22="+username);
//        	logger.info("password:=22="+password);
        	dbconn = DriverManager.getConnection(url, username, password);//此处获取的url等信息为空
//        	dbconn = DriverManager.getConnection(env.getProperty("erp.url"), env.getProperty("erp.username"), env.getProperty("erp.password"));
        	logger.info("dbconn=22="+dbconn);
        	logger.info("dbconn.isValid(0)=22="+dbconn.isValid(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("数据库连接失败！");
		}
		return dbconn;
	}
	
	// ERP数据端业务方法
	//往ERP数据库插入数据（采购订单）
	public int insertERP(Formtable_main_46WithBLOBs main46) {
		int ret = 0;
        String select = "select * from [MF_POS]";//简单查询语句
        
        //获取签订日期
        String qdrqStr = main46.getQdrq();
        Date qdrqDate = null;
        try {
//        	qdrqDate = sdf.parse(qdrqStr);
        	qdrqDate = new SimpleDateFormat("yyyy-MM-dd").parse(qdrqStr);
			logger.info("qdrqDate=="+qdrqDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info("日期转换失败！");
			e.printStackTrace();
			logger.error(e.getMessage());
//			logger.info(e.getMessage());
		}
        
        //当前PoNo
        String autopono = "${erp.autopono}";
        System.out.println("autopono=="+autopono);
        String currentPoNo = getAutoPoNo(qdrqDate);
        targetid = currentPoNo;//赋值给静态类，供其他方法调用
        
        
        //插入业务表TP_POS 一条MF_POS表记录对应多条TP_POS记录
//        String insertTF_POS = "insert into TF_POS (OS_ID,OS_NO,ITM,QT_NO,PRD_NO) values ('PO','"+currentPoNo+"','4','SQ5C240003','WBGOBAL000000')";
//        logger.info("insertTF_POS=="+insertTF_POS);
        
        //插入业务表TP_POS_Z 一条TF_POS表记录对应一条TP_POS_Z记录
//        String insertTF_POS_Z = "insert into TF_POS_Z (OS_ID,OS_NO,ITM,YT) values ('PO','"+currentPoNo+"','"+getTF_POS_ZNewMaxITM()+"','用途') ;";
//        logger.info("insertTF_POS_Z=="+insertTF_POS_Z);
        
        //插入工作流WF_TEMPID表数据 //225668
//        String insertWF_TEMPID = "insert into WF_TEMPID(TYPE,ITM,IS_USED) values('IN','"+getNewMaxITM()+"','T');";
//        logger.info("insertWF_TEMPID=="+insertWF_TEMPID);
        
        //插入工作流wf_flow_inst表数据 //225668
//        StringBuffer insertWF_FLOW_INST = new StringBuffer();
//        insertWF_FLOW_INST.append("insert into WF_FLOW_INST(INST_ID,TEMP_ID,BIL_NO,BIL_ID, BIL_DD, USR, DEP, REM,SH_STATE, CUR_NODE, BIL_ITM) ");
//        insertWF_FLOW_INST.append("values                  ('"+getNewMaxITM()+"','10002','"+currentPoNo+"','PO','2022-07-12 00:00:00.000','GZDS','0000','厂商名称:升谱光电半导体有限公司;币别:;单据类别：;采购1个货品;数量合计:1;金额合计:34.19;','0','10005','0')");
//        logger.info("insertWF_FLOW_INST=="+insertWF_FLOW_INST.toString());
		
        //记录交换源数据及目标数据
        StringBuffer targetjsonBuf = new StringBuffer();
        StringBuffer sourcejsonBuf = new StringBuffer();
        try {
            PreparedStatement statement = null;
            statement = conn.prepareStatement(select);
            ResultSet res = null;
//            res = statement.executeQuery(); //此语句放开后，不管res有没有被引用，都会影响数据插入数据时间，大概是1:30一分半时间
            statement.close();//影响卡1:30一分半时间位置，注释时：会卡在insertMF_POS插入语句的执行，此语句放开时，会卡在当前try语句前
            //当查询下一行有记录时：res.next()返回值为true，反之为false
//	            while (res.next()) {
//	                String OS_NO = res.getString("OS_NO");
//	                String QT_NO = res.getString("QT_NO");
//	                logger.info("OS_NO：" + OS_NO + "QT_NO：" + QT_NO );
//	            }
            

            //修改
//			            statement = dbConn.prepareStatement(update);
//			            int res1 = statement.executeUpdate();
//			            logger.info(res1);
            
            //插入业务表 MF_POS
            //必填字段 
            //QT_NO:转入单号==dh:合同编号
            String QT_NO = main46.getDh();//SQ5C240001
            //OS_DD:采购日期==签订日期:qdrq
//            String OS_DD = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//获取当前日期,只能接受yyyy-MM-dd 00:00:00.000,如果时分秒不为0时，采购订单速查过过滤不到数据
//            logger.info("OS_DD=1="+OS_DD);
            String OS_DD = main46.getQdrq();//获取当前日期,只能接受yyyy-MM-dd 00:00:00.000,如果时分秒不为0时，采购订单速查过过滤不到数据
//            logger.info("OS_DD=2="+OS_DD);
//            String OS_DD = main46.getQdrq();//获取签订日期作为采购日期
            //USR:录入人==采购员:jfcgy
            String USR = main46.getJfcgy()+"";//甲方采购员ID
            String usr = (String) usermap.get(USR);
            //OA-ERP用户对应关系
            if(StringUtils.isEmpty(usr)) {
//            	USR = "DEMO";//为空时默认值
            	USR = (String) usermap.get("default");//为空时获取配置文件的默认值default
            } else {
				USR = usr;
			}
            //PAY_REM:交易方式==??
//            String PAY_REM = "结帐期:2022-08-01; 票据到期日:2022-09-01";//当前日期往后7天，然后再往后30天
            String PAY_REM = "结帐期:"+OaErpTool.getFirstDayofAppointMonth(qdrqStr, "yyyy-MM-dd", 1)+"; 票据到期日:"+OaErpTool.getFirstDayofAppointMonth(qdrqStr, "yyyy-MM-dd", 2);
            
            //CUS_NO:厂商==yfgf1:乙方（供方）
            String CUS_NO = main46.getYfgf1()+"";
            //PO_DEP:采购部门==??
            String PO_DEP = "0000";//默认为总经办
            //USE_DEP:使用部门==??
            String USE_DEP = "0000";//默认为总经办
            //TAX_ID:扣税类别==??
            String TAX_ID = "2";//默认为2
            //ZHANG_ID:立账==??
            String ZHANG_ID = "1";//默认为1
            //SEND_MTH:交货方式==??
            String SEND_MTH = "1";//默认为1
            //EXC_RTO:页面不可见，此字段为null时不仅影响保存，还影响合计金额不会自动计算合计
            String EXC_RTO = "1.00000000";//默认为1
            //REM:备注==??
            String REM = "OA数据交换标识：（新物料订购合同）"+main46.getDh();
            
            String insertMF_POS="insert into MF_POS(OS_ID,OS_NO,QT_NO,OS_DD,USR,PAY_REM,CUS_NO,PO_DEP,USE_DEP,TAX_ID,ZHANG_ID,SEND_MTH,EXC_RTO,REM) values('PO','"+ currentPoNo +"','"+QT_NO+"','"+OS_DD+"','"+USR+"','"+PAY_REM+"','"+CUS_NO+"','"+PO_DEP+"','"+USE_DEP+"','"+TAX_ID+"','"+ZHANG_ID+"','"+SEND_MTH+"','"+EXC_RTO+"','"+REM+"')";
            logger.info("insertMF_POS=="+insertMF_POS);
            targetjsonBuf.append("insertMF_POS=="+insertMF_POS+";\n");
            //添加MF_POS表数据
            statement = conn.prepareStatement(insertMF_POS);
            int mf_posFlag = statement.executeUpdate();
            logger.info("MF_POS表数据添加："+mf_posFlag);
            statement.close();//关闭statement
            
            
            
            //获取明细表List
			List<Formtable_main_46_dt1> main46dt1List = main46Service.selectMain46dt1ListByMainid(main46.getId());
			sourcejsonBuf.append(JSON.toJSONString(main46)).append(JSON.toJSONString(main46dt1List));
			
			sourcejson = sourcejsonBuf.toString();//赋值给静态常量
            logger.info("sourcejson:\n"+sourcejson+"\n");
            
            //删除TF_POS_Z历史数据 ERP系统删除采购订单时会自动删除，但OA交互数据在ERP系统里删除时不会删除此表数据，假如有历史数据ERP也能自动清空，而OA交互则会冲突
            String selectTF_POS_Z = "select * from TF_POS_Z where OS_NO='"+currentPoNo+"' and OS_ID='PO'";
            statement = conn.prepareStatement(selectTF_POS_Z);
            ResultSet rs = statement.executeQuery();
            logger.info(rs.toString());
//            String TF_POS_ZJson = JSON.toJSONString(rs);//不支持定位的更新和删除。
            String TF_POS_ZJson = resultSetToJson(rs);
            logger.info("selectTF_POS_Z=="+selectTF_POS_Z);
            logger.info("TF_POS_ZJson=="+TF_POS_ZJson);
            targetjsonBuf.append("selectTF_POS_Z=="+selectTF_POS_Z+";\n");
            targetjsonBuf.append("TF_POS_ZJson=="+TF_POS_ZJson+";\n");
            
            while(rs.next()) {//前面resultSetToJson方法执行过rs.next()方法后，rs循环过后内容为空！！
            	logger.info("1.................");
            }
            rs.close();
            statement.close();
            
            String deleteTF_POS_Z= "delete from TF_POS_Z where OS_NO='"+currentPoNo+"' and OS_ID='PO'";
            targetjsonBuf.append("deleteTF_POS_Z="+deleteTF_POS_Z+";\n");
            logger.info("deleteTF_POS_Z=="+deleteTF_POS_Z);
            statement = conn.prepareStatement(deleteTF_POS_Z);
            int deleteTF_POS_ZFlag = statement.executeUpdate();
            logger.info("TF_POS_Z表数据删除："+deleteTF_POS_ZFlag);
            statement.close();
            
			int tf_posFlag = 0;
			int tf_pos_zFlag = 0;
			int i = 1;
			//货品个数（明细表条数）
			int hp = 0;
			//货品数量（合计）
			double sl = 0D;
			//货品总金额
			double je = 0;
			for (Formtable_main_46_dt1 main46dt1 : main46dt1List) {
				logger.info("main46dt1="+i+"="+JSON.toJSON(main46dt1));
				
				int tf_posNextMaxITM = 1;
				String tf_posMaxITM = "select max(ITM) ITM from TF_POS where OS_NO = '"+currentPoNo+"'";
				statement = conn.prepareStatement(tf_posMaxITM);
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
					int int1 = resultSet.getInt("ITM");
					tf_posNextMaxITM = int1+1;
				}
				statement.close();
				
				//添加TF_POS表数据 需要对应关系
				//QT_NO:请购单号==??
//				String QT_NO = "";//前面已经定义过了 SQ5C240003
				
				//PRD_NAME:品名==wlmc:物料名称
				String PRD_NAME = main46dt1.getWlmc();
				//PRD_NO:品号==wlbh:物料编码
				String PRD_NO = main46dt1.getWlbm();
				//WH:仓库==??
				String WH = "H001";//默认：耗材仓
				//UNIT:单位==dw:单位
//				String UNIT = main46dt1.getDw();
				String UNIT = "1";//1-->个
				//QTY:数量==sl:数量
				String QTY = main46dt1.getSl()+"";
				sl = sl+main46dt1.getSl().doubleValue();
				//UP:单价==djj:单价
				String UP = main46dt1.getDjj()+"";
				//AMT:金额==jej
				String AMT = main46dt1.getJej()+"";
				//AMTN:金额(此项为空时,合计金额为空)==jej
				String AMTN = main46dt1.getJej()+"";
				je = je + main46dt1.getJej().doubleValue();
				//EST_DD:预交日==jhrq:交货日期
				String EST_DD = main46dt1.getJhrq();
				//SPC:货品规格==ggjxh:规格及型号
				String SPC = main46dt1.getGgjxh();
				//PRE_ITM==??
				String PRE_ITM = tf_posNextMaxITM+"";//默认和ITM值保持一致
				//OTH_ITM==??
				String OTH_ITM = tf_posNextMaxITM+"";//默认和ITM值保持一致
				String insertTF_POS = "insert into TF_POS (OS_ID,OS_NO,ITM,QT_NO,PRD_NO,PRD_NAME,WH,UNIT,QTY,UP,AMT,AMTN,EST_DD,SPC,PRE_ITM,OTH_ITM) values ('PO','"+currentPoNo+"','"+tf_posNextMaxITM+"','"+QT_NO+"','"+PRD_NO+"','"+PRD_NAME+"','"+WH+"','"+UNIT+"','"+QTY+"','"+UP+"','"+AMT+"','"+AMTN+"','"+EST_DD+"','"+SPC+"','"+PRE_ITM+"','"+OTH_ITM+"')";
		        logger.info("insertTF_POS="+i+"="+insertTF_POS);
		        targetjsonBuf.append("insertTF_POS="+i+"="+insertTF_POS+";\n");
	            statement = conn.prepareStatement(insertTF_POS);
	            tf_posFlag = statement.executeUpdate();
	            statement.close();
	            logger.info("TF_POS表数据添加：第"+i+"条记录："+tf_posFlag);
	            
	            
	            //添加TF_POS_Z表数据 需要对应关系
	            String insertTF_POS_Z = "insert into TF_POS_Z (OS_ID,OS_NO,ITM,YT) values ('PO','"+currentPoNo+"','"+tf_posNextMaxITM+"','用途') ";
	            logger.info("insertTF_POS_Z="+i+"="+insertTF_POS_Z);
	            targetjsonBuf.append("insertTF_POS_Z="+i+"="+insertTF_POS_Z+";\n");
	            statement = conn.prepareStatement(insertTF_POS_Z);
	            tf_pos_zFlag = statement.executeUpdate();
	            logger.info("TF_POS_Z表数据添加：第"+i+"条记录："+tf_pos_zFlag);
	            statement.close();//关闭statement
	            i++;
			}
			hp = i-1;//前面循环结束时运算了i++,故而实际条目是i-1
            
            statement.close();//关闭statement
            
            //新的最大ITM
            String newMaxITM = getNewMaxITM();
            
            //添加 WF_TEMPID表数据 需要对应关系
            String insertWF_TEMPID = "insert into WF_TEMPID(TYPE,ITM,IS_USED) values('IN','"+newMaxITM+"','T')";
            logger.info("insertWF_TEMPID=="+insertWF_TEMPID);
            targetjsonBuf.append("insertWF_TEMPID=="+insertWF_TEMPID+";\n");
            statement = conn.prepareStatement(insertWF_TEMPID);
//	            boolean tempIdFlag = statement.execute();//execut()数据入库，返回还是false
            int wf_tepidFlag = statement.executeUpdate();
//	            statement.executeUpdate(tempIdInsert);
            logger.info("WF_TEMPID表数据添加："+wf_tepidFlag);
            statement.close();//关闭statement
            
            //添加WF_FLOW_INST表数据 需要对应关系
            StringBuffer insertWF_FLOW_INST = new StringBuffer();
            
            //BIL_DD:单据日期==qdrq:签订日期
            String BIL_DD = qdrqStr;
            //USR:制单人==jfcgy:甲方采购员ID
//            String USR = "";
//            new DecimalFormat("######0").format(sl);
//            new DecimalFormat("######0.00").format(je);
            REM = "厂商名称:升谱光电半导体有限公司;币别:;单据类别：;采购"+hp+"个货品;数量合计:"+new DecimalFormat("######0").format(sl)+";金额合计:"+new DecimalFormat("######0.00").format(je)+";";//重置REM值
            insertWF_FLOW_INST.append("insert into WF_FLOW_INST(INST_ID,TEMP_ID,BIL_NO,BIL_ID, BIL_DD, USR, DEP, REM,SH_STATE, CUR_NODE, BIL_ITM) ");
            insertWF_FLOW_INST.append("values ('"+newMaxITM+"','10002','"+currentPoNo+"','PO','"+BIL_DD+"','"+USR+"','0000','"+REM+"','0','10005','0')");
            logger.info("insertWF_FLOW_INST=="+insertWF_FLOW_INST.toString());
            targetjsonBuf.append("insertWF_FLOW_INST=="+insertWF_FLOW_INST.toString()+";\n");
            statement = conn.prepareStatement(insertWF_FLOW_INST.toString());
//	            boolean instFlag = statement.execute();//execut()数据入库，返回还是false
            int wf_flow_instFlag = statement.executeUpdate();
            logger.info("WF_FLOW_INST表数据添加："+wf_flow_instFlag);
            statement.close();//关闭statement
            
            targetjson = targetjsonBuf.toString();//赋值给静态常量
            logger.info("targetjson:\n"+targetjson);
            
            //所有业务表和工作流表数据正常入库才返回成功标识1，否则为0
			if (mf_posFlag > 0 && tf_posFlag > 0 && tf_pos_zFlag > 0 && wf_tepidFlag > 0 && wf_flow_instFlag > 0) {
				ret = 1;
			}
			//
//			int j = 10/0;
            
            //
//	            statement = dbConn.prepareStatement(instinsert.toString());
//	          boolean execute = statement.execute();
//	          int execute = statement.executeUpdate();
//	          int execute = statement.executeUpdate(instinsert.toString());
//	          logger.info("添加wf_flow_inst表是否成功："+execute);
            

            //删除
//			            statement = dbConn.prepareStatement(delete);
//			            int res3=statement.executeUpdate();
//			            logger.info(res3);
            
        } catch (Exception e) {
        	//抛异常时也给sourcejson和targetjson常量赋值
        	sourcejson = sourcejsonBuf.toString();//赋值给静态常量
        	logger.info("sourcejson:\n"+sourcejson+"\n");
        	targetjson = targetjsonBuf.toString();//赋值给静态常量
            logger.info("targetjson:\n"+targetjson);
            
            e.printStackTrace();
//            message = e.getMessage();
//            message = e.getLocalizedMessage();
            StringBuffer messageBuff = new StringBuffer();
            messageBuff.append(e.getMessage()+";\n");
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
//            	logger.info("==================1===============");
//				logger.info(stackTraceElement.toString());
				messageBuff.append(stackTraceElement.toString()+";\n");
//				logger.info("==================2===============");
			}
            message = messageBuff.toString();
            
            logger.info("数据库操作失败！");
        }
        return ret;
	}
	
	//生成采购订单号，规则：PO+年份最后1位+1位月份（1-9ABC）+2位日（01-12）+4位排序（0001开始）
    public static String getAutoPoNo(Date date) {
    	PreparedStatement statement;
    	//获取当前年份最后1位
    	//以当前时间生成采购订单号
//    	String dateStr = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(new Date());
    	//以签订时间生成采购订单号
    	String dateStr = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(date);
    
    	String[] split = dateStr.split("-");//分割年月日
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
//    	logger.info("prePoNo="+prePoNo);
    	
    	//当前数据库中最的采购订单号 后缀
    	String maxSufString = "0001";
		try {//查询目标数据库中当天的采购订单后最大的订单号 后缀
			String select = "select * from MF_POS where OS_NO like '"+prePoNo+"%'";
			statement = conn.prepareStatement(select);
			ResultSet res=statement.executeQuery();
			//当查询下一行有记录时：res.next()返回值为true，反之为false
            while (res.next()) {//循环比较结束后，maxSufString为最新的订单号后缀
                String OS_NO = res.getString("OS_NO");
//                logger.info("OS_NO：" + OS_NO);
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
			logger.info("数据库连接失败！");
		}
		logger.info("getAutoPoNo=="+prePoNo+maxSufString);
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
			statement = conn.prepareStatement(select);
			ResultSet res=statement.executeQuery();
			//当查询下一行有记录时：res.next()返回值为true，反之为false
            while (res.next()) {//循环比较结束后，itm为当前最新的ITM号
                itm = res.getString("ITM");
                logger.info("ITM:" + itm);
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
			logger.info("数据库连接失败！");
		}
    	logger.info("getNewMaxITM=="+itm);
    	return itm;
    }
    
    /**
     * 生成TF_POS_Z表最新的ITM号
     * @return
     */
    public static String getTF_POS_ZNewMaxITM(Date date){
    	PreparedStatement statement ;
    	String itm = "";
    	try {//获取WF_TEMPID表最大ITM号
    		String select = "select max(ITM) ITM from TF_POS_Z where OS_ID = 'PO' and OS_NO = '"+getAutoPoNo(date)+"'";
    		statement = conn.prepareStatement(select);
    		ResultSet res=statement.executeQuery();
    		//当查询下一行有记录时：res.next()返回值为true，反之为false
    		while (res.next()) {//循环比较结束后，itm为当前最新的ITM号
    			itm = res.getString("ITM");
//                logger.info("ITM:" + itm);
    			//itm为null时，赋值为0参与运算
    			if(StringUtils.isEmpty(itm)) {
    				itm="0";
    			}
    			
    			int parseInt = Integer.parseInt(itm);
    			parseInt++;//当前ITM+1,生成最新的ITM号
    			itm = parseInt+"";
//    			logger.info("itm="+itm);
    		}
    		//关闭statement
    		statement.close();//关闭
//            dbConn.close();//关闭jdbc
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		logger.info("数据库连接失败！");
    	}
    	logger.info("getTF_POS_ZNewMaxITM=="+itm);
    	return itm;
    }
    
    /**
     * Java数据库ResultSet转json实现
     * @param rs:ResultSet数据库结果集
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public String resultSetToJson(ResultSet rs) throws SQLException,JSONException  {  
       // json数组  
       JSONArray array = new JSONArray();  
        
       // 获取列数  
       ResultSetMetaData metaData = rs.getMetaData();  
       int columnCount = metaData.getColumnCount();  
        
       // 遍历ResultSet中的每条数据  
        while (rs.next()) {  
            JSONObject jsonObj = new JSONObject();  
             
            // 遍历每一列  
            for (int i = 1; i <= columnCount; i++) {  
                String columnName =metaData.getColumnLabel(i);  
                String value = rs.getString(columnName);  
                jsonObj.put(columnName, value);  
            }   
//            array.put(jsonObj);
            array.add(jsonObj);
        }  
        
       return array.toString();  
    }
    
    
    //校验数据，满足数据条件才往ERP数据库插入数据
    public boolean vailidata() {
    	//erp系统不能为空字段，主表：MF_POS
    	
    	//erp系统不能为空字段，明细表：TF_POS
    	
    	return false ;
    }

}
