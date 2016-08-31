package com.assp.modules.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.assp.common.constant.SystemConstants;
import com.assp.modules.sys.entity.User;
import com.assp.modules.template.entity.Template;
import com.assp.modules.website.entity.Website;

/**
 * 辅助工具
 * @author songhongxu
 *
 */
public class SessionUtils {

	/**
	 * 获取session中的用户 </P> 详细描述(可选) </p>
	 * 
	 * @param request
	 * @return <p>
	 *         修改记录:(日期,修改人,描述) (可选)
	 *         </p>
	 */
	
	public static User getloginUser(HttpServletRequest request){ 
		 User user = (User)request.getSession().getAttribute("loginUser"); 
		 return user;
	 }
	
	/**获取当前登录用户信息
	 * @param request
	 * @return
	 */
	public static User getCurrentLoginUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(SystemConstants.CURRENT_LOGIN_USER);
	}
	 

	/**
	 * 
	 * @Title: getSiteTemplate
	 * @Description: 获取session 中的站点模板
	 * @author (shx@sxw100.com)
	 * @param @param request
	 * @param @return 设定文件
	 * @return Template 返回类型
	 * @throws
	 */
	public static Template getSiteTemplate(HttpSession session) {
		return session.getAttribute("siteTempalte") == null ? null
				: (Template) session.getAttribute("siteTempalte");
	}

	/**
	 * 
	 * @Title: getWebsite
	 * @Description: 获取session 中的website
	 * @author (wangkang@sxw100.com)
	 * @param @param request
	 * @param @return 设定文件
	 * @return Template 返回类型
	 * @throws
	 */
    public static Website getWebsite(HttpSession session){
		return session.getAttribute("website") == null ? null
				: (Website) session.getAttribute("website");
	}
    
	/**
	 * 
	 * @Title: getWebsiteId
	 * @Description: 获取session 中的website
	 * @author (wzp@sxw100.com)
	 * @param @param request
	 * @param @return 设定文件
	 * @return Template 返回类型
	 * @throws
	 */
	public static Integer getWebsiteId(HttpSession session){
		Website websiteVO = getWebsite(session);
		return websiteVO == null ? null	: websiteVO.getSiteId();
	}

}
