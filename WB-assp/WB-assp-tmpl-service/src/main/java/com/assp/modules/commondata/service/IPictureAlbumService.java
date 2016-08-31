package com.assp.modules.commondata.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.assp.modules.commondata.entity.PictureAlbumType;

/**
 * 类简述
 * <p>
 *   图册类型业务处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 上午11:40:16 
 */
public interface IPictureAlbumService extends BasicService<PictureAlbum> {
	
/**
 * @return 
 * 
* @Title: addAlbumAndGetprimaryKey 
* @Description: 添加图册并返回主键
* @author (panlinlin@sxw100.com)
* @param @param pictureAlbum    设定文件 
* @return void    返回类型 
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
* @Description: 根据相册id查询查询图片
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
* @Description: 查询全部相册显示是否存在需审核图片挂相关所属分类，
* @author (panlinlin@sxw100.com)
* @param @param object
* @param @return    设定文件 
* @return List<PictureAlbum>    返回类型 
* @throws
 */
public List<PictureAlbum> queryAlbumAllAndReviewInRelType(Object object);


/**
 * 
* @Title: queryAlbumTypeByAlbumId 
* @Description:查询相册所有属的分类
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
* @Description: 查询该分类下的所有图册 
* @author (panlinlin@sxw100.com)
* @param @param albumTypeId
* @param @return    设定文件 
* @return List<PictureAlbum>    返回类型 
* @throws
 */
public List<PictureAlbum> queryAllAlbumByTypeId(Integer albumTypeId);
/**
 * 
* @Title: queryReviewPicByAlbumId 
* @Description: 根据图册id查询已审核的图片
* @author (panlinlin@sxw100.com)
* @param @param albumId
* @param @return    设定文件 
* @return List<Picture>    返回类型 
* @throws
 */
public List<Picture> queryReviewPicByAlbumId(Integer albumId);

		
}
  