package com.assp.modules.module.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.assp.modules.commondata.entity.Article;
import com.assp.modules.commondata.entity.ArticleType;
import com.assp.modules.commondata.entity.Media;
import com.assp.modules.commondata.entity.MediaType;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.template.entity.TemplateColumns;
import com.assp.modules.template.entity.TemplateFooter;
import com.assp.modules.template.entity.TemplateLogo;

/**
 * 类简述
 * <p>
 * 模板模块
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 下午4:45:20
 */
public class TemplateModuleVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer moduleId; // '模块id',
	private Integer modulePid; // '模块父类型id',
	private String moduleType; // '模块类型',
	private String moduleName; // '模块名称',
	private String moduleTmpl; // '样式功能id',
	private Integer moduleItemLineNum;//模块单行数据条数限制
	private Integer moduleItemColumnNum;//模块数据列数限制
	private Integer showTitile;//是否显示标题
	private String extendUrl ; // 更多url 
	private Date createTime; // '创建时间',
	private Date updateTime; // '更新时间',
	private Integer operatorId; // '操作人id',
	private String operatorName; // '操作人姓名',
	private Integer dataStatus; // '数据状态 0 正常 1 删除',
	private Integer siteId; // '站点id',
	
	private List<Article> articles;  //模块包含图片资源
	private List<ArticleType> articleTypes;//模块包含文章类型资源
	private List<Picture> pictures; //模块包含图片资源
	private List<Media> medias; //模块包含视频资源
	private List<MediaType> mediaTypes;//模块包含视频类型资源
	List<Object> articleTypeIds; //文章类型ids
	List<String> articleTypeNames; //文章类型名称
	List<Object> mediaTypeIds;//视频类型ids
	List<Object> mediaTypeName;//视频类型名称
   private TemplateLogo templateLogo;
	
	private List<TemplateColumns> templateColumns;
	
	private TemplateFooter templateFooter;
	
	
	private List<Integer> moduleIds ; //多个模块id
	
	
	
	public List<Integer> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(List<Integer> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public TemplateFooter getTemplateFooter() {
		return templateFooter;
	}

	public void setTemplateFooter(TemplateFooter templateFooter) {
		this.templateFooter = templateFooter;
	}

	public TemplateLogo getTemplateLogo() {
		return templateLogo;
	}

	public void setTemplateLogo(TemplateLogo templateLogo) {
		this.templateLogo = templateLogo;
	}

	public List<TemplateColumns> getTemplateColumns() {
		return templateColumns;
	}

	public void setTemplateColumns(List<TemplateColumns> templateColumns) {
		this.templateColumns = templateColumns;
	}

	public String getExtendUrl() {
		return extendUrl;
	}

	public void setExtendUrl(String extendUrl) {
		this.extendUrl = extendUrl;
	}

	public String getModuleTmpl() {
		return moduleTmpl;
	}

	public void setModuleTmpl(String moduleTmpl) {
		this.moduleTmpl = moduleTmpl;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}
	public List<MediaType> getMediaTypes() {
		return mediaTypes;
	}

	public void setMediaTypes(List<MediaType> mediaTypes) {
		this.mediaTypes = mediaTypes;
	}
	public Integer getModulePid() {
		return modulePid;
	}

	public void setModulePid(Integer modulePid) {
		this.modulePid = modulePid;
	}

	public List<String> getArticleTypeNames() {
		return articleTypeNames;
	}

	public void setArticleTypeNames(List<String> articleTypeNames) {
		this.articleTypeNames = articleTypeNames;
	}

	public List<Object> getArticleTypeIds() {
		return articleTypeIds;
	}

	public void setArticleTypeIds(List<Object> articleTypeIds) {
		this.articleTypeIds = articleTypeIds;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public List<ArticleType> getArticleTypes() {
		return articleTypes;
	}

	public void setArticleTypes(List<ArticleType> articleTypes) {
		this.articleTypes = articleTypes;
	}
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}


	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public List<Object> getMediaTypeIds() {
		return mediaTypeIds;
	}

	public void setMediaTypeIds(List<Object> mediaTypeIds) {
		this.mediaTypeIds = mediaTypeIds;
	}

	public List<Object> getMediaTypeName() {
		return mediaTypeName;
	}

	public void setMediaTypeName(List<Object> mediaTypeName) {
		this.mediaTypeName = mediaTypeName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getShowTitile() {
		return showTitile;
	}

	public void setShowTitile(Integer showTitile) {
		this.showTitile = showTitile;
	}

	public Integer getModuleItemLineNum() {
		return moduleItemLineNum;
	}

	public void setModuleItemLineNum(Integer moduleItemLineNum) {
		this.moduleItemLineNum = moduleItemLineNum;
	}

	public Integer getModuleItemColumnNum() {
		return moduleItemColumnNum;
	}

	public void setModuleItemColumnNum(Integer moduleItemColumnNum) {
		this.moduleItemColumnNum = moduleItemColumnNum;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

}
