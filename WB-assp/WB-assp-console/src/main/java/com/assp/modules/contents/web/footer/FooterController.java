package com.assp.modules.contents.web.footer;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.modules.template.entity.TemplateFooter;
import com.assp.modules.template.entity.TemplateFooterRelColumns;
import com.assp.modules.template.service.ITemplateFooterRelColumnsService;
import com.assp.modules.template.service.ITemplateFooterService;

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
@RequestMapping(value = "/contents/footer")
public class FooterController {
	
	@Autowired
	ITemplateFooterService iTemplateFooterService;
	
	@Autowired
	ITemplateFooterRelColumnsService iTemplateFooterRelColumnsService;
	
	/**
	 * zhangtl
	 * 查询页脚列表
	 * @param footer
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryFooterList")
	public  Map<String,Object> queryFooterList(TemplateFooter footer,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model) throws Exception{
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<TemplateFooter>  footerList = iTemplateFooterService.queryByExample(footer);
		int count = iTemplateFooterService.queryCountByExample(footer);
		map.put("total", count);
		map.put("rows", footerList);
		return map;
	}
	
	/**
	 * zhangtl
	 * 跳转编辑或者添加页面
	 * @param response
	 * @param request
	 * @param session
	 * @param templateFooterId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toFooterOperate")
	public String toFooterOperate(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Integer templateFooterId,
			Model model){
		TemplateFooter footer = new TemplateFooter();
		if(templateFooterId!=null){
			footer = iTemplateFooterService.queryByPrimaryKey(templateFooterId);
		}
		model.addAttribute("footer", footer);
		return "/webconsole/footer/createOrEditFooter";
	}
	
	/**
	 * zhangtl
	 * 添加/编辑footer
	 * @param footer
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createOrEditFooter")
	public Map<String,Object>  createOrEditFooter(
			TemplateFooter footer,
			Integer templateId,
			@RequestParam(value = "columnsId", required = false) List<String> columnsIds,
			Integer relStatus,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model) {
		Map<String,Object> map=new HashMap<String,Object>();
		Integer sortNum = 0;
		if(footer.getTemplateFooterId()!=null){
			TemplateFooterRelColumns record = new TemplateFooterRelColumns();
			record.setTemplateFooterId(footer.getTemplateFooterId());
			List<TemplateFooterRelColumns> ls = iTemplateFooterRelColumnsService.queryAll(record);
			if(ls!=null && ls.size()!=0){
				for (int i = 0; i < ls.size(); i++) {
					iTemplateFooterRelColumnsService.delete(ls.get(i));
				}
			}
			int i = iTemplateFooterService.updateByPrimaryKeySelective(footer);
			//sortNum = iTemplateFooterRelColumnsService.querySortNumMaxObj(footer.getTemplateFooterId()).getSortNum();
			
			if(i==0){
				map.put("res", "0");
				map.put("msg", "修改失败");
			}else{
				map.put("res", "1");
				map.put("msg", "修改成功");
			}
		}else{
		//添加模板
			int i = iTemplateFooterService.insertReturnKey(footer);
			//sortNum = iTemplateFooterRelColumnsService.querySortNumMaxObj(footer.getTemplateFooterId()).getSortNum();
			
			if(i==0){
				map.put("res", "0");
				map.put("msg", "添加失败");
			}else{
				map.put("res", "1");
				map.put("msg", "添加成功");
			}
		}
		
		for (int j = 0; j < columnsIds.size(); j++) {
			TemplateFooterRelColumns frc = new TemplateFooterRelColumns();
			frc.setColumnsId(Integer.parseInt(columnsIds.get(j)));
			frc.setTemplateFooterId(footer.getTemplateFooterId());
			frc.setRelStatus(0);
			frc.setSortNum(sortNum+(j+1));
			frc.setColumnsHide(0);
			iTemplateFooterRelColumnsService.addSelective(frc);
		}
		return map;
	}
	
	/**
	 * zhangtl
	 * footer删除
	 * @param templateFooterId
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="delFooter")
	public Map<String, Object> delFooter(String templateFooterIds,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		Map<String,Object> map=new HashMap<String,Object>();
		String[] templateFooterId = templateFooterIds.split(",");
		for (int i = 0; i < templateFooterId.length; i++) {
			int k = iTemplateFooterService.deleteByPrimaryKey(Integer.parseInt(templateFooterId[i]));
			TemplateFooterRelColumns record = new TemplateFooterRelColumns();
			record.setTemplateFooterId(Integer.parseInt(templateFooterId[i]));
			iTemplateFooterRelColumnsService.delete(record);
			if(k==0){
				map.put("res", "0");
			}else{
				map.put("res", "1");
			}
		}
		return map;
	}
	
	/**
	 * zhangtl
	 * @param templateFooterId
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryFooterRelColumns")
	public Map<String,Object> queryFooterRelColumns(Integer templateFooterId,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session,Model model){
		Map<String,Object> map=new HashMap<String,Object>();
		TemplateFooterRelColumns record = new TemplateFooterRelColumns();
		record.setTemplateFooterId(templateFooterId);
		List<TemplateFooterRelColumns> footerRelColumnsList = iTemplateFooterRelColumnsService.queryAll(record);
		map.put("footerRelColumnsList", footerRelColumnsList);
		return map;
	}
	
	
}
  