package com.assp.modules.template.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 模板公用页框架
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wzp@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月18日 
 */
@Table(name = "wms_template_common_rel_frame")
public class TemplateCommonRelFrame implements TemplateRelFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer commonRelFrameId; // '模板导航项关联框架',
	private Integer commonRelFramePid; // '父id',
	private Integer templateId; //
	private Integer frameId; // '框架id',
	private Integer sortNum; // '排序',
	private Integer commonType; // '每预置类型',
	private Integer commonTypeItem; // '每预置类型中的不同种类',
	private String commonIframeName; // '框架名称',
	private String cssClass; // '样式',
	private Integer relStatus; // '关联关系是否有效 0 有效 1 失效',
	
	@Transient
	private List<TemplateCommonRelFrame> subTemplateCommonRelFrame;  //子框架
	
	@Transient
	private List<TemplateFrameRelModule> templateFrameRelModules;

	private Integer siteId;//站点id，用作查询用
	
	public Integer getCommonRelFrameId() {
		return commonRelFrameId;
	}

	public void setCommonRelFrameId(Integer commonRelFrameId) {
		this.commonRelFrameId = commonRelFrameId;
	}

	public Integer getCommonRelFramePid() {
		return commonRelFramePid;
	}

	public void setCommonRelFramePid(Integer commonRelFramePid) {
		this.commonRelFramePid = commonRelFramePid;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
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

	public List<TemplateCommonRelFrame> getSubTemplateCommonRelFrame() {
		return subTemplateCommonRelFrame;
	}

	public void setSubTemplateCommonRelFrame(
			List<TemplateCommonRelFrame> subTemplateCommonRelFrame) {
		this.subTemplateCommonRelFrame = subTemplateCommonRelFrame;
	}

	public List<TemplateFrameRelModule> getTemplateFrameRelModules() {
		return templateFrameRelModules;
	}

	public void setTemplateFrameRelModules(
			List<TemplateFrameRelModule> templateFrameRelModules) {
		this.templateFrameRelModules = templateFrameRelModules;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public Integer getCommonType() {
		return commonType;
	}

	public void setCommonType(Integer commonType) {
		this.commonType = commonType;
	}

	public Integer getCommonTypeItem() {
		return commonTypeItem;
	}

	public void setCommonTypeItem(Integer commonTypeItem) {
		this.commonTypeItem = commonTypeItem;
	}

	public String getCommonIframeName() {
		return commonIframeName;
	}

	public void setCommonIframeName(String commonIframeName) {
		this.commonIframeName = commonIframeName;
	}


	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

}
