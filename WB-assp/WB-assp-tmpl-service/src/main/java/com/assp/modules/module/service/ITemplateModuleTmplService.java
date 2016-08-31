package com.assp.modules.module.service;

import java.util.List;

import com.assp.common.service.BasicService;
import com.assp.modules.module.entity.TemplateModuleTmpl;
import com.github.abel533.entity.Example;

public interface ITemplateModuleTmplService extends BasicService<TemplateModuleTmpl>{

	List<TemplateModuleTmpl> vagueQueryByExample(Example moduleTmplExample);

	
}
