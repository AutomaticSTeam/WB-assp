package com.assp.modules.commondata.mapper;   

import java.util.List;

import com.assp.modules.commondata.entity.MediaType;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *  视频类型处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月31日 下午5:53:14 
 */
public interface MediaTypeMapper extends Mapper<MediaType> {

	   /**
	    * 
	   * @Title: selectMediaTypeByMediaId 
	   * @Description: 通过视频查询视频类型集合
	   *@author (shx@sxw100.com)
	   * @param @return    设定文件 
	   * @return List<MediaType>    返回类型 
	   * @throws
	    */
	   public List<MediaType> selectMediaTypeByMediaId(Integer mediaId);
}
  