package com.assp.modules.template.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.web.BaseController;
import com.assp.modules.common.SessionUtils;
import com.assp.modules.commondata.entity.Article;
import com.assp.modules.commondata.entity.ArticleRelType;
import com.assp.modules.commondata.entity.ArticleType;
import com.assp.modules.commondata.entity.Media;
import com.assp.modules.commondata.entity.MediaType;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.assp.modules.commondata.entity.PictureAlbumType;
import com.assp.modules.commondata.service.IArticleRelTypeService;
import com.assp.modules.commondata.service.IArticleService;
import com.assp.modules.commondata.service.IArticleTypeService;
import com.assp.modules.commondata.service.IMediaService;
import com.assp.modules.commondata.service.IMediaTypeService;
import com.assp.modules.commondata.service.IPictureAlbumService;
import com.assp.modules.commondata.service.IPictureAlbumTypeService;
import com.assp.modules.commondata.service.IPictureService;
import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.module.service.ITemplateModuleService;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.entity.TemplateColumns;
import com.assp.modules.template.entity.TemplateCommonRelFrame;
import com.assp.modules.template.entity.TemplateRelFrame;
import com.assp.modules.template.entity.TemplateVo;
import com.assp.modules.template.service.ITemplateColumnsService;
import com.assp.modules.template.service.ITemplateFooterService;
import com.assp.modules.template.service.ITemplateService;
import com.assp.modules.template.vo.ModuleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 类简述
 * <p>
 *      模板控制器
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 下午5:23:30 
 */
@Controller
@RequestMapping(value = "/template")
public class TempalteController extends BaseController {
	
	private static final Logger logger = Logger. getLogger(TempalteController.class);
	
	@Autowired
	private ITemplateService  iTemplateService;
	
	@Autowired
	private ITemplateFooterService  iTemplateFooterService;
	
	@Autowired
	private ITemplateColumnsService  iTemplateColumnsService;
	
	@Autowired
	private ITemplateModuleService iTemplateModuleService;
	
	@Autowired
	private IArticleService  iArticleService;
	
	@Autowired
	private IArticleRelTypeService  iArticleRelTypeService;
	
	@Autowired
	private IArticleTypeService iArticleTypeService;
	
	@Autowired
	private IMediaService iMediaService;
	

	@Autowired
	private IPictureAlbumService iPictureAlbumService;
	
	@Autowired
	private IPictureService iPictureService;
	
	@Autowired
	private IPictureAlbumTypeService iPictureAlbumTypeService;

	@Autowired
	private IMediaTypeService  IMediaTypeService;
	

