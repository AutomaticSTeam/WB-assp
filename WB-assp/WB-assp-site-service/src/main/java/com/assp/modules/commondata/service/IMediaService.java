package com.assp.modules.commondata.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.commondata.entity.Media;

/**
 * 类简述
 * <p>
 *   视频服务类
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月31日 下午5:55:52 
 */
public interface IMediaService extends BasicService<Media>{

	/**
	 * 
	* @Title: queryMediaByCodition 
	* @Description: 根据条件查询视频
	*@author (shx@sxw100.com)
	* @param @param media
	* @param @return    设定文件 
	* @return List<Media>    返回类型 
	* @throws
	 */
	public  List<Media> queryMediaByCodition(Media media);
	
	
	/**
	 * 
	* @Title: addMedia 
	* @Description: 添加视频 
	*@author (shx@sxw100.com)
	* @param @param media    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	 public void addMedia(Media media);
	 
	 /**
	  * 
	 * @Title: batchUpdateMedia 
	 * @Description: 批量更新视频信息
	 *@author (shx@sxw100.com)
	 * @param @param media
	 * @param @param mediaIds    设定文件 
	 * @return void    返回类型 
	 * @throws
	  */
	 public void batchUpdateMedia(Media media );

	 /**
	  * 
	 * @Title: updateMediaReadNum 
	 * @Description: 阅读数量增加 
	 * @author (panlinlin@sxw100.com)
	 * @param @param mediaId    设定文件 
	 * @return void    返回类型 
	 * @throws
	  */
	public void updateMediaReadNum(Media media);
	
}
  