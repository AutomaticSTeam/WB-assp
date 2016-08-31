package com.assp.modules.commondata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.PictureAlbumType;
import com.assp.modules.commondata.mapper.PictureAlbumTypeMapper;
import com.assp.modules.commondata.service.IPictureAlbumTypeService;

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
public class PictureAlbumTypeServiceImpl  implements IPictureAlbumTypeService{

	@Autowired
	private PictureAlbumTypeMapper pictureAlbumTypeMapper;
	
	@Override
	public PictureAlbumType queryOne(PictureAlbumType record) {
		return pictureAlbumTypeMapper.selectOne(record);
	}

	@Override
	public List<PictureAlbumType> queryAll(PictureAlbumType record) {
		return pictureAlbumTypeMapper.select(record);
	}

	@Override
	public int queryCount(PictureAlbumType record) {
		return pictureAlbumTypeMapper.selectCount(record);
	}

	@Override
	public PictureAlbumType queryByPrimaryKey(Object key) {
		return pictureAlbumTypeMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(PictureAlbumType record) {
		return pictureAlbumTypeMapper.insert(record);
	}

	@Override
	public int addSelective(PictureAlbumType record) {
		return pictureAlbumTypeMapper.insertSelective(record);
	}

	@Override
	public int delete(PictureAlbumType record) {
		return pictureAlbumTypeMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return pictureAlbumTypeMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(PictureAlbumType record) {
		return pictureAlbumTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(PictureAlbumType record) {
		return pictureAlbumTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(PictureAlbumType example) {
		return pictureAlbumTypeMapper.selectCountByExample(example);
	}

	@Override
	public List<PictureAlbumType> queryByExample(PictureAlbumType example) {
		return pictureAlbumTypeMapper.selectByExample(example);
	}

	@Override
	public List<PictureAlbumType> queryChildrenByPid(Integer albumTypePId) {
		return pictureAlbumTypeMapper.queryChildrenByPid(albumTypePId);
	}

	@Override
	public List<PictureAlbumType> getAllParetTypes(Integer albumTypeId) {
		
		List<PictureAlbumType> paretTypes=new ArrayList<>();
		
		PictureAlbumType albumType=queryByPrimaryKey(Integer.valueOf(albumTypeId));
		paretTypes.add(albumType);
		
		if(albumType.getAlbumTypePid()!=null){
			PictureAlbumType albumType2=queryByPrimaryKey(albumType.getAlbumTypePid());
			paretTypes.add(albumType2);
		}
		return paretTypes;
	}

}
  