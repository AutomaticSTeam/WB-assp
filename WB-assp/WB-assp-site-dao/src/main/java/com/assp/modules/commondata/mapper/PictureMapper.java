package com.assp.modules.commondata.mapper;   

import java.util.List;

import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *    图片数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月5日 下午5:43:36 
 */
public interface PictureMapper extends Mapper<Picture>{

	/**
	 * @return 
	 * 
	* @Title: insertMedia 
	* @Description: 插入图片返回id
	* @author (panlinlin@sxw100.com)
	* @param @param media    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	  public int insertPicture(Picture picture);

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
	* @Description: 更新图片状态为不可用
	* @author (panlinlin@sxw100.com)
	* @param @param picture
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updatePictureStatus(Picture picture);
	
	/**
	 * 
	* @Title: updatePictureResult 
	* @Description: 更新图片最终提交结果
	* @author (panlinlin@sxw100.com)
	* @param @param picture
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updatePictureResult(Picture picture);

	/**
	 * 
	* @Title: review_yesById 
	* @Description: 审核通过
	* @author (panlinlin@sxw100.com)
	* @param @param valueOf
	* @param @return    设定文件 
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
	public int updatePictureforDelete(Integer valueOf);

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
	* @Title: batchAddPic 批量插入图片
	* @author (panlinlin@sxw100.com)
	* @param @param picList
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public int batchAddPic(List<Picture> picList);

	/** 
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
  