package com.assp.modules.template.web;

import java.text.SimpleDateFormat;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.constant.SystemConstants;
import com.assp.common.upload.Upload;
import com.assp.common.utils.DateUtils;
import com.assp.common.web.BaseController;
import com.assp.modules.commondata.entity.Article;
import com.assp.modules.commondata.entity.ArticleType;
import com.assp.modules.commondata.entity.Color;
import com.assp.modules.commondata.entity.Media;
import com.assp.modules.commondata.entity.MediaType;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.assp.modules.commondata.entity.PictureAlbumType;
import com.assp.modules.commondata.service.IArticleService;
import com.assp.modules.commondata.service.IArticleTypeService;
import com.assp.modules.commondata.service.IColorService;
import com.assp.modules.commondata.service.IMediaService;
import com.assp.modules.commondata.service.IMediaTypeService;
import com.assp.modules.commondata.service.IPictureAlbumService;
import com.assp.modules.commondata.service.IPictureAlbumTypeService;
import com.assp.modules.commondata.service.IPictureService;
import com.assp.modules.config.entity.ColumnsType;
import com.assp.modules.config.entity.IndustryType;
import com.assp.modules.config.service.IColumnsTypeService;
import com.assp.modules.config.service.IIndustryTypeService;
import com.assp.modules.frame.entity.Frame;
import com.assp.modules.frame.entity.framesRefFrame;
import com.assp.modules.frame.service.IFramesRefFrameService;
import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.module.entity.TemplateModuleRelContent;
import com.assp.modules.module.service.ITemplateModuleRelContentService;
import com.assp.modules.module.service.ITemplateModuleService;
import com.assp.modules.sys.entity.User;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.entity.TemplateColumns;
import com.assp.modules.template.entity.TemplateColumnsRelFrame;
import com.assp.modules.template.entity.TemplateFooter;
import com.assp.modules.template.entity.TemplateFrameRelModule;
import com.assp.modules.template.entity.TemplateLogo;
import com.assp.modules.template.entity.TemplateRelColumns;
import com.assp.modules.template.service.IFrameService;
import com.assp.modules.template.service.ITemplateColumnsRelFrameService;
import com.assp.modules.template.service.ITemplateColumnsService;
import com.assp.modules.template.service.ITemplateFooterService;
import com.assp.modules.template.service.ITemplateFrameRelModuleService;
import com.assp.modules.template.service.ITemplateLogoService;
import com.assp.modules.template.service.ITemplateRelColumnsService;
import com.assp.modules.template.service.ITemplateService;
import com.assp.modules.template.vo.ModuleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
	private ITemplateModuleRelContentService templateModuleRelContentService;
	
	@Autowired
	private IIndustryTypeService iIndustryTypeService;
	
	@Autowired
	private ITemplateLogoService iTemplateLogoService;
	
	//@Autowired
	//private IBannerService iBannerServicel;
	
	@Autowired
	private IColorService iColorService;
	
	@Autowired
	private IColumnsTypeService iColumnsTypeService;
	
	@Autowired
	private ITemplateRelColumnsService iTemplateRelColumnsService;
	
	@Autowired
	private IPictureService pictureService;
	
	@Autowired
	private IPictureAlbumService pictureAlbumService;
	
	@Autowired
	private IPictureAlbumTypeService pictureAlbumTypeService;
	
	@Autowired
	private IMediaService mediaService;
	
	@Autowired
	private IMediaTypeService mediaTypeService;
	
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IArticleTypeService articleTypeService;
	
	@Autowired
	private IFrameService iFrameService;
	
	@Autowired
	private ITemplateColumnsRelFrameService iTemplateColumnsRelFrameService;
	
	@Autowired
	private ITemplateFrameRelModuleService iTemplateFrameRelModuleService;
	
	@Autowired
	private IFramesRefFrameService iFramesRefFrameService;

	/**
	 * zhangtl
	 * 添加模板
	 * @param tempalte
	 * @param columnsId
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addTemplate")
	public Map<String,Object>  addTemplate(
			Template template,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model) {
		Map<String,Object> map=new HashMap<String,Object>();
		String templateCode = "10000";
		//获取最大编码加1
		Template tmpl = iTemplateService.queryMaxTemplateCode(template);
		if(tmpl != null){
			 templateCode = tmpl.getTemplateCode();
		}
		int templateCodeInt = Integer.parseInt(templateCode)+1;
		//添加模板
		template.setTemplateCode(templateCodeInt+"");
		template.setCreateTime(DateUtils.getCurrentDate());
		template.setUpdateTime(DateUtils.getCurrentDate());
		template.setDataStatus(2);
		int i = iTemplateService.insertKey(template);
		//创建上传文件存放目录
		Upload.autoCreateDirect("", templateCodeInt+"/images");
		Upload.autoCreateDirect("", templateCodeInt+"/medias");
		Upload.autoCreateDirect("", templateCodeInt+"/css");
		System.out.println("主键ID:"+template.getTemplateId());
		if(i==0){
			map.put("res", "0");
		}else{
			map.put("res", "1");
			map.put("templateId", template.getTemplateId());
			map.put("templateCode", template.getTemplateCode());
		}
		return map;
	}
	
	/**
	 * 添加资源目录
	 * @param template
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addTemplateResour")
	public Map<String,Object> addTemplateResour(Template template,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		template.setCreateTime(DateUtils.getCurrentDate());
		template.setUpdateTime(DateUtils.getCurrentDate());
		template.setDataStatus(2);
		Map<String,Object> map=new HashMap<String,Object>();
		int i = iTemplateService.updateByPrimaryKeySelective(template);
		if(i==0){
			map.put("res", "0");
		}else{
			map.put("res", "1");
			map.put("templateId", template.getTemplateId());
		}
		return map;
	}
	
	/**
	 * 查询模板列表
	 * @param template
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryTemplateList")
	public Map<String,Object> queryTemplateList(Template template,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		Map<String,Object> map=new HashMap<String,Object>();
		//template.setDataStatus(0);
		List<Template>  templateList = iTemplateService.queryByExample(template);
		int count = iTemplateService.queryCountByExample(template);
		map.put("total", count);
		map.put("rows", templateList);
		return map;
	}
	
	/**
	 * 检查模板下是否有导航
	 * @param templateId
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkTemplateIsHavCols")
	public Map<String,Object> checkTemplateIsHavCols(Integer templateId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		Map<String,Object> map=new HashMap<String,Object>();
		TemplateRelColumns trc = new TemplateRelColumns();
		trc.setTemplateId(templateId);
		List<TemplateRelColumns>  templateList = iTemplateRelColumnsService.queryAll(trc);
		if(templateList!=null && templateList.size()>0){
			map.put("res", 1);
		}else{
			map.put("res", 0);
		}
		return map;
	}
	
	/**
	 * 添加导航
	 * @param templateId
	 * @param columns
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addColumns")
	public Map<String,Object> addColumns(Integer templateId,
			TemplateColumns columns,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		Map<String,Object> map=new HashMap<String,Object>();
		//添加菜单
		iTemplateColumnsService.addGetprimaryKey(columns);
		
		//建立模板和菜单关系
		TemplateRelColumns relCol = iTemplateRelColumnsService.querySortNumMaxObj(templateId);
		TemplateRelColumns trc = new TemplateRelColumns();
		trc.setTemplateId(templateId);
		trc.setColumnsId(columns.getColumnsId());
		trc.setRelStatus(0);
		if(relCol==null){
			trc.setSortNum(1);
		}else{
			trc.setSortNum(relCol.getSortNum()+1);
		}
		int k = iTemplateRelColumnsService.addSelective(trc);
		if(k>0){
			logger.info("---添加导航成功---");
			map.put("res", 1);
		}else{
			logger.info("---添加导航失败---");
			map.put("res", 0);
		}
		TemplateColumns tcol = iTemplateColumnsService.queryByPrimaryKey(columns.getColumnsId());
		//是否使用自定义框架 1使用
		if(tcol.getIsUseFrames()!=null && tcol.getIsUseFrames()==1){
			framesRefFrame frf = new framesRefFrame();
			frf.setFramesId(tcol.getFramesId());
			List<framesRefFrame> frfList = iFramesRefFrameService.queryAll(frf);
			for (int i = 0; i < frfList.size(); i++) {
				TemplateColumnsRelFrame tcFrame = new TemplateColumnsRelFrame();
				frf=frfList.get(i);
				tcFrame.setColumnsRelFramePid(0);
				tcFrame.setColumnsId(columns.getColumnsId());
				tcFrame.setFrameId(frf.getFrameId());
				tcFrame.setSortNum(frf.getSortNum());
				tcFrame.setRelStatus(0);
				iTemplateColumnsRelFrameService.addSelective(tcFrame);
			}
		}
		return map; 
	}
	
	/**
	 * 查询导航列表
	 * @param template
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryColumnsList")
	public Map<String,Object> queryColumnsList(Template template,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,ModelMap model){
		Map<String,Object> map=new HashMap<String,Object>();
		List<TemplateColumns> tcList = new ArrayList<>();
		if(template.getTemplateId()!=null){
			template = iTemplateService.queryByPrimaryKey(template.getTemplateId());
			tcList = iTemplateColumnsService.queryTemplateColumnsByTemplateId(template.getTemplateId());
		}else{
			TemplateColumns tc = new TemplateColumns();
			tc.setDataStatus(0);
			tcList = iTemplateColumnsService.queryAll(tc);
		}
		map.put("template", template);
		map.put("tcList", tcList);
		return map;
	}
	
	/**
	 * 根据导航查询模块
	 * @param columnsId
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryFrameByColumnsId")
	public Map<String,Object> queryFrameByColumnsId(Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,ModelMap model){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Frame> frameList = iFrameService.queryFrameByColumnsId(columnsId);
		map.put("frameList", frameList);
		return map;
	}
	
	/**
	 * 查询模块列表
	 * @param frame
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryFrameList")
	public Map<String,Object> queryFrameList(Frame frame,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,ModelMap model){
		Map<String,Object> map=new HashMap<String,Object>();
		frame.setDataStatus(0);
		List<Frame> frameList = iFrameService.queryAll(frame);
		int count = iFrameService.queryCountByExample(frame);
		map.put("total", count);
		map.put("rows", frameList);
		return map;
	}
	
	/**
	 * 建立导航与模块的关系
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addColumnsRelFrame")
	public Map<String, Object> addColumnsRelFrame(String frameIds,
			Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,ModelMap model){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] frameIdAry = frameIds.split(",");
		TemplateColumnsRelFrame relFrame = iTemplateColumnsRelFrameService.querySortNumMaxObj(columnsId);
		for (int i = 0; i < frameIdAry.length; i++) {
			TemplateColumnsRelFrame crf = new TemplateColumnsRelFrame();
			crf.setFrameId(Integer.parseInt(frameIdAry[i]));
			crf.setColumnsId(columnsId);
			crf.setColumnsRelFramePid(0);
			crf.setRelStatus(0);
			if(relFrame==null){
				crf.setSortNum(1);
			}else{
				crf.setSortNum(relFrame.getSortNum()+1);
			}
			int k =iTemplateColumnsRelFrameService.addSelective(crf);
			if(k==1){
				map.put("res", 1);
			}else{
				map.put("res", 0);
			}
		}
		return map;
	}
	
	/**
	 * 发布模板
	 * @param templateIds
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/releaseTemplate")
	public Map<String, Object> releaseTemplate(String templateIds,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,ModelMap model){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] templateIdAry = templateIds.split(",");
		for (int i = 0; i < templateIdAry.length; i++) {
			Template template = new Template();
			template.setTemplateId(Integer.parseInt(templateIdAry[i]));
			template.setDataStatus(0);
			int k = iTemplateService.updateByPrimaryKeySelective(template);
			if(k==1){
				map.put("res", 1);
			}else{
				map.put("res", 0);
			}
		}	
		return map;
	}
	
	/**
	 * 
	* @Title: queryPageFrame 
	* @Description: 加载页面框架信息
	* @author (zhangtl)
	* @param @param columnsId
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/queryPageFrame")
	public void  queryPageFrame(
			@RequestParam(value = "columnsId", required = false) Integer columnsId,
			HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("--进入queryPageFrame 方法");
		Map<String , Object>  returnMap = new HashMap<String , Object>();
		List<TemplateColumnsRelFrame>  TemplateColumnsRelFrames = iTemplateService.queryPageFrameInfo(columnsId);
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
	 * frame关联module
	 * zhangtl
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @param columnsRelFrameId  wms_template_columns_rel_frame表主键ID
	 * @param moduleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveFrameRelModule")
	public Map<String,Object> saveFrameRelModule(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			Integer columnsRelFrameId,
			Integer moduleId,
			Integer sort
			){
		Map<String,Object> map=new HashMap<String,Object>();
		//TemplateFrameRelModule relModule = iTemplateFrameRelModuleService.querySortNumMaxObj(columnsRelFrameId);
		TemplateFrameRelModule frm = new TemplateFrameRelModule();
		frm.setColumnsRelFrameId(columnsRelFrameId);
		frm.setModuleId(moduleId);
		/*if(relModule==null){
			frm.setSortNum(1);
		}else{
			frm.setSortNum(relModule.getSortNum()+1);
		}*/
		frm.setSortNum(sort);
		int k = iTemplateFrameRelModuleService.addSelective(frm);
		if(k==1){
			map.put("res", 1);
		}else{
			map.put("res", 0);
		}
		return map;
	}
	
	/**
	 * 查询模板类型
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @param dataStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryIndustryTypes")
	public Map<String,Object> queryIndustryTypes(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			Integer dataStatus){
		Map<String,Object> map=new HashMap<String,Object>();
		IndustryType indust = new IndustryType();
		indust.setData_status(dataStatus);
		List<IndustryType> industList = iIndustryTypeService.queryAll(indust);
		map.put("industList", industList);
		return map;
	}
	
	/**
	 * 查询模板logo
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryTemplateLogo")
	public Map<String,Object> queryTemplateLogo(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		Map<String,Object> map=new HashMap<String,Object>();
		TemplateLogo logo = new TemplateLogo();
		List<TemplateLogo> logoList = iTemplateLogoService.queryAll(logo);
		map.put("logoList", logoList);
		return map;
	}
	
	/**
	 * 查询banner图
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @param dataStatus
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value="/queryBanner")
	public Map<String,Object> queryBanner(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			Integer dataStatus){
		Map<String,Object> map=new HashMap<String,Object>();
		Banner banner = new Banner();
		banner.setDataStatus(dataStatus);
		List<Banner> bannerList = iBannerServicel.queryAll(banner);
		map.put("bannerList", bannerList);
		return map;
	}*/
	
	/**
	 * 查询页脚图
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @param dataStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryTemplateFooter")
	public Map<String,Object> queryTemplateFooter(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		Map<String,Object> map=new HashMap<String,Object>();
		TemplateFooter footer = new TemplateFooter();
		List<TemplateFooter> footerList = iTemplateFooterService.queryAll(footer);
		map.put("footerList", footerList);
		return map;
	}
	
	/**
	 * 查询颜色
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @param dataStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryColor")
	public Map<String,Object> queryColor(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			Integer dataStatus){
		Map<String,Object> map=new HashMap<String,Object>();
		Color color = new Color();
		color.setDataStatus(dataStatus);
		List<Color> colorList = iColorService.queryAll(color);
		map.put("colorList", colorList);
		return map;
	}
	
	/**
	 * 查询导航类型
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @param dataStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryColumnType")
	public Map<String,Object> queryColumnType(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			Integer dataStatus){
		Map<String,Object> map=new HashMap<String,Object>();
		ColumnsType coltype = new ColumnsType();
		coltype.setDataStatus(dataStatus);
		List<ColumnsType> coltypeList = iColumnsTypeService.queryAll(coltype);
		map.put("coltypeList", coltypeList);
		return map;
	}
	
	/**
	 * 查询所属父导航
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @param dataStatus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryTemplateColumn")
	public Map<String,Object> queryTemplateColumn(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			Integer dataStatus,
			Integer templateId){
		Map<String,Object> map=new HashMap<String,Object>();
		TemplateRelColumns trc = new TemplateRelColumns();
		trc.setTemplateId(templateId);
		trc.setRelStatus(0);
		List<TemplateRelColumns> trcList = iTemplateRelColumnsService.queryAll(trc);
		List<TemplateColumns> tempcolumnList = new ArrayList<TemplateColumns>();
		for (int i = 0; i < trcList.size(); i++) {
			TemplateColumns tempcolumn = new TemplateColumns();
			tempcolumn.setDataStatus(dataStatus);
			tempcolumn.setColumnsId(trcList.get(i).getColumnsId());
			TemplateColumns  tc = iTemplateColumnsService.queryOne(tempcolumn);
			tempcolumnList.add(tc);
		}
		map.put("tempcolumnList", tempcolumnList);
		return map;
	}
	
	/**
	 * 
	* @Title: queryPModules 
	* @Description: 查询所有父模块
	* @author (panlinlin@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @param dataStatu
	* @param @return    设定文件 
	* @return List<TemplateModule>    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/queryPModules")
	public List<TemplateModule> queryPModules(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			String dataStatu,
			Integer columnsRelFrameId,
			Integer sort){
		TemplateFrameRelModule frm = new TemplateFrameRelModule();
		frm.setColumnsRelFrameId(columnsRelFrameId);
		frm.setSortNum(sort);
		List<TemplateFrameRelModule> frmList = iTemplateFrameRelModuleService.queryAll(frm);
		List<TemplateModule> TemplateModuleList= new ArrayList<>();
		if(frmList!=null && frmList.size()>0){
			TemplateModule record=new TemplateModule();
			record.setDataStatus(Integer.valueOf(dataStatu));
			record.setModuleId(frmList.get(0).getModuleId());
			TemplateModuleList = iTemplateModuleService.queryAll(record);
		}
		return TemplateModuleList;
	}
	
	/**
	 * 
	* @Title: queryDataType 
	* @Description: 根据数据类型查询具体数据
	* @author (panlinlin@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @param dataStatu
	* @param @return    设定文件 
	* @return List<TemplateModule>    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/queryDataByType")
	public HashMap<String, Object> queryDataByType(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			String dataStatus,String dataType){
		HashMap<String, Object> data = new HashMap<String,Object>();
		//ArrayList<Object> rows=null;
		switch (Integer.valueOf(dataType)) {
		case 0:  //图片资源
			Picture picture = new Picture();
			picture.setDataStatus(Integer.valueOf(dataStatus));
			List<Picture> pictures = pictureService.queryAll(picture);
			int pictureCount = pictureService.queryCount(picture);
			data.put("rows", pictures);
			data.put("total", pictureCount);
			break;
		case 1: //图册资源
			PictureAlbum album = new PictureAlbum(); 
			List<PictureAlbum> pictureAlbumas = pictureAlbumService.queryAlbumAllAndReview(album);
			int albumCount = pictureAlbumService.queryCount(album);
			data.put("rows", pictureAlbumas);
			data.put("total", albumCount);
			break;
		case 2 : // 图册类型资源
			PictureAlbumType albumType = new PictureAlbumType();
			List<PictureAlbumType> pictureAlbumTypes = pictureAlbumTypeService.queryAll(albumType);
			int pictureAlbumTypeCount = pictureAlbumTypeService.queryCount(albumType);
			data.put("rows", pictureAlbumTypes);
			data.put("total", pictureAlbumTypeCount);
			break;
		case 3: //文章资源
			Article article=new Article();
			article.setDataStatus(Integer.valueOf(dataStatus));
			List<Article> articles = articleService.queryAll(article);
			int articleCount = articleService.queryCount(article);
			data.put("rows", articles);
			data.put("total", articleCount);
			break;
		case 4 : //文章类型资源
			ArticleType articleType = new ArticleType();
			articleType.setDataStatus(Integer.valueOf(dataStatus));
			List<ArticleType> articleTypes = articleTypeService.queryAll(articleType);
			int articleTypeCount = articleTypeService.queryCount(articleType);
			data.put("rows", articleTypes);
			data.put("total", articleTypeCount);
			break;
		case 5 : //视频资源
			Media media = new Media();
			media.setDataStatus(Integer.valueOf(dataStatus));
			List<Media> medias = mediaService.queryAll(media);
			int mediaConunt = mediaService.queryCount(media);
			data.put("rows", medias);
			data.put("total", mediaConunt);
			break;
		case 6 : //视频类型资源
			MediaType mediaType=new MediaType();
			mediaType.setDataStatus(Integer.valueOf(dataStatus));
			List<MediaType> mediaTypes = mediaTypeService.queryAll(mediaType);
			int mediaTypeCount = mediaTypeService.queryCount(mediaType);
			data.put("rows", mediaTypes);
			data.put("total", mediaTypeCount);
			break;
		case 7 : //logo
			TemplateLogo templateLogo=new TemplateLogo();
			List<TemplateLogo> templateLogos = iTemplateLogoService.queryAll(templateLogo);
			int templateLogoCount = iTemplateLogoService.queryCount(templateLogo);
			data.put("rows", templateLogos);
			data.put("total", templateLogoCount);
			break;	
		default: 
			break;
		}
		data.put("type", dataType);
		return data;
		
	}
	
	/*******************************************编辑板式开始************************************************/
	/**
	 * 
	* @Title: queryTemplateByPage 
	* @Description:    分页查询视频
	*@author (sdg@sxw100.com)
	* @param @param currentPage
	* @param @param pageSize
	* @param @param flag
	* @param @param sort
	* @param @param orderColum
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/queryTemplateByPage")
	public void queryTemplateByPage(
			Template template,
			@RequestParam(value = "page", required = false) Integer currentPage, 
			@RequestParam(value = "rows", required = false) Integer pageSize,
			@RequestParam(value = "typeId", required = false) Integer mediaTypeId,
			HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		PageHelper.startPage(currentPage, pageSize, true); //设置分页
		template.setDataStatus(0);
		List<Template> templates = iTemplateService.queryAll(template);
		PageInfo<Template> pageInfo = new PageInfo<Template>(templates);
		returnMap.put("currentPage", currentPage);
		returnMap.put("rows", templates);
		returnMap.put("pages", pageInfo.getPages());
		returnMap.put("total", pageInfo.getTotal());
		printJSONWithDateFormat(response, returnMap);
	}
	
	/**
	 * 
	* @Title: editMediaProperties 
	* @Description: 批量更新视频属性
	*@author (shx@sxw100.com)
	* @param @param media
	* @param @param mediaIds
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/editTemplateProperties")
	public void editTemplateProperties(
			Template template,
			@RequestParam(value = "templateIdsStr", required = false) String templateIdsStr,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = 0;
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null){
			template.setOperatorId(1);
			template.setOperatorName("admin");
		}else{
			template.setOperatorId(loginUser.getUserId());
			template.setOperatorName(loginUser.getUserName());
		}
		try {
			List<Integer>  templateIds = new ArrayList<Integer>();
			String[] str = templateIdsStr.split(",");
			for(String s : str){
				if(StringUtils.isNoneBlank(s)){
					templateIds.add(Integer.parseInt(s));
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String updateTime = sdf.format(new Date());
			template.setUpdateTime(DateUtils.parseDate(updateTime, "yyyy-MM-dd HH:mm:ss"));
			template.setTemplateIds(templateIds);
			iTemplateService.batchUpdateTemplate(template);
		} catch (Exception e) {
			 rst = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSONWithDateFormat(response, returnMap);
	}
	
	
	
	/**
	 * 
	* @Title: saveModule 
	* @Description:添加模块
	* @author (panlinlin@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @param module
	* @param @param moduleRelConten
	* @param @param dataStatu
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveModule")
	public Map<String, Object> saveModule(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			TemplateModule module,
			String dataStatu){
		module.setDataStatus(0);
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("tip", SystemConstants.TIP_FAIL);
		data.put("code", 0);
		 if(iTemplateModuleService.addModuleReturnId(module)>0){
			 data.put("tip", SystemConstants.TIP_SUCCESS);
			 data.put("code", 1);
			 data.put("Key", module.getModuleId());
		 }
		 return data;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveModuleRelContent")
	public Map<String, Object> saveModuleRelContent(HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model,
			TemplateModuleRelContent moduleRelConten,
			String dataStatu){
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("tip", SystemConstants.TIP_FAIL);
		data.put("code", 0);
		 if(templateModuleRelContentService.addSelective(moduleRelConten)>0){
				data.put("tip", SystemConstants.TIP_SUCCESS);
				data.put("code", 1);
		 }
		 return data;
	}
}