package com.assp.modules.website.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月22日 下午12:27:35
 */
@Table(name = "wms_user_rel_website")
public class UserRelWebsite {

	@Id
	private Integer userRelSiteId; // '用户关联网站信息id',
	private Integer userId; // '用户id',
	private Integer siteId; // '站点id',
	private Date createTime; // '创建时间',
	private Date deadTime; // '到期时间',
	private Integer relStatus; // '关系状态 0 可用（默认） 1 不可用',

	public Integer getUserRelSiteId() {
		return userRelSiteId;
	}

	public void setUserRelSiteId(Integer userRelSiteId) {
		this.userRelSiteId = userRelSiteId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
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

	public Integer getRelStatus() {
		return relStatus;
	}

	public void setRelStatus(Integer relStatus) {
		this.relStatus = relStatus;
	}

}
