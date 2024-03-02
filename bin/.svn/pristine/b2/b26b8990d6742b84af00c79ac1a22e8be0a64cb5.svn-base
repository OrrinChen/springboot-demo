package cn.java.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.entity.Formtable_main_46WithBLOBs;
import cn.java.entity.Formtable_main_46_dt1;
import cn.java.entity.Workflow_requestbaseWithBLOBs;
import cn.java.mapper.Formtable_main_46Mapper;
import cn.java.mapper.Formtable_main_46_dt1Mapper;
import cn.java.mapper.Workflow_requestbaseMapper;
import cn.java.service.Main46Service;
/**
 * 业务表：Formtable_main_46（新物料订购合同主表）
 * @author ds16
 *
 */
@Service
public class Main46ServiceImpl implements Main46Service {
	
	@Autowired
	private Workflow_requestbaseMapper workflow_requestbaseMapper;
	@Autowired
	private Formtable_main_46Mapper formtable_main_46Mapper;
	@Autowired
	private Formtable_main_46_dt1Mapper formtable_main_46_dt1Mapper;
	
	/**
	 * 根据requestid获取工作流对象
	 * @return
	 */
	@Override
	public Workflow_requestbaseWithBLOBs selectWorkflowByRequestid(Integer requestid) {
		return workflow_requestbaseMapper.selectByRequestid(requestid);
	}
	
	/**
	 * 根据id获取业务主表对象（Formtable_main_46）
	 * @param id:主表id
	 * @return
	 */
	@Override
	public Formtable_main_46WithBLOBs selectMain46ById(Integer id) {
		Formtable_main_46WithBLOBs formtable_main_46WithBLOBs = formtable_main_46Mapper.selectByPrimaryKey(id);
		return formtable_main_46WithBLOBs;
	}

	/**
	 * 根据requestid获取业务主表对象（Formtable_main_46）
	 * @param requestid:主表requestid
	 * @return
	 */
	@Override
	public Formtable_main_46WithBLOBs selectMain46ByRequestid(Integer requestid) {
		Formtable_main_46WithBLOBs formtable_main_46WithBLOBs = formtable_main_46Mapper.selectByRequestid(requestid);
		return formtable_main_46WithBLOBs;
	}
	
	/**
	 * 根据id获取业务明细表（Formtable_main_46_dt1）
	 * @param id:明细表id
	 * @return
	 */
	@Override
	public Formtable_main_46_dt1 selectMain46dt1ById(Integer id) {
		Formtable_main_46_dt1 formtable_main_46_dt1 = formtable_main_46_dt1Mapper.selectByPrimaryKey(id);
		return formtable_main_46_dt1;
	}
	
	/**
	 * 根据主表id获取业务明细表list<Formtable_main_46_dt1>
	 * @param mainid:主表id
	 * @return
	 */
	@Override
	public List<Formtable_main_46_dt1> selectMain46dt1ListByMainid(Integer mainid) {
		List<Formtable_main_46_dt1> main46dt1List = formtable_main_46_dt1Mapper.selectByMainid(mainid);
		return main46dt1List;
	}
	
	/**
	 * 
	 */
	@Override
	public Workflow_requestbaseWithBLOBs selectWorkflowByIdAndTime(Integer workflowid, Date maxTime) {
		return workflow_requestbaseMapper.selectWorkflowByIdAndTime(workflowid,maxTime);
		
	}
	
	
}
