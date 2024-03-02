package cn.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.entity.Good;
import cn.java.mapper.GoodMapper;
import cn.java.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {
	
	@Autowired
	private GoodMapper goodMapper;
	
	/**
	 * 查询所有goods表数据(List对象封装)
	 */
	public List<Map<String, Object>> getAllGoods(){
		return goodMapper.getAllGoods();
	}
	
	/**
	 * 查询所有goods表数据(Good对象封装)
	 */
	public List<Good> selectAllGoods(){
		return goodMapper.selectAllGoods();
	}

}
