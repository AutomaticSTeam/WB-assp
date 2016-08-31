package com.assp.modules.order.entity;

import java.util.Date;

public class OrderVo extends Order {
	private String dkInfo; // 带宽信息

	private String dkOperators; // 带宽运营商

	private String dkType;// 带宽类型 1：共享 2：独享

	private String spaceSize; // 空间大小

	private String templateId; // 模板id

	private String templateName; // 模板名称

	private String templateThumbnailUrl; // 模板图片

	private Integer isUse; // 是否启用 0：否 1：是

	private Date checkTime; // 审核时间

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getDkInfo() {
		return dkInfo;
	}

	public void setDkInfo(String dkInfo) {
		this.dkInfo = dkInfo;
	}

	public String getDkOperators() {
		return dkOperators;
	}

	public void setDkOperators(String dkOperators) {
		this.dkOperators = dkOperators;
	}

	public String getDkType() {
		if ("1".equals(dkType)) {
			return "共享";
		} else {
			return "独享";
		}
		// return dkType;
	}

	public void setDkType(String dkType) {
		this.dkType = dkType;
	}

	public String getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(String spaceSize) {
		this.spaceSize = spaceSize;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateThumbnailUrl() {
		return templateThumbnailUrl;
	}

	public void setTemplateThumbnailUrl(String templateThumbnailUrl) {
		this.templateThumbnailUrl = templateThumbnailUrl;
	}

}
