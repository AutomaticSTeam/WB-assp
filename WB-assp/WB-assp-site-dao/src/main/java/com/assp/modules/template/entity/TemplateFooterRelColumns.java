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
 * @CreateDate 2016年7月11日
 */
@Table(name="wms_template_footer_rel_columns")
public class TemplateFooterRelColumns implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer templateFooterRelColumnsId; // '模板脚关联主键id',
	private Integer templateFooterId; // '模板站脚id',
	private Integer columnsId; // '模板对应的导航id',
	private Integer sortNum; // '排序',
	private Integer columnsHide; // 是否隐藏,
	private Integer relStatus; // '关联关系是否有效',
	
	private Integer siteId;//站点id，用作查询用
	
	public Integer getTemplateFooterRelColumnsId() {
		return templateFooterRelColumnsId;
	}
	public void setTemplateFooterRelColumnsId(Integer templateFooterRelColumnsId) {
		this.templateFooterRelColumnsId = templateFooterRelColumnsId;
	}
	public Integer getTemplateFooterId() {
		return templateFooterId;
	}
	public void setTemplateFooterId(Integer templateFooterId) {
		this.templateFooterId = templateFooterId;
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
	public Integer getColumnsHide() {
		return columnsHide;
	}
	public void setColumnsHide(Integer columnsHide) {
		this.columnsHide = columnsHide;
	}
	public Integer getRelStatus() {
		return relStatus;
	}
	public void setRelStatus(Integer relStatus) {
		this.relStatus = relStatus;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

}
