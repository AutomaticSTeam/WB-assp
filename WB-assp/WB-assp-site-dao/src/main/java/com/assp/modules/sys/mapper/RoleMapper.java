package com.assp.modules.sys.mapper;   

import java.util.List;

import com.assp.modules.sys.entity.Role;
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
 * @CreateDate 2016年4月20日 上午11:19:00 
 */
public interface RoleMapper extends Mapper<Role> {
	
	/**
	 * 
	* @Title: selectRolesByUserId 
	* @Description: 通过用户id获取所有角色
	*@author (shx@sxw100.com)
	* @param @param userId
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws
	 */
	 public List<Role> selectRolesByUserId(Integer userId);

}
  