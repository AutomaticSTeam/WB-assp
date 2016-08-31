package com.assp.modules.template.service.impl;   

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assp.modules.commondata.mapper.PictureAlbumMapper;
import com.assp.modules.module.entity.TemplateModule;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.entity.TemplateColumns;
import com.assp.modules.template.entity.TemplateColumnsRelFrame;
import com.assp.modules.template.entity.TemplateCommonRelFrame;
import com.assp.modules.template.entity.TemplateFooter;
import com.assp.modules.template.entity.TemplateFooterRelColumns;
import com.assp.modules.template.entity.TemplateFrameRelModule;
import com.assp.modules.template.entity.TemplateLogo;
import com.assp.modules.template.entity.TemplateRelColumns;
import com.assp.modules.template.entity.TemplateRelFrame;
import com.assp.modules.template.entity.TemplateVo;
import com.assp.modules.template.mapper.TemplateBannerMapper;
import com.assp.modules.template.mapper.TemplateColumnsMapper;
import com.assp.modules.template.mapper.TemplateColumnsRelFrameMapper;
import com.assp.modules.template.mapper.TemplateCommonRelFrameMapper;
import com.assp.modules.template.mapper.TemplateFooterMapper;
import com.assp.modules.template.mapper.TemplateFrameRelModuleMapper;
import com.assp.modules.template.mapper.TemplateLogoMapper;
import com.assp.modules.template.mapper.TemplateMapper;
import com.assp.modules.template.mapper.TemplateRelColumnsMapper;
import com.assp.modules.template.service.ITemplateService;
import com.assp.modules.website.entity.Website;
import com.assp.modules.website.mapper.WebsiteMapper;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

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
 * @CreateDate 2016年4月26日 下午5:18:31 
 */
@Service
public class TemplateServiceImpl implements ITemplateService {
	
	@Autowired
	private TemplateMapper templateMapper;
	
	@Autowired
	private TemplateColumnsMapper templateColumnsMapper;
	
	@Autowired
	private TemplateRelColumnsMapper templateRelColumnsMapper;
	
	@Autowired
	private TemplateFooterMapper templateFooterMapper;
	
	@Autowired 
	private TemplateBannerMapper templateBannerMapper;
	
	@Autowired
	private  TemplateLogoMapper templateLogoMapper;
	
	@Autowired
	private TemplateColumnsRelFrameMapper templateColumnsRelFrameMapper;
	
	@Autowired
	private TemplateCommonRelFrameMapper templateCommonRelFrameMapper;
	
	@Autowired
	private TemplateFrameRelModuleMapper templateFrameRelModuleMapper;
	
	@Autowired
	private PictureAlbumMapper  pictureAlbumMapper;

	@Autowired
	private WebsiteMapper websiteMapper;
	
	@Override
	public Template queryOne(Template record) {
		return templateMapper.selectOne(record);
	}

	@Override
	public List<Template> queryAll(Template record) {
		return templateMapper.select(record);
	}

	@Override
	public int queryCount(Template record) {
		return templateMapper.selectCount(record);
	}

	@Override
	public Template queryByPrimaryKey(Object key) {
		return templateMapper.selectByPrimaryKey(key);
	}

	@Override
	public int add(Template record) {
		return templateMapper.insert(record);
	}

	@Override
	public int addSelective(Template record) {
		return templateMapper.insertSelective(record);
	}

