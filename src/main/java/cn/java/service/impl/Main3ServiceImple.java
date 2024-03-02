package cn.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.entity.Formtable_main_3;
import cn.java.mapper.Formtable_main_3Mapper;
import cn.java.service.Main3Service;

/**
 * table:formtable_main_3(请假申请单)
 * 业务实现类
 * @author Administrator
 *
 */
@Service
public class Main3ServiceImple implements Main3Service {
	
	@Autowired
	private Formtable_main_3Mapper formtable_main_3Mapper;
	
	@Override
	public Formtable_main_3 getDataById(int id) {
		Formtable_main_3 formtable_main_3 = formtable_main_3Mapper.selectByPrimaryKey(id);
		return formtable_main_3;
	}
	
	@Override
	public List getAllData() {
		List list = formtable_main_3Mapper.selectAll();
		return list;
	}
	
	@Override
	public List getDataByConditon(int id) {
		return formtable_main_3Mapper.selectByCondition(id);
	}

	@Override
	public Formtable_main_3 getNextMaxDataById(int maxSourceId) {
		return formtable_main_3Mapper.selectNextMaxById(maxSourceId);
	}
	
}
