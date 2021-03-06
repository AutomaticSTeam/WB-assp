package com.assp.modules.sys.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.sys.entity.User;
import com.assp.modules.website.entity.Website;

/**
 * 类简述
 * <p>
 *    用户服务类
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月20日 下午2:18:26 
 */
public interface IUserService extends BasicService<User> {
	
	/**
	 * 
	* @Title: userLoginSys 
	* @Description: 用户登录系统
	*@author (shx@sxw100.com)
	* @param @param user
	* @param @return    设定文件 
	* @return User    返回类型 
	* @throws
	 */
	public User userLoginSys(User user);

	/**
	 * 
	* @Title: getUserWebSite 
	* @Description: 获取当前用户下的站点信息
	* @author (panlinlin@sxw100.com)
	* @param @param userId
	* @param @return    设定文件 
	* @return List<Website>    返回类型 
	* @throws
	 */
	public List<Website> getUserWebSite(Integer userId);

	/**
	 * 
	* @Title: getUserNum 
	* @Description: 获取当前访问用户数
	* @author (panlinlin@sxw100.com)
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getUserNum();

	/**
	 * @param integer 
	 * 
	* @Title: updateUserNum 
	* @Description: 修改访问用户数 
	* @author (panlinlin@sxw100.com)
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public void updateUserNum(Integer integer);
	
	
	
	public int insertUser(User user);
	public void insertUserRole(Integer userid);
	
	/**
	 * 
	 * 描述：用户分配角色
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月28日 下午5:00:40
	 * @version
	 */
	public boolean addUserRole(String[] roleIds,String userId);
	
	/**
	 * 
	 * 描述：根据用户id删除中间表
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月28日 下午5:00:51
	 * @version
	 */
	public boolean deleteUserRoleByUserId(String userId);
	
	/**
	 * 
	 * 描述：根据用户id 获取已分配的角色
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月28日 下午5:05:04
	 * @version
	 */
	public List<String> getUserRoleByUserId(String userId);
	
	/**
	 * 
	 * 描述：批量删除
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月30日 下午2:53:27
	 * @version
	 */
	public boolean deleteUserByIds(String[] ids);

}
  