package com.assp.modules.sys.mapper;   

import java.util.List;

import com.assp.modules.sys.entity.Permission;
import com.assp.modules.sys.entity.PermissionVo;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *     权限资源数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年4月20日 上午11:00:28 
 */
public interface PermissionMapper extends Mapper<Permission>{
        /**
         * 
        * @Title: selectPermissionsByVo 
        * @Description: 通过条件查找权限资源
        *@author (shx@sxw100.com)
        * @param @param permissionVo
        * @param @return    设定文件 
        * @return List<Permission>    返回类型 
        * @throws
         */
	     public List<Permission> selectPermissionsByVo(PermissionVo permissionVo);
}
  