package com.assp.modules.template.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 模板页框架
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月17日 上午11:50:26
 */
@Table(name = "wms_template_columns_rel_frame")
public class TemplateColumnsRelFrame implements TemplateRelFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer columnsRelFrameId; // '模板导航项关联框架',
	private Integer columnsRelFramePid; // '父id',
	private Integer columnsId; //
	private Integer frameId; // '框架id',
	private Integer sortNum; // '排序',
	private String cssClass; // '样式',
	private Integer relStatus; // '关联关系是否有效 0 有效 1 失效',
	
	@Transient
	private List<TemplateColumnsRelFrame> subTemplateColumnsRelFrame;  //子框架
	
	@Transient
	private List<TemplateFrameRelModule> templateFrameRelModules;
	
	private Integer siteId;//站点id，用作查询用
	
	public Integer getColumnsRelFramePid() {
		return columnsRelFramePid;
	}

	public void setColumnsRelFramePid(Integer columnsRelFramePid) {
		this.columnsRelFramePid = columnsRelFramePid;
	}

	public List<TemplateColumnsRelFrame> getSubTemplateColumnsRelFrame() {
		return subTemplateColumnsRelFrame;
	}

	public void setSubTemplateColumnsRelFrame(
			List<TemplateColumnsRelFrame> subTemplateColumnsRelFrame) {
		this.subTemplateColumnsRelFrame = subTemplateColumnsRelFrame;
	}

	public List<TemplateFrameRelModule> getTemplateFrameRelModules() {
		return templateFrameRelModules;
	}

	public void setTemplateFrameRelModules(
			List<TemplateFrameRelModule> templateFrameRelModules) {
		this.templateFrameRelModules = templateFrameRelModules;
	}

	public Integer getColumnsRelFrameId() {
		return columnsRelFrameId;
	}

	public void setColumnsRelFrameId(Integer columnsRelFrameId) {
		this.columnsRelFrameId = columnsRelFrameId;
	}

	public Integer getColumnsId() {
		return columnsId;
	}

	public void setColumnsId(Integer columnsId) {
		this.columnsId = columnsId;
	}

	public Integer getFrameId() {
		return frameId;
	}

	public void setFrameId(Integer frameId) {
		this.frameId = frameId;
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
