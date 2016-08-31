package com.assp.modules.commondata.mapper;   

import java.util.List;
import java.util.Map;

import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.assp.modules.commondata.entity.PictureAlbumType;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *    图册数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月5日 下午5:42:28 
 */
public interface PictureAlbumMapper extends Mapper<PictureAlbum> {

	/**
	 * 
	* @Title: addAlbumAndGetprimaryKey 
	* @Description: 添加相册并返回主键
	* @author (panlinlin@sxw100.com)
	* @param @param pictureAlbum
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int addAlbumAndGetprimaryKey(PictureAlbum pictureAlbum);

	/**
	 * 
	* @Title: updataAlbunById 
	* @Description: 编辑相册
	* @author (panlinlin@sxw100.com)
	* @param @param pictureAlbum
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updataAlbunById(PictureAlbum pictureAlbum);

	/**
	 * 
	* @Title: queryPicByAlbumId 
	* @Description: 根据图册id查询图片
	* @author (panlinlin@sxw100.com)
	* @param @param valueOf
	* @param @return    设定文件 
	* @return PictureAlbum    返回类型 
	* @throws
	 */
	public List<Picture> queryPicByAlbumId(Integer valueOf);

	/**
	 * 
	* @Title: queryPicByAlbumIdToReview 
	* @Description: 查询待审核图片
	* @author (panlinlin@sxw100.com)
	* @param @param valueOf
	* @param @return    设定文件 
	* @return List<Picture>    返回类型 
	* @throws
	 */
	public List<Picture> queryPicByAlbumIdToReview(Integer valueOf);

	/**
	 * 
	* @Title: queryAlbumAllAndReview 
	* @Description: 查询全部相册显示是否存在需审核图片
	* @author (panlinlin@sxw100.com)
	* @param @param object
	* @param @return    设定文件 
	* @return List<PictureAlbum>    返回类型 
	* @throws
	 */
	public List<PictureAlbum> queryAlbumAllAndReview(Object object);
	/**
	 * 
	 * @Title: queryAlbumAllAndReviewInRelType 
	 * @Description: 查询全部相册显示是否存在需审核图片,存在关联的type
	 * @author (panlinlin@sxw100.com)
	 * @param @param object
	 * @param @return    设定文件 
	 * @return List<PictureAlbum>    返回类型 
	 * @throws
	 */
	public List<PictureAlbum> queryAlbumAllAndReviewInRelType(Object object);

	
	/**
	 * 查询相册分类
	* @Title: queryAlbumTypeByAlbumId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author (panlinlin@sxw100.com)
	* @param @param albumId
	* @param @return    设定文件 
	* @return PictureAlbumType    返回类型 
	* @throws
	 */
	public PictureAlbumType queryAlbumTypeByAlbumId(int albumId);

	/**
	 * 
	* @Title: queryAllAlbumByTypeId 
	* @Description: 查询当前分类下的所有图册
	* @author (panlinlin@sxw100.com)
	* @param @param albumTypeId
	* @param @return    设定文件 
	* @return List<PictureAlbum>    返回类型 
	* @throws
	 */
	public List<PictureAlbum> queryAllAlbumByTypeId(Integer albumTypeId);
	
	/**
	 * 
	* @Title: queryPicByPragrams 
	* @Description: 通过条件查找图册图片
	*@author (shx@sxw100.com)
	* @param @param pragram
	* @param @return    设定文件 
	* @return List<Picture>    返回类型 
	* @throws
	 */
	public List<Picture> queryPicByPragrams(Map<String,Object> pragram);

	/**
	 * 
	* @Title: queryReviewPicByAlbumId 
	* @Description: 根据相册id查询相册中已经审核的图片
	* @author (panlinlin@sxw100.com)
	* @param @param albumId
	* @param @return    设定文件 
	* @return List<Picture>    返回类型 
	* @throws
	 */
	public List<Picture> queryReviewPicByAlbumId(Integer albumId);
	
	/**
	 * 
	* @Title: batchUpdatePictureAlbum 
	* @Description: TODO(批量修改图册内容) 
	*@author (wangkang@sxw100.com)
	* @param @param pictureAlbum    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void batchUpdatePictureAlbum(PictureAlbum pictureAlbum);

}
  