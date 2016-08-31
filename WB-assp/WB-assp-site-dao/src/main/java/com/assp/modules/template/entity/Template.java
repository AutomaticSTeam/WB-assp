package com.assp.modules.template.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 上午11:50:41
 */
@Table(name="wms_template")
public class Template implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer templateId; // '模板id',
	private String templateCode; // '模板编码',
	private String templateName; // '模板名称',
	private Integer industryTypeId; // '模板类型id',
	private Integer colorId;		// '颜色类型id',
	private Integer platformId; // '模板应用平台',
	private Integer templateBannerId; // '轮播图id',
	private Integer templateLogoId; // '模板logoid',
	private Integer templateFooterId; // '模板页脚id',
	private String templateThumbnailUrl; // '模板缩略图地址',
	private String templateViewUrl; // '模板展示图地址',
	private Integer isEffect; // '是否有效 0 有效（默认） 1 失效',
	private Integer isRecommended; // '是否是推荐模板 0 否 1 是',
	private Integer templateSize; // '模板大小单位(K)',
	private String templateStandardWidth; // '模板宽度',
	private String templateStandardHeight; // '模板高度',
	private Integer operatorId; // '操作人id',
	private String operatorName; // '操作人姓名',
	private Date createTime; // '创建时间',
	private Date updateTime; // '更新时间',
	private int dataStatus; // '数据状态 0 正常 1 删除2未发布',
	private Integer isContainsVedio; // '是否有视频',
	@Transient
	private Integer isCollect;//是否被收藏
	
	@Transient
	private Integer userId;//用户id，用作查询用
	
	@Transient
	private Integer siteId;//站点id，用作查询用
	
	@Transient
	private  List<Integer> templateIds;

	public List<Integer> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(List<Integer> templateIds) {
		this.templateIds = templateIds;
	}

	public Integer getTemplateBannerId() {
		return templateBannerId;
	}

	public void setTemplateBannerId(Integer templateBannerId) {
		this.templateBannerId = templateBannerId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public Integer getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
	

	public Integer getTemplateLogoId() {
		return templateLogoId;
	}

	public void setTemplateLogoId(Integer templateLogoId) {
		this.templateLogoId = templateLogoId;
	}

	public Integer getTemplateFooterId() {
		return templateFooterId;
	}

	public void setTemplateFooterId(Integer templateFooterId) {
		this.templateFooterId = templateFooterId;
	}

	public String getTemplateThumbnailUrl() {
		return templateThumbnailUrl;
	}

	public void setTemplateThumbnailUrl(String templateThumbnailUrl) {
		this.templateThumbnailUrl = templateThumbnailUrl;
	}

	public String getTemplateViewUrl() {
		return templateViewUrl;
	}

	public void setTemplateViewUrl(String templateViewUrl) {
		this.templateViewUrl = templateViewUrl;
	}

	public Integer getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect;
	}

	public Integer getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Integer isRecommended) {
		this.isRecommended = isRecommended;
	}

	public Integer getTemplateSize() {
		return templateSize;
	}

	public void setTemplateSize(Integer templateSize) {
		this.templateSize = templateSize;
	}

	public String getTemplateStandardWidth() {
		return templateStandardWidth;
	}

	public void setTemplateStandardWidth(String templateStandardWidth) {
		this.templateStandardWidth = templateStandardWidth;
	}

	public String getTemplateStandardHeight() {
		return templateStandardHeight;
	}

	public void setTemplateStandardHeight(String templateStandardHeight) {
		this.templateStandardHeight = templateStandardHeight;
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

	public int getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getIsContainsVedio() {
		return isContainsVedio;
	}

	public void setIsContainsVedio(Integer isContainsVedio) {
		this.isContainsVedio = isContainsVedio;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}


}
