package com.assp.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
  * 类简述
  * <p>
  * 用户反馈信息(optional)
  * </p>
  * 
  * @Company 动力威视
  * @Copyright
  * @author (wangkang@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年7月1日 上午10:55:07 
  */
@Table(name="wms_user_feedback")
public class UserFeedback implements Serializable{
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer feedbackId;			//'用回反馈ID'
	private String feedbackName;		//'用户姓名'
	private String feedbackBusiness;	//'企业名称'
	private Integer feedbackPhone;		//'用户电话'
	private String feedback_email;		//'用户邮箱'
	private String feedbackSuggest;		//'用户建议'
	private Date createTime;			//'创建时间'
	private Date updateTime;			//'修改时间'
	private Integer dataStatus;			//'状态0正常 1删除'
	
	
	public Integer getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getFeedbackName() {
		return feedbackName;
	}
	public void setFeedbackName(String feedbackName) {
		this.feedbackName = feedbackName;
	}
	public String getFeedbackBusiness() {
		return feedbackBusiness;
	}
	public void setFeedbackBusiness(String feedbackBusiness) {
		this.feedbackBusiness = feedbackBusiness;
	}
	public Integer getFeedbackPhone() {
		return feedbackPhone;
	}
	public void setFeedbackPhone(Integer feedbackPhone) {
		this.feedbackPhone = feedbackPhone;
	}
	public String getFeedback_email() {
		return feedback_email;
	}
	public void setFeedback_email(String feedback_email) {
		this.feedback_email = feedback_email;
	}
	public String getFeedbackSuggest() {
		return feedbackSuggest;
	}
	public void setFeedbackSuggest(String feedbackSuggest) {
		this.feedbackSuggest = feedbackSuggest;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}
	
	
	
}
  