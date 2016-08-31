package com.assp.modules.template.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.constant.SystemConstants;
import com.assp.common.web.BaseController;
import com.assp.modules.module.entity.TemplateModuleTmpl;
import com.assp.modules.module.service.ITemplateModuleTmplService;

/**
 * 
  * 类简述
  * <p>
  * 模块模板类
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (panlinlin@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年7月15日 下午6:44:10
 */
@Controller
@RequestMapping(value = "/console/moduleTmpl")
public class TemplateModuleTmplController extends BaseController{

	private static final Logger logger = Logger.getLogger(TemplateModuleTmplController.class);
	
	@Autowired
	private ITemplateModuleTmplService templateModuleTmplService;
	
	/**
	 * 
	* @Title: getVerificationCode 
	* @Description: 添加模块模板
	* @author (panlinlin@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param moduleTmpl
	* @param @param session
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	@RequestMapping("/addModuleTmpl")
	@ResponseBody
	public Map<String, Object> getVerificationCode(HttpServletResponse response,HttpServletRequest request,TemplateModuleTmpl moduleTmpl,HttpSession session)  {
		
		logger.debug("--进入addModuleTmpl方法--");
		Map<String, Object> data = new HashMap<String,Object>();
		//User loginUser = (User) session.getAttribute("loginUser");
		try {
			Date date = new Date();
			moduleTmpl.setCreateTime(date);
			moduleTmpl.setUpdateTime(date);
			if(moduleTmpl.getModuleTmlId()==null){
				templateModuleTmplService.addSelective(moduleTmpl);
			}else{
				//moduleTmpl.setDataStatus(Integer.valueOf(0));
				templateModuleTmplService.updateByPrimaryKeySelective(moduleTmpl);
			}
			data.put("tip", SystemConstants.TIP_SUCCESS);
			data.put("code", 1);
		} catch (Exception e) {
			data.put("tip", SystemConstants.TIP_FAIL);
			data.put("code", 0);
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 
	* @Title: ManagerModuleTmpl 
	* @Description: 查询模块模板 进入模块模板管理页面 
	* @author (panlinlin@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @return    设定文件 
	* @return List<TemplateModuleTmpl>    返回类型 
	* @throws
	 */
	@RequestMapping("/ManagerModuleTmpl")
	@ResponseBody
	public Map<String, Object> ManagerModuleTmpl(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			TemplateModuleTmpl moduleTmpl)  {
		
		logger.debug("--进入ManagerModuleTmpl方法--");
		
		moduleTmpl.setDataStatus(0);
		List<TemplateModuleTmpl> TemplateModuleTmplList = templateModuleTmplService.queryByExample(moduleTmpl);
		int count = templateModuleTmplService.queryCountByExample(moduleTmpl);
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("total", count);
		data.put("rows", TemplateModuleTmplList);
		return data;
	}
	/**
	 * 
	* @Title: editModuleTmpl 
	* @Description: 编辑模块模板
	* @author (panlinlin@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param moduleTmpl
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/editModuleTmpl")
	public String editModuleTmpl(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			TemplateModuleTmpl moduleTmpl,
			Model model)  {
		
		logger.debug("--进入editModuleTmpl方法--");
		TemplateModuleTmpl data = templateModuleTmplService.queryOne(moduleTmpl);
			model.addAttribute("reasult", data);
		return "/module/addModule";
	}
	
	/**
	 * 
	* @Title: deletModuleTmpl 
	* @Description: 删除模块模板
	* @author (panlinlin@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param moduleTmpl
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/deletModuleTmpl")
	@ResponseBody
	public Map<String, Object> deletModuleTmpl(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "moduleTmlIds", required = false) String moduleTmlIds,
			Model model)  {
		logger.debug("--进入deletModuleTmpl方法--");
		Map<String, Object> data = new HashMap<String,Object>();
		if(moduleTmlIds!=null&&moduleTmlIds.length()>0){
			TemplateModuleTmpl moduleTmpl = new TemplateModuleTmpl();
			moduleTmpl.setDataStatus(1);
			String[] moduleTmlIdArray = moduleTmlIds.split(",");
			for (int i = 0; i < moduleTmlIdArray.length; i++) {
				moduleTmpl.setModuleTmlId(Integer.valueOf(moduleTmlIdArray[i]));
				templateModuleTmplService.updateByPrimaryKeySelective(moduleTmpl);
			}
			data.put("tip", SystemConstants.TIP_SUCCESS);
			data.put("code", 1);
		}else{
			data.put("tip", SystemConstants.TIP_FAIL);
			data.put("code", 0);
		}
		return data;
	}
	
	/**
	 * 
	* @Title: queryModuleTmpl 
	* @Description: 模糊查询模块模板 
	* @author (panlinlin@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @param moduleTmpl
	* @param @return    设定文件 
	* @return List<TemplateModuleTmpl>    返回类型 
	* @throws
	 */
	@RequestMapping("/queryModuleTmpl")
	@ResponseBody
	public List<TemplateModuleTmpl> queryModuleTmpl(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			TemplateModuleTmpl moduleTmpl)  {
		
		logger.debug("--进入queryModuleTmpl方法--");
		List<TemplateModuleTmpl> data = templateModuleTmplService.queryByExample(moduleTmpl);
		return data;
	}
}


