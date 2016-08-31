package com.assp.modules.frame.mapper;   

import java.util.List;

import com.assp.modules.frame.entity.Frame;
import com.github.abel533.mapper.Mapper;

/**
 * 类简述
 * <p>
 *     框架板式数据处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月10日 下午5:08:48 
 */
public interface FrameMapper extends Mapper<Frame>{
	/**
	 * 根据导航查询模块
	 * @param columnsId
	 * @return
	 */
	List<Frame> queryFrameByColumnsId(Integer columnsId);
}
  