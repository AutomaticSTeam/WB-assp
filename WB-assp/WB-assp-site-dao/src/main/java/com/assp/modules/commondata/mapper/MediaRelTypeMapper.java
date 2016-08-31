package com.assp.modules.commondata.mapper;   

import java.util.List;

import com.assp.modules.commondata.entity.MediaRelType;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年6月2日 下午2:31:15 
 */
public interface MediaRelTypeMapper extends Mapper<MediaRelType>{

	/**
	 * 
	* @Title: selectMediaRelTypeByCdt 
	* @Description: 通过条件查找关联关系
	*@author (shx@sxw100.com)
	* @param @param mediaRelType
	* @param @return    设定文件 
	* @return List<MediaRelType>    返回类型 
	* @throws
	 */
	public List<MediaRelType> selectMediaRelTypeByCdt(MediaRelType mediaRelType);

	/**批量删除以视频ids
	 * @param mediaIds
	 */
	public void bathDelByMediaIds(List<Integer> mediaIds);
	
}
  