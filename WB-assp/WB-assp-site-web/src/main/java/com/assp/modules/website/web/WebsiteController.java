package com.assp.modules.website.web;

import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.assp.common.ResponseJsonData;
import com.assp.common.utils.DateUtils;
import com.assp.common.utils.JSONUtil;
import com.assp.common.web.BaseController;
import com.assp.modules.common.SessionUtils;
import com.assp.modules.commondata.entity.Article;
import com.assp.modules.commondata.entity.ArticleRelType;
import com.assp.modules.commondata.entity.Media;
import com.assp.modules.commondata.entity.MediaRelType;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.assp.modules.commondata.entity.PictureAlbumRelType;
import com.assp.modules.commondata.service.IArticleRelTypeService;
import com.assp.modules.commondata.service.IArticleService;
import com.assp.modules.commondata.service.IMediaRelTypeService;
import com.assp.modules.commondata.service.IMediaService;
import com.assp.modules.commondata.service.IPictureAlbumRelTypeService;
import com.assp.modules.commondata.service.IPictureAlbumService;
import com.assp.modules.commondata.service.IPictureService;
import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.module.entity.TemplateModuleRelContent;
import com.assp.modules.module.entity.TemplateModuleVo;
import com.assp.modules.module.service.ITemplateModuleRelContentService;
import com.assp.modules.module.service.ITemplateModuleService;
import com.assp.modules.sys.entity.User;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.entity.TemplateColumns;
import com.assp.modules.template.entity.TemplateCommonRelFrame;
import com.assp.modules.template.entity.TemplateFooter;
import com.assp.modules.template.entity.TemplateFooterRelColumns;
import com.assp.modules.template.entity.TemplateLogo;
import com.assp.modules.template.entity.TemplateRelColumns;
import com.assp.modules.template.entity.TemplateRelFrame;
import com.assp.modules.template.service.ITemplateColumnsService;
import com.assp.modules.template.service.ITemplateFooterRelColumnsService;
import com.assp.modules.template.service.ITemplateFooterService;
import com.assp.modules.template.service.ITemplateLogoService;
import com.assp.modules.template.service.ITemplateRelColumnsService;
import com.assp.modules.template.service.ITemplateService;
import com.assp.modules.website.entity.Website;
import com.assp.modules.website.service.IWebsiteService;
import com.assp.modules.website.vo.ModuleVo;


/**
 * 类简述
 * <p>
 *     站点编辑控制器
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月26日 
 */
@Controller
@RequestMapping(value = "/site")
public class WebsiteController extends BaseController {
	
	private static final Logger logger = Logger. getLogger(WebsiteController.class);
	
	@Autowired
	private ITemplateService  iTemplateService;
	@Autowired
	private ITemplateLogoService  iTemplateLogoService;
	@Autowired
	private ITemplateColumnsService  iTemplateColumnsService;
	@Autowired
	private ITemplateFooterRelColumnsService  iTemplateFooterRelColumnsService;
	@Autowired
	private ITemplateFooterService iTemplateFooterService;
	@Autowired
	private ITemplateRelColumnsService  iTemplateRelColumnsService;
	@Autowired
	private ITemplateModuleRelContentService  iTemplateModuleRelContentService;
	@Autowired
	private ITemplateModuleService iTemplateModuleService;
	
	@Autowired
	private IArticleService iArticleService;
	
	@Autowired
	private IArticleRelTypeService iArticleRelTypeService;
	@Autowired
	private IPictureService iPictureService;
	@Autowired
	private IPictureAlbumService iPictureAlbumService;
	@Autowired
	private IMediaService iMediaService;
	@Autowired
	private IMediaRelTypeService iMediaRelTypeService;
	
   @Autowired 
   private IWebsiteService  iWebsiteService;
	
   @Autowired
   private IPictureAlbumRelTypeService iPictureAlbumRelTypeService;
   
