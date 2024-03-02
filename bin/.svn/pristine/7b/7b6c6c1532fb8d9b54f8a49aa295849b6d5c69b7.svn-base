package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.java.entity.Food;

public interface FoodMapper {
	/**
	 * 添加数据
	 * 通过#{key的属性名}取值
	 * @param food:食品持久化对象
	 * @return
	 */
	@Insert("insert  into `foods`(`id`,`food_name`,`food_taste`,`food_price`,`food_description`) values (#{id},#{foodName},#{foodTaste},#{foodPrice},#{foodDescription})")
	int addFood(Food food);
	
	/**
	 * 删除数据(根据id)
	 * 通过#{arg下标}取值，即通过arg0,arg1,arg2,...传参数
	 * @param id:主键
	 * @return
	 */
	@Delete("delete from foods where id = #{arg0}")
	int delFood(Long id);
	
	/**
	 * 获取Foods表的所有数据
	 * @return List<Map<String,Object>封装数据
	 */
	@Select("SELECT * FROM foods")
	List<Map<String, Object>> findFood();
}
