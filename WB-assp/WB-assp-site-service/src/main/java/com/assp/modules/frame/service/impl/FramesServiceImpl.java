package com.assp.modules.frame.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.frame.entity.Frames;
import com.assp.modules.frame.mapper.FramesMapper;
import com.assp.modules.frame.service.IFramesService;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

@Service
public class FramesServiceImpl implements IFramesService {
	
	@Autowired
	private  FramesMapper framesMapper;
	
	@Override
	public Frames queryOne(Frames record) {
		// TODO Auto-generated method stub
		return framesMapper.selectOne(record);
	}

	@Override
	public List<Frames> queryAll(Frames record) {
		// TODO Auto-generated method stub
		return framesMapper.select(record);
	}

	@Override
	public int queryCount(Frames record) {
		// TODO Auto-generated method stub
		return framesMapper.selectCount(record);
	}

	@Override
	public Frames queryByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return framesMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Frames record) {
		// TODO Auto-generated method stub
		return framesMapper.insert(record);
	}

	@Override
	public int addSelective(Frames record) {
		// TODO Auto-generated method stub
		return framesMapper.insertSelective(record);
	}

	@Override
	public int delete(Frames record) {
		// TODO Auto-generated method stub
		return framesMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return framesMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Frames record) {
		// TODO Auto-generated method stub
		return framesMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Frames record) {
		// TODO Auto-generated method stub
		return framesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Frames example) {
		// TODO Auto-generated method stub
		Example frameExample = new Example(Frames.class);
		Criteria criteria =  frameExample.createCriteria();
		if(example.getFramesName() != null){
			criteria.andLike("framesName", "%"+example.getFramesName()+"%"); 
		}
		criteria.andEqualTo("dataStatus", Integer.valueOf(0)); 
		frameExample.setOrderByClause("updateTime DESC");
		return framesMapper.selectCountByExample(frameExample);
	}

	@Override
	public List<Frames> queryByExample(Frames example) {
		// TODO Auto-generated method stub
		Example frameExample = new Example(Frames.class);
		Criteria criteria =  frameExample.createCriteria();
		if(example.getFramesName() != null){
			criteria.andLike("framesName", "%"+example.getFramesName()+"%"); 
		}
		criteria.andEqualTo("dataStatus", Integer.valueOf(0)); 
		frameExample.setOrderByClause("updateTime DESC");
		return framesMapper.selectByExample(frameExample);
	}
	
}
