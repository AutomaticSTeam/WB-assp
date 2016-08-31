package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.MediaType;
import com.assp.modules.commondata.mapper.MediaTypeMapper;
import com.assp.modules.commondata.service.IMediaTypeService;
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
 * @CreateDate 2016年5月31日 下午6:00:52 
 */
@Service
public class MediaTypeServiceImpl implements IMediaTypeService {

	@Autowired
	private MediaTypeMapper  mediaTypeMapper;
	
	@Override
	public MediaType queryOne(MediaType record) {
		return mediaTypeMapper.selectOne(record);
	}

	@Override
	public List<MediaType> queryAll(MediaType record) {
		return mediaTypeMapper.select(record);
	}

	@Override
	public int queryCount(MediaType record) {
		return mediaTypeMapper.selectCount(record);
	}

	@Override
	public MediaType queryByPrimaryKey(Object key) {
		return mediaTypeMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(MediaType record) {
		return 0;
	}

	@Override
	public int addSelective(MediaType record) {
		return mediaTypeMapper.insertSelective(record);
	}

	@Override
	public int delete(MediaType record) {
		return mediaTypeMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MediaType record) {
		return mediaTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(MediaType record) {
		return mediaTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(MediaType example) {
		return 0;
	}

	@Override
	public List<MediaType> queryByExample(MediaType example) {
		return null;
	}

}
  