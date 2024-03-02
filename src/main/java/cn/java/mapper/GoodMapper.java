package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import cn.java.entity.Good;

public interface GoodMapper {
	/**
	 * 定义查询方法(通过@Select注解配置查询语句)
	 * @return
	 */
	@Select("SELECT * FROM goods")
	public List<Map<String, Object>> getAllGoods();
	
	/**
	 * 定义查询方法(通过xml文件配置查询语句)
	 * @return
	 */
	public List<Good> selectAllGoods();

}
