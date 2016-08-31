package com.assp.modules.contents.web.website;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.ResponseJsonData;
import com.assp.common.web.BaseController;
import com.assp.modules.common.SessionUtils;
import com.assp.modules.website.entity.Website;
import com.assp.modules.website.service.IWebsiteService;

/**
 * 
 * 类简述
 * <p>
 * 站点信息编辑控制器
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wzp@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年8月9日 下午3:12:55
 */
@Controller
@RequestMapping("/contents/website")
public class WebsiteConsoleController extends BaseController {

	@Autowired
	private IWebsiteService iWebsiteService; // 站点服务

	/**
	 * @throws Exception 
	 * 
	 * @Title: queryWebsiteVO
	 * @Description: 查询站点相关信息
	 * @author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@RequestMapping("/queryWebsiteVO")
	public String queryWebsiteVO(Website websiteVO, HttpServletResponse response,
			HttpServletRequest request, HttpSession session) throws Exception {

		if(websiteVO.getSiteId() == null || websiteVO.getSiteId() == Integer.valueOf(0)){
			throw new Exception("非法操作");
		}
		
		// 获取website
		//Website website = syncSessionWebsite(websiteVO,session);
		
		Website website = iWebsiteService.queryOne(websiteVO);
		request.setAttribute("websiteVO", website);
		return "/webconsole/website/editWebsite";
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Title: editWebsiteVO
	 * @Description: 修改站点信息
	 * @author (wzp@sxw100.com)
	 * @param @param response
	 * @param @param request 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@RequestMapping("/editWebsiteVO")
	@ResponseBody
	public String editWebsiteVO(Website websiteVO, HttpServletResponse response,
			HttpServletRequest request, HttpSession session) throws Exception {
		ResponseJsonData reponseJsonData = new ResponseJsonData();
		if(websiteVO.getSiteId() == null || websiteVO.getSiteId() == Integer.valueOf(0)){
			reponseJsonData.setCode(0);
			reponseJsonData.setMesssage("非法操作");
			throw new Exception("非法操作");
		}
		iWebsiteService.updateByPrimaryKeySelective(websiteVO);
		return reponseJsonData.generateResultStr();
		
	}

	/**
	 * 
	 * @Title: syncSessionWebsite
	 * @Description: 同步session 中的website
	 * @author (wzp@sxw100.com)
	 * @param @param request
	 * @param @return 设定文件
	 * @return Website 返回类型
	 * @throws
	 */
	private Website syncSessionWebsite(Website websiteVO, HttpSession session) {
		
		Website tmp = SessionUtils.getWebsite(session);
		// 判断是否一致，不一致则按传过来的为准
		if (null != websiteVO.getSiteId()) {
			if (null == tmp || (tmp.getSiteId().intValue() != websiteVO
							.getSiteId().intValue())) {
				
				tmp = iWebsiteService.queryOne(websiteVO);
				// 查询当前站点放入缓存中
				session.setAttribute("website", tmp);
				session.setMaxInactiveInterval(60 * 60 * 24);
			} 
		} 
		
		return tmp;
	}

}
