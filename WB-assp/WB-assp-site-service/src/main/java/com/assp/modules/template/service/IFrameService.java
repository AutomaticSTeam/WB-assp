package com.assp.modules.template.service;   

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.frame.entity.Frame;

/**
 * 类简述
 * <p>
 *     栏目服务接口
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (zhangtl@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年7月21日 上午9:43:38 
 */
public interface IFrameService extends BasicService<Frame>{
	List<Frame> queryFrameByColumnsId(Integer columnsId);
}
  