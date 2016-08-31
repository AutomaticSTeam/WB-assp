package com.assp.modules.module.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 模板模块关联数据
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 下午5:00:00
 */
@Table(name = "wms_template_module_rel_content")
public class TemplateModuleRelContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer moduleRelContentId; // '模块关联内容id',
	private Integer moduleId; // '模块id',
	private Integer contentDataType; // '请查看类型列表',
	private String dataTargetTable; // '目标数据表名称',
	private Integer dataColumnId;
	private Integer sortNum; // '排序 0 同序',
	private Integer relStatus; // '关联状态 0 正常 1 取消',

	private Integer siteId;//站点id，用作查询用
	
	public Integer getModuleRelContentId() {
		return moduleRelContentId;
	}

	public void setModuleRelContentId(Integer moduleRelContentId) {
		this.moduleRelContentId = moduleRelContentId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}


	public Integer getContentDataType() {
		return contentDataType;
	}

	public void setContentDataType(Integer contentDataType) {
		this.contentDataType = contentDataType;
	}

	public String getDataTargetTable() {
		return dataTargetTable;
	}

	public void setDataTargetTable(String dataTargetTable) {
		this.dataTargetTable = dataTargetTable;
	}

	public Integer getDataColumnId() {
		return dataColumnId;
	}

	public void setDataColumnId(Integer dataColumnId) {
		this.dataColumnId = dataColumnId;
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

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

}
