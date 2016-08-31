package com.assp.modules.contents.web.logo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.modules.template.entity.TemplateLogo;
import com.assp.modules.template.service.ITemplateLogoService;

/**
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * 
  * @Company 动力威视
  * @Copyright
  * @author (zhangtl@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年7月29日
  */
@Controller
@RequestMapping(value = "/contents/logo")
public class LogoController {
	
	@Autowired
	ITemplateLogoService iTemplateLogoService;
	
	@ResponseBody
	@RequestMapping(value = "/queryLogoList")
	public  Map<String,Object> queryLogoList(TemplateLogo logo,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model) throws Exception{
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<TemplateLogo>  logoList = iTemplateLogoService.queryByExample(logo);
		int count = iTemplateLogoService.queryCountByExample(logo);
		map.put("total", count);
		map.put("rows", logoList);
		return map;
	}
	
	/**
	 * zhangtl
	 * 跳转编辑或者添加页面
	 * @param response
	 * @param request
	 * @param session
	 * @param templateLogoId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toLogoOperate")
	public String toLogoOperate(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Integer templateLogoId,
			Model model){
		TemplateLogo logo = new TemplateLogo();
		if(templateLogoId!=null){
			logo = iTemplateLogoService.queryByPrimaryKey(templateLogoId);
		}
		model.addAttribute("logo", logo);
		return "/webconsole/logo/createOrEditLogo";
	}
	
	/**
	 * zhangtl
	 * 添加/编辑logo
	 * @param tempalte
	 * @param columnsId
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createOrEditLogo")
	public Map<String,Object>  createOrEditLogo(
			TemplateLogo logo,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(logo.getTemplateLogoId()!=null){
			int i = iTemplateLogoService.updateByPrimaryKeySelective(logo);
			if(i==0){
				map.put("res", "0");
				map.put("msg", "修改失败");
			}else{
				map.put("res", "1");
				map.put("msg", "修改成功");
			}
		}else{
		//添加模板
			int i = iTemplateLogoService.addSelective(logo);
			if(i==0){
				map.put("res", "0");
				map.put("msg", "添加失败");
			}else{
				map.put("res", "1");
				map.put("msg", "添加成功");
			}
		}
		return map;
	}
	
	/**
	 * zhangtl
	 * logo删除
	 * @param templateLogoIds
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="delLogo")
	public Map<String, Object> delLogo(String templateLogoIds,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] templateLogoId = templateLogoIds.split(",");
		for (int i = 0; i < templateLogoId.length; i++) {
			int k = iTemplateLogoService.deleteByPrimaryKey(Integer.parseInt(templateLogoId[i]));
			if(k==0){
				map.put("res", "0");
			}else{
				map.put("res", "1");
			}
		}
		return map;
	}
	
}
  