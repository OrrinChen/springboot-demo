package cn.java.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.java.controller.Main46Controller;
import cn.java.entity.Formtable_main_3;
import cn.java.entity.Formtable_main_46;
import cn.java.entity.Formtable_main_46WithBLOBs;
import cn.java.entity.Oaerp;
import cn.java.mapper.OaerpMapper;
import cn.java.service.Main46Service;
import cn.java.service.OaerpService;
import cn.java.tasks.OaErpTask;

/**
 * oa_erp数据表业务类
 * @author Administrator
 *
 */
@Service
public class OaerpServiceImpl implements OaerpService {
	
	Logger logger  = Logger.getLogger(OaerpServiceImpl.class);
	
	@Autowired
	private OaerpMapper oaerpMapper;
	
	/**
	 * 插入数据
	 */
	@Override
	public int insertData() {
		Oaerp oaerp = new Oaerp();
		UUID uuid = UUID.randomUUID();
		oaerp.setId(uuid.toString());
		oaerp.setchangelog("changelog");
		oaerp.setSourceid("sourceid");
		oaerp.setSourcejson("sourcejson");
		oaerp.setSourcetable("sourcetable");
		oaerp.setStatus("status");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatdate = sdf.format(date);
		logger.info("date="+date);
		logger.info("formatdate="+formatdate);
		byte[] time = null;
		oaerp.setTime(date);
		int falg = oaerpMapper.insert(oaerp);
		return falg;
	}
	
	/**
	 * 插入数据
	 * Formtable_main_3源数据表
	 */
	@Override
	public int insertData(Formtable_main_3 main3) {
		Oaerp oaerp = new Oaerp();
		UUID uuid = UUID.randomUUID();
		oaerp.setId(uuid.toString());
		oaerp.setchangelog(OaErpTask.message);
		OaErpTask.message ="";//记录后台报错信息后清空
		oaerp.setSourceid(main3.getId().toString());
		String main3String = JSONObject.toJSONString(main3);
		oaerp.setSourcejson(main3String);//main3对象
		oaerp.setSourcetable("Formtable_main_3");
		oaerp.setStatus(OaErpTask.status+"");//1:成功 0:失败 3:数据格式不正确
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatdate = sdf.format(date);
		logger.info("date="+date);
		logger.info("formatdate="+formatdate);
		byte[] time = null;
		oaerp.setTime(date);
		int falg = oaerpMapper.insert(oaerp);
		return falg;
	}
	
	
	/**
	 * 插入数据
	 * Formtable_main_46:源数据表
	 * Date:当前源数据表记录对应的工作流办结时间
	 */
	@Override
	public int insertData(Formtable_main_46WithBLOBs main46,Date date) {
		Oaerp oaerp = new Oaerp();
		UUID uuid = UUID.randomUUID();
		oaerp.setId(uuid.toString());
//		oaerp.setchangelog("交换记录");
		oaerp.setchangelog(Main46Controller.message);
		Main46Controller.message ="";//记录后台报错信息后清空
		oaerp.setSourceid(main46.getId().toString());
//		String main46String = JSONObject.toJSONString(main46);
//		oaerp.setSourcejson(main46String);//main46对象
		oaerp.setSourcejson(Main46Controller.sourcejson);//main46对象
		Main46Controller.sourcejson ="";//记录后清空
		oaerp.setSourcetable("formtable_main_46;formtable_main_46_dt1");
		oaerp.setStatus(Main46Controller.status+"");//1:成功 0:失败 3:数据格式不正确
		Main46Controller.status = 0;//记录后恢复默认值
		
		//target记录
		oaerp.setTargettable("MF_POS;TF_POS;TF_POS_Z;WF_TEMPID;WF_FLOW_INST");//目标表
		oaerp.setTargetid(Main46Controller.targetid);//目标表id
		Main46Controller.targetid = "";//记录后清空
		oaerp.setTargetjson(Main46Controller.targetjson);//目标表json记录
		Main46Controller.targetjson = "";//记录后清空
		
		//记录交换触发条件：时间戳
//		Date maxTime = getMaxTime("formtable_main_46;formtable_main_46_dt1");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String formatdate = sdf.format(maxTime);
//		logger.info("maxTime="+maxTime);
//		logger.info("formatdate="+formatdate);
//		byte[] time = null;
		//触发条件：工作流归档时间
		oaerp.setTime(date);
		//数据交互时间
		oaerp.setchangetime(new Date());//当前时间
		
		int falg = oaerpMapper.insert(oaerp);
		return falg;
	}
	
	/**
	 * 获取源表最大id值
	 * @return
	 */
	@Override
	public int getMaxSourceId() {
		return oaerpMapper.selectMaxSourceId();
	}
	
	/**
	 * 根据sourcetable获取当前最大交换时间
	 * @return
	 */
	@Override
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS",timezone = "GMT+8")不起作用
	public Date getMaxTime(String sourcetable) {
		return oaerpMapper.selectMaxTime(sourcetable);
	}

	/**
	 * 根据表名字段获取所有数据
	 * tableNames:表名或者表名字段组合
	 */
	@Override
	public List<Oaerp> getAllData(String tableNames) {
		logger.info("tableNames=="+tableNames);
//		return oaerpMapper.getAll(tableNames);
		List<Oaerp> listAll = oaerpMapper.getAll(tableNames);
		
		return listAll;
	}

	
	/**
	 * 根据表名字段获取最新一行数据
	 * @param tableNames:表名或者表名字段组合
	 * @return
	 */
	@Override
	public Oaerp getTop1Data(String tableNames) {
		Oaerp oaerp = oaerpMapper.selectTop1Data(tableNames);
		return oaerp;
	}
	
	/**
	 * 根据查询条件获取数据
	 * @param tableNames 表名或者表名字段组合
	 * @param condition 查询条件
	 */
	@Override
	public List<Oaerp> getOaerpData(String tableNames, String status, String targetid) {
//		List<Oaerp> aoerpList = oaerpMapper.getOaerpData(tableNames,condition);
		List<Oaerp> aoerpList = oaerpMapper.getOaerpDataByCondition(tableNames, status, targetid);
		return aoerpList;
	}

}
