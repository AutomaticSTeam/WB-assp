package com.assp.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.modules.sys.entity.User;
import com.assp.modules.sys.service.IUserService;
import com.assp.modules.template.service.ITemplateService;
import com.assp.modules.website.entity.Website;

/**
 * 
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (panlinlin@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年6月17日 下午6:51:22
 */
@Controller
@RequestMapping(value = "/sys/user")
public class UserController {

	private static final Logger logger = Logger. getLogger(LoginController.class);
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private ITemplateService  iTemplateService;
	
	@RequestMapping(value = "/getUserWebSite")
	@ResponseBody
	public List<Website> getUserWebSite(HttpSession session) {
		User loginUser = (User) session.getAttribute("loginUser");
		List<Website> data=iUserService.getUserWebSite(loginUser.getUserId());
		return data;
		
	};
	
}
  