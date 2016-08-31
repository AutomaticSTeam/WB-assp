package com.assp.modules.commondata.service;


import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.commondata.entity.PictureAlbumType;

/**
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * 
  * @Company 动力威视
  * @Copyright
  * @author (panlinlin@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年6月18日 下午5:50:28 
  */
public interface IPictureAlbumTypeService extends BasicService<PictureAlbumType> {

	/**
	 * 
	* @Title: queryChildrenByPid 
	* @Description: 根据父id查询所有子节点 
	* @author (panlinlin@sxw100.com)
	* @param @param albumTypeId
	* @param @return    设定文件 
	* @return List<PictureAlbumType>    返回类型 
	* @throws
	 */
	List<PictureAlbumType> queryChildrenByPid(Integer albumTypePId);

	//根据当前分类查询父节点
	List<PictureAlbumType> getAllParetTypes(Integer albumTypeId);

}
  