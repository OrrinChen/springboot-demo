package cn.java.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.java.entity.Oaerp;

public interface OaerpMapper {
    int deleteByPrimaryKey(String id);

    int insert(Oaerp record);

    int insertSelective(Oaerp record);

    Oaerp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Oaerp record);

    int updateByPrimaryKey(Oaerp record);
    
	int selectMaxSourceId();
	/**
	 * 根据sourcetable获取当前最大交换时间
	 * @param sourcetable
	 * @return
	 */
	//@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8") //不起作用
	Date selectMaxTime(String sourcetable);
	/**
	 * 
	 * @return
	 */
	@Select("select * from oa_erp where sourcetable = #{tableNames} order by changetime desc")
	List<Oaerp> getAll(String tableNames);
	
	/**
	 * 根据表名字段获取最新一行数据
	 * @param tableNames:表名或者表名字段组合
	 * @return
	 */
	@Select("select TOP 1 * from oa_erp where sourcetable = #{tableNames} order by changetime desc")
	Oaerp selectTop1Data(String tableNames);
	
	/**
	 * 
	 * @param tableNames
	 * @param condition
	 * @return
	 */
	@Select("select * from oa_erp where sourcetable = #{arg0} and targetid = #{arg1} order by changetime desc")
	List<Oaerp> getOaerpData(String tableNames, String condition);
	
	/**
	 * 根据查询条件获取数据
	 * @param tableName
	 * @param condition
	 * @return
	 */
	List<Oaerp> getOaerpDataByCondition(String tableNames, String status, String targetid);
}