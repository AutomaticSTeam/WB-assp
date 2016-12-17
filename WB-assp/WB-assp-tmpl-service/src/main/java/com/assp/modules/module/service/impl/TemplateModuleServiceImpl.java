package com.assp.modules.module.service.impl;   

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.Article;
import com.assp.modules.commondata.entity.ArticleType;
import com.assp.modules.commondata.entity.Media;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbumType;
import com.assp.modules.commondata.mapper.ArticleMapper;
import com.assp.modules.commondata.mapper.ArticleTypeMapper;
import com.assp.modules.commondata.mapper.MediaMapper;
import com.assp.modules.commondata.mapper.PictureAlbumMapper;
import com.assp.modules.commondata.mapper.PictureAlbumTypeMapper;
import com.assp.modules.commondata.mapper.PictureMapper;
import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.module.entity.TemplateModuleRelContent;
import com.assp.modules.module.entity.TemplateModuleVo;
import com.assp.modules.module.mapper.TemplateModuleMapper;
import com.assp.modules.module.mapper.TemplateModuleRelContentMapper;
import com.assp.modules.module.service.ITemplateModuleService;
import com.assp.modules.template.entity.TemplateColumns;
import com.assp.modules.template.entity.TemplateColumnsRelFrame;
import com.assp.modules.template.entity.TemplateCommonRelFrame;
import com.assp.modules.template.entity.TemplateFooter;
import com.assp.modules.template.entity.TemplateFooterRelColumns;
import com.assp.modules.template.entity.TemplateFrameRelModule;
import com.assp.modules.template.entity.TemplateLogo;
import com.assp.modules.template.entity.TemplateRelColumns;
import com.assp.modules.template.mapper.TemplateColumnsMapper;
import com.assp.modules.template.mapper.TemplateColumnsRelFrameMapper;
import com.assp.modules.template.mapper.TemplateCommonRelFrameMapper;
import com.assp.modules.template.mapper.TemplateFooterMapper;
import com.assp.modules.template.mapper.TemplateFrameRelModuleMapper;
import com.assp.modules.template.mapper.TemplateLogoMapper;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 下午5:20:59 
 */
@Service
public class TemplateModuleServiceImpl  implements ITemplateModuleService{
	
	@Autowired
	private TemplateModuleMapper templateModuleMapper;
	@Autowired
	private TemplateFrameRelModuleMapper templateFrameRelModuleMapper;
	
	@Autowired
	private TemplateModuleRelContentMapper templateModuleRelContentMapper;
	
	@Autowired
	private ArticleMapper  articleMapper;
	
	@Autowired
	private ArticleTypeMapper  articleTypeMapper;
	
	@Autowired
	private PictureMapper pictureMapper  ;
	
	@Autowired
	private PictureAlbumMapper pictureAlbumMapper;
	
	@Autowired
	private PictureAlbumTypeMapper pictureAlbumTypeMapper;
	
	@Autowired
	private  MediaMapper mediaMapper;
	
	@Autowired
	private  TemplateLogoMapper templateLogoMapper;
	
	@Autowired
	private TemplateColumnsMapper templateColumnsMapper;
	
	@Autowired
	private TemplateFooterMapper templateFooterMapper;
	
	@Autowired
	private TemplateColumnsRelFrameMapper templateColumnsRelFrameMapper;
	@Autowired
	private TemplateCommonRelFrameMapper templateCommonRelFrameMapper;

	@Override
	public TemplateModule queryOne(TemplateModule record) {
		return templateModuleMapper.selectOne(record);
	}

	@Override
	public List<TemplateModule> queryAll(TemplateModule record) {
		return templateModuleMapper.select(record);
	}

	@Override
	public int queryCount(TemplateModule record) {
		return templateModuleMapper.selectCount(record);
	}

