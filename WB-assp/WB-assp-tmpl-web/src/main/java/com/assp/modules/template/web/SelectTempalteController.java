package com.assp.modules.template.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assp.common.web.BaseController;
import com.assp.modules.common.SessionUtils;
import com.assp.modules.commondata.service.IColorService;
import com.assp.modules.commondata.service.IUserRelTemplateService;
import com.assp.modules.config.entity.IndustryType;
import com.assp.modules.config.service.IIndustryTypeService;
import com.assp.modules.sys.entity.User;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.entity.TemplateRelColumns;
import com.assp.modules.template.entity.UserRelTemplate;
import com.assp.modules.template.service.ITemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 类简述
 * <p>
 * 模板选择控制器
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangzhipeng@sxw100.com)
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/selectTemplate")
public class SelectTempalteController extends BaseController {

	@Autowired
	private ITemplateService iTemplateService;
	@Autowired
	private IIndustryTypeService iIndustryTypeService;
	@Autowired
	private IColorService iColorService;
	@Autowired
	private IUserRelTemplateService iUserRelTemplateService;

	/**
	 * 
	 * @Title: queryTemplates
	 * @Description: 查询模板分页
	 * @author (wangzhipeng@sxw100.com)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/queryTemplatesByPage")
	public void queryTemplatesByPage(
			Template example,
			@RequestParam(value = "showType") String showType,
			@RequestParam(value = "page", required = false) Integer currentPage,
			@RequestParam(value = "rows", required = false) Integer pageSize,
			HttpServletResponse response, HttpServletRequest request) {

		logger.info("====进入queryTemplatesByPage");

		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 设置分页
		PageHelper.startPage(currentPage, pageSize, true);
		// 获取当前用户
		User loginUser = SessionUtils.getCurrentLoginUser(request);
		example.setUserId(loginUser.getUserId());
		List<Template> templates;

		if ("guesslike".equals(showType)) {// 猜你喜欢

			example.setIsRecommended(1);
			templates = iTemplateService.queryTemplateByCondition(example);

		} else if ("collect".equals(showType)) {// 收藏

			templates = iTemplateService
					.queryTemplateIncollectByCondition(example);

		} else {// 行业分类

			templates = iTemplateService.queryTemplateByCondition(example);

		}

		PageInfo<Template> pageInfo = new PageInfo<Template>(templates);
		returnMap.put("currentPage", currentPage);
		returnMap.put("rows", templates);
		returnMap.put("pages", pageInfo.getPages());
		returnMap.put("total", pageInfo.getTotal());
		printJSONWithDateFormat(response, returnMap);
	}

	/**
	 * 
	 * @Title: queryTemplates
	 * @Description: 选择模板跳转到选择模板
	 * @author (wangzhipeng@sxw100.com)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/toTemplate")
	public String toTemplate(
			@RequestParam(value = "templateId") Integer templateId,
			HttpServletResponse response, HttpServletRequest request) {

		logger.info("进入--toTemplate方法");

		StringBuffer st = new StringBuffer(
				"redirect:/template/loadTempLatePage.do?templateId=");
		
		st.append(templateId);

		// 获取该模板默认第一个columnid
		Template temrc = new Template();
		
		temrc.setTemplateId(templateId);
		
		List<TemplateRelColumns> list = iTemplateService
				.queryTemplateRelColumnsByCondition(temrc);

		if (list != null && list.size() > 0) {

			TemplateRelColumns temp = list.get(0);
			st.append("&columnsId=").append(temp.getColumnsId());

		}

		return st.toString();
	}
	
	/**
	 * 
	 * @Title: toSearch
	 * @Description: 选择模板预览
	 * @author (wangzhipeng@sxw100.com)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/toSearch")
	public String toSearch(
			@RequestParam(value = "templateId") Integer templateId,
			HttpServletResponse response, HttpServletRequest request) {
		
		logger.info("进入--toSearch方法");
		
		// 获取该模板默认第一个columnid
		Template temrc = new Template();
		
		temrc.setTemplateId(templateId);
		
		List<TemplateRelColumns> list = iTemplateService
				.queryTemplateRelColumnsByCondition(temrc);
		
		if (list != null && list.size() > 0) {
			
			TemplateRelColumns temp = list.get(0);
			request.setAttribute("columnsId", temp.getColumnsId());
		}
		request.setAttribute("templateId", templateId);
		return "/template/selectTemplate/viewTemplate";
	}
	
	/**
	 * 
	 * @Title: toWebsite
	 * @Description: 选择模板跳转到站点
	 * @author (wangzhipeng@sxw100.com)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/toWebsite")
	public String toWebsite(
			@RequestParam(value = "templateId") Integer templateId,
			HttpServletResponse response, HttpServletRequest request) {
		
		logger.info("进入--toTemplate方法");
		
		StringBuffer st = new StringBuffer(
				"redirect:/site/loadWebsitePage.do?templateId=");
		
		st.append(templateId);
		
		// 获取该模板默认第一个columnid
		Template temrc = new Template();
		
		temrc.setTemplateId(templateId);
		
		List<TemplateRelColumns> list = iTemplateService
				.queryTemplateRelColumnsByCondition(temrc);
		
		if (list != null && list.size() > 0) {
			
			TemplateRelColumns temp = list.get(0);
			st.append("&columnsId=").append(temp.getColumnsId());
			
		}
		
		return st.toString();
	}

	/**
	 * 
	 * @Title: queryTemplates
	 * @Description: 查询企业分类
	 * @author (wangzhipeng@sxw100.com)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/queryIndustryTypes")
	public void queryTemplates(HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.info("进入--queryIndustryTypes方法");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<IndustryType> industryTypes = iIndustryTypeService.queryAll(null);
		
		returnMap.put("datas", industryTypes);
		
		printJSONWithDateFormat(response, returnMap);
	}

	/**
	 * 
	 * @Title: queryColors
	 * @Description: 查询颜色分类
	 * @author (wangzhipeng@sxw100.com)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/queryColors")
	public void queryColors(HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.info("进入--queryColors方法");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();

		returnMap.put("datas", iColorService.queryAll(null));
		
		printJSONWithDateFormat(response, returnMap);
	}

	/**
	 * 
	 * @Title: collectTemplate
	 * @Description: 收藏模板
	 * @author (wangzhipeng@sxw100.com)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/collectTemplate")
	public void collectTemplate(UserRelTemplate example,
			HttpServletResponse response,
			HttpServletRequest request) {

		logger.info("进入--collectTemplate方法");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 获取当前用户
		User loginUser = SessionUtils.getCurrentLoginUser(request);
		
		if (loginUser != null) {
			
			example.setUserId(loginUser.getUserId());
			
			example.setUpdateTime(new Date());
			
			int checkCount = iUserRelTemplateService.queryCount(example);
			
			// 检测是否已收藏
			if (checkCount > 0) {
				
				returnMap.put("result", "2");
				returnMap.put("message", "已收藏");
				
			} else {
				
				iUserRelTemplateService.add(example);
				returnMap.put("result", "1");
				returnMap.put("message", "已收藏");
				
			}
		} else {
			
			returnMap.put("result", "0");
			returnMap.put("message", "请重新登录");
			
		}
		
		printJSONWithDateFormat(response, returnMap);
	}

	/**
	 * 
	 * @Title: delCollectTemplate
	 * @Description: 去掉收藏模板
	 * @author (wangzhipeng@sxw100.com)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/delCollectTemplate")
	public void delCollectTemplate(UserRelTemplate example,
			HttpServletResponse response,
			HttpServletRequest request) {

		logger.info("进入--delCollectTemplate方法");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 获取当前用户
		User loginUser = SessionUtils.getCurrentLoginUser(request);
		
		if (loginUser != null) {
			
			example.setUserId(loginUser.getUserId());
			int checkCount = iUserRelTemplateService.queryCount(example);
			// 检测是否已收藏
			if (checkCount < 0) {
				
				returnMap.put("result", "2");
				returnMap.put("message", "未收藏");
				
			} else {
				
				iUserRelTemplateService.delete(example);
				returnMap.put("result", "1");
				returnMap.put("message", "操作成功");
				
			}
		} else {
			
			returnMap.put("result", "0");
			returnMap.put("message", "请重新登录");
			
		}
		
		printJSONWithDateFormat(response, returnMap);
		
	}

}