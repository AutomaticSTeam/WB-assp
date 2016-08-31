package com.assp.modules.template.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类简述
 * <p>
 * 模板横幅
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午3:02:52
 */
@Table(name = "wms_template_banner")
public class TemplateBanner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3225484086032206162L;
	@Id
	private Integer templateBannerId; // '模板横幅id',
	private Integer templateId; // '模板id',
	private String templateBannerName; // '横幅的名称',
	private Integer bannerId; // '资源横幅id',
	private Integer industryTypeId; // '行业类型id',
	private String templateBannerImgUrl; // '模板横幅图片地址',
	private String bannerImgDesc; //图片描述
	private String bannerFormatWidth; //宽度
	private String bannerFormatHeight; //高度
	private Date createTime; // '创建时间',
	private Date updateTime; // '更新时间',
	private Integer operatorId; // '操作人id',
	private String operatorName; // '操作人姓名',
	private int dataStatus; // '数据状态 0 可用 1 不可用',

	public Integer getTemplateBannerId() {
		return templateBannerId;
	}

	public void setTemplateBannerId(Integer templateBannerId) {
		this.templateBannerId = templateBannerId;
	}

	public String getTemplateBannerName() {
		return templateBannerName;
	}

	public void setTemplateBannerName(String templateBannerName) {
		this.templateBannerName = templateBannerName;
	}

	public Integer getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getTemplateBannerImgUrl() {
		return templateBannerImgUrl;
	}

	public void setTemplateBannerImgUrl(String templateBannerImgUrl) {
		this.templateBannerImgUrl = templateBannerImgUrl;
	}
	

	public String getBannerImgDesc() {
		return bannerImgDesc;
	}

	public void setBannerImgDesc(String bannerImgDesc) {
		this.bannerImgDesc = bannerImgDesc;
	}

	public String getBannerFormatWidth() {
		return bannerFormatWidth;
	}

	public void setBannerFormatWidth(String bannerFormatWidth) {
		this.bannerFormatWidth = bannerFormatWidth;
	}

	public String getBannerFormatHeight() {
		return bannerFormatHeight;
	}

	public void setBannerFormatHeight(String bannerFormatHeight) {
		this.bannerFormatHeight = bannerFormatHeight;
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

	public int getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}
	
	

}
