package com.assp.modules.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.frame.entity.Frame;
import com.assp.modules.frame.mapper.FrameMapper;
import com.assp.modules.frame.service.IFrameService;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

@Service
public class frameServiceImple implements IFrameService {
	
	@Autowired
	private  FrameMapper frameMapper;
	
	@Override
	public Frame queryOne(Frame record) {
		// TODO Auto-generated method stub
		return frameMapper.selectOne(record);
	}

	@Override
	public List<Frame> queryAll(Frame record) {
		// TODO Auto-generated method stub
		return frameMapper.select(record);
	}

	@Override
	public int queryCount(Frame record) {
		// TODO Auto-generated method stub
		return frameMapper.selectCount(record);
	}

	@Override
	public Frame queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return frameMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Frame record) {
		// TODO Auto-generated method stub
		return frameMapper.insert(record);
	}

	@Override
	public int addSelective(Frame record) {
		// TODO Auto-generated method stub
		return frameMapper.insertSelective(record);
	}

	@Override
	public int delete(Frame record) {
		// TODO Auto-generated method stub
		return frameMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return frameMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Frame record) {
		// TODO Auto-generated method stub
		return frameMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Frame record) {
		// TODO Auto-generated method stub
		return frameMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Frame example) {
		// TODO Auto-generated method stub
		Example frameExample = new Example(Frame.class);
		Criteria criteria =  frameExample.createCriteria();
		if(example.getFrameName() != null){
			criteria.andLike("frameName", "%"+example.getFrameName()+"%"); 
		}
		criteria.andEqualTo("dataStatus", Integer.valueOf(0)); 
		frameExample.setOrderByClause("updateTime DESC");
		return frameMapper.selectCountByExample(frameExample);
	}

	@Override
	public List<Frame> queryByExample(Frame example) {
		// TODO Auto-generated method stub
		Example frameExample = new Example(Frame.class);
		Criteria criteria =  frameExample.createCriteria();
		if(example.getFrameName() != null){
			criteria.andLike("frameName", "%"+example.getFrameName()+"%"); 
		}
		criteria.andEqualTo("dataStatus", Integer.valueOf(0)); 
		frameExample.setOrderByClause("updateTime DESC");
		return frameMapper.selectByExample(frameExample);
	}
	
}
