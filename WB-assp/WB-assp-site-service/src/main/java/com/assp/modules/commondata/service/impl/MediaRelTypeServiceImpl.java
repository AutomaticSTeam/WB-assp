package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.MediaRelType;
import com.assp.modules.commondata.mapper.MediaRelTypeMapper;
import com.assp.modules.commondata.service.IMediaRelTypeService;

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
 * @CreateDate 2016年6月2日 下午2:33:17 
 */
@Service
public class MediaRelTypeServiceImpl implements IMediaRelTypeService{
	
	@Autowired
	private MediaRelTypeMapper  mediaRelTypeMapper;

	@Override
	public MediaRelType queryOne(MediaRelType record) {
		return mediaRelTypeMapper.selectOne(record);
	}

	@Override
	public List<MediaRelType> queryAll(MediaRelType record) {
		return mediaRelTypeMapper.select(record);
	}

	@Override
	public int queryCount(MediaRelType record) {
		return mediaRelTypeMapper.selectCount(record);
	}

	@Override
	public MediaRelType queryByPrimaryKey(Object key) {
		return null;
	}

	@Override
	public int add(MediaRelType record) {
		return 0;
	}

	@Override
	public int addSelective(MediaRelType record) {
		return mediaRelTypeMapper.insertSelective(record);
	}

	@Override
	public int delete(MediaRelType record) {
		return mediaRelTypeMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MediaRelType record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(MediaRelType record) {
		return mediaRelTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(MediaRelType example) {
		return 0;
	}

	@Override
	public List<MediaRelType> queryByExample(MediaRelType example) {
		return null;
	}

	@Override
	public void bathDelByMediaIds(List<Integer> mediaIds) {
		// TODO Auto-generated method stub
		mediaRelTypeMapper.bathDelByMediaIds(mediaIds);
	}

}
  