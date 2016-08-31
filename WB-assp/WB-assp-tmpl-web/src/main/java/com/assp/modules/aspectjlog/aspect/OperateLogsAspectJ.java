package com.assp.modules.aspectjlog.aspect;   

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.assp.modules.aspectjlog.entity.OperateLogs;
import com.assp.modules.aspectjlog.mapper.OperateLogsMapper;
import com.assp.modules.sys.entity.User;

/**
 * 类简述
 * <p>
 *     系统操作日志切面
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月20日 下午5:01:33 
 */
@Component
@Aspect
public class OperateLogsAspectJ {
	private static final Logger logger = Logger.getLogger(OperateLogsAspectJ.class);
	
	
	/**操作常量*/
	private final int OPERATE_TYPE_ADD = 0; // 增加
	private final  int OPERATE_TYPE_DELETE = 1; // 删除
	private final  int OPERATE_TYPE_UPDATE = 2; // 修改
	private final int OPERATE_TYPE_ASSIGN = 3; // 分配
	private final int OPERATE_TYPE_QUERY = 4 ; //查询
	
	/**模块常量*/
	private final String OPERATE_MODULE_PERMISSION = "权限模块"; // 权限模块
	
	/**操作的实体*/
	private final String OPERATE_OBJECT_USER = "用户"; // 用户对象
	private final String OPERATE_OBJECT_ROLE = "角色"; // 角色对象
	private final String OPERATE_OBJECT_PERMISSION = "资源"; // 资源对象
	
	/**登录相关*/
	private final String OPERATE_TYPE_LOGIN = "登录"; //登录
	private final String OPERATE_TYPE_LOGOUT = "注销";//注销
	
	private final String OPERATE_OBJECT_ROLEPERMISSION_REF = "角色资源关系"; // 角色资源关系对象
	private final String OPERATE_OBJECT_GROUPROLE_REF = "组织角色关系"; // 组织角色关系对象
	private final String OPERATE_OBJECT_USERROLE_REF = "用户角色关系"; // 用户角色关系对象
	
	@Autowired
	private OperateLogsMapper operateLogsMapper;
	
	/**
	 * 增删改主切点
	 */
	//增加主表记录
	@Pointcut("execution(* com.assp.modules.*.service.*Service.add*(..))")
	public void pointCut_add() {}
	//物理删除记录
	@Pointcut("execution(* com.assp.modules.*.service.*Service.deleteBy*(..))")
	public void pointCut_delete() {}
	//修改和逻辑删除
	@Pointcut("execution(* com.assp.modules.*.service.*Service.updateBy*(..))")
	public void pointCut_update() {}
	//分配关系
	@Pointcut("execution(* com.assp.modules.*.service.*Service.assign*(..))")
	public void pointCut_assign() {}
	
	//登录
	@Pointcut("execution(* com.assp.modules.sys.web.LoginController.login(..))")
	public void pointCut_login() {}
    //注销
	@Pointcut("execution(* com.assp.modules.sys.web.LoginController.logOut(..))")
	public void pointCut_loginOut() {}
	
	@After("pointCut_login()")
	public void afterLogin(JoinPoint joinPoint) {
		logger.debug("====after afterLogin aspect executing====");
		//如果需要这里可以取出参数进行处理
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = (HttpServletRequest) args[3];
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user != null){
			saveLogs(user,OPERATE_TYPE_QUERY,null,OPERATE_TYPE_LOGIN);
		}
	}
	
	@Before("pointCut_loginOut()")
	public void afterLoginOut(JoinPoint joinPoint) {
		logger.debug("====before afterLogin aspect executing====");
		//如果需要这里可以取出参数进行处理
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = (HttpServletRequest) args[2];
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user != null){
			saveLogs(user,OPERATE_TYPE_QUERY,null,OPERATE_TYPE_LOGOUT);
		}
	
	}
	
	@After("pointCut_add()")
	public void afterAdd(JoinPoint joinPoint) {
		//如果需要这里可以取出参数进行处理
		Object[] args = joinPoint.getArgs();
		if(args == null || args.length <= 0){
			logger.info("系统日志增加切面获取参数失败!");
			return ;
		}
		//获取系统中
		User user = (User) RequestContextHolder.getRequestAttributes().getAttribute("loginUser", 1);
		if(user != null){
			saveLogs(user,OPERATE_TYPE_ADD,args[0],null);
		}
	}

	@After("pointCut_delete()")
	public void afterDelete(JoinPoint joinPoint) {
		//如果需要这里可以取出参数进行处理
		Object[] args = joinPoint.getArgs();
		if(args == null || args.length <= 0){
			logger.info("系统日志增加切面获取参数失败!");
			return ;
		}
		//获取系统中
		User user = (User) RequestContextHolder.getRequestAttributes().getAttribute("loginUser", 1);
		if(user != null){
			saveLogs(user,OPERATE_TYPE_DELETE,args[0],null);
		}
	}

	@After("pointCut_update()")
	public void afterUpdate(JoinPoint joinPoint) {
		//如果需要这里可以取出参数进行处理
		Object[] args = joinPoint.getArgs();
		if(args == null || args.length <= 0){
			logger.info("系统日志增加切面获取参数失败!");
			return ;
		}
		//获取系统中
		User user = (User) RequestContextHolder.getRequestAttributes().getAttribute("loginUser", 1);
		int flag = 0 ; // 由于本系统的主表是采用逻辑删除,因此需要在修改的时候区分删除或者是修改  0修改 1删除
		if(user != null){
			Object oprateObj = args[0];
			saveLogs(user,OPERATE_TYPE_UPDATE,args[0],null);
		}
	}
	
	@After("pointCut_assign()")
	public void afterAssign(JoinPoint joinPoint) {
		//如果需要这里可以取出参数进行处理
		Object[] args = joinPoint.getArgs();
		if(args == null || args.length <= 0){
			logger.info("系统日志增加切面获取参数失败!");
			return ;
		}
		//获取系统中
		User user = (User) RequestContextHolder.getRequestAttributes().getAttribute("loginUser", 1);
		if(user != null){
			saveLogs(user,OPERATE_TYPE_ASSIGN,args[0],null);
		}
	}
	
	//记录日志
	private void saveLogs(User user,Integer operateType,Object oprateObj,String specOpt){
		String operateModule = "";
		String operateContent = "";
		String remark = "";
		if(oprateObj == null){ //用户权限
			operateModule +=OPERATE_MODULE_PERMISSION + "-"+specOpt;
			operateContent += "用户 【"+user.getNickName()+"】 在【"+ OPERATE_MODULE_PERMISSION+"】中" +  specOpt;
			remark= operateContent;
		}else{ // To Do 
			String realOpt = "";
			String clazzStr = "";
			if(oprateObj instanceof  User){
				operateModule ="用户模块";
				clazzStr = "用户";
			}
			operateContent += "用户 【"+user.getNickName()+"】 在【"+operateModule+"】中" ;
			if(operateType == 1){
				realOpt ="添加" + clazzStr;
			}else if(operateType == 2){
				realOpt ="删除" + clazzStr;
			}else if(operateType == 3){
				realOpt ="修改" + clazzStr;
			}else if(operateType == 4){
				realOpt ="分配" + clazzStr;
			}else{
				realOpt ="其他操作";
			}
			operateContent+=realOpt;
			remark= "具体模块操作-【"+operateModule+"】";
		}
		OperateLogs operateLogs =  new OperateLogs(user.getUserId(), user.getUserName(), operateModule, operateContent, operateType, remark);
		operateLogsMapper.insertSelective(operateLogs);
	}
}
  