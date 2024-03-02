package cn.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.entity.Food;
import cn.java.mapper.FoodMapper;
import cn.java.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodMapper foodMapper;

	/**
	 * 保存数据
	 * @param food:持久化实体类(食物)
	 * @return
	 */
	public boolean saveFood(Food food) {
		return foodMapper.addFood(food) > 0 ? true : false;
	}

	/**
	 * 删除数据(根据id)
	 * 
	 * @param id:主键
	 * @return
	 */
	public boolean delFood(Long id) {
		return foodMapper.delFood(id) > 0 ? true : false;
	}
	
	/**
	 * 获取Foods表的所有数据
	 * @return List<Map<String,Object>封装数据
	 */
	public List<Map<String, Object>> findFood(){
		return foodMapper.findFood();
	}

}