	@Override
	public int delete(Template record) {
		return templateMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return templateMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(Template record) {
		return templateMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Template record) {
		return templateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int queryCountByExample(Template example) {
		Example templateExample = new Example(Template.class);
		Criteria criteria =  templateExample.createCriteria();
		criteria.andNotEqualTo("dataStatus", Integer.valueOf(1)); 
		templateExample.setOrderByClause("updateTime DESC");
		return templateMapper.selectCountByExample(templateExample);
	}

	@Override
	public List<Template> queryByExample(Template example) {
		Example templateExample = new Example(Template.class);
		Criteria criteria =  templateExample.createCriteria();
		criteria.andNotEqualTo("dataStatus", Integer.valueOf(1)); 
		templateExample.setOrderByClause("updateTime DESC");
		return templateMapper.selectByExample(templateExample);
	}

	@Override
	public TemplateVo qeruyTemplatePage(Template tempalte) {
		if(tempalte == null ) return null;
		TemplateVo templateVo = new TemplateVo();
		
		/**设置页面logo*/
		if(tempalte.getTemplateLogoId() != null){
			TemplateLogo tl = new  TemplateLogo();
			tl.setTemplateLogoId(tempalte.getTemplateLogoId());
			templateVo.setTemplateLogo(templateLogoMapper.selectOne(tl));
		}
	
		/**设置页面栏目*/
		List<TemplateColumns> templateColumns = templateColumnsMapper.selectTemplateColumnsByTemplateId(tempalte.getTemplateId());
		templateVo.setTemplateColumns(templateColumns);
		
		/**设置页面banner*/
		if( null != tempalte.getTemplateBannerId() ){
			Map<String,Object> pragram = new HashMap<String,Object>();
			pragram.put("id", tempalte.getTemplateBannerId());
			pragram.put("limitNum", 5);
			templateVo.setBannerPictures(pictureAlbumMapper.queryPicByPragrams(pragram));
		}
		/*TemplateBanner tb = new TemplateBanner();
		tb.setTemplateId(tempalte.getTemplateId());
		List<TemplateBanner> templateBanners = templateBannerMapper.select(tb);
		templateVo.setTemplateBanners(templateBanners);*/
		
		/**设置页面页脚*/
		if(tempalte.getTemplateFooterId() != null){
			TemplateFooter tf = new TemplateFooter();
			tf.setTemplateFooterId(tempalte.getTemplateFooterId());
			TemplateFooter templateFooter = templateFooterMapper.selectOne(tf);
			if(templateFooter != null){
				//List<TemplateColumns> footerColumns = templateColumnsMapper.selectTemplateColumnsByTemplateFooterId(tempalte.getTemplateFooterId());
				TemplateFooterRelColumns trc = new TemplateFooterRelColumns();
				trc.setTemplateFooterId(tempalte.getTemplateFooterId());
				trc.setColumnsHide(0);
				List<TemplateColumns> footerColumns = templateColumnsMapper.selectTemplateColumnsByTemplateFooterRelColumns(trc);
				templateFooter.setTemplateColumns(footerColumns);
			}
			templateVo.setTemplateFooter(templateFooter);
		}
		return templateVo;
	}

	@Override
	public List<TemplateModule> queryTemplateDynamicModules(Template tempalte) {
		return null;
	}

	@Override
	public List<TemplateColumnsRelFrame> queryPageFrameInfo(Integer columnsId) {
		List<TemplateColumnsRelFrame> templateColumnsRelFrames = new ArrayList<TemplateColumnsRelFrame>();
		/*TemplateColumnsRelFrame tcr = new TemplateColumnsRelFrame();
		tcr.setColumnsId(columnsId);
		tcr.setColumnsRelFramePid(Integer.valueOf(0));
		tcr.setRelStatus(Integer.valueOf(0));*/
		Example templateExample = new Example(TemplateColumnsRelFrame.class);
		Criteria criteria =  templateExample.createCriteria();
		criteria.andNotEqualTo("relStatus", Integer.valueOf(1)); 
		criteria.andEqualTo("columnsRelFramePid", Integer.valueOf(0));
		criteria.andEqualTo("columnsId", columnsId);
		templateExample.setOrderByClause("sortNum asc");
	    templateColumnsRelFrames = templateColumnsRelFrameMapper.selectByExample(templateExample);
	    if(templateColumnsRelFrames != null && templateColumnsRelFrames.size() > 0){
		   	 for(TemplateColumnsRelFrame crf : templateColumnsRelFrames){
		   		/*TemplateColumnsRelFrame subTcr = new TemplateColumnsRelFrame();
		   		subTcr.setColumnsId(columnsId);
		   		subTcr.setColumnsRelFramePid(crf.getColumnsRelFrameId());
		   		subTcr.setRelStatus(Integer.valueOf(0));*/
		   		templateExample = new Example(TemplateColumnsRelFrame.class);
		   		criteria =  templateExample.createCriteria();
				criteria.andNotEqualTo("relStatus", Integer.valueOf(1)); 
				criteria.andEqualTo("columnsRelFramePid", crf.getColumnsRelFrameId());
				criteria.andEqualTo("columnsId", columnsId);
				templateExample.setOrderByClause("sortNum asc");
		   		List<TemplateColumnsRelFrame> subTemplateColumnsRelFrame =   templateColumnsRelFrameMapper.selectByExample(templateExample);
		   		if(subTemplateColumnsRelFrame != null  && subTemplateColumnsRelFrame.size() > 0){
		   			crf.setSubTemplateColumnsRelFrame(subTemplateColumnsRelFrame);
		   			for(TemplateColumnsRelFrame sub : subTemplateColumnsRelFrame){
		   				List<TemplateFrameRelModule> subtfr = getTemplateFrameRelModules(sub) ;
		   				sub.setTemplateFrameRelModules((subtfr != null && subtfr.size() > 0) ? subtfr : null);
		   			}
		   		}
			     crf.setTemplateFrameRelModules(getTemplateFrameRelModules(crf));
			 }
	    }
		return templateColumnsRelFrames;
	}
	
	@Override
	public List<TemplateRelFrame> queryPageFrameInfo(Template template,Integer columnsId, List<TemplateCommonRelFrame> tcrList) {
		
		List<TemplateRelFrame> templateRelFrames = queryPageFrameInfo(template,columnsId);
		
		if(tcrList != null && tcrList.size() > 0){
			for(TemplateCommonRelFrame tcr : tcrList){
				templateRelFrames.addAll(getTemplateCommonRelFrames(tcr));
			}
		}
		//排序
		Collections.sort(templateRelFrames, new Comparator<TemplateRelFrame>(){
			@Override
			public int compare(TemplateRelFrame o1, TemplateRelFrame o2) {
				// TODO Auto-generated method stub
				return (o1.getSortNum() == null || o2.getSortNum() == null) ? 0 : (o1.getSortNum().intValue() - o2.getSortNum().intValue());
			}
			
		});
		return templateRelFrames;
	}
	@Override
	public List<TemplateRelFrame> queryPageFrameInfo(Template template,Integer columnsId) {
		
		List<TemplateRelFrame> templateRelFrames = new ArrayList<TemplateRelFrame>();
		
		//获取公共加载模块儿
		if(template != null && template.getTemplateId() != null){
			TemplateCommonRelFrame tcr = new TemplateCommonRelFrame();
			tcr.setTemplateId(template.getTemplateId());
			tcr.setCommonRelFramePid(0);
			tcr.setCommonType(0);
			tcr.setRelStatus(Integer.valueOf(0));
			// 添加进公用类
			templateRelFrames.addAll(getTemplateCommonRelFrames(tcr));
		}
		//如果有columnid,加载columns
		if(columnsId != null){
			List<TemplateColumnsRelFrame> list = queryPageFrameInfo(columnsId);
			templateRelFrames.addAll(list);
			//排序
			Collections.sort(templateRelFrames, new Comparator<TemplateRelFrame>(){
				@Override
				public int compare(TemplateRelFrame o1, TemplateRelFrame o2) {
					// TODO Auto-generated method stub
					return (o1.getSortNum() == null || o2.getSortNum() == null) ? 0 : (o1.getSortNum().intValue() - o2.getSortNum().intValue());
				}
				
			});
		}
		
		return templateRelFrames;
	}
	
	private List<TemplateCommonRelFrame> getTemplateCommonRelFrames(TemplateCommonRelFrame tcr){
		List<TemplateCommonRelFrame> templist = new ArrayList<TemplateCommonRelFrame>();
		List<TemplateCommonRelFrame> list = templateCommonRelFrameMapper.select(tcr);
		if(list != null && list.size() > 0){
			for(TemplateCommonRelFrame crf : list){
				TemplateCommonRelFrame subTcr = new TemplateCommonRelFrame();
				subTcr.setTemplateId(tcr.getTemplateId());
				subTcr.setCommonRelFramePid(crf.getCommonRelFrameId());
				subTcr.setRelStatus(Integer.valueOf(0));
				List<TemplateCommonRelFrame> subTemplateCommonsRelFrame =   templateCommonRelFrameMapper.select(subTcr);
				if(subTemplateCommonsRelFrame != null  && subTemplateCommonsRelFrame.size() > 0){
					crf.setSubTemplateCommonRelFrame(subTemplateCommonsRelFrame);
					for(TemplateCommonRelFrame sub : subTemplateCommonsRelFrame){
						List<TemplateFrameRelModule> subtfr = getTemplateFrameRelModules(sub) ;
						sub.setTemplateFrameRelModules((subtfr != null && subtfr.size() > 0) ? subtfr : null);
					}
				}
				crf.setTemplateFrameRelModules(getTemplateFrameRelModules(crf));
				templist.add(crf);
			}
		}
		return templist;
	}
	private List<TemplateFrameRelModule>  getTemplateFrameRelModules(TemplateColumnsRelFrame crf ){
		 TemplateFrameRelModule frm = new TemplateFrameRelModule();
		 frm.setColumnsRelFrameId(crf.getColumnsRelFrameId());
		 return  templateFrameRelModuleMapper.select(frm);
	}
	private List<TemplateFrameRelModule>  getTemplateFrameRelModules(TemplateCommonRelFrame crf ){
		TemplateFrameRelModule frm = new TemplateFrameRelModule();
		frm.setCommonRelFrameId(crf.getCommonRelFrameId());
		return  templateFrameRelModuleMapper.select(frm);
	}

	@Override
	public List<Template> queryTemplateByCondition(Template example) {
		// TODO Auto-generated method stub
		return templateMapper.queryTemplateByCondition(example);
	}

	@Override
	public List<TemplateRelColumns> queryTemplateRelColumnsByCondition(
			Template example) {
		// TODO Auto-generated method stub
		TemplateRelColumns tem = new TemplateRelColumns();
		if(example!=null)
			tem.setTemplateId(example.getTemplateId());
		
		return templateRelColumnsMapper.selectByOrder(tem);
	}

	@Override
	public List<Template> queryTemplateIncollectByCondition(Template example) {
		// TODO Auto-generated method stub
		return templateMapper.queryTemplateIncollectByCondition(example);
	}

	@Override
	public void batchUpdateTemplate(Template example) {
		// TODO Auto-generated method stub
		templateMapper.batchUpdateTemplate(example);
	}

	@Override
	public int insertKey(Template template) {
		// TODO Auto-generated method stub
		return templateMapper.insertKey(template);
	}

	@Override
	public Template queryMaxTemplateCode(Template template) {
		// TODO Auto-generated method stub
		return templateMapper.queryMaxTemplateCode(template);
	}


	@Override
	public void executeSync(Website website) {
	//生成同步脚本入口
		//websiteMapper.creaetMainSyncSql();
	//更改同步状态
		//websiteMapper.changeSyncSql();
	//执行存储过程	
	websiteMapper.executeSync(website);
		
	}

}
  