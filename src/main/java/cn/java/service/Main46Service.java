package cn.java.service;

import java.util.Date;
import java.util.List;

import cn.java.entity.Formtable_main_46WithBLOBs;
import cn.java.entity.Formtable_main_46_dt1;
import cn.java.entity.Workflow_requestbaseWithBLOBs;

public interface Main46Service {

	/**
	 * 根据id获取业务主表对象（Formtable_main_46）
	 * @param id:主表id
	 * @return
	 */
	Formtable_main_46WithBLOBs selectMain46ById(Integer id);

	/**
	 * 根据id获取业务明细表（Formtable_main_46_dt1）
	 * @param id:明细表id
	 * @return
	 */
	Formtable_main_46_dt1 selectMain46dt1ById(Integer id);

	/**
	 * 根据主表id获取业务明细表list<Formtable_main_46_dt1>
	 * @param mainid:主表id
	 * @return
	 */
	List<Formtable_main_46_dt1> selectMain46dt1ListByMainid(Integer mainid);

	/**
	 * 根据requestid获取工作流对象
	 * @return
	 */
	Workflow_requestbaseWithBLOBs selectWorkflowByRequestid(Integer requestid);
	
	/**
	 * 根据指定时间获取工作流对象
	 * @param maxTime
	 * @return
	 */
	Workflow_requestbaseWithBLOBs selectWorkflowByIdAndTime(Integer workflowid, Date maxTime);

	/**
	 * 根据requestid获取业务主表对象（Formtable_main_46）
	 * @param requestid:主表requestid
	 * @return
	 */
	Formtable_main_46WithBLOBs selectMain46ByRequestid(Integer requestid);

}