	@Override
	public TemplateModule queryByPrimaryKey(Object key) {
		return templateModuleMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(TemplateModule record) {
		return templateModuleMapper.insert(record);
	}

	@Override
	public int addSelective(TemplateModule record) {
		return templateModuleMapper.insertSelective(record);
	}

	@Override
	public int delete(TemplateModule record) {
		return templateModuleMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateModuleMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(TemplateModule record) {
		return templateModuleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TemplateModule record) {
		return templateModuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(TemplateModule example) {
		return templateModuleMapper.selectCountByExample(example);
	}

	@Override
	public List<TemplateModule> queryByExample(TemplateModule example) {
		return null;
	}

	@Override
	public List<TemplateModule> queryTemplateModulesByIds(List<Integer> templateModuleIds) {
		if(templateModuleIds == null ) return null;
		TemplateModuleVo templateModuleVo = new TemplateModuleVo();
		templateModuleVo.setModuleIds(templateModuleIds);
		List<TemplateModule>  templateModules =  templateModuleMapper.selectTemplateModulesByVo(templateModuleVo );
	    if(templateModules != null && templateModules.size() > 0){ //模板非空
			    for(TemplateModule tm : templateModules){
			    	List<TemplateModuleRelContent> templateModuleRelContents =  tm.getTemplateModuleRelContents();
					if(templateModuleRelContents != null && templateModuleRelContents.size() >0 ){
						List<Object> pictureIds = new ArrayList<Object>();
						List<Integer> articleIds = new ArrayList<Integer>();
						List<Integer> articleTypeIds = new ArrayList<Integer>();
						List<Integer> mediaTypeIds = new ArrayList<Integer>();
						Integer albumId = null;
						Integer albumTypeId = null;
						Integer articleTypeForBookId = null;
						Integer templateLogoId = null;
						Integer templateId = null; // 用户确定模板导航模块 
						Integer templateFooterId = null; //  页脚模块
						for(TemplateModuleRelContent tmrd : templateModuleRelContents){
							switch (tmrd.getContentDataType().intValue()) {
							case 1:  //文章分类
								articleTypeIds.add(tmrd.getDataColumnId());
								break;
							case 2:  //文章
								articleIds.add(tmrd.getDataColumnId());
								break;
							case 3:  //图书分类
								articleTypeForBookId = tmrd.getDataColumnId();
								break;
							case 5:  //图册分类
								albumTypeId = tmrd.getDataColumnId();
								break;
							case 6:  //图册
								albumId = tmrd.getDataColumnId();
								break;
							case 7:  //图片
								pictureIds.add(tmrd.getDataColumnId());
								break;
							case 8:  //视频分类
								mediaTypeIds.add(tmrd.getDataColumnId());
								break;
							case 10: // logo
								templateLogoId =  tmrd.getDataColumnId();
								continue;
							case 11: // 导航
								templateId =  tmrd.getDataColumnId();
								continue;
							case 12: // 页脚
								templateFooterId =  tmrd.getDataColumnId();
								continue;
							case 13: // 添加模块儿
								continue;
							case 14: // 空模块儿
								continue;	
							}
						}
						tm.setPictures(queryPictures(pictureIds,albumId,albumTypeId)); //获取图片
						 List<Article> articles = queryArticles(articleIds , articleTypeIds,articleTypeForBookId );
						 tm.setArticles(articles); //获取图片
						 tm.setMedias(queryMedias(mediaTypeIds));
						 if(templateLogoId != null){
							 tm.setTemplateLogo(queryLogo(templateLogoId)); // 加载logo 模块
						 }
						 if(templateId != null){
							 tm.setTemplateColumns(queryTemplateColumns(templateId));
						 }
						 if(templateFooterId != null){
							 tm.setTemplateFooter(queryTemplateFooter(templateFooterId));
						 }
					}
			    }
		}
		return templateModules;
	}
	
	@Override
	public TemplateModule queryTemplateModuleByTemplateModule(TemplateModule templateModule) {
		if(templateModule == null || templateModule.getModuleId() == null ) return null;
		TemplateModule tempTemplateModule = null;
		TemplateModuleVo templateModuleVo = new TemplateModuleVo();
		templateModuleVo.setModuleId(templateModule.getModuleId());
		List<TemplateModule>  templateModules =  templateModuleMapper.selectTemplateModulesByVo(templateModuleVo );
		if(templateModules != null && templateModules.size() > 0){ //模板非空
			tempTemplateModule = templateModules.get(0);
			List<TemplateModuleRelContent> templateModuleRelContents =  tempTemplateModule.getTemplateModuleRelContents();
			if(templateModuleRelContents != null && templateModuleRelContents.size() >0 ){
					List<Object> pictureIds = new ArrayList<Object>();
					List<Integer> articleIds = new ArrayList<Integer>();
					List<Integer> articleTypeIds = new ArrayList<Integer>();
					List<Integer> mediaTypeIds = new ArrayList<Integer>();
					Integer albumId = null;
					Integer albumTypeId = null;
					Integer articleTypeForBookId = null;
					Integer templateLogoId = null;
					Integer templateId = null; // 用户确定模板导航模块 
					Integer templateFooterId = null; //  页脚模块
					for(TemplateModuleRelContent tmrd : templateModuleRelContents){
						switch (tmrd.getContentDataType().intValue()) {
						case 1:  //文章分类
							articleTypeIds.add(tmrd.getDataColumnId());
							break;
						case 2:  //文章
							articleIds.add(tmrd.getDataColumnId());
							break;
						case 3:  //图书分类
							articleTypeForBookId = tmrd.getDataColumnId();
							break;
						case 5:  //图册分类
							albumTypeId = tmrd.getDataColumnId();
							break;
						case 6:  //图册
							albumId = tmrd.getDataColumnId();
							break;
						case 7:  //图片
							pictureIds.add(tmrd.getDataColumnId());
							break;
						case 8:  //视频分类
							mediaTypeIds.add(tmrd.getDataColumnId());
							break;
						case 10: // logo
							templateLogoId =  tmrd.getDataColumnId();
							continue;
						case 11: // 导航
							templateId =  tmrd.getDataColumnId();
							continue;
						case 12: // 页脚
							templateFooterId =  tmrd.getDataColumnId();
							continue;
						case 13: // 添加模块儿
							continue;
						case 14: // 空模块儿
							continue;	
						}
					}
					tempTemplateModule.setPictures(queryPictures(pictureIds,albumId,albumTypeId)); //获取图片
					List<Article> articles = queryArticles(articleIds , articleTypeIds,articleTypeForBookId );
					tempTemplateModule.setArticles(articles); //获取图片
					tempTemplateModule.setMedias(queryMedias(mediaTypeIds));
					if(templateLogoId != null){
						tempTemplateModule.setTemplateLogo(queryLogo(templateLogoId)); // 加载logo 模块
					}
					if(templateId != null){
						tempTemplateModule.setTemplateColumns(queryTemplateColumns(templateId));
					}
					if(templateFooterId != null){
						tempTemplateModule.setTemplateFooter(queryTemplateFooter(templateFooterId));
					}
				}
		}
		
		return tempTemplateModule;
	}
	
	//页脚数据
	private TemplateFooter queryTemplateFooter(Integer templateFooterId){
		if(templateFooterId == null) return null; 
		TemplateFooter tf = new TemplateFooter();
		tf.setTemplateFooterId(templateFooterId);
		TemplateFooter templateFooter = templateFooterMapper.selectOne(tf);
		if(templateFooter != null){
			//List<TemplateColumns> footerColumns = templateColumnsMapper.selectTemplateColumnsByTemplateFooterId(templateFooterId);
			// 根据条件查询
			TemplateFooterRelColumns trc = new TemplateFooterRelColumns();
			trc.setTemplateFooterId(templateFooterId);
			trc.setColumnsHide(0);
			List<TemplateColumns> footerColumns = templateColumnsMapper.selectTemplateColumnsByTemplateFooterRelColumns(trc);
			templateFooter.setTemplateColumns(footerColumns);
		}
		return templateFooter;
	}
	
	//导航数据
	private List<TemplateColumns> queryTemplateColumns(Integer templateId){
		//变为根据条件查询，
		//List<TemplateColumns>   templateColumns = templateColumnsMapper.selectTemplateColumnsByTemplateId(templateId);
		TemplateRelColumns trc = new TemplateRelColumns();
		trc.setTemplateId(templateId);
		trc.setRelStatus(0);
		trc.setColumnsHide(0);
		List<TemplateColumns>   templateColumns = templateColumnsMapper.selectTemplateColumnsByTemplateRelColumns(trc);
		List<TemplateColumns>   tColumns = new ArrayList<TemplateColumns>();
		if(templateColumns != null  && templateColumns.size() > 0){
			for(TemplateColumns tc :  templateColumns){
				tc.setSubTemplateColumns(getSubTemplateColumns(templateColumns,tc.getColumnsId()));
				if(tc.getColumnsPid() != null && tc.getColumnsPid().intValue() == 0){
					tColumns.add(tc);
				}
				
			}
		}
		return templateColumns;
	}
	//获取子导航菜单
	private List<TemplateColumns> getSubTemplateColumns(List<TemplateColumns>   templateColumns , Integer pid){
		List<TemplateColumns>   tColumns = null;
		if(templateColumns != null  && templateColumns.size() > 0){
			tColumns = new ArrayList<TemplateColumns>();
			for(TemplateColumns tc :  templateColumns){
				if(tc.getColumnsPid() != null && tc.getColumnsPid().intValue() == pid){
					tColumns.add(tc);
				}
			}
		}
		return tColumns;
	}
	
	//查询logo
	private TemplateLogo queryLogo(Integer templateLogoId){
		TemplateLogo tl = new  TemplateLogo();
		tl.setTemplateLogoId(templateLogoId);
		return templateLogoId == null ? null : templateLogoMapper.selectOne(tl);
	}
	
	//查询视频资源
	private List<Media> queryMedias(List<Integer> mediaTypeIds){
		if(mediaTypeIds == null || mediaTypeIds.size() <=0) return null;
		Media media = new Media();
		media.setMediaTypes(mediaTypeIds);
		media.setLimitNum(10);
		return mediaMapper.selectMediasByCondition(media );
	}
	
	//查询文章资源
	private List<Article> queryArticles(List<Integer> articleIds , List<Integer> articleTypeIds, Integer articleTypeForBookId){
		if(articleIds == null && articleTypeIds == null  ) return null;
		List<Article>   articles = new ArrayList<Article>();
		if(articleIds != null && articleIds.size() > 0 ){ //通过文章id集合获取文章
			Article  article = new Article();
			article.setArticleIds(articleIds);
			article.setDataStatus(0);
			article.setArticleReview(1);//审核状态 1已审核
			article.setLimitNum(10);
			articles = articleMapper.selectArticlesByCondition(article);
		}
		
       if(articleTypeIds != null && articleTypeIds.size() > 0 ){ //通过文章类型id获取文章
    		Article  article = new Article();
			article.setArticleTypes(articleTypeIds);
			article.setDataStatus(0);
			article.setArticleReview(1);//审核状态 1已审核
			article.setLimitNum(10);
			articles = articleMapper.selectArticlesByCondition(article);
		}
       
       if(articleTypeForBookId != null  ){ //通过文章类型id获取图书
	   		Article  article = new Article();
	   		//根据分类id查询查询子分类
	   		ArticleType at = new ArticleType();
	   		at.setArticleTypePid(articleTypeForBookId);
	   		List<ArticleType> articleTypelist = articleTypeMapper.select(at);
			//查询每个文章 分类的第一条装入结果集
	   		if(articleTypelist!=null&&articleTypelist.size()>0){
	   			for (ArticleType articleType : articleTypelist) {
	   				article.setArticleTypeId(articleType.getArticleTypeId());
	   				article.setDataStatus(0);
	   				article.setArticleReview(1);//审核状态 1已审核
	   				article.setLimitNum(10);
	   				List<Article> articleList = articleMapper.selectArticlesByTypeId(articleType.getArticleTypeId());
	   				if(articleList!=null&&articleList.size()>0){
	   					articles.add(articleList.get(0));
	   				}
				}
	   		}
	   		
       }
		return articles;
	}
	
	//查询图片资源
	private List<Picture> queryPictures(List<Object> pictureIds,Integer albumId,Integer albumTypeId){
		List<Picture> pictures = new ArrayList<Picture>();
		if(albumId != null){
			pictures = pictureAlbumMapper.queryReviewPicByAlbumId(albumId); //查找图册中的已审核的图片
		}else if(albumTypeId != null){
			//查询一级子节点第一个类型下的图册
			PictureAlbumType albumType = new PictureAlbumType();
			albumType.setAlbumTypeId(albumTypeId);
			pictures= pictureAlbumTypeMapper.queryOnePicture(albumType);
			
		}else if(pictureIds !=null && pictureIds.size() >0 ){
			Example picturesExample = new Example(Picture.class);
			Criteria criteria =  picturesExample.createCriteria();
			criteria.andIn("pictureId", pictureIds);
			criteria.andEqualTo("dataStatus", Integer.valueOf(0)); //数据状态 0 正常
			criteria.andEqualTo("isReview", Integer.valueOf(1)); //审核状态 1 已审核
			 pictures = pictureMapper.selectByExample(picturesExample);
		}
		return pictures;
	}

	@Override
	public void addModuleForTemplateColumns(
			TemplateRelColumns templateRelColumns) {
		// TODO Auto-generated method stub
		// 查找预设模块组
		TemplateCommonRelFrame tcrf = new TemplateCommonRelFrame();
		tcrf.setTemplateId(templateRelColumns.getTemplateId());
		//tcrf.setCommonType(1);
		//tcrf.setCommonTypeItem(0);
		tcrf.setRelStatus(0);
		// 此处只做了一层查找，目前已够用，如果有层级关系 请回归调用扩展
		List<TemplateCommonRelFrame> tcrfList = templateCommonRelFrameMapper.select(tcrf);
		TemplateColumnsRelFrame selfTcrf = null;
		TemplateColumnsRelFrame tempSelfTcrf = null;
		Integer sortNum = null;
		TemplateFrameRelModule templateFrameRelModule;
		List<TemplateFrameRelModule> tfrmsList = new ArrayList<TemplateFrameRelModule>();
		List<TemplateFrameRelModule> tempList;
		if(tcrfList != null && tcrfList.size() > 0){
			for(TemplateCommonRelFrame tcrf1 : tcrfList){
				
				if(tcrf1.getCommonType() == null || tcrf1.getCommonType() > 1){
					continue;
				}
					
				tempSelfTcrf = new TemplateColumnsRelFrame();
				tempSelfTcrf.setColumnsId(templateRelColumns.getColumnsId());
				tempSelfTcrf.setColumnsRelFramePid(tcrf1.getCommonRelFramePid());
				tempSelfTcrf.setCssClass(tcrf1.getCssClass());
				tempSelfTcrf.setFrameId(tcrf1.getFrameId());
				tempSelfTcrf.setSortNum(tcrf1.getSortNum());
				tempSelfTcrf.setRelStatus(0);
				templateColumnsRelFrameMapper.insertGetprimaryKey(tempSelfTcrf);
				// 对预置模块儿 做填充准备
				if(tcrf1.getCommonType().intValue() == Integer.valueOf(1)){
					selfTcrf = tempSelfTcrf;
				}
				
				// 查询 templateFrameRelModule 框架
				templateFrameRelModule = new TemplateFrameRelModule();
				templateFrameRelModule.setCommonRelFrameId(tcrf1.getCommonRelFrameId());
				tempList = templateFrameRelModuleMapper.select(templateFrameRelModule);
				if(tempList != null && tempList.size()>0){
					for(TemplateFrameRelModule tfrm : tempList){
						templateFrameRelModule = new TemplateFrameRelModule();
						templateFrameRelModule.setColumnsRelFrameId(tempSelfTcrf.getColumnsRelFrameId());
						templateFrameRelModule.setModuleId(tfrm.getModuleId());
						templateFrameRelModule.setSortNum(tfrm.getSortNum());
						templateFrameRelModule.setCssClass(tfrm.getCssClass());
						tfrmsList.add(templateFrameRelModule);
						templateFrameRelModuleMapper.insert(templateFrameRelModule);
						sortNum = tfrm.getSortNum();
					}
				}
			}
		}
		// 更新module
		/*if(tfrmsList.size() > 0){
			templateFrameRelModuleMapper.insertList(tfrmsList);
		}*/
		// 加入一个默认的空模块儿，放到最后一个框架里面
		if(selfTcrf != null){
			
			TemplateModule templateModule = new TemplateModule();
			templateModule.setModulePid(0);
			templateModule.setDataStatus(0);
			templateModule.setModuleTmpl("module_add_tmpl");
			templateModuleMapper.addModuleReturnId(templateModule);
			// 保存内容
			TemplateModuleRelContent templateModuleRelContent = new TemplateModuleRelContent();
			// 7为空，待录入
			templateModuleRelContent.setContentDataType(13);
			templateModuleRelContent.setModuleId(templateModule.getModuleId());
			templateModuleRelContent.setRelStatus(0);
			templateModuleRelContentMapper.insert(templateModuleRelContent);
			
			templateFrameRelModule = new TemplateFrameRelModule();
			templateFrameRelModule.setColumnsRelFrameId(selfTcrf.getColumnsRelFrameId());
			templateFrameRelModule.setModuleId(templateModule.getModuleId());
			templateFrameRelModule.setSortNum(sortNum);
			templateFrameRelModuleMapper.insert(templateFrameRelModule);
		}
	}

	@Override
	public int addModuleReturnId(TemplateModule module) {
		return templateModuleMapper.addModuleReturnId( module);
	}

	@Override
	public void addModuleForParentModule(TemplateModule tempTemplateModule) {
		
		// TODO Auto-generated method stub
		
		switch(tempTemplateModule.getModuleType()){
		case 1:
			tempTemplateModule.setModuleStyleType(1);
		case 2:
			TemplateModule templateModule = new TemplateModule();
			templateModule.setSiteId(tempTemplateModule.getSiteId());
			templateModule.setModulePid(tempTemplateModule.getModuleId());
			templateModule.setDataStatus(0);
			templateModule.setModuleType(0);
			templateModule.setModuleName("模块名称");
			templateModule.setShowTitile(1);
			templateModule.setPageSize(20);
			templateModule.setModuleItemLineNum(3);
			templateModule.setModuleItemColumnNum(3);
			// 模块添加模板
			templateModule.setModuleTmpl("module_add_tmpl");
			templateModuleMapper.addModuleReturnId(templateModule);
			// 保存内容
			TemplateModuleRelContent templateModuleRelContent = new TemplateModuleRelContent();
			// 13为添加模块模板，待录入
			templateModuleRelContent.setContentDataType(13);
			templateModuleRelContent.setSiteId(tempTemplateModule.getSiteId());
			templateModuleRelContent.setModuleId(templateModule.getModuleId());
			templateModuleRelContent.setRelStatus(0);
			templateModuleRelContentMapper.insert(templateModuleRelContent);
		}
		templateModuleMapper.updateByPrimaryKeySelective(tempTemplateModule);
		
	}

}
  