package com.assp.modules.template.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 用户收藏模板类
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangzhipeng@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年6月27日
 */
@Table(name = "wms_user_rel_template")
public class UserRelTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer userRelTemplateId;			 // 'id',
	private Integer userId;			 // '用户id',
	private Integer templateId;			 // '模板id',
	private String relStatus; 		// '关系状态0 可用（默认） 1 不可用',
	@Transient
	private Date createTime; 		// '创建时间',
	private Date updateTime; 		// '更新时间',
	private Date deadTime; 		// '到期时间',
	public Integer getUserRelTemplateId() {
		return userRelTemplateId;
	}
	public void setUserRelTemplateId(Integer userRelTemplateId) {
		this.userRelTemplateId = userRelTemplateId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getRelStatus() {
		return relStatus;
	}
	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getDeadTime() {
		return deadTime;
	}
	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
}
