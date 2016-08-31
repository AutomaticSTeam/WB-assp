package com.assp.modules.frame.service;

import com.assp.common.service.BasicService;
import com.assp.modules.frame.entity.framesRefFrame;

public interface IFramesRefFrameService extends BasicService<framesRefFrame>{
	
	public framesRefFrame querySortNumMaxObj(Integer framesId);
}
