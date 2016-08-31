package com.assp.modules.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.frame.entity.framesRefFrame;
import com.assp.modules.frame.mapper.FramesRefFrameMapper;
import com.assp.modules.frame.service.IFramesRefFrameService;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

@Service
public class FramesRefFrameServiceImpl implements IFramesRefFrameService {
	
	@Autowired
	private  FramesRefFrameMapper framesRefFrameMapper;

	@Override
	public framesRefFrame queryOne(framesRefFrame record) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.selectOne(record);
	}

	@Override
	public List<framesRefFrame> queryAll(framesRefFrame record) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.select(record);
	}

	@Override
	public int queryCount(framesRefFrame record) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.selectCount(record);
	}

	@Override
	public framesRefFrame queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(framesRefFrame record) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.insert(record);
	}

	@Override
	public int addSelective(framesRefFrame record) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.insertSelective(record);
	}

	@Override
	public int delete(framesRefFrame record) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(framesRefFrame record) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(framesRefFrame record) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(framesRefFrame example) {
		// TODO Auto-generated method stub
		return framesRefFrameMapper.selectCountByExample(example);
	}

	@Override
	public List<framesRefFrame> queryByExample(framesRefFrame example) {
		// TODO Auto-generated method stub
		Example frameExample = new Example(framesRefFrame.class);
		Criteria criteria =  frameExample.createCriteria();
		criteria.andEqualTo("framesId", example.getFramesId()); 
		criteria.andEqualTo("relStatus", Integer.valueOf(0)); 
		frameExample.setOrderByClause("frameId ASC");
		return framesRefFrameMapper.selectByExample(frameExample);
	}
	
}
