package com.assp.modules.commondata.mapper;   

import java.util.List;

import com.assp.modules.commondata.entity.Media;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *     视频处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月31日 下午5:47:54 
 */
public interface MediaMapper extends Mapper<Media> {

	  /**
	   * 
	  * @Title: insertMedia 
	  * @Description: 插入视频返回id
	  *@author (shx@sxw100.com)
	  * @param @param media    设定文件 
	  * @return void    返回类型 
	  * @throws
	   */
	  public void insertMedia(Media media);
	  
	  /**
	   * 
	  * @Title: selectMediasByCondition 
	  * @Description: 通过条件查找视频
	  *@author (shx@sxw100.com)
	  * @param @param media
	  * @param @return    设定文件 
	  * @return List<Media>    返回类型 
	  * @throws
	   */
	  public List<Media> selectMediasByCondition(Media media);
	  
	  /**
	   * 
	  * @Title: batchUpdateMedia 
	  * @Description: 批量更新视频属性
	  *@author (shx@sxw100.com)
	  * @param @param media    设定文件 
	  * @return void    返回类型 
	  * @throws
	   */
	  public void batchUpdateMedia(Media media );

	  /**
	   * 
	  * @Title: updateMediaReadNum 
	  * @Description: 阅读数量加1
	  * @author (panlinlin@sxw100.com)
	  * @param @param mediaId    设定文件 
	  * @return void    返回类型 
	  * @throws
	   */
	public void updateMediaReadNum(Media mediaId); 
	  
}
  