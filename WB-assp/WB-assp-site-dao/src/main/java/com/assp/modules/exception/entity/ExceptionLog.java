package com.assp.modules.exception.entity;

import java.io.Serializable;
import java.util.Date;

public class ExceptionLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	//发生异常的系统
	private String systemCode;
	//异常发生时间
	private Date occuredTime;
	//异常stacktrace
	private String stackTrace;
	//异常发生发生的地点(类、请求)
	private String source;
	//定位异常的线索(异常发生时的关键上下文)
	private String hints;
	//其他备注信息
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public Date getOccuredTime() {
		return occuredTime;
	}
	public void setOccuredTime(Date occuredTime) {
		this.occuredTime = occuredTime;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getHints() {
		return hints;
	}
	public void setHints(String hints) {
		this.hints = hints;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}