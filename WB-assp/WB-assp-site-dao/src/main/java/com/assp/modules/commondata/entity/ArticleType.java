package com.assp.modules.commondata.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 文章类型
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 上午10:44:09
 */
@Table(name = "wms_article_type")
public class ArticleType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer articleTypeId; // '主键',
	private String articleTypeName; // '分类名称',
	private String articleTypeDesc; // '文章分类描述',
	private Integer articleTypePid; // 
	private Integer operatorId;
	private String operatorName; // '创建者姓名',
	private Date createTime; // '创建时间',
	private Date updateTime; // '更新时间',
	private Integer dataStatus; // '数据状态 0 正常 1 删除',


	
	private Integer siteId;//站点id，用作查询用

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getArticleTypeName() {
		return articleTypeName;
	}

	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}

	public String getArticleTypeDesc() {
		return articleTypeDesc;
	}

	public void setArticleTypeDesc(String articleTypeDesc) {
		this.articleTypeDesc = articleTypeDesc;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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

	public Integer getArticleTypePid() {
		return articleTypePid;
	}

	public void setArticleTypePid(Integer articleTypePid) {
		this.articleTypePid = articleTypePid;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}
