package com.assp.modules.template.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 板框关联模块表
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月18日 下午4:34:03
 */
@Table(name = "wms_template_frame_rel_module")
public class TemplateFrameRelModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer frameRelModuleId; // '框架关联模块id',
	private Integer columnsRelFrameId; // '排版id',
	private Integer commonRelFrameId; // '公用排版id',
	private Integer moduleId; // '框架id',
	private String cssClass; // '样式',
	private Integer sortNum; // '排序',

	private Integer siteId;//站点id，用作查询用
	
	public Integer getFrameRelModuleId() {
		return frameRelModuleId;
	}

	public void setFrameRelModuleId(Integer frameRelModuleId) {
		this.frameRelModuleId = frameRelModuleId;
	}

	public Integer getColumnsRelFrameId() {
		return columnsRelFrameId;
	}

	public void setColumnsRelFrameId(Integer columnsRelFrameId) {
		this.columnsRelFrameId = columnsRelFrameId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getCommonRelFrameId() {
		return commonRelFrameId;
	}

	public void setCommonRelFrameId(Integer commonRelFrameId) {
		this.commonRelFrameId = commonRelFrameId;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

}
