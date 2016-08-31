package com.assp.modules.commondata.service.impl;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.entity.Media;
import com.assp.modules.commondata.entity.MediaRelType;
import com.assp.modules.commondata.mapper.MediaMapper;
import com.assp.modules.commondata.mapper.MediaRelTypeMapper;
import com.assp.modules.commondata.mapper.MediaTypeMapper;
import com.assp.modules.commondata.service.IMediaService;

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
 * @CreateDate 2016年5月31日 下午5:57:12 
 */
@Service
public class MediaServiceImpl implements IMediaService{

	@Autowired
	 private MediaMapper mediaMapper;
	
	@Autowired
	private MediaTypeMapper mediaTypeMapper;
	
	@Autowired
	private MediaRelTypeMapper mediaRelTypeMapper ;
	
	@Override
	public Media queryOne(Media record) {
		return mediaMapper.selectOne(record);
	}

	@Override
	public List<Media> queryAll(Media record) {
		return mediaMapper.select(record);
	}

	@Override
	public int queryCount(Media record) {
		return mediaMapper.selectCount(record);
	}

	@Override
	public Media queryByPrimaryKey(Object key) {
		return mediaMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Media record) {
		return mediaMapper.insert(record);
	}

	@Override
	public int addSelective(Media record) {
		return mediaMapper.insertSelective(record);
	}

	@Override
	public int delete(Media record) {
		return mediaMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return mediaMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Media record) {
		return mediaMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Media record) {
		return mediaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Media example) {
		return 0;
	}

	@Override
	public List<Media> queryByExample(Media example) {
		return null;
	}

	@Override
	public List<Media> queryMediaByCodition(Media media) {
		List<Media>  medias = mediaMapper.selectMediasByCondition(media);
		for(Media m : medias ){
			MediaRelType mediaRelType = new MediaRelType();
			mediaRelType.setMediaId(m.getMediaId());
			List<MediaRelType> mrts = mediaRelTypeMapper.selectMediaRelTypeByCdt(mediaRelType);
			m.setMediaRelTypes(mrts);
		}
		return medias;
	}

	@Override
	public void addMedia(Media media) {
		mediaMapper.insertMedia(media);
	}

	@Override
	public void batchUpdateMedia(Media media) {
		mediaMapper.batchUpdateMedia(media);
	}

	@Override
	public void updateMediaReadNum(Media mediaId) {
		mediaMapper.updateMediaReadNum(mediaId);
	}

}
  