	/**
	 * @throws Exception 
	 * @throws NumberFormatException 
	 * 
	* @Title: templateToSite 
	* @Description: 迁移站点数据并跳转站点页面
	*@author (shx@sxw100.com)
	* @param @param template
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/templateToSite")
	public String templateToSite(Template template,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session, Model model) throws NumberFormatException,
			Exception {
		logger.debug("--进入templateToSite 方法");
		User user = SessionUtils.getloginUser(request);

		Map<String, Object> map = iWebsiteService.templateToWebsite(template,
				user);
		if ("1".equals(String.valueOf(map.get("syncRst")))) {
			logger.debug("同步服务器数据失败****");
		}
		StringBuffer sb = new StringBuffer("redirect:/site/loadWebsitePage.do?");
		if (map.get("siteId") != null) {
			sb.append("siteId=").append(map.get("siteId"));
		}

		if (map.get("siteTemplateId") != null) {
			Integer templateId = (Integer) map.get("siteTemplateId");
			sb.append("&").append("templateId=").append(templateId);
			Template temrc = new Template();
			temrc.setTemplateId(templateId);
			List<TemplateRelColumns> list = iTemplateService
					.queryTemplateRelColumnsByCondition(temrc);
			if (list != null && list.size() > 0) {
				TemplateRelColumns temp = list.get(0);
				sb.append("&columnsId=").append(temp.getColumnsId());
			}
		}

		return sb.toString();
	}
	
	
	
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
	@RequestMapping(value = "/loadWebsitePage")
	public String  loadTempLatePage(
			Template tempalte,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			@RequestParam(value = "siteId", required = false) Integer siteId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model) {
		logger.debug("--进入loadWebsitePage 方法");
		if(siteId != null){
			Website website = iWebsiteService.queryByPrimaryKey(siteId);
			session.setAttribute("website", website);
		}
		tempalte = syncSessionTemplate(tempalte ,  session); // 同步session 信息
		model.addAttribute("columnsId", columnsId);
		model.addAttribute("tempalte", tempalte);
		model.addAttribute("siteId", siteId);
		return "/website/index";
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
		tmp.setSiteId(SessionUtils.getWebsiteId(session));
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
	@RequestMapping(value = "/loadDynamicWebsiteDatas")
	@ResponseBody
	public Map<String , Object>  loadDynamicTempLateDatas(
			@RequestParam(value = "moduleIds", required = false) List<Integer> moduleIds,
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("--进入loadDynamicWebsiteDatas 方法");
		Map<String , Object>  returnMap = new HashMap<String , Object>();
		List<TemplateModule> moudles =  iTemplateModuleService.queryTemplateModulesByIds(moduleIds);
		List<ModuleVo>  moduleVos = new ArrayList<ModuleVo>();
		if(moudles != null && moudles.size() > 0){
			for(TemplateModule moudle : moudles){
				 if(moudle.getModulePid() == null || moudle.getModulePid().intValue() == Integer.valueOf(0)){
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
	}
	
	/**
	 * 
	 * @Title: loadDynamicModuleDatas 
	 * @Description: 局部加载动态资源
	 * @author  (wzp@sxw100.com)
	 * @param @param moduleIds
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/loadDynamicModuleDatas")
	@ResponseBody
	public String  loadDynamicModuleDatas(
			TemplateModule templateModule,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) {
		
		logger.debug("--进入loadDynamicModuleDatas 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		templateModule.setSiteId(SessionUtils.getWebsiteId(session));
		TemplateModule moudle =  iTemplateModuleService.queryTemplateModuleByTemplateModule(templateModule);
		
		// 获取子类
		if(moudle.getModulePid().intValue() == Integer.valueOf(0)){
			TemplateModule record =new TemplateModule();
			record.setModulePid(moudle.getModuleId());
			List<TemplateModule> tm1 = 	iTemplateModuleService.queryAll(record );
			if(tm1 != null && tm1.size() > 0){
				List<Integer> ids = new ArrayList<Integer>();
				for(TemplateModule tml : tm1){
					ids.add(tml.getModuleId().intValue());
				}
				List<TemplateModule> subMoudles =  iTemplateModuleService.queryTemplateModulesByIds(ids);
				if(subMoudles != null) {
					moudle.setChildTemplateModules(subMoudles);
				}
			}
		 }
		
		// 获取父类
		if(moudle != null){
			
			if(moudle.getModulePid().intValue() != Integer.valueOf(0)){
				TemplateModule parentMoudle = iTemplateModuleService.queryByPrimaryKey(moudle.getModulePid());
				moudle.setParentTemplateModule(parentMoudle);
			}
		}
		reponseJsonData.addData("module", moudle);
		
		return reponseJsonData.generateResultStr();
	}
	
	
	/**
	 * 
	* @Title: modifyTempLateLogo
	* @Description: 持久logo修改
	*@author (wzp@sxw100.com)
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/modifyTempLateLogo")
	public void  modifyTempLateLogo(
			TemplateLogo tempalteLogo,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session) {
		
		logger.debug("--进入modifyTempLateLogo 方法");
		
		Map<String , Object>  returnMap = new HashMap<String , Object>();
		
		try{
			Website websiteVO = SessionUtils.getWebsite(session);
			tempalteLogo.setSiteId(websiteVO.getSiteId());
			iTemplateLogoService.updateByPrimaryKeySelective(tempalteLogo);
			returnMap.put("code", 1);
			
		}catch(Exception e){
			
			logger.error(e);
			returnMap.put("code", 0);
			
		}
		
		printJSON(response, returnMap);
	}
	
	/**
	 * 
	 * @Title: queryTempLateNav
	 * @Description: 查询导航
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/queryTempLateNav")
	@ResponseBody
	public String  queryTempLateNav(
			TemplateModule templatemodule,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.debug("--进入queryTempLateNav 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		//先查询本TemplateModuleRelContent
		TemplateModuleRelContent trt = new TemplateModuleRelContent();
		trt.setModuleId(templatemodule.getModuleId());
		trt = iTemplateModuleRelContentService.queryOne(trt);
		List<TemplateColumns> templateColumns = iTemplateColumnsService.queryTemplateColumnsByTemplateId(trt.getDataColumnId());
		reponseJsonData.addData("rows", templateColumns);
		reponseJsonData.addData("templateId", trt.getDataColumnId());
		return reponseJsonData.generateResultStr();
	}
	
	/**
	 * 
	 * @Title: modifyTempLatefocus
	 * @Description: 持久轮换图修改
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/modifyTempLatefocus")
	@ResponseBody
	public String  modifyTempLatefocus(
			@RequestParam(required = false) String module,
			@RequestParam(required = false) String datas,
			@RequestParam(required = false) Integer pictureTypeId,
			@RequestParam Integer moduleId,
			@RequestParam(required = false) String templateModuleRelContents,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) {
		
		logger.debug("--进入modifyTempLatefocus 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			Integer siteId = SessionUtils.getWebsiteId(session);
			JSONObject jsonObject;//
			// 更新模块
			if(StringUtils.isNotBlank(module)){
				jsonObject = JSONObject.parseObject(module);
				TemplateModule moduleVO = new TemplateModule();
				moduleVO.setModuleId(jsonObject.getInteger("moduleId"));
				moduleVO = iTemplateModuleService.queryOne(moduleVO);
				if(moduleVO != null){
					//请求赋值
					JSONUtil.copyfromJson(moduleVO, jsonObject);
					moduleVO.setModuleItemLineNum(8);
					iTemplateModuleService.updateByPrimaryKeySelective(moduleVO);
				}
			}
			//更换图册
			TemplateModuleRelContent templateModuleRelContent = new TemplateModuleRelContent();
			templateModuleRelContent.setModuleId(moduleId);
			templateModuleRelContent = iTemplateModuleRelContentService.queryOne(templateModuleRelContent);
			if(templateModuleRelContent.getDataColumnId().intValue() != pictureTypeId.intValue()){
				templateModuleRelContent.setDataColumnId(pictureTypeId);
				iTemplateModuleRelContentService.updateByPrimaryKeySelective(templateModuleRelContent);
			}
			
			JSONArray pictureArray = JSONArray.parseArray(datas);
			Picture picture;
			jsonObject = null;
			if(pictureArray != null){
				for(int i = 0 ; i < pictureArray.size(); i++){
					jsonObject = pictureArray.getJSONObject(i);
					picture = new Picture();
					//由于对象属性不全，则需先查一下遍 再更新
					if(jsonObject.getInteger("pictureId") != null && jsonObject.getInteger("pictureId") > 0 ){
						picture.setPictureId(jsonObject.getInteger("pictureId"));
						picture = iPictureService.queryOne(picture);
						//请求赋值
						JSONUtil.copyfromJson(picture, jsonObject);
						//持久化
						picture.setUpdateTime(new Date());
						iPictureService.updateByPrimaryKey(picture);
					}else {
						//请求赋值
						JSONUtil.copyfromJson(picture, jsonObject);
						//如果是新添加进来，但有删除了的 就不予入库
						if(picture.getDataStatus() != null && picture.getDataStatus() == 1)
							continue;
						picture.setPictureId(null);
						picture.setSiteId(siteId);
						picture.setPictureAlbumId(pictureTypeId);
						picture.setDataStatus(0);
						picture.setIsReview(1);
						picture.setCreateTime(new Date());
						picture.setUpdateTime(new Date());
						iPictureService.add(picture);
					}
					
				}
			}
			
		}catch(Exception e){
			logger.error(e);
			reponseJsonData.setCode(0);
		}
		
		return reponseJsonData.generateResultStr();
	}
	/**
	 * 
	 * @Title: modifyTempLateNav
	 * @Description: 持久nav修改
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/modifyTempLateNav")
	public void  modifyTempLateNav(
			@RequestParam String datas,
			@RequestParam Integer templateId,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.debug("--进入modifyTempLateNav 方法");
		
		Map<String , Object>  returnMap = new HashMap<String , Object>();
		
		try{
			List<TemplateColumns> templateColumnsList = JSONArray.parseArray(datas,TemplateColumns.class);
		//	List<TemplateRelColumns> templateRelColumnsList = new ArrayList<TemplateRelColumns>();
			TemplateRelColumns templateRelColumns;
			for(TemplateColumns templateColumns:templateColumnsList){

				iTemplateColumnsService.updateByPrimaryKeySelective(templateColumns);
				
				//更新排序，隐藏，删除操作
				templateRelColumns = new TemplateRelColumns();
				templateRelColumns.setColumnsId(templateColumns.getColumnsId());
				templateRelColumns.setTemplateId(templateId);
				templateRelColumns = iTemplateRelColumnsService.queryOne(templateRelColumns);
				
				if(templateColumns.getDataStatus() == Integer.valueOf(1)){
					templateRelColumns.setRelStatus(1);
				}
				templateRelColumns.setSortNum(templateColumns.getSortNum());
				templateRelColumns.setColumnsHide(templateColumns.getColumnsHide());
				//templateRelColumnsList.add(templateRelColumns);
				iTemplateRelColumnsService.updateByPrimaryKeySelective(templateRelColumns);	
					
			}
			
			
			returnMap.put("code", 1);
			
		}catch(Exception e){
			
			logger.error(e);
			returnMap.put("code", 0);
			
		}
		printJSON(response, returnMap);
	}
	
	/**
	 * 
	 * @Title: addTempLateNav
	 * @Description: 持久nav修改
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/addTempLateNav")
	@ResponseBody
	public String  addTempLateNav(
			TemplateColumns templateColumns,
			@RequestParam Integer templateId,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) {
		
		logger.debug("--进入addTempLateNav 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			Integer siteId = SessionUtils.getWebsiteId(session);
			if(siteId == null){
				throw new Exception("session siteId is validation!");
			}
			templateColumns.setSiteId(siteId);
			iTemplateColumnsService.addGetprimaryKey(templateColumns);
			// 设置一些别要属性
			iTemplateColumnsService.updateByPrimaryKeySelective(templateColumns);
			
			// 更新排序，隐藏，删除操作
			TemplateRelColumns templateRelColumns = new TemplateRelColumns();
			templateRelColumns.setColumnsId(templateColumns.getColumnsId());
			templateRelColumns.setSiteId(siteId);
			templateRelColumns.setTemplateId(templateId);
			templateRelColumns.setSortNum(templateColumns.getSortNum());
			templateRelColumns.setColumnsHide(templateColumns.getColumnsHide());
			iTemplateRelColumnsService.addSelective(templateRelColumns);
			
			// 设置预定义的模块组
			iTemplateModuleService.addModuleForTemplateColumns(templateRelColumns);
			
			// 添加自定义菜单
			templateColumns.setColumnsTypeName("自定义菜单");
			reponseJsonData.addData("templateColumns", templateColumns);
		}catch(Exception e){
			
			logger.error(e);
			reponseJsonData.setCode(0);
			
		}
		
		return reponseJsonData.generateResultStr();
	}
	
	/**
	 * 
	 * @Title: modifyModule
	 * @Description: 持久模块修改
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/modifyModuleVO")
	@ResponseBody
	public String  modifyModuleVO(
			TemplateModule templateModule,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) {
		
		logger.debug("--进入modifyModule 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			Integer siteId = SessionUtils.getWebsiteId(session);
			/*if(siteId == null){
				reponseJsonData.setCode(0);
				reponseJsonData.setMesssage("session siteId is validation!");
				logger.error("session siteId is validation!");
				return reponseJsonData.generateResultStr();
			}*/
			TemplateModule tempTemplateModule = iTemplateModuleService.queryByPrimaryKey(templateModule.getModuleId());
			tempTemplateModule.setModuleType(templateModule.getModuleType());
			iTemplateModuleService.addModuleForParentModule(tempTemplateModule);
		}catch(Exception e){
			
			logger.error(e);
			reponseJsonData.setCode(0);
			
		}
		
		return reponseJsonData.generateResultStr();
	}

	
	/**
	 * 
	 * @Title: queryFooterNav
	 * @Description: 查询站脚导航
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/queryFooterNav")
	@ResponseBody
	public String  queryFooterNav(
			TemplateModule templatemodule,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session) {
		
		logger.debug("--进入queryFooterNav 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		//先查询本TemplateModuleRelContent
		TemplateModuleRelContent trt = new TemplateModuleRelContent();
		trt.setModuleId(templatemodule.getModuleId());
		trt = iTemplateModuleRelContentService.queryOne(trt);
		Template template = SessionUtils.getSiteTemplate(session);
		List<TemplateColumns> templateColumns = iTemplateColumnsService.selectTemplateColumnsInFooterByTemplateVO(template);
		reponseJsonData.addData("rows", templateColumns);
		reponseJsonData.addData("footerId", trt.getDataColumnId());
		reponseJsonData.addData("templateId", template.getTemplateId());
		return reponseJsonData.generateResultStr();
	}
	
	/**
	 * 
	 * 
	 * @Title: modifyTempLateNav
	 * @Description: 持久nav修改
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/modifyFooterNav")
	public void  modifyFooterNav(
			@RequestParam(required=false) String datas,
			@RequestParam(required=false) String copyrightInfo,
			@RequestParam Integer footerId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session) {
		
		logger.debug("--进入modifyFooterNav 方法");
		
		Map<String , Object>  returnMap = new HashMap<String , Object>();
		Template template = SessionUtils.getSiteTemplate(session);
		try{
			Integer siteId = SessionUtils.getWebsiteId(session);
			List<TemplateColumns> templateColumnsList = JSONArray.parseArray(datas,TemplateColumns.class);
			TemplateFooterRelColumns templateFooterRelColumns;
			for(TemplateColumns templateColumns:templateColumnsList){

				iTemplateColumnsService.updateByPrimaryKey(templateColumns);
				
				//更新排序，隐藏，删除操作
				templateFooterRelColumns = new TemplateFooterRelColumns();
				templateFooterRelColumns.setColumnsId(templateColumns.getColumnsId());
				templateFooterRelColumns.setSiteId(siteId);
				templateFooterRelColumns.setTemplateFooterId(footerId);
				templateFooterRelColumns = iTemplateFooterRelColumnsService.queryOne(templateFooterRelColumns);
				if(templateFooterRelColumns == null ){
					templateFooterRelColumns = new TemplateFooterRelColumns();
					templateFooterRelColumns.setSiteId(siteId);
					templateFooterRelColumns.setColumnsId(templateColumns.getColumnsId());
					templateFooterRelColumns.setTemplateFooterId(footerId);
				}
				
				// 删除操作
				if(templateColumns.getDataStatus() == Integer.valueOf(1)){
					templateFooterRelColumns.setRelStatus(1);
					// 上面导航栏做删除操作
					TemplateRelColumns templateRelColumns = new TemplateRelColumns();
					templateRelColumns.setColumnsId(templateColumns.getColumnsId());
					templateRelColumns.setSiteId(siteId);
					templateRelColumns.setTemplateId(template.getTemplateId());
					templateRelColumns = iTemplateRelColumnsService.queryOne(templateRelColumns);
					if(templateRelColumns != null){
						templateRelColumns.setRelStatus(1);
						iTemplateRelColumnsService.updateByPrimaryKeySelective(templateRelColumns);
					}
				}
				
				templateFooterRelColumns.setSortNum(templateColumns.getSortNum());
				templateFooterRelColumns.setColumnsHide(templateColumns.getColumnsHide());
				//templateRelColumnsList.add(templateRelColumns);
				if(templateFooterRelColumns.getTemplateFooterRelColumnsId() != null){
					iTemplateFooterRelColumnsService.updateByPrimaryKey(templateFooterRelColumns);	
				}else{
					iTemplateFooterRelColumnsService.addSelective(templateFooterRelColumns);	
				}
					
			}
			//站脚
			if(StringUtils.isNotEmpty(copyrightInfo)){
				TemplateFooter templateFooter = new TemplateFooter();
				templateFooter.setTemplateFooterId(footerId);
				templateFooter.setSiteId(siteId);
				templateFooter.setCopyrightInfo(copyrightInfo);
				iTemplateFooterService.updateByPrimaryKeySelective(templateFooter);
			}
			
			returnMap.put("code", 1);
			
		}catch(Exception e){
			
			logger.error(e);
			returnMap.put("code", 0);
			
		}
		printJSON(response, returnMap);
	}
	
	/**
	 * 
	 * @Title: getTempLateFocus
	 * @Description: 获取模块数据
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/getTempLateFocus")
	@ResponseBody
	public String  getTempLateFocus(
			@RequestParam Integer moduleId,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) {
		
		logger.debug("--进入getTempLateFocus 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			Integer siteId = SessionUtils.getWebsiteId(session);
			TemplateModule templateModuleVO = new TemplateModule();
			templateModuleVO.setSiteId(siteId);
			templateModuleVO.setModuleId(moduleId);
			TemplateModule templateModule = iTemplateModuleService.queryTemplateModuleByTemplateModule(templateModuleVO);			
			
			reponseJsonData.addData("module", templateModule);
		}catch(Exception e){
			
			logger.error(e);
			reponseJsonData.setCode(0);
		}
		
		return reponseJsonData.generateResultStr();
	}
	
	/**
	 * 
	 * @Title: getPictureAlbums
	 * @Description: 获取模块数据
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/getPictureAlbums")
	@ResponseBody
	public String  getPictureAlbums(
			HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.debug("--进入getPictureAlbums 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			
			List<PictureAlbum> pictureAlbums = iPictureAlbumService.queryAll(null);			
			
			reponseJsonData.addData("pictureAlbums", pictureAlbums);
		}catch(Exception e){
			
			logger.error(e);
			reponseJsonData.setCode(0);
		}
		
		return reponseJsonData.generateResultStr();
	}
	
	/**
	 * 
	 * @Title: getPictureAlbums
	 * @Description: 获取模块数据
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/getPictures")
	@ResponseBody
	public String  getPictures(
			@RequestParam Integer pictureAlbumId,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.debug("--进入getPictures 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			Picture pic = new Picture();
			pic.setPictureAlbumId(pictureAlbumId);
			List<Picture> pictures = iPictureService.queryAll(pic);			
			
			reponseJsonData.addData("pictures", pictures);
		}catch(Exception e){
			
			logger.error(e);
			reponseJsonData.setCode(0);
		}
		
		return reponseJsonData.generateResultStr();
	}
	
	/**
	 * 
	 * @Title: getPictureAlbums
	 * @Description: 获取单个图册
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/getPictureAlbum")
	@ResponseBody
	public String  getPictureAlbum(
			@RequestParam Integer albumId,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.debug("--进入getPictureAlbum 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			PictureAlbum pictureAlbum = new PictureAlbum();
			pictureAlbum.setAlbumId(albumId);;
			pictureAlbum = iPictureAlbumService.queryOne(pictureAlbum);			
			
			reponseJsonData.addData("pictureAlbum", pictureAlbum);
		}catch(Exception e){
			
			logger.error(e);
			reponseJsonData.setCode(0);
		}
		
		return reponseJsonData.generateResultStr();
	}
	/**
	 * 
	 * @Title: modifyPictureAlbum
	 * @Description: 修改图册
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/modifyPictureAlbum")
	@ResponseBody
	public String  modifyPictureAlbum(
			PictureAlbum pictureAlbum,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.debug("--进入getPictureAlbum 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			if(pictureAlbum.getAlbumId() == null){
				pictureAlbum.setAlbumStatus(0);
				Date date = new Date();
				pictureAlbum.setCreateTime(date);
				// 操作记录
				User user = SessionUtils.getCurrentLoginUser(request);
				pictureAlbum.setOperatorId(user.getUserId());
				pictureAlbum.setOperatorName(user.getRealName());
				iPictureAlbumService.add(pictureAlbum);
			}else{
				PictureAlbum tempPictureAlbum = new PictureAlbum();
				tempPictureAlbum.setAlbumId(pictureAlbum.getAlbumId());
				tempPictureAlbum = iPictureAlbumService.queryOne(tempPictureAlbum);			
				//赋值
				tempPictureAlbum.setAlbumName(pictureAlbum.getAlbumName());
				iPictureAlbumService.updateByPrimaryKey(pictureAlbum);
			}
			
		}catch(Exception e){
			
			logger.error(e);
			reponseJsonData.setCode(0);
		}
		
		return reponseJsonData.generateResultStr();
	}
	
	
	/*************************************************************************文章的  新增  编辑 删除  置顶 （开始）*****************************************************************************/
	/**
	 * 
	* @Title: toArticleEditOrView 
	* @Description: TODO(根据ID查找文章) 
	*@author (wangkang@sxw100.com)
	* @param @param articles
	* @param @param flag
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/toArticleEditOrView")
	public String toArticleEditOrView(
			Article articles,
			@RequestParam(value="flag",required=false) Integer flag,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model ){
		logger.debug("--进入toArticleEditOrView 方法");
		model.addAttribute("article", iArticleService.queryOne(articles));
			return "/website/manage/pop/addArticlePop";///DL-wms-web/src/main/webapp/jsp/template/manage/pop/addArticlePop.jsp
		}
	
	/**
	 * 
	* @Title: addArticle 
	* @Description: TODO(添加文章相关内容) 
	*@author (wangkang@sxw100.com)
	* @param @param article
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/addArticle")
	public String addArticle(
			Article article,
			@RequestParam(value = "articleTypeId", required = false) List<String> articleTypeIds,
			HttpServletResponse response, 
			HttpServletRequest request,
			Model model,HttpSession session) {
		logger.debug("--进入addArticle 方法");
		String reasult = "success";
		//获取website
		Website website = SessionUtils.getWebsite(session);
		Integer siteId = website.getSiteId();
		User loginUser = SessionUtils.getCurrentLoginUser(request);
		article.setOperatorId(loginUser.getUserId());
		article.setOperatorName(loginUser.getUserName());
		try {
		if(article.getArticleId() == null){
			article.setSiteId(siteId);
			article.setArticleSize(Integer.valueOf(0));//文章大小默认为0
			article.setDataStatus(Integer.valueOf(0));
			article.setArticleTop(Integer.valueOf(1));//在页面中添加文章时，默认置顶	
			article.setArticleReview(Integer.valueOf(0));
			article.setCreateTime(new Date());
			article.setUpdateTime(new Date());
			
			if(article.getArticleWordNum()>=7){
				article.setArticleWordNum(article.getArticleWordNum()-Integer.valueOf(7));
			}else{
				article.setArticleWordNum(Integer.valueOf(0));
			}
			//添加文章
			
			//在页面中添加时，回归已审核状态
			article.setArticleReview(Integer.valueOf(1));
			iArticleService.addArticle(article);
		}else{
			article.setUpdateTime(new Date());
			if(article.getArticleWordNum()>=7){
				article.setArticleWordNum(article.getArticleWordNum()-Integer.valueOf(7));
			}else{
				article.setArticleWordNum(Integer.valueOf(0));
			}

			//回归审核状态
			article.setArticleReview(Integer.valueOf(1));
			
			iArticleService.updateByPrimaryKeySelective(article);
			//文章类型发生变化 修改文章类型关联
			//删除原有关系
			ArticleRelType art = new ArticleRelType();
			art.setArticleId(article.getArticleId());
			iArticleRelTypeService.delete(art);
		}
		//添加文章关联类型
			if(articleTypeIds !=null && articleTypeIds.size() > 0){
				
				for (String articleTypeId : articleTypeIds) {
					ArticleRelType articleRelType =new ArticleRelType();
					articleRelType.setSiteId(siteId);
					articleRelType.setArticleId(article.getArticleId());
					articleRelType.setArticleTypeId(Integer.valueOf(articleTypeId));
					iArticleRelTypeService.addSelective(articleRelType);
				}
			}
		} catch (Exception e) {
			reasult = "fail";
			e.printStackTrace();
		}
		model.addAttribute("reasult", reasult);
		request.setAttribute("msg", "window.parent.location.reload();window.parent.layer.closeAll();");
		return "/common/commonRefresh";
	}
	
	
	
	/**
	 * 
	* @Title: editUpdateArticle 
	* @Description: TODO(批量修改文章) 
	*@author (wangkang@sxw100.com)
	* @param @param article
	* @param @param articleIdsStr
	* @param @param editTime
	* @param @param request
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/editUpdateArticle")
	public void editUpdateArticle(
			Article article,
			@RequestParam(value="articleIdsStr",required=false) String  articleIdsStr,
			@RequestParam(value = "editTime", required = false) String editTime,
			HttpServletRequest request,
			HttpServletResponse response){
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		int rst = 0;
		
		try {
			if(StringUtils.isNotBlank(editTime)){article.setCreateTime(DateUtils.parseDate(editTime, "yyyy-MM-dd"));}
			List<Integer> articleIds = new ArrayList<Integer>();
			String[] str = articleIdsStr.split(",");
			for (String st : str) {
				if(StringUtils.isNoneBlank(st)){
					articleIds.add(Integer.parseInt(st));
				}
			}
			article.setArticleIds(articleIds);
			
			//审核  赋值为1
			if(article.getArticleReview()==null||article.getArticleReview()!=1){
				article.setArticleReview(1);
			}
			article.setUpdateTime(new Date());
			iArticleService.bathUpdateArticle(article);
			
			//若果是删除操作，article。dataStatus为1即删除文章关联
			if(article!=null&&article.getDataStatus()!=null&&article.getDataStatus()==1){
				iArticleRelTypeService.bathDelByArticleIds(articleIds);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rst = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSON(response, returnMap);
	}
		/*************公共的查找dataColumnId****************/	
	/**
	 * 
	* @Title: selectDateColumnId 
	* @Description: TODO(根据moduleId查找dataColumnId) 
	*@author (wangkang@sxw100.com)
	* @param @param templateModuleRelContent
	* @param @param request
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/selectDateColumnId")
	public void selectDateColumnId(
			TemplateModuleRelContent templateModuleRelContent,
			HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<TemplateModuleRelContent> templateModuleRelContents = iTemplateModuleRelContentService.queryAll(templateModuleRelContent);
		returnMap.put("templateModuleRelContents", templateModuleRelContents);
		printJSON(response, returnMap);
		
	}
	
	
/*************************************************************************文章的编辑 删除  置顶   （结束）*****************************************************************************/	
/*************************************************************************视频的编辑 删除  置顶   （开始）*****************************************************************************/
/**
 * 
* @Title: toMediaEditOrView 
* @Description: TODO(查找视频) 
*@author (wangkang@sxw100.com)
* @param @param mediaRecord
* @param @param response
* @param @param request
* @param @param model
* @param @return    设定文件 
* @return String    返回类型 
* @throws
 */
	@RequestMapping("/toMediaEditOrView")
	public String  toMediaEditOrView(
			Media mediaRecord,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) {
		Media media = iMediaService.queryOne(mediaRecord);
		model.addAttribute("media", media);
			return "/website/manage/pop/addMediaPop";
	}
	/**
	 * 
	* @Title: editMediaProperties 
	* @Description: TODO(批量编辑视频内容) 
	*@author (wangkang@sxw100.com)
	* @param @param media
	* @param @param mediaIdsStr
	* @param @param editTime
	* @param @param response
	* @param @param request
	* @param @param session    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/editMediaProperties")
	public void editMediaProperties(
			Media media,
			@RequestParam(value = "mediaIdsStr", required = false) String mediaIdsStr,
			@RequestParam(value = "editTime", required = false) String editTime,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = 0;
		User loginUser = SessionUtils.getCurrentLoginUser(request);
		media.setOperatorId(loginUser.getUserId());
		media.setOperatorName(loginUser.getUserName());
		try {
			if(StringUtils.isNotBlank(editTime)){media.setCreateTime(DateUtils.parseDate(editTime, "yyyy-MM-dd"));}
			List<Integer>  mediaIds = new ArrayList<Integer>();
			String[] str = mediaIdsStr.split(",");
			for(String s : str){
				if(StringUtils.isNoneBlank(s)){
					mediaIds.add(Integer.parseInt(s));
				}
			}
			media.setMediaIds(mediaIds);
			
			//默认为审核1
			if(media.getIsReview()==null||media.getIsReview()!=1){
				media.setIsReview(Integer.valueOf(1));
			}
			media.setUpdateTime(new Date());
			iMediaService.batchUpdateMedia(media);
			//若果是删除操作，media。dataStatus为1即删除文章关联
			if(media!=null&&media.getDataStatus()!=null&&media.getDataStatus()==1){
				iMediaRelTypeService.bathDelByMediaIds(mediaIds);
			}
		} catch (Exception e) {
			 rst = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSONWithDateFormat(response, returnMap);
	}
	/**
	 * 
	* @Title: addOrEditMedia 
	* @Description: TODO(添加视频或者修改视频) 
	*@author (wangkang@sxw100.com)
	* @param @param media
	* @param @param mediaTypeIds
	* @param @param response
	* @param @param request
	* @param @param model
	* @param @param session
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/addOrEditMedia")
	public String  addOrEditMedia(
			Media media,
			@RequestParam(value = "mediaTypeId", required = false) List<String> mediaTypeIds,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model,HttpSession session) {
		String reasult = "success";
		User loginUser = SessionUtils.getCurrentLoginUser(request);
		media.setOperatorId(loginUser.getUserId());
		media.setOperatorName(loginUser.getUserName());
		media.setUpdateTime(new Date());
		media.setIsReview(Integer.valueOf(0));
		try {
			if(media.getMediaId() == null){
				media.setDataStatus(Integer.valueOf(0));
				media.setCreateTime(new Date());
				media.setIsTop(Integer.valueOf(1));//在页面中添加文章时，默认置顶
				media.setIsTop(Integer.valueOf(1));//在页面中添加文章时，默认审核
				//添加视频
				iMediaService.addMedia(media);
			}else{
				// 编辑文章时 默认审核
				media.setIsReview(Integer.valueOf(1));
				iMediaService.updateByPrimaryKeySelective(media);
				//删除原有关联关系
				MediaRelType mrt =  new MediaRelType();
				 mrt.setMediaId(media.getMediaId());
				iMediaRelTypeService.delete(mrt);
			}
			//添加视频关联类型
			if(media.getMediaId() != null && mediaTypeIds != null && mediaTypeIds.size() > 0){
				MediaRelType mrt = null;
				for(String mediaTypeId : mediaTypeIds ){
					 mrt = new MediaRelType();
					 mrt.setMediaId(media.getMediaId());
					 mrt.setMediaTypeId(Integer.parseInt(mediaTypeId));
					 iMediaRelTypeService.addSelective(mrt);
				}
			}
			
		} catch (Exception e) {
			reasult = "fail";
			e.printStackTrace();
		}
		model.addAttribute("reasult", reasult);
		request.setAttribute("msg", "window.parent.location.reload();window.parent.layer.closeAll();");
		return "/common/commonRefresh";
	}
/*************************************************************************视频的编辑 删除  置顶   （结束）*****************************************************************************/	
/********************************************图片的编辑 删除  置顶*********************************************************************/
	/**
	 * 
	* @Title: queryPictureIdFindAlbumId 
	* @Description: TODO(通过图片Id查找图册ID) 
	*@author (wangkang@sxw100.com)
	* @param @param picture
	* @param @param pictureId
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @param session    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value= "/queryPictureIdFindAlbumId")
	public void queryPictureIdFindAlbumId(
			Picture picture,
			@RequestParam(value = "pictureId", required = false) Integer pictureId ,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			HttpSession session){
		logger.debug("--进入queryPictureIdFindAlbumId 方法");
		Map<String, Object> returnMap = new HashMap<String,Object>();
		returnMap.put("albumId", iPictureService.queryByPrimaryKey(pictureId).getPictureAlbumId());
		printJSON(response, returnMap);
	}
	/**
	 * 
	* @Title: selectTemplateModule 
	* @Description: TODO(根据ID查询模块) 
	*@author (wangkang@sxw100.com)
	* @param @param templateModules
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/selectTemplateModule")
	public String selectTemplateModule(
			TemplateModule templateModules,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,HttpSession session){
		templateModules.setSiteId(SessionUtils.getWebsiteId(session));
		model.addAttribute("templateModule",iTemplateModuleService.queryByPrimaryKey(templateModules.getModuleId()));
		return "/website/manage/pop/modulePop";
		}
	
	/**
	 * 
	* @Title: addTemplateModuleVo 
	* @Description: TODO(页面中模块的编辑) 
	*@author (wangkang@sxw100.com)
	* @param @param templateModuleVo
	* @param @param articleTypeIds
	* @param @param mediaTypeIds
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @param session
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/addTemplateModuleVo")
	public String addTemplateModuleVo(
			TemplateModuleVo templateModuleVo,
			@RequestParam(value = "articleTypeId", required = false) List<String> articleTypeIds,
			@RequestParam(value = "albumTypeId", required = false) List<String> albumTypeIds,
			@RequestParam(value = "mediaTypeId", required = false) List<String> mediaTypeIds,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			HttpSession session){
		String reasult = "success";
		try {
			Integer dataType = 0;
			Integer siteId = SessionUtils.getWebsiteId(session);
			//修改dataColumnId
			if(mediaTypeIds != null && mediaTypeIds.size() > 0){
				TemplateModuleRelContent tmrc = null;
				for(String mediaTypeId : mediaTypeIds ){
					tmrc = new TemplateModuleRelContent();
					tmrc.setSiteId(siteId);
					tmrc.setModuleId(templateModuleVo.getModuleId());
					tmrc = iTemplateModuleRelContentService.queryOne(tmrc);
					tmrc.setContentDataType(8);
					// 如果新添加情况，则设置默认目标物理表，这里是视频类别
					if(StringUtils.isBlank(tmrc.getDataTargetTable())){
						tmrc.setDataTargetTable("wms_media_type");
					}
					tmrc.setDataColumnId(Integer.parseInt(mediaTypeId));
					iTemplateModuleRelContentService.updateByPrimaryKeySelective(tmrc);
				}
				dataType = 8;
			};
			if(articleTypeIds != null && articleTypeIds.size() > 0){
				TemplateModuleRelContent tmrc = null;
				for(String articleTypeId : articleTypeIds ){
					tmrc = new TemplateModuleRelContent();
					tmrc.setSiteId(siteId);
					tmrc.setModuleId(templateModuleVo.getModuleId());
					tmrc = iTemplateModuleRelContentService.queryOne(tmrc);
					tmrc.setContentDataType(1);
					// 如果新添加情况，则设置默认目标物理表，这里是文章类别
					if(StringUtils.isBlank(tmrc.getDataTargetTable())){
						tmrc.setDataTargetTable("wms_article_type");
					}
					tmrc.setDataColumnId(Integer.parseInt(articleTypeId));
					iTemplateModuleRelContentService.updateByPrimaryKeySelective(tmrc);
				}
				dataType = 1;
			};
			// 更新模块儿
			TemplateModule templateModule = new TemplateModule();
			templateModule.setSiteId(siteId);
			templateModule.setModuleId(templateModuleVo.getModuleId());
			templateModule.setModuleName(templateModuleVo.getModuleName());
			
			// 对于新添加模块儿要加默认模板
			if(StringUtils.isBlank(templateModule.getModuleTmpl()) || templateModule.getModuleTmpl().equals("module_add_tmpl")){
				templateModule.setShowTitile(1);
				switch(dataType){
				  case 1:
					  templateModule.setModuleItemLineNum(20);
					  templateModule.setModuleItemColumnNum(1);
					  templateModule.setModuleTmpl("module_imgtxt_list_tmpl");
					  break;
				  case 8:
					  templateModule.setModuleItemLineNum(8);
					  templateModule.setModuleItemColumnNum(1);
					  templateModule.setModuleTmpl("module_productinfo_list_tmpl");
					  break;
				}
			}
			iTemplateModuleService.updateByPrimaryKeySelective(templateModule);
		} catch (NumberFormatException e) {
			reasult = "fail";
			e.printStackTrace();
		}
		model.addAttribute("reasult", reasult);
		request.setAttribute("msg", "window.parent.Template.reloadModule({moduleId:"+templateModuleVo.getModuleId()+"});window.parent.layer.closeAll();");
		return "/common/commonRefresh";
		}
	
	
	/**
	 * 
	* @Title: editPictureAlbumProperties 
	* @Description: TODO(批量修改图册) 
	*@author (wangkang@sxw100.com)
	* @param @param picture
	* @param @param 
	* @param @param editTime
	* @param @param request
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/editPictureAlbumProperties")
	public void editPictureAlbumProperties(
			Picture picture,
			@RequestParam(value="albumRelTypeStatus",required=false) String  albumRelTypeStatus,
			@RequestParam(value="moduleId",required=false) String  moduleId,
			@RequestParam(value = "editTime", required = false) String editTime,
			HttpServletRequest request,
			HttpServletResponse response){
		logger.debug("--进入editPictureAlbumProperties 方法");
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		int rst = 0;
		
		try {
			if(StringUtils.isNotBlank(editTime)){picture.setCreateTime(DateUtils.parseDate(editTime, "yyyy-MM-dd"));}
			/*  批量编辑暂时么有  
			 * List<Integer> pictureIds = new ArrayList<Integer>();
			String[] str = pictureIdsStr.split(",");
			for (String st : str) {
				if(StringUtils.isNoneBlank(st)){
					pictureIds.add(Integer.parseInt(st));
				}
			}
			picture.setPictureIds(pictureIds);*/
			
			//审核  赋值为1
			if(picture.getIsReview() == null || picture.getIsReview() != 1){
				picture.setIsReview(1);
			}
			picture.setUpdateTime(new Date());
			PictureAlbum pictureAlbum = new PictureAlbum();
			pictureAlbum.setAlbumId(iPictureService.queryByPrimaryKey(picture.getPictureId()).getPictureAlbumId());
			pictureAlbum.setUpdateTime(new Date());
			iPictureAlbumService.updateByPrimaryKeySelective(pictureAlbum);
			iPictureService.updateByPrimaryKeySelective(picture);
			
			//若果是删除操作，article。dataStatus为1即删除文章关联
			if(picture != null && albumRelTypeStatus !=null && Integer.valueOf(albumRelTypeStatus) == 1){
				  TemplateModuleRelContent tmrc =new TemplateModuleRelContent();
				  tmrc.setModuleId(Integer.valueOf(moduleId));
				  PictureAlbumRelType part= new PictureAlbumRelType();
				  //获取albumId
				  part.setAlbumId( iPictureService.queryByPrimaryKey(picture.getPictureId()).getPictureAlbumId());
				  //获取albumtypeId
				  part.setAlbumTypeId( iTemplateModuleRelContentService.queryOne(tmrc).getDataColumnId());
				iPictureAlbumRelTypeService.delete(part);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rst = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSON(response, returnMap);
	}
	
	
	
	
	
	/**
	 * 
	 * @Title: addFooterNav
	 * @Description: 持久站脚nav修改
	 *@author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/addFooterNav")
	@ResponseBody
	public String  addFooterNav(
			TemplateColumns templateColumns,
			@RequestParam Integer footerId,
			@RequestParam Integer templateId,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) {
		
		logger.debug("--进入addFooterNav 方法");
		
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		
		try{
			Integer siteId = SessionUtils.getWebsiteId(session);
			templateColumns.setSiteId(siteId);
			iTemplateColumnsService.addGetprimaryKey(templateColumns);
			// 设置一些别要属性
			templateColumns.setCustomUrl("/site/loadWebsitePage.do?templateId="+templateId+"&columnsId=" + templateColumns.getColumnsId() );
			iTemplateColumnsService.updateByPrimaryKeySelective(templateColumns);			
			// 更新排序，隐藏，删除操作
			TemplateRelColumns templateRelColumns = new TemplateRelColumns();
			templateRelColumns.setColumnsId(templateColumns.getColumnsId());
			templateRelColumns.setTemplateId(templateId);
			templateRelColumns.setSiteId(siteId);
			templateRelColumns.setSortNum(templateColumns.getSortNum());
			templateRelColumns.setColumnsHide(1);
			iTemplateRelColumnsService.addSelective(templateRelColumns);
			
			// 设置预定义的模块组
			iTemplateModuleService.addModuleForTemplateColumns(templateRelColumns);			
			
			// 更新排序，隐藏，删除操作
			TemplateFooterRelColumns templateFooterRelColumns = new TemplateFooterRelColumns();
			templateFooterRelColumns.setSiteId(siteId);
			templateFooterRelColumns.setColumnsId(templateColumns.getColumnsId());
			templateFooterRelColumns.setTemplateFooterId(footerId);
			templateFooterRelColumns.setSortNum(templateColumns.getSortNum());
			templateFooterRelColumns.setColumnsHide(templateColumns.getColumnsHide());
			iTemplateFooterRelColumnsService.add(templateFooterRelColumns);
			
			templateColumns.setColumnsTypeName("自定义菜单");
			reponseJsonData.addData("templateColumns", templateColumns);
		}catch(Exception e){
			
			logger.error(e);
			reponseJsonData.setCode(0);
			
		}
		
		return reponseJsonData.generateResultStr();
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
		return "/website/contentPage";//DL-wms-web/src/main/webapp/jsp/
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
				returnMap.put("contentData", iPictureService.queryOne(picture));
				break;
				
		}
		printJSON(response, returnMap);
	}
	
	
	@RequestMapping(value = "/executeSync")
	public void executeSync(
			Website website,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session){
		long t1 = new Date().getTime();
		logger.debug("--进入executeSync 方法");
		iTemplateService.executeSync(website);
		long t2 = new Date().getTime();
		System.out.println(t2-t1);
	}
	
	@RequestMapping(value = "/getWebsiteInfoByTemplate")
	@ResponseBody
	public TemplateRelColumns  getWebsiteInfoByTemplate(
			Template template, 
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) {
			template.setSiteId(SessionUtils.getWebsiteId(session));
			List<TemplateRelColumns> data = new ArrayList<>();
			if(template.getTemplateId()!=null){
				data=iTemplateService.queryTemplateRelColumnsByCondition(template);
			}
			return data.get(0);
	}
}