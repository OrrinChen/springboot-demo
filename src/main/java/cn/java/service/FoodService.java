package cn.java.service;

import java.util.List;
import java.util.Map;

import cn.java.entity.Food;

public interface FoodService {
	
	/**
	 * 保存数据
	 * @param food:持久化实体类(食物)
	 * @return
	 */
	boolean saveFood(Food food);
	
	/**
	 * 删除数据(根据id)
	 * @param id:主键
	 * @return
	 */
	boolean delFood(Long id);
	
	/**
	 * 获取Foods表的所有数据
	 * @return List<Map<String,Object>封装数据
	 */
	List<Map<String, Object>> findFood();

}