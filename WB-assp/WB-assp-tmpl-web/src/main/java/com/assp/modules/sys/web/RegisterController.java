package com.assp.modules.sys.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.utils.RegexUtils;
import com.assp.common.utils.SendSms;
import com.assp.common.web.BaseController;
import com.assp.modules.sys.entity.User;
import com.assp.modules.sys.service.IUserService;

/**
 * 
 * 描述：wms用户注册
 * 创建人： 王小雷
 * 创建时间: 2016-7-18 下午5:30:59
 * @version
 */
@Controller
@RequestMapping("/sys/register")
public class RegisterController extends BaseController {
	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private IUserService iUserService;

	@RequestMapping("registerUser")
	@ResponseBody
	public Map<String, Object> registerUser(
			User user,
			@RequestParam(value = "verifyCode", required = false) String verifyCode,
			HttpServletRequest request ,
			HttpServletResponse response,
			HttpSession session)throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.debug("registerUser 方法");
		logger.debug("verifyCode --- " + verifyCode);
		int status = 0 ; // 0 失败  1  成功
		if(StringUtils.isNotBlank(verifyCode) && session.getAttribute("verifyCode") != null &&  verifyCode.equals(String.valueOf(session.getAttribute("verifyCode")))){
			// 判断是否有重复用户
			User checkUser=new User();
			checkUser.setUserName(user.getUserName());
			User userRst=iUserService.queryOne(checkUser);
			
			if(userRst == null ){ // 当前用户不存在
				logger.debug("当前用户不存在 " );
				// 设置密码
				String olPpassword =user.getPassword();
				String password = new SimpleHash("SHA-256", olPpassword).toString();
				password.endsWith(user.getPassword());
				user.setPassword(password);
				// 完善用户信息
				user.setPhone(user.getUserName());
				user.setIsActive(1);
				user.setNickName(user.getUserName());
				user.setRealName(user.getUserName());
				user.setSex(0);
				user.setUserStatus(0);
				user.setDataStatus(0);
				user.setSource(0);
				
				int userId = iUserService.insertUser(user);
				if(userId > 0 ){
					status = 1 ;
					map.put("msg", "恭喜您注册成功！");
					iUserService.insertUserRole(userId);
					user.setPassword(olPpassword);
					map.put("user", user);
				}else{
					map.put("msg", "很抱歉，注册失败！");
				}
			}else{
				map.put("msg", "该用户已存在，请重新输入！");
			}
		}else{
			map.put("msg", "验证码错误！");
		}
		map.put("status", status);
		return map;
	}
	/**
	 * 
	 * 描述：展示用户信息
	 * 创建人： 王小雷
	 * 创建时间: 2016-7-20 下午4:26:06
	 * @version
	 */
	@RequestMapping("userCenter")
	public String userCenter(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		//从session里拿user
		User user=(User)request.getSession().getAttribute("loginUser");
		//新建一个对象  只把session里的username 或者userid放进去
		User nameUser=new User();
		nameUser.setUserName(user.getUserName());
		//通过单个对象查询  实现数据库信息与页面同步
		User oneUser=iUserService.queryOne(nameUser);
		logger.debug("同步用户查询 " );
		model.addAttribute("usercenterlist",oneUser);
		logger.debug("用户中心展示 " );
		return "/common/login/usercenter";
	}
	/**
	 * 
	 * 描述：个人中心  修改信息
	 * 创建人： 王小雷
	 * 创建时间: 2016-7-20 下午4:19:36
	 * @version
	 */
	@ResponseBody
	@RequestMapping("updUserCenter")
	public Map<String, Object> updUserCenter(User user){
		Map<String, Object> map=new HashMap<String, Object>();
		int updateUser=iUserService.updateByPrimaryKeySelective(user);
		logger.debug("用户中心更新 " );
		if(updateUser>0){
			map.put("status","0" );
			map.put("msg", "修改成功！");
		}else{
			map.put("status", "1");
			map.put("msg", "修改失败！");
		}
		return map;
	}
	/**
	 * 
	 * 描述：发送短信
	 * 创建人： 王小雷
	 * 创建时间: 2016-7-22 上午9:18:34
	 * @version
	 */
	@ResponseBody
	@RequestMapping("/sendMessage")
	public Map<String, Object> sendMessage(User user,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		//生成验证码
		String verifyCode = randomVerifyCode().toString();
		//String verifyCode="123456"; // To Do  随机生成验证码
		session.setAttribute("verifyCode", verifyCode);
		Map<String, Object> returnMap=new HashMap<String, Object>();
        if(StringUtils.isNotBlank(user.getUserName())){ //手机号不为空下发短信
        	// TO Do 
        	if(RegexUtils.isMobileNO(user.getUserName())){
    			String sendStatus = SendSms.sendSmstoReg(user.getUserName(), verifyCode);
    			if("2".equals(sendStatus)){
    				returnMap.put("rst", true);
    			}
    		}
        }else{
        	returnMap.put("rst", false);
        }
		
		return returnMap;
	}
	@ResponseBody
	@RequestMapping("updataPwd")
	public Map<String, Object> updataPwd(User user,String password,String newPasswprd,HttpServletRequest request) throws IOException{
		Map<String, Object> pwdMap =new HashMap<String,Object>();

		//获取旧密码输入信息
		String oldpassword = new SimpleHash("SHA-256", password).toString();
		User loginUser=(User)request.getSession().getAttribute("loginUser");
		//实现同步   syncUser同步用户
		User syncUser=new User();
		syncUser.setUserId( loginUser.getUserId());
		User newUser=iUserService.queryOne(syncUser);
		//旧密码输入信息与旧密码判断
		if(!newUser.getPassword().equals(oldpassword)){
			pwdMap.put("status","7" );
			pwdMap.put("msg", "请输入正确的原密码！");
		}else{
		//判断新密码
		String pwd =new SimpleHash("SHA-256", newPasswprd).toString();
		newUser.setPassword(pwd);
		int p=iUserService.updateByPrimaryKeySelective(newUser);
		if(p>0){
			pwdMap.put("status","2" );
			pwdMap.put("msg", "修改成功！");
		}
		}
		return pwdMap;
	}
	
	
	/**
	 * 
	 * 描述：生成验证码
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月25日 下午6:02:24
	 * @version
	 */
	private Integer randomVerifyCode(){
		return (int)((Math.random()*9+1)*100000);
	}
}
