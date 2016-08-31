package com.assp.modules.sys.mapper;   

import java.util.List;
import java.util.Map;

import com.assp.modules.sys.entity.User;
import com.assp.modules.website.entity.Website;
import com.github.abel533.mapper.Mapper;

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
 * @CreateDate 2016年4月20日 上午11:03:11 
 */
public interface UserMapper extends Mapper<User> {

	/**
	 * 
	* @Title: selectRolesAndPermissionsByUserId 
	* @Description: 通过用户的id查找对应的角色和权限
	*@author (shx@sxw100.com)
	* @param @param userId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String,Object>> selectRolesAndPermissionsByUserId(Integer userId);

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
	 * @param userUum 
	 * 
	* @Title: updateUserNum 
	* @Description: 更改访问用户数 
	* @author (panlinlin@sxw100.com)
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public void updateUserNum(Integer userUum);
	
	
	public int insertUser(User user);
	public void insertUserRole(Integer userid);
}
  