package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.assp.modules.commondata.entity.PictureAlbumType;
import com.assp.modules.commondata.mapper.PictureAlbumMapper;
import com.assp.modules.commondata.service.IPictureAlbumService;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

/**
 * 类简述
 * <p>
 *     图册类型
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月6日 上午11:40:56 
 */
@Service
public class PictureAlbumServiceImpl implements IPictureAlbumService{
	
	@Autowired
	private  PictureAlbumMapper  pictureAlbumMapper;

	@Override
	public PictureAlbum queryOne(PictureAlbum record) {
		return pictureAlbumMapper.selectOne(record);
	}

	@Override
	public List<PictureAlbum> queryAll(PictureAlbum record) {
		return pictureAlbumMapper.select(record);
	}

	@Override
	public int queryCount(PictureAlbum record) {
		return pictureAlbumMapper.selectCount(record);
	}

	@Override
	public PictureAlbum queryByPrimaryKey(Object key) {
		return pictureAlbumMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(PictureAlbum record) {
		return pictureAlbumMapper.insert(record);
	}

	@Override
	public int addSelective(PictureAlbum record) {
		return pictureAlbumMapper.insertSelective(record);
	}

	@Override
	public int delete(PictureAlbum record) {
		return pictureAlbumMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return pictureAlbumMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(PictureAlbum record) {
		return pictureAlbumMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(PictureAlbum record) {
		return pictureAlbumMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(PictureAlbum example) {
		return pictureAlbumMapper.selectCountByExample(example);
	}

	@Override
	public List<PictureAlbum> queryByExample(PictureAlbum example) {
		Example  paExample = new Example(PictureAlbum.class);
		Criteria criteria = paExample.createCriteria();
		
		return pictureAlbumMapper.selectByExample(example);
	}

	@Override
	public int addAlbumAndGetprimaryKey(PictureAlbum pictureAlbum) {
		return pictureAlbumMapper.addAlbumAndGetprimaryKey(pictureAlbum);
	}

	@Override
	public int updataAlbunById(PictureAlbum pictureAlbum) {
		return pictureAlbumMapper.updataAlbunById(pictureAlbum);
	}

	@Override
	public List<Picture> queryPicByAlbumId(Integer valueOf) {
		return pictureAlbumMapper.queryPicByAlbumId(valueOf);
	}

	@Override
	public List<Picture> queryPicByAlbumIdToReview(Integer valueOf) {
		return pictureAlbumMapper.queryPicByAlbumIdToReview(valueOf);
	}

	@Override
	public List<PictureAlbum> queryAlbumAllAndReview(Object object) {
		return pictureAlbumMapper.queryAlbumAllAndReview(object);
	}
	
	@Override
	public List<PictureAlbum> queryAlbumAllAndReviewInRelType(Object object) {
		return pictureAlbumMapper.queryAlbumAllAndReviewInRelType(object);
	}


	@Override
	public PictureAlbumType queryAlbumTypeByAlbumId(int albumId) {
		return pictureAlbumMapper.queryAlbumTypeByAlbumId(albumId);
	}

	@Override
	public List<PictureAlbum> queryAllAlbumByTypeId(Integer albumTypeId) {
		List<PictureAlbum> allAlbums = pictureAlbumMapper.queryAllAlbumByTypeId(albumTypeId);
		//将图册首张图片放入
				for (int i = 0; i < allAlbums.size(); i++) {
					List<Picture> picByAlbumIds = queryPicByAlbumId(allAlbums.get(i).getAlbumId());
					if(picByAlbumIds.size()>0){
						allAlbums.get(i).setPicture(picByAlbumIds.get(0));
						//空图册不显示
					}else{
						allAlbums.remove(i);
					}
				}
		return allAlbums;
	}

	@Override
	public List<Picture> queryReviewPicByAlbumId(Integer albumId) {
		return pictureAlbumMapper.queryReviewPicByAlbumId(albumId);
	}

	

}
  