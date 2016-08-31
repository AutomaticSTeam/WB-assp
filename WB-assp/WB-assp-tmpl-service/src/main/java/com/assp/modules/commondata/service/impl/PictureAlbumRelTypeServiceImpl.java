package com.assp.modules.commondata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.PictureAlbumRelType;
import com.assp.modules.commondata.mapper.PictureAlbumRelTypeMapper;
import com.assp.modules.commondata.service.IPictureAlbumRelTypeService;

/**
  * 类简述
  * <p>
  * 类说明、详细描述(optional)
  * </p>
  * 
  * @Company 动力威视
  * @Copyright
  * @author (panlinlin@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年6月18日 下午5:51:22 
  */
@Service
public class PictureAlbumRelTypeServiceImpl  implements IPictureAlbumRelTypeService{

	@Autowired
	private PictureAlbumRelTypeMapper pictureAlbumRelTypeMapper;
	
	@Override
	public PictureAlbumRelType queryOne(PictureAlbumRelType record) {
		return pictureAlbumRelTypeMapper.selectOne(record);
	}

	@Override
	public List<PictureAlbumRelType> queryAll(PictureAlbumRelType record) {
		return pictureAlbumRelTypeMapper.select(record);
	}

	@Override
	public int queryCount(PictureAlbumRelType record) {
		return pictureAlbumRelTypeMapper.selectCount(record);
	}

	@Override
	public PictureAlbumRelType queryByPrimaryKey(Object key) {
		return pictureAlbumRelTypeMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(PictureAlbumRelType record) {
		return pictureAlbumRelTypeMapper.insert(record);
	}

	@Override
	public int addSelective(PictureAlbumRelType record) {
		return pictureAlbumRelTypeMapper.insertSelective(record);
	}

	@Override
	public int delete(PictureAlbumRelType record) {
		return pictureAlbumRelTypeMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return pictureAlbumRelTypeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(PictureAlbumRelType record) {
		return pictureAlbumRelTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(PictureAlbumRelType record) {
		return pictureAlbumRelTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(PictureAlbumRelType example) {
		return pictureAlbumRelTypeMapper.selectCountByExample(example);
	}

	@Override
	public List<PictureAlbumRelType> queryByExample(PictureAlbumRelType example) {
		return pictureAlbumRelTypeMapper.selectByExample(example);
	}

	@Override
	public void addBatch(List<PictureAlbumRelType> pps) {
		// TODO Auto-generated method stub
		pictureAlbumRelTypeMapper.addBatch(pps);
	}

}
  