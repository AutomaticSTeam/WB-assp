package com.assp.modules.template.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 模板类
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangzhipeng@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年6月28日
 */
@Table(name="wms_template_rel_columns")
public class TemplateRelColumns implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer templateRelColumnsId; // '模板关联主键id',
	private Integer templateId; // '模板id',
	private Integer columnsId; // '模板对应的导航id',
	private Integer sortNum; // '排序',
	private Integer columnsHide; // 是否隐藏,
	private Integer relStatus; // '关联关系是否有效',
	
	private Integer siteId;//站点id，用作查询用
	
	public Integer getTemplateRelColumnsId() {
		return templateRelColumnsId;
	}
	public void setTemplateRelColumnsId(Integer templateRelColumnsId) {
		this.templateRelColumnsId = templateRelColumnsId;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public Integer getColumnsId() {
		return columnsId;
	}
	public void setColumnsId(Integer columnsId) {
		this.columnsId = columnsId;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Integer getRelStatus() {
		return relStatus;
	}
	public void setRelStatus(Integer relStatus) {
		this.relStatus = relStatus;
	}
	public Integer getColumnsHide() {
		return columnsHide;
	}
	public void setColumnsHide(Integer columnsHide) {
		this.columnsHide = columnsHide;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	



}
