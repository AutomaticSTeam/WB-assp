package com.assp.modules.aspectjlog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类简述
 * <p>
 * 用于记录用户在系统中所有的操作动作
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月18日 下午3:04:57
 */
@Table(name = "sys_operate_logs")
public class OperateLogs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer operateLogsId; // '系统操作日志id'
	private Integer operatorId; // '操作人id'
	private String operateName; // '操作人姓名'
	private String operateModule; // '操作模块'
	private String operateContent; // '操作内容'
	private Date operateTime; // '操作时间'
	private Integer operateType; // '操作类型'
	private String remark; // '备注'
	
	public OperateLogs(){}
	public OperateLogs(Integer operatorId , String operateName, String operateModule, String operateContent, Integer operateType,String remark){
		this.operatorId = operatorId;
		this.operateName = operateName;
		this.operateModule = operateModule;
		this.operateContent = operateContent;
		this.operateTime = new Date();
		this.operateType = operateType;
		this.remark = remark;
	}

	public Integer getOperateLogsId() {
		return operateLogsId;
	}

	public void setOperateLogsId(Integer operateLogsId) {
		this.operateLogsId = operateLogsId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getOperateModule() {
		return operateModule;
	}

	public void setOperateModule(String operateModule) {
		this.operateModule = operateModule;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
