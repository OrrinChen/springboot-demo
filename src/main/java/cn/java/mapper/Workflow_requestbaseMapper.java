package cn.java.mapper;

import java.util.Date;

import cn.java.entity.Workflow_requestbaseWithBLOBs;

public interface Workflow_requestbaseMapper {
    int insert(Workflow_requestbaseWithBLOBs record);

    int insertSelective(Workflow_requestbaseWithBLOBs record);
    /*
     * 根据主键id获取工作流对象
     */
    Workflow_requestbaseWithBLOBs selectByRequestid(Integer id);
    
    /**
     * 根据工作流id和交换时间获取工作流对象
     * @param workflowid
     * @param maxTime
     */
    Workflow_requestbaseWithBLOBs selectWorkflowByIdAndTime(Integer workflowid, Date maxTime);
}