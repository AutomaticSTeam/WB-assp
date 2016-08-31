package com.assp.modules.commondata.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;

/**
 * 类简述
 * <p>
 *    图片服务处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 上午11:25:33 
 */
public interface IPictureService extends BasicService<Picture> {

	 
	 /**
	  * 
	 * @Title: getPictureById 
	 * @Description: 根据主键查询图片
	 * @author (panlinlin@sxw100.com)
	 * @param @param picId
	 * @param @return    设定文件 
	 * @return Picture    返回类型 
	 * @throws
	  */
	 public Picture getPictureById(int picId);

	 /**
	  * 
	 * @Title: updatePictureStatus 
	 * @Description: 删除图片（更新图片状态）
	 * @author (panlinlin@sxw100.com)
	 * @param @param picture
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	  */
	public int updatePictureStatus(Picture picture);


	/**
	 * 
	* @Title: review_yesById 
	* @Description: 审核通过
	* @author (panlinlin@sxw100.com)
	* @param @param valueOf    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int review_yesById(Integer valueOf);

	/**
	 * 
	* @Title: updatePictureforDelete 
	* @Description: 删除相册时连带删除图片
	* @author (panlinlin@sxw100.com)
	* @param @param valueOf
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int updatePictureforDelete(Integer valueOf);

	/**
	 * 
	* @Title: queryAlbumByPId 
	* @Description: 查询图片所在的相册
	* @author (panlinlin@sxw100.com)
	* @param @param pictureId
	* @param @return    设定文件 
	* @return PictureAlbum    返回类型 
	* @throws
	 */
	public PictureAlbum queryAlbumByPId(Integer pictureId);

	/**
	 * 
	* @Title: batchAddPic 
	* @Description: 批量插入图片
	* @author (panlinlin@sxw100.com)
	* @param @param picList
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int batchAddPic(List<Picture> picList);

	/**
	 * 
	* @Title: batchUpdataPic 
	* @Description: 批量更新图片
	* @author (panlinlin@sxw100.com)
	* @param @param picList
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int batchUpdataPic(List<Picture> picList);
}
  