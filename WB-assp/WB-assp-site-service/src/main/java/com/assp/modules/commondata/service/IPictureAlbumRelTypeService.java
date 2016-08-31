package com.assp.modules.commondata.service;

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.commondata.entity.PictureAlbumRelType;

/**
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * 
  * @Company 动力威视
  * @Copyright
  * @author (wangzhipeng@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年6月21日 下午6:02:28 
  */
public interface IPictureAlbumRelTypeService extends BasicService<PictureAlbumRelType> {

	void addBatch(List<PictureAlbumRelType> pps);

}
  