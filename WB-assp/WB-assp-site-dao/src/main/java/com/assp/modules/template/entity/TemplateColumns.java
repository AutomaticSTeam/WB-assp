package com.assp.modules.template.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 模板栏目
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午2:52:41
 */
@Table(name = "wms_template_columns")
public class TemplateColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer columnsId; // '导航id',
	private String columnsName; // ' 导航名称',
	private Integer columnsTypeId; // '导航类型id',
	private Integer columnsPid; // '导航的父id',
	private Integer openType; // '打开方式 0 当前页 1 新页',
	private String customUrl; // '自定义链接',
	private int dataStatus; // '数据状态 0 使用 1未使用',
	private String columnsIcon;
	private String columnsIconActive;
	@Transient
	private Integer columnsSort;//排序
	@Transient
	private Integer columnsHide;//是否隐藏
	
	@Transient
	private Integer ismodified;//是否修改
	
	@Transient
	private List<TemplateColumns> subTemplateColumns;
	
	private Integer siteId;//站点id，用作查询用
	
	public List<TemplateColumns> getSubTemplateColumns() {
		return subTemplateColumns;
	}

	public void setSubTemplateColumns(List<TemplateColumns> subTemplateColumns) {
		this.subTemplateColumns = subTemplateColumns;
	}

	public String getColumnsIcon() {
		return columnsIcon;
	}

	public void setColumnsIcon(String columnsIcon) {
		this.columnsIcon = columnsIcon;
	}

	public String getColumnsIconActive() {
		return columnsIconActive;
	}

	public void setColumnsIconActive(String columnsIconActive) {
		this.columnsIconActive = columnsIconActive;
	}

	@Transient
	private String columnsTypeName;  //栏目类型名称
	
	@Transient
	private Integer templateFooterRelColumnsId; //关联页脚表id
	
	@Transient
	private Integer templateRelColumnsId; //关联模板表id
	
	@Transient
	private int sortNum; //排序
	
	
	

	public Integer getTemplateFooterRelColumnsId() {
		return templateFooterRelColumnsId;
	}

	public void setTemplateFooterRelColumnsId(Integer templateFooterRelColumnsId) {
		this.templateFooterRelColumnsId = templateFooterRelColumnsId;
	}

	public Integer getTemplateRelColumnsId() {
		return templateRelColumnsId;
	}

	public void setTemplateRelColumnsId(Integer templateRelColumnsId) {
		this.templateRelColumnsId = templateRelColumnsId;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getColumnsTypeName() {
		return columnsTypeName;
	}

	public void setColumnsTypeName(String columnsTypeName) {
		this.columnsTypeName = columnsTypeName;
	}

	public Integer getColumnsId() {
		return columnsId;
	}

	public void setColumnsId(Integer columnsId) {
		this.columnsId = columnsId;
	}

	public String getColumnsName() {
		return columnsName;
	}

	public void setColumnsName(String columnsName) {
		this.columnsName = columnsName;
	}

	public Integer getColumnsTypeId() {
		return columnsTypeId;
	}

	public void setColumnsTypeId(Integer columnsTypeId) {
		this.columnsTypeId = columnsTypeId;
	}

	public Integer getColumnsPid() {
		return columnsPid;
	}

	public void setColumnsPid(Integer columnsPid) {
		this.columnsPid = columnsPid;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public String getCustomUrl() {
		return customUrl;
	}

	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}

	public int getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getColumnsSort() {
		return columnsSort;
	}

	public void setColumnsSort(Integer columnsSort) {
		this.columnsSort = columnsSort;
	}

	public Integer getColumnsHide() {
		return columnsHide;
	}

	public void setColumnsHide(Integer columnsHide) {
		this.columnsHide = columnsHide;
	}

	public Integer getIsmodified() {
		return ismodified;
	}

	public void setIsmodified(Integer ismodified) {
		this.ismodified = ismodified;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}


}
