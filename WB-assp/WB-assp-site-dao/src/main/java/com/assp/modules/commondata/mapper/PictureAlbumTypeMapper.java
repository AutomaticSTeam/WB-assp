package com.assp.modules.commondata.mapper;

import java.util.List;

import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbumType;
import com.github.abel533.mapper.Mapper;
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
  * @CreateDate 2016年6月20日 上午9:37:00
 */
public interface PictureAlbumTypeMapper  extends Mapper<PictureAlbumType>{

	/**
	 * 
	* @Title: queryChildrenByPid 
	* @Description: 根据父id查询所有子节点
	* @author (panlinlin@sxw100.com)
	* @param @param albumTypePId
	* @param @return    设定文件 
	* @return List<PictureAlbumType>    返回类型 
	* @throws
	 */
	List<PictureAlbumType> queryChildrenByPid(Integer albumTypePId);
	
	/**
	 * 
	* @Title: queryOnePicture 
	* @Description: 查询当前分类图册的第一张图片
	* @author (panlinlin@sxw100.com)
	* @param @param albumType
	* @param @return    设定文件 
	* @return List<Picture>    返回类型 
	* @throws
	 */
	List<Picture> queryOnePicture(PictureAlbumType albumType);
	
}