	/**
	 * 
	* @Title: loadTempLatePage 
	* @Description: 加载模板页 固定模块信息
	*@author (shx@sxw100.com)
	* @param @param tempalte
	* @param @param pageType
	* @param @param user
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadTempLatePage")
	public String  loadTempLatePage(
			Template tempalte,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model) {
		logger.debug("--进入loadTempLatePage 方法");
		
		tempalte = syncSessionTemplate(tempalte ,  session); // 同步session 信息
		/*TemplateVo templateVo = iTemplateService.qeruyTemplatePage(tempalte);
		model.addAttribute("templateVo", templateVo);*/
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("tempalte", tempalte);
		return "/template/index";
	}
	
	// 同步session 信息
	private  Template  syncSessionTemplate(Template tempalte,HttpSession session){
		Template tmp = null;
		if(null != tempalte.getTemplateId()){
			if( null == SessionUtils.getSiteTemplate(session)  
					|| (null != SessionUtils.getSiteTemplate(session) && SessionUtils.getSiteTemplate(session).getTemplateId().intValue() !=  tempalte.getTemplateId().intValue())){
					 tmp = iTemplateService.queryOne(tempalte);
					//查询当前模板放入缓存中
					session.setAttribute("siteTempalte", tmp);
					session.setMaxInactiveInterval(60*60*24);;//以秒为单位
			}else{
				tmp = SessionUtils.getSiteTemplate(session)  ;
			}
		}else{
			tmp = SessionUtils.getSiteTemplate(session);
		}
		if(tmp == null ) tmp = tempalte; //防止异常
		return tmp;
	}
	
	
	
	/**
	 * 
	* @Title: queryPageFrame 
	* @Description: 
	*                   加载页面框架信息
	*@author (shx@sxw100.com)
	* @param @param columnsId
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/queryPageFrame")
	public void  queryPageFrame(
			@RequestParam(value = "showType", required = false) Integer showType,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session) {
		logger.debug("--进入queryPageFrame 方法");
		Map<String , Object>  returnMap = new HashMap<String , Object>();
		Template tmp = SessionUtils.getSiteTemplate(session);
		List<TemplateRelFrame>  TemplateColumnsRelFrames = null;
		// 如果showtype不为null,显示为特殊
		if(showType != null){
			switch(showType){
			   case 2://内容页
			   {  
				   List<TemplateCommonRelFrame> tcrList = new ArrayList<TemplateCommonRelFrame>();
				   TemplateCommonRelFrame templateCommonRelFrame = new TemplateCommonRelFrame();
				   templateCommonRelFrame.setCommonType(2);
				   templateCommonRelFrame.setRelStatus(0);
				   tcrList.add(templateCommonRelFrame);
				   TemplateColumnsRelFrames = iTemplateService.queryPageFrameInfo(tmp, columnsId, tcrList);
			   }
			   break;
			   default:
				   TemplateColumnsRelFrames = iTemplateService.queryPageFrameInfo(null,columnsId);
			}
		}else{
			TemplateColumnsRelFrames = iTemplateService.queryPageFrameInfo(null,columnsId);
		}
		returnMap.put("frames", TemplateColumnsRelFrames);
		printJSON(response, returnMap);
	}
	
	/**
	 * 
	* @Title: loadDynamicTempLateDatas 
	* @Description: 加载动态资源
	* @author  (shx@sxw100.com)
	* @param @param moduleIds
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadDynamicTempLateDatas")
	@ResponseBody
	public Map<String , Object>  loadDynamicTempLateDatas(
			@RequestParam(value = "moduleIds", required = false) List<Integer> moduleIds,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("--进入loadDynamicTempLateDatas 方法");
		Map<String , Object>  returnMap = new HashMap<String , Object>();
		List<TemplateModule> moudles =  iTemplateModuleService.queryTemplateModulesByIds(moduleIds);
		List<ModuleVo>  moduleVos = new ArrayList<ModuleVo>();
		if(moudles != null && moudles.size() > 0){
			for(TemplateModule moudle : moudles){
				 if(moudle.getModulePid().intValue() == Integer.valueOf(0)){
					 ModuleVo  moduleVo = new ModuleVo();
					TemplateModule record =new TemplateModule();
					record.setModulePid(moudle.getModuleId());
					List<TemplateModule> tm = new ArrayList<TemplateModule>();
					tm.add(moudle);
					List<TemplateModule> tm1 = 	iTemplateModuleService.queryAll(record );
					if(tm1 != null && tm1.size() > 0){
						List<Integer> ids = new ArrayList<Integer>();
						for(TemplateModule tml : tm1){
							ids.add(tml.getModuleId().intValue());
						}
						List<TemplateModule> subMoudles =  iTemplateModuleService.queryTemplateModulesByIds(ids);
						if(subMoudles != null) tm.addAll(subMoudles);
					}
					
					 moduleVo.setViewType((tm.size() > 1)? 1 : 0);
					 moduleVo.setMoudles(tm);
					 moduleVos.add(moduleVo);
				 }
			}
		}
		returnMap.put("columnsId", columnsId);
		returnMap.put("moduleVos", moduleVos);
		return returnMap;
		//printJSON(response, returnMap);
	}
	
	
	/**
	 * 
	* @Title: loadContentPageId 
	* @Description: TODO(获取内容的ID和类型 ) 
	* @author (wangkang@sxw100.com)
	* @param @param tempalte
	* @param @param articleId
	* @param @param articleTypeId
	* @param @param columnsId
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadContentPageId")
	public String  loadContentPageId(
			Template tempalte,
			@RequestParam(value = "articleId", required = false) Integer articleId,
			@RequestParam(value = "mediaId", required = false) Integer mediaId,
			@RequestParam(value = "pictureId", required = false) Integer pictureId,
		//	@RequestParam(value = "templateId", required = false) Integer templateId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model) {
		logger.debug("--进入loadContentPageId 方法");
	//	tempalte = syncSessionTemplate(tempalte ,  session); // 同步session 信息
		if(articleId != null){
			model.addAttribute("contentDataId", articleId);
			model.addAttribute("dataType", 2);
			};
		if(mediaId != null){
			model.addAttribute("contentDataId", mediaId);
			model.addAttribute("dataType", 9);
		};
		if(pictureId != null){
			model.addAttribute("contentDataId", pictureId);
			model.addAttribute("dataType", 7);
		};
		return "/template/contentPage";//DL-wms-web/src/main/webapp/jsp/
	}
	
	
	/**
	 * 
	* @Title: loadContentPage 
	* @Description: TODO(获取内容属性) 
	* @author (wangkang@sxw100.com)
	* @param  设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadContentPage")
	public void loadContentPage(
			@RequestParam(value = "contentDataId", required = false) Integer contentDataId,
			@RequestParam(value = "dataType", required = false) Integer dataType,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session){
		logger.debug("--进入loadContentPage 方法");
		Map<String , Object>  returnMap = new HashMap<String , Object>();
		//dataType==0101 类型为文章  dataType==0401 类型为视频  dataType==030101  类型为图册
		switch(dataType){
			case 2:
				Article article = new Article();
				article.setArticleId(contentDataId);
				returnMap.put("contentData", iArticleService.queryOne(article));
				break;
			case 9:
				Media media = new Media(); 
			    media.setMediaId(contentDataId);
			    returnMap.put("contentData", iMediaService.queryOne(media));
				break;
			case 7:
				Picture picture = new Picture();
				picture.setPictureId(contentDataId);
				returnMap.put("contentData", iPictureService.queryOne(picture));
				break;
				
		}
		printJSON(response, returnMap);
	}
	
	
	private  List<Article> setPageArticle(List<Article> articles , Integer articleId){
		if(articles == null ||  articleId == null  ) return null;
		List<Article> returnList = new ArrayList<Article>();
		int index = 0;
		for(int i =0 ; i < articles.size() ; i ++ ){
			if(articles.get(i).getArticleId().intValue() == articleId.intValue() ){
				index = i;
				break;
			}
		}
		if(index != 0){
			returnList.add(articles.get(index-1)); // 上一篇文章
		}
		returnList.add(articles.get(index)); // 当前文章
		if(index < (articles.size()-1)){
			returnList.add(articles.get(index+1)); // 下篇文章
		}
		return returnList;
	}
	
	/**
	 * 
	* @Title: loadMediaContentPage 
	* @Description: 加载视频
	*@author (shx@sxw100.com)
	* @param @param tempalte
	* @param @param media
	* @param @param response
	* @param @param request
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadMediaContentPage")
	public String  loadMediaContentPage(
			Template tempalte,
			Media media,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			Model model) {
		logger.debug("--进入loadArticlePage 方法");
		tempalte = SessionUtils.getSiteTemplate(session) ;
		TemplateVo templateVo = iTemplateService.qeruyTemplatePage(tempalte);
		Media mediaRst = iMediaService.queryOne(media );
		
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("mediaId", media.getMediaId());
		model.addAttribute("media", mediaRst);
		model.addAttribute("templateVo", templateVo);
		Integer readNum = mediaRst.getReadNum();
		mediaRst.setReadNum(readNum+1);
		//阅读数量加1
		iMediaService.updateMediaReadNum(mediaRst);
		return "/template/content/mediaContents";
	}
	
	/**
	 * 
	* @Title: loadPictureContentPage 
	* @Description: 跳转图片详情页
	* @author (panlinlin@sxw100.com)
	* @param @param tempalte
	* @param @param picture
	* @param @param response
	* @param @param request
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadPictureContentPage")
	public String  loadPictureContentPage(
			Template tempalte,
			Picture picture,
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpSession session,
			Model model) {
		logger.debug("--进入loadPictureContentPage 方法");
		tempalte = SessionUtils.getSiteTemplate(session) ;
		TemplateVo templateVo = iTemplateService.qeruyTemplatePage(tempalte);
		
		//查询该图片所id获取所属该图册
		PictureAlbum album=iPictureService.queryAlbumByPId(picture.getPictureId());
		int albumId=album.getAlbumId();
		//查询该图册下的所有图片
		List<Picture> pictures = iPictureAlbumService.queryReviewPicByAlbumId(albumId);
		//查询该图册下的所属分类
		PictureAlbumType albumType=iPictureAlbumService.queryAlbumTypeByAlbumId(albumId);
		if(albumType==null){
			return "/template/content/pictureContents";
		};
		//查询该分类的父节点
		List<PictureAlbumType> paretTypes = iPictureAlbumTypeService.getAllParetTypes(albumType.getAlbumTypeId());
		//查询当前分类的所有图册并放入首张图片
		List<PictureAlbum> pictureAlbums = iPictureAlbumService.queryAllAlbumByTypeId(albumType.getAlbumTypeId());
		
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("albumId", albumId);
		model.addAttribute("album", album);
		model.addAttribute("paretTypes", paretTypes);
		model.addAttribute("pictureAlbums", pictureAlbums);
		model.addAttribute("pictures", pictures);
		model.addAttribute("templateVo", templateVo);
		return "/template/content/pictureContents";
	};

	/**
	 * 
	* @Title: loadMPictureTypePage 
	* @Description: 跳转图片分类页
	* @author (panlinlin@sxw100.com)
	* @param @param tempalte
	* @param @param media
	* @param @param albumTypeId
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadPictureTypePage")
	public String  loadMPictureTypePage(
			Template tempalte,
			Media media,
			@RequestParam(value = "albumTypeId", required = false) String  albumTypeId,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Model model) {
		logger.debug("--进入loadMPictureTypePage 方法");
		tempalte = SessionUtils.getSiteTemplate(session);
		TemplateVo templateVo = iTemplateService.qeruyTemplatePage(tempalte);
		
		PictureAlbumType albumType = iPictureAlbumTypeService.queryByPrimaryKey(Integer.valueOf(albumTypeId));
		PictureAlbumType albumPType =iPictureAlbumTypeService.queryByPrimaryKey(albumType.getAlbumTypePid());
		//不存在父节点说明当前节点为顶级节点--》转向全部分类
		if(albumPType==null){
			//查询所有父子级分类
			List<PictureAlbumType> PictureAlbumTypes = iPictureAlbumTypeService.queryChildrenByPid(albumType.getAlbumTypeId());
			//查询一级子节点第一个类型下的图册
			List<PictureAlbum> pictureAlbums = iPictureAlbumService.queryAllAlbumByTypeId(PictureAlbumTypes.get(0).getAlbumTypeId());
			PictureAlbumTypes.add(0, albumType);
			//将图册首张图片放入
			for (int i = 0; i < pictureAlbums.size(); i++) {
				List<Picture> pics = iPictureAlbumService.queryReviewPicByAlbumId(pictureAlbums.get(i).getAlbumId());
				if(pics.size()>0){
					pictureAlbums.get(i).setPicture(pics.get(0));
					//空图册不显示
				}else{
					pictureAlbums.remove(i);
				}
			}
			model.addAttribute("columnsId", columnsId);
			model.addAttribute("PictureAlbumTypes", PictureAlbumTypes);
			model.addAttribute("pictureAlbums", pictureAlbums);
			model.addAttribute("type", 1);
			
			//存在父节点说明当前节点为子节点--》转向子级分类
		}else {
			List<PictureAlbumType> PictureAlbumTypes = new ArrayList<>();
			PictureAlbumTypes.add(albumPType);
			PictureAlbumTypes.add(albumType);
			//查询当前分类的所有图册
			List<PictureAlbum> pictureAlbums = iPictureAlbumService.queryAllAlbumByTypeId(Integer.valueOf(albumTypeId));
			//将图册首张图片放入
			for (int i = 0; i < pictureAlbums.size(); i++) {
				List<Picture> picByAlbumIds = iPictureAlbumService.queryReviewPicByAlbumId(pictureAlbums.get(i).getAlbumId());
				if(picByAlbumIds.size()>0){
					pictureAlbums.get(i).setPicture(picByAlbumIds.get(0));
					//空图册不显示
				}else{
					pictureAlbums.remove(i);
				}
			}
				model.addAttribute("columnsId", columnsId);
				model.addAttribute("PictureAlbumTypes", PictureAlbumTypes);
				model.addAttribute("columnsId", columnsId);
				model.addAttribute("pictureAlbums", pictureAlbums);
				model.addAttribute("type", 0);
		}
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("templateVo", templateVo);
		return "/template/content/pictureTypeContent";
		
	}
	
	/**
	 * 
	* @Title: loadColumnistPage 
	* @Description: 加载专题二级页面
	*@author (shx@sxw100.com)
	* @param @param tempalte
	* @param @param media
	* @param @param mediaTypeId
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadColumnistPage")
	public String  loadColumnistPage(
			Template tempalte,
			Media media,
			@RequestParam(value = "articleTypeId", required = false) String articleTypeIds,
			@RequestParam(value = "flag", required = false) String flag,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Model model) {
		logger.debug("--进入loadColumnistPage 方法");
		TemplateVo templateVo = iTemplateService.qeruyTemplatePage( SessionUtils.getSiteTemplate(session));
		List<ArticleType> articleTypes = new ArrayList<ArticleType>();
		List<Integer> articleTIds = new ArrayList<Integer>();
		String [] articleTypeIdArr = articleTypeIds.split(",");
		for(String key : articleTypeIdArr ){
			articleTIds.add(Integer.valueOf(key));
			ArticleType articleType = new ArticleType();
			articleType.setArticleTypeId(Integer.valueOf(key));
			articleType.setDataStatus(Integer.valueOf(0));
			ArticleType articleTypeRst = iArticleTypeService.queryOne(articleType);
			if(articleTypeRst != null){articleTypes.add(articleTypeRst);}
			if(StringUtils.isBlank(flag)){
				ArticleType articleType1 = new ArticleType();
				articleType1.setArticleTypePid(Integer.valueOf(key));
				articleType1.setDataStatus(Integer.valueOf(0));
				List<ArticleType>  aTypes =  iArticleTypeService.queryAll(articleType1);
				if(aTypes != null & aTypes.size() > 0) { 
					articleTypes.addAll(aTypes);
					for(ArticleType at : aTypes ){
						articleTIds.add(at.getArticleTypeId());
					}
				}
			}
		}
		Article article = new Article();
		article.setArticleTypes(articleTIds);
		article.setDataStatus(Integer.valueOf(0));
		List<Article>  articles = iArticleService.queryByArticleCodition(article);
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("articles", articles);
		model.addAttribute("articleTypes", articleTypes);
		model.addAttribute("templateVo", templateVo);
		return "/template/content/columnistType";
	}
	
	/**
	 * 
	* @Title: loadMediaTypePage 
	* @Description: 加载视频类型页
	*@author (shx@sxw100.com)
	* @param @param tempalte
	* @param @param media
	* @param @param mediaTypeId
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadMediaTypePage")
	public String  loadMediaTypePage(
			Template tempalte,
			Media media,
			@RequestParam(value = "mediaTypeId", required = false) Integer mediaTypeId,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Model model) {
		logger.debug("--进入loadArticlePage 方法");
		tempalte = SessionUtils.getSiteTemplate(session) ;
		TemplateVo templateVo = iTemplateService.qeruyTemplatePage(tempalte);
		//通过视频顶级分类查找视频并返回所有分类
		MediaType mtR = new MediaType();
		mtR.setDataStatus(Integer.valueOf(0));
		mtR.setMediaTypeId(mediaTypeId);
		List<MediaType> mediaTypes = IMediaTypeService.queryAll(mtR );
		
		MediaType mt = new MediaType();
		mt.setDataStatus(Integer.valueOf(0));
		mt.setMediaTypePid(mediaTypeId);
		List<MediaType> mediaChildTypes = IMediaTypeService.queryAll(mt );
		
		if(mediaChildTypes != null ) {mediaTypes.addAll(mediaChildTypes);} 
		List<Integer> mediaTypeIds = new ArrayList<Integer>();
		for(MediaType mediaType : mediaTypes){
			mediaTypeIds.add(mediaType.getMediaTypeId());
		}
		media.setMediaTypes(mediaTypeIds);
		List<Media> medias = iMediaService.queryMediaByCodition(media);
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("medias", medias);
		model.addAttribute("mediaTypes", mediaTypes);
		model.addAttribute("templateVo", templateVo);
		return "/template/content/mediaTypes";
	}
	
	
	/**
	 * 
	* @Title: loadInformationPage 
	* @Description: 加载文章咨询页
	*@author (shx@sxw100.com)
	* @param @param tempalte
	* @param @param articleTypeIds
	* @param @param media
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadInformationPage")
	public String  loadInformationPage(
			Template tempalte,
			@RequestParam(value = "page", required = false) Integer currentPage, 
			@RequestParam(value = "rows", required = false) Integer pageSize,
			@RequestParam(value = "articleTypeId", required = false) String articleTypeIds,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			Media media,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Model model) {
		logger.debug("--进入loadInformationPage 方法");
		TemplateVo templateVo = iTemplateService.qeruyTemplatePage( SessionUtils.getSiteTemplate(session));
		List<ArticleType> articleTypes = new ArrayList<ArticleType>();
		List<Integer> articleTIds = new ArrayList<Integer>();
		String [] articleTypeIdArr = articleTypeIds.split(",");
		for(String key : articleTypeIdArr ){
			articleTIds.add(Integer.valueOf(key));
			ArticleType articleType = new ArticleType();
			articleType.setArticleTypeId(Integer.valueOf(key));
			articleType.setDataStatus(Integer.valueOf(0));
			ArticleType articleTypeRst = iArticleTypeService.queryOne(articleType);
			if(articleTypeRst != null){articleTypes.add(articleTypeRst);}
		}
		if(currentPage==null){
			currentPage=1;
		}
		if(pageSize==null){
			pageSize=6;
		}
		PageHelper.startPage(currentPage, pageSize,true);//设置分页
		Article article = new Article();
		article.setArticleTypes(articleTIds);
		article.setDataStatus(Integer.valueOf(0));
		List<Article>  articles = iArticleService.queryByArticleCodition(article);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rows", articles);
		model.addAttribute("pages", pageInfo.getPages());
		model.addAttribute("total", pageInfo.getTotal());
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("articles", articles);
		model.addAttribute("articleTypes", articleTypes);
		model.addAttribute("templateVo", templateVo);
		return "/template/content/articleInformations";
	}
	

	/**
	 * 
	* @Title: loadBookContentPage 
	* @Description: TODO(跳转图书详情页) 
	* @author (panlinlin@sxw100.com)
	* @param @param tempalte
	* @param @param picture
	* @param @param response
	* @param @param request
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadBookContentPage")
	public String  loadBookContentPage(
			Template tempalte,
			Article article,
			HttpServletResponse response,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpSession session,
			HttpServletRequest request,Model model) {
		logger.debug("--进入loadBookContentPage 方法");
		 TemplateVo templateVo = iTemplateService.qeruyTemplatePage(SessionUtils.getSiteTemplate(session));
		//查询该图书所属分类
		ArticleRelType relType = new ArticleRelType();
		relType.setArticleId(article.getArticleId());
		Article articleNow = iArticleService.queryByPrimaryKey(article.getArticleId());
		List<ArticleRelType> ArticleRelTypes = iArticleRelTypeService.queryAll(relType);
		
		Integer ArticleTypeId=ArticleRelTypes.get(0).getArticleTypeId();
		
		ArticleType articleType = iArticleTypeService.queryByPrimaryKey(ArticleTypeId);
		ArticleType articlePType = iArticleTypeService.queryByPrimaryKey(articleType.getArticleTypePid());
		List<ArticleType> articleTypes = new ArrayList<>();
		articleTypes.add(articlePType);
		articleTypes.add(articleType);
		//查询该分类下的所有图书
		List<Article> articles=iArticleService.queryAllArticleVyTypeId(ArticleTypeId);
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("articleTypes", articleTypes);
		model.addAttribute("article", articleNow);
		model.addAttribute("articles", articles);
		model.addAttribute("templateVo", templateVo);
		return "/template/content/bookContents";
	};


	

	/**
	 * 
	* @Title: loadBookTypePage 
	* @Description: 加载图分类
	* @author (panlinlin@sxw100.com)
	* @param @param tempalte
	* @param @param media
	* @param @param articleTypeId
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/loadBookTypePage")
	public String  loadBookTypePage(
		Template tempalte,
		Media media,
		@RequestParam(value = "articleTypeId", required = false) String  articleTypeId,
		@RequestParam(value = "columnsId", required = false) Integer columnsId,
		HttpServletResponse response,
		HttpServletRequest request,
		HttpSession session,
		Model model) {
		logger.debug("--进入loadBookTypePage 方法");
		tempalte = SessionUtils.getSiteTemplate(session);
		TemplateVo templateVo = iTemplateService.qeruyTemplatePage(tempalte);
	
		//通过顶级分类查找并返回所有分类
		ArticleType articleType = iArticleTypeService.queryByPrimaryKey(Integer.valueOf(articleTypeId));
		if(articleType.getArticleTypePid()==0){
				//当前为顶节点
				//查询所有子节点
			 ArticleType ext = new ArticleType();
			 ext.setArticleTypePid(Integer.valueOf(articleTypeId));
			 List<ArticleType> articleTypes = iArticleTypeService.queryAll(ext);
			 articleTypes.add(0, articleType);
			 ArticleType firType = articleTypes.get(1);
			 //查询该分类下的所有图书
			 List<Article> articles=iArticleService.queryAllArticleVyTypeId(firType.getArticleTypeId());
			 model.addAttribute("articleTypes", articleTypes);
			 model.addAttribute("type", 1);
			model.addAttribute("articles", articles);
		}else{
			ArticleType articlePType = iArticleTypeService.queryByPrimaryKey(articleType.getArticleTypePid());
			 List<ArticleType>  articleTypes = new ArrayList<>();
			 articleTypes.add(0, articlePType);
			 articleTypes.add(1, articleType);
			 ArticleType firType =articleType;
			//查询该分类下的所有图书
			 List<Article> articles=iArticleService.queryAllArticleVyTypeId(firType.getArticleTypeId());
			 
			 model.addAttribute("articleTypes", articleTypes);
			 model.addAttribute("type", 0);
			model.addAttribute("articles", articles);
		}
			model.addAttribute("columnsId", columnsId);
			model.addAttribute("templateVo", templateVo);
			return "/template/content/bookTypeContent";
		}
	

	@RequestMapping(value = "/loadWebsiteMapPage")
	public String  loadWebsiteMapPage(
			Template tempalte,
			Media media,
			@RequestParam(value = "templateId", required = false) Integer templateId,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Model model) {
		logger.debug("--进入loadWebsiteMapPage 方法");
		tempalte = SessionUtils.getSiteTemplate(session) ;
		TemplateVo templateVo = iTemplateService.qeruyTemplatePage(tempalte);
		//获取站点一级
		List<TemplateColumns> templateColumnsList = iTemplateColumnsService.queryTemplateColumnsByTemplateId(columnsId);
		@SuppressWarnings("rawtypes")
		Map<Integer, List> data = new HashMap<>();
		for (int i = 0; i < templateColumnsList.size(); i++) {
			TemplateColumns tc = templateColumnsList.get(i);
			Integer cId = tc.getColumnsId();
			if(cId==32){
				//直播
				HashMap<Integer, String> mapData = new HashMap<>();
				mapData.put(0, "");
			}else if(cId==33){
				//中华魂资讯-->获取咨询信息
				//图书
				ArticleType articleType=new ArticleType();
				articleType.setArticleTypePid(20);
				List<ArticleType> ArticleTypes = iArticleTypeService.queryAll(articleType);
				data.put(1, ArticleTypes);
			}else if(cId==34){
				//精彩视频
				MediaType mediaType = new MediaType();
				mediaType.setMediaTypeId(10);
				List<MediaType> MediaTypeList = IMediaTypeService.queryAll(mediaType);
				data.put(2, MediaTypeList);
			}else if(cId==35){
				//研究会专栏
				ArticleType articleType=new ArticleType();
				articleType.setArticleTypePid(29);
				List<ArticleType> articleTypes = iArticleTypeService.queryAll(articleType);
				articleType.setArticleTypePid(32);
				List<ArticleType> articleTypes2 = iArticleTypeService.queryAll(articleType);
				ArrayList<Object> articleTypePlist = new ArrayList<>();
				articleTypePlist.add(articleTypes);
				articleTypePlist.add(articleTypes2);
				data.put(3, articleTypePlist);
			}else if(cId==36){
				//图书
				ArticleType articleType=new ArticleType();
				articleType.setArticleTypePid(24);
				List<ArticleType> ArticleTypes = iArticleTypeService.queryAll(articleType);
				data.put(4, ArticleTypes);
			}else if(cId==37){
				//图片-->查询图片分类
				PictureAlbumType albumType=new PictureAlbumType();
				albumType.setAlbumTypePid(4);
				List<PictureAlbumType> PictureAlbumTypes = iPictureAlbumTypeService.queryAll(albumType);
				data.put(5, PictureAlbumTypes);
			}
		}
		model.addAttribute("templateColumnsList", templateColumnsList);
		model.addAttribute("data", data);
		model.addAttribute("templateVo", templateVo);
		return "/template/content/websiteMap";
	}
	
}