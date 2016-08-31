package com.assp.modules.commondata.mapper;

import java.util.List;

import com.assp.modules.commondata.entity.PictureAlbumRelType;
import com.github.abel533.mapper.Mapper;
/**
 * 
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (wangzhipeng@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年6月21日 
 */
public interface PictureAlbumRelTypeMapper  extends Mapper<PictureAlbumRelType>{

	/**批量插入
	 * @param examples
	 * @return
	 */
	void addBatch(List<PictureAlbumRelType> examples);
	
}