package cn.java.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.java.entity.Oaerp;
import cn.java.service.OaerpService;

/**
 * OA-ERP数据交换
 * @author Administrator
 *
 */
@CrossOrigin//跨域
@Controller
@RequestMapping("/oaerp")
public class OaerpController {
	
	@Autowired
	private OaerpService oaerpService;
	
	Logger logger = Logger.getLogger(OaerpController.class);
	
	@RequestMapping("/test")
	public void test() {
		logger.info("OaErpController.test()。。。");
	}
	
	@RequestMapping("/getBean")
	@ResponseBody
	public String getBean() {
		logger.info("OaErpController.getBean()...");
		//return "OaErpController.getBean()...";
		return "fornt/toHome.jsp";
	}
	
	@RequestMapping("/createData")
	@ResponseBody
	public boolean createData() {
		int insertData = oaerpService.insertData();
		return insertData>0?true:false;
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Oaerp> getAll(){
		List<Oaerp> oaerpList = oaerpService.getAllData("formtable_main_46;formtable_main_46_dt1");
		return oaerpList;
	}
	
	@RequestMapping("/getOaerp")
	@ResponseBody
	public PageInfo<Oaerp> getOaerp(
//			@RequestParam(name = "query"/* , defaultValue = "" */) Map<String, String> condition,
			@RequestParam(name = "status"/* , defaultValue = "" */) String status,
			@RequestParam(name = "targetid"/* , defaultValue = "" */) String targetid,
			@RequestParam(name = "pagenum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pagesize", defaultValue = "2") Integer pageSize){
//		String condition = "123";
		logger.info("status:"+status+",targetid:"+targetid+",pagenum:"+pageNum+",pagesize:"+pageSize);
		// 后台分页PageHelper
		PageHelper.startPage(pageNum, pageSize);
		List<Oaerp> oaerpList = oaerpService.getOaerpData("formtable_main_46;formtable_main_46_dt1",status,targetid);
		//对Page<E>结果进行包装 包含分页信息及结果集list
		logger.info("-----1----"+oaerpList.size());
		PageInfo<Oaerp> pageInfo = new PageInfo<Oaerp>(oaerpList);
		logger.info("-----2----"+oaerpList.size());
		return pageInfo;
	}
	
	@RequestMapping("/getTop1")
	@ResponseBody
	public Oaerp getTop1() {
		Oaerp oaerp = oaerpService.getTop1Data("formtable_main_46;formtable_main_46_dt1");
		return oaerp;
	}
	
	@RequestMapping("/getMaxTime")
	@ResponseBody
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS",timezone = "GMT+8")//不起作用
	public Date getMaxTime() {
		Date maxTime = oaerpService.getMaxTime("formtable_main_46;formtable_main_46_dt1");
		logger.info("maxTime=="+maxTime);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(maxTime);
		logger.info("dateStr=="+dateStr);
//		return "maxTime="+dateStr ;
//		Date date  = new Date();
//		logger.info("date=="+date);
		return maxTime;
	}
	
	@RequestMapping("/getMaxTimeStr")
	@ResponseBody
	public String getMaxTimeStr() {
		Date maxTime = oaerpService.getMaxTime("formtable_main_46;formtable_main_46_dt1");
		logger.info("maxTime=="+maxTime);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(maxTime);
		logger.info("dateStr=="+dateStr);
		return "maxTimeStr="+dateStr ;
	}
	
}
