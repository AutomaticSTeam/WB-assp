package com.assp.modules.template.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 类简述
 * <p>
 * 模板页脚
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午1:31:26
 */
@Table(name = "wms_template_footer")
public class TemplateFooter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer templateFooterId; // '页脚id',
	private String copyrightInfo; // '版权信息',
	private Integer styleFunctionId; // '持有的样式id',
	private int isShowTechnicalSupport; // '是否支持技术支持 0 支持 1 不支持',
	private int isShowMobileEdit; // '是否支持手机版 0 支持 1 不支持',
	private int isShowAdminLogin; // '是否支持管理登录 0 支持 1 不支持',
	
	 @Transient
	private List<TemplateColumns>  templateColumns;
	
	private Integer siteId;//站点id，用作查询用
	 
	public List<TemplateColumns> getTemplateColumns() {
		return templateColumns;
	}

	public void setTemplateColumns(List<TemplateColumns> templateColumns) {
		this.templateColumns = templateColumns;
	}

	public Integer getTemplateFooterId() {
		return templateFooterId;
	}

	public void setTemplateFooterId(Integer templateFooterId) {
		this.templateFooterId = templateFooterId;
	}

	public String getCopyrightInfo() {
		return copyrightInfo;
	}

	public void setCopyrightInfo(String copyrightInfo) {
		this.copyrightInfo = copyrightInfo;
	}

	public Integer getStyleFunctionId() {
		return styleFunctionId;
	}

	public void setStyleFunctionId(Integer styleFunctionId) {
		this.styleFunctionId = styleFunctionId;
	}

	public int getIsShowTechnicalSupport() {
		return isShowTechnicalSupport;
	}

	public void setIsShowTechnicalSupport(int isShowTechnicalSupport) {
		this.isShowTechnicalSupport = isShowTechnicalSupport;
	}

	public int getIsShowMobileEdit() {
		return isShowMobileEdit;
	}

	public void setIsShowMobileEdit(int isShowMobileEdit) {
		this.isShowMobileEdit = isShowMobileEdit;
	}

	public int getIsShowAdminLogin() {
		return isShowAdminLogin;
	}

	public void setIsShowAdminLogin(int isShowAdminLogin) {
		this.isShowAdminLogin = isShowAdminLogin;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}
