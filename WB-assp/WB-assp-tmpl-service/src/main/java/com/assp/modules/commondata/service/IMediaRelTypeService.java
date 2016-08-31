package com.assp.modules.commondata.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.commondata.entity.MediaRelType;

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
 * @CreateDate 2016年6月2日 下午2:32:37 
 */
public interface IMediaRelTypeService extends BasicService<MediaRelType> {

	/**批量删除操作以视频ids
	 * @param mediaIds
	 */
	void bathDelByMediaIds(List<Integer> mediaIds);

}
  