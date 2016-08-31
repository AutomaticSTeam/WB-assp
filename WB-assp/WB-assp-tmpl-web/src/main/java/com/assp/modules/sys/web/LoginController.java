package com.assp.modules.sys.web;   

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.constant.SystemConstants;
import com.assp.common.utils.RedisCacheUtil;
import com.assp.common.utils.VerifyCodeUtil;
import com.assp.common.web.BaseController;
import com.assp.modules.sys.entity.User;
import com.assp.modules.sys.service.IUserService;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月18日 下午5:17:25 
 */
@Controller
@RequestMapping(value="/sys/login")
public class LoginController extends BaseController {

	private static final Logger logger = Logger. getLogger(LoginController.class);
	
	@Autowired
	private IUserService iUserService;
	
	
	/**
	 * 
	* @Title: getVerificationCode 
	* @Description: ajax 获取验证码
	*@author (shx@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @return
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/getVerificationCode")
	public String getVerificationCode(HttpServletResponse response,HttpServletRequest request) throws IOException {
		// 将生成的验证码放入session
		HttpSession session = request.getSession(true);
		// 设置Mime类型
		response.setContentType(VerifyCodeUtil.getMimeType());
		// 生成验证码
		String sRand = VerifyCodeUtil.createVerifyCode(response.getOutputStream());
		session.setAttribute("checkCode", sRand);
		return null;
	}
	
	/**
	 * 
	* @Title: login 
	* @Description: 登录
	*@author (shx@sxw100.com)
	* @param @param verifyCode
	* @param @param vistSource
	* @param @param user
	* @param @param response
	* @param @param request
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public HashMap<String, Object> login(
			@RequestParam(value = "verifyCode", required = false) String verifyCode,
			@RequestParam(value = "vistSource", required = false) String vistSource,
			User user, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String pw = user.getPassword();
		String source=null;
		switch (Integer.valueOf(vistSource)) {
		case SystemConstants.VIST_SOURCE_WMS:
			source="WMS";
			break;
		case SystemConstants.VIST_SOURCE_DOTNET:
			source="DOTNET";
			break;
		case SystemConstants.VIST_SOURCE_REGISTER:
			source="REGISTER";
			break;
		default:
			break;
		}
		logger.debug("进入登录方法，登录来源" +source);
		HashMap<String, Object> data = new HashMap<>();
		model.addAttribute("nickName", user.getNickName());
		if(Integer.valueOf(vistSource).equals(SystemConstants.VIST_SOURCE_WMS)){ // wms 网站用户登录 需验证 验证码
			String checkCode = (String) request.getSession(true).getAttribute("checkCode");
			if(!checkCode.equals(verifyCode)){
				model.addAttribute("errorTip", "您输入的验证码不正确请重新输入！");
				data.put("tip", "您输入的验证码不正确请重新输入！");
				data.put("status", 1);
				return data;
			}
		}
		User loginUser = iUserService.userLoginSys(user);
		if(loginUser != null){ //登录成功
			Cache<String, Object> redisCache = RedisCacheUtil.getRedisCache();
			request.getSession().setAttribute("loginUser", loginUser);
			UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getNickName(), pw, false);
			try {
				SecurityUtils.getSubject().login(token);
				if (redisCache != null ) {
					Subject currentUser = SecurityUtils.getSubject();
					redisCache.put(loginUser.getNickName(), currentUser.getSession().getId());
				}
				data.put("tip", "登录成功");
				data.put("status", 0);
				return data;
			} catch (AuthenticationException e) {
				e.printStackTrace();
				model.addAttribute("errorTip", "账户或者密码错误！");
				data.put("tip", "用户名或密码错误，请重新登录！");
				data.put("status", 2);
				return data;
			}
		}else if(vistSource.equals(SystemConstants.VIST_SOURCE_WMS)){
			model.addAttribute("errorTip", "用户名或密码错误，请重新登录！");
			data.put("tip", "用户名或密码错误，请重新登录！");
			data.put("status", 2);
			return data;
		}else{ //如果dotnot 用户登录失败  TODO
			data.put("tip", "用户名或密码错误，请重新登录！");
			data.put("status", 2);
			return data;
		}
	}
	
	/**
	 * 
	* @Title: logOut 
	* @Description: 退出
	*@author (shx@sxw100.com)
	* @param @param model
	* @param @param response
	* @param @param request
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("/logout")
	public String logOut(Model model, HttpServletResponse response,HttpServletRequest request) throws Exception {
		logger.debug("-----用户注销---");
		SecurityUtils.getSubject().logout();
		return "redirect:/jsp/common/login/login.jsp";
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(HttpSession session) throws Exception {
		User loginUser = (User) session.getAttribute("loginUser");
		return loginUser;
	}
}
  