package cn.java.service;

import java.util.Date;
import java.util.List;

import cn.java.entity.Formtable_main_3;
import cn.java.entity.Formtable_main_46WithBLOBs;
import cn.java.entity.Oaerp;

public interface OaerpService {
	/**
	 * 插入数据
	 * @return
	 */
	int insertData();
	/**
	 * 获取源表最大id值
	 * @return
	 */
	int getMaxSourceId();
	/**
	 * 插入数据
	 * @param Formtable_main_3目标表实例对象
	 * @return
	 */
	int insertData(Formtable_main_3 main3);
	/**
	 * 根据sourcetable获取当前最大交换时间
	 * @return
	 */
	Date getMaxTime(String sourcetable);
	/**
	 * 插入数据
	 * Formtable_main_46:源数据表
	 * Date:当前源数据表记录对应的工作流办结时间
	 */
	int insertData(Formtable_main_46WithBLOBs main46,Date date);
	/**
	 * 根据表名字段获取所有数据
	 * tableNames:表名或者表名字段组合
	 */
	List<Oaerp> getAllData(String tableNames);
	/**
	 * 根据表名字段获取最新一行数据
	 * @param tableNames:表名或者表名字段组合
	 * @return
	 */
	Oaerp getTop1Data(String tableNames);
	/**
	 * 根据查询条件获取数据
	 * @param tableNames 表名或者表名字段组合
	 * @param condition 查询条件
	 */
	List<Oaerp> getOaerpData(String tableNames, String status, String targetid);

}