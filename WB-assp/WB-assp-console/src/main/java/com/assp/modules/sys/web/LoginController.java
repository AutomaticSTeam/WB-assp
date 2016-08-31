package com.assp.modules.sys.web;   

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assp.common.utils.VerifyCodeUtil;
import com.assp.common.web.BaseController;

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
@RequestMapping(value = "/sys/login")
public class LoginController extends BaseController {

	
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
	
	
	
	
}
  