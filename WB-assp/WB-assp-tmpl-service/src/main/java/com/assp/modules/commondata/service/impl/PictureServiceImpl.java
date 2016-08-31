package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.assp.modules.commondata.mapper.PictureMapper;
import com.assp.modules.commondata.service.IPictureService;

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
 * @CreateDate 2016年5月6日 上午11:26:51 
 */
@Service
public class PictureServiceImpl implements IPictureService{
	
	@Autowired
	private PictureMapper  pictureMapper;
 
	@Override
	public Picture queryOne(Picture record) {
		return pictureMapper.selectOne(record);
	}

	@Override
	public List<Picture> queryAll(Picture record) {
		return pictureMapper.select(record);
	}

	@Override
	public int queryCount(Picture record) {
		return pictureMapper.selectCount(record);
	}

	@Override
	public Picture queryByPrimaryKey(Object key) {
		return pictureMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Picture record) {
		return pictureMapper.insert(record);
	}

	@Override
	public int addSelective(Picture record) {
		return pictureMapper.insertSelective(record);
	}

	@Override
	public int delete(Picture record) {
		return pictureMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return pictureMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Picture record) {
		return pictureMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Picture record) {
		return pictureMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Picture example) {
		return pictureMapper.selectCountByExample(example);
	}

	@Override
	public List<Picture> queryByExample(Picture example) {
		return null;
	}

	@Override
	public int addPicture(Picture picture) {
		
		return pictureMapper.insertPicture(picture);
	}

	@Override
	public Picture getPictureById(int picId) {
		
		return pictureMapper.getPictureById(picId);
	}

	@Override
	public int updatePictureStatus(Picture picture) {
		return pictureMapper.updatePictureStatus(picture);
	}

	@Override
	public int updatePictureResult(Picture picture) {
		return pictureMapper.updatePictureResult(picture);
	}

	@Override
	public int review_yesById(Integer valueOf) {
		return pictureMapper.review_yesById(valueOf);
	}

	@Override
	public int updatePictureforDelete(Integer valueOf) {
		return pictureMapper.updatePictureforDelete(valueOf);
	}

	@Override
	public PictureAlbum queryAlbumByPId(Integer pictureId) {
		return pictureMapper.queryAlbumByPId(pictureId);
	}
}
  