package com.assp.modules.contents.web.vedio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.utils.DateUtils;
import com.assp.common.web.BaseController;
import com.assp.modules.commondata.entity.Media;
import com.assp.modules.commondata.entity.MediaRelType;
import com.assp.modules.commondata.entity.MediaType;
import com.assp.modules.commondata.service.IMediaRelTypeService;
import com.assp.modules.commondata.service.IMediaService;
import com.assp.modules.commondata.service.IMediaTypeService;
import com.assp.modules.sys.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
  * 类简述
  * <p>
  *     视频控制器
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (shx@sxw100.com)
  * @version 1.0
  * @CreateDate 2016年5月30日 下午3:12:55
 */
@Controller
@RequestMapping("/contents/media")
public class VedioController extends BaseController {
	
	@Autowired
	private IMediaTypeService iMediaTypeService; //视频类型服务
	
	@Autowired
	private IMediaService   iMediaService; //视频服务类
	
	@Autowired
	 private IMediaRelTypeService iMediaRelTypeService; //视频关联类型
	
	/*******************************************视频相关开始************************************************/
	/**
	 * 
	* @Title: queryMediasByPage 
	* @Description:    分页查询视频
	*@author (shx@sxw100.com)
	* @param @param currentPage
	* @param @param pageSize
	* @param @param flag
	* @param @param sort
	* @param @param orderColum
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/queryMediasByPage")
	public void queryMediasByPage(
			Media media,
			@RequestParam(value = "page", required = false) Integer currentPage, 
			@RequestParam(value = "rows", required = false) Integer pageSize,
			@RequestParam(value = "typeId", required = false) Integer mediaTypeId,
			HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		PageHelper.startPage(currentPage, pageSize, true); //设置分页
		if(mediaTypeId != null){
			List<Integer> mediaTypes = new ArrayList<Integer>();
			mediaTypes.add(mediaTypeId);
			media.setMediaTypes(mediaTypes);
		}
		List<Media> medias = iMediaService.queryMediaByCodition(media);
		PageInfo<Media> pageInfo = new PageInfo<Media>(medias);
		returnMap.put("currentPage", currentPage);
		returnMap.put("rows", medias);
		returnMap.put("pages", pageInfo.getPages());
		returnMap.put("total", pageInfo.getTotal());
		printJSONWithDateFormat(response, returnMap);
	}
	
	@RequestMapping("/toMediaEditOrView")
	public String  toMediaEditOrView(
			Media mediaRecord,
			@RequestParam(value = "flag", required = false) Integer flag,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) {
		Media media = iMediaService.queryOne(mediaRecord);
		model.addAttribute("media", media);
		if(flag.intValue() == 1){
			return "/webconsole/vedio/createOrEditVedio";
		}else{
			return "/webconsole/vedio/vedioView";
		}
	}
	
	
	/**
	 * 
	* @Title: addMedia 
	* @Description: 添加视频
	*@author (shx@sxw100.com)
	* @param @param media
	* @param @param mediaTypeIds
	* @param @param response
	* @param @param request
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/addOrEditMedia")
	public Map<String, Object>  addOrEditMedia(
			Media media,
			@RequestParam(value = "mediaTypeId", required = false) List<String> mediaTypeIds,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model,HttpSession session) {
		logger.debug("--进入addOrEditMedia 方法");
		Map<String, Object> map = new HashMap<String, Object>();
		String reasult = "success";
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null){
			media.setOperatorId(1);
			media.setOperatorName("admin");
		}else{
			media.setOperatorId(loginUser.getUserId());
			media.setOperatorName(loginUser.getUserName());
		}
		media.setUpdateTime(new Date());
		media.setIsReview(Integer.valueOf(0));
		if(media.getMediaId() == null){
			media.setDataStatus(Integer.valueOf(0));
			media.setCreateTime(new Date());
			media.setIsTop(Integer.valueOf(0));
			//添加视频
			iMediaService.addMedia(media);
		}else{
			//设置未审状态
			media.setIsReview(0);
			iMediaService.updateByPrimaryKeySelective(media);
			//删除原有关联关系
			MediaRelType mrt =  new MediaRelType();
			 mrt.setMediaId(media.getMediaId());
			iMediaRelTypeService.delete(mrt);
		}
		//添加视频关联类型
		if(media.getMediaId() != null && mediaTypeIds != null && mediaTypeIds.size() > 0){
			MediaRelType mrt = null;
			for(String mediaTypeId : mediaTypeIds ){
				 mrt = new MediaRelType();
				 mrt.setMediaId(media.getMediaId());
				 mrt.setMediaTypeId(Integer.parseInt(mediaTypeId));
				 iMediaRelTypeService.addSelective(mrt);
			}
		}
		model.addAttribute("reasult", reasult);
		map.put("reasult", reasult);
		//return "/webconsole/vedio/vedioManage";
		return map;
	}
	
	/**
	 * 
	* @Title: editMediaProperties 
	* @Description: 批量更新视频属性
	*@author (shx@sxw100.com)
	* @param @param media
	* @param @param mediaIds
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/editMediaProperties")
	public void editMediaProperties(
			Media media,
			@RequestParam(value = "mediaIdsStr", required = false) String mediaIdsStr,
			@RequestParam(value = "editTime", required = false) String editTime,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = 0;
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null){
			media.setOperatorId(1);
			media.setOperatorName("admin");
		}else{
			media.setOperatorId(loginUser.getUserId());
			media.setOperatorName(loginUser.getUserName());
		}
		try {
			if(StringUtils.isNotBlank(editTime)){media.setCreateTime(DateUtils.parseDate(editTime, "yyyy-MM-dd"));}
			List<Integer>  mediaIds = new ArrayList<Integer>();
			String[] str = mediaIdsStr.split(",");
			for(String s : str){
				if(StringUtils.isNoneBlank(s)){
					mediaIds.add(Integer.parseInt(s));
				}
			}
			media.setMediaIds(mediaIds);
			
			//不是审核操作就回到未审核装填
			if(media.getIsReview()==null||media.getIsReview()!=1){
				media.setIsReview(0);
			}
			iMediaService.batchUpdateMedia(media);
			//若果是删除操作，media。dataStatus为1即删除文章关联
			if(media!=null&&media.getDataStatus()!=null&&media.getDataStatus()==1){
				iMediaRelTypeService.bathDelByMediaIds(mediaIds);
			}
		} catch (Exception e) {
			 rst = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSONWithDateFormat(response, returnMap);
	}
	
	
	/*******************************************视频相关结束************************************************/
	
	/*******************************************视频分类相关开始************************************************/
	/**
	 * 
	* @Title: queryMediaTypes 
	* @Description:    查询媒体类型
	*@author (shx@sxw100.com)
	* @param @param mediaType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/queryMediaTypes")
	public void queryMediaTypes(
			MediaType mediaType,
			HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<MediaType> mediaTypes = iMediaTypeService.queryAll(mediaType);
		returnMap.put("mediaTypes", mediaTypes);
		printJSONWithDateFormat(response, returnMap);
	}
	
	/**
	 * 
	* @Title: addOrEditMediaType 
	* @Description: 添加、修改或者删除视频分类
	*@author (shx@sxw100.com)
	* @param @param mediaType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/addOrEditMediaType")
	public void  addOrEditMediaType(
			MediaType mediaType,
			HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String reasult = "success";
		try {
			if(mediaType.getMediaTypeId() != null){
				iMediaTypeService.updateByPrimaryKeySelective(mediaType);
				if(mediaType.getDataStatus()!= null  && mediaType.getDataStatus().intValue() == 1 ){
					MediaRelType record = new MediaRelType();
					record.setMediaTypeId(mediaType.getMediaTypeId());
					iMediaRelTypeService.delete(record );
				}
			}else{
				iMediaTypeService.addSelective(mediaType);
			}
		} catch (Exception e) {
			reasult = "fail";
			e.printStackTrace();
		}
		returnMap.put("reasult", reasult);
		printJSON(response, returnMap);
		
	}
	/**
	 * 
	* @Title: queryHasMediaTypes 
	* @Description:   查重
	*@author (shx@sxw100.com)
	* @param @param mediaType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/queryHasMediaTypes")
	public void queryHasMediaTypes(
			MediaType mediaType,
			HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = iMediaTypeService.queryCount(mediaType);
		returnMap.put("rst", rst);
		printJSONWithDateFormat(response, returnMap);
	}
	
	/*******************************************视频分类相关结束************************************************/
	
	
	/*******************************************视频与关联关系相关开始************************************************/
	
	
	/**
	 * 
	* @Title: queryMediaTypeRels 
	* @Description: 查询视频关联类型
	*@author (shx@sxw100.com)
	* @param @param mediaRelType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/queryMediaTypeRels")
	public void  queryMediaTypeRels(
			MediaRelType mediaRelType ,
			HttpServletResponse response,
			HttpServletRequest request) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			List<MediaRelType>  mediaRelTypes=   iMediaRelTypeService.queryAll(mediaRelType);
			returnMap.put("mediaRelTypes", mediaRelTypes);
			printJSON(response, returnMap);
	}
	
	/**
	 * 
	* @Title: queryHasMediaTypeRel 
	* @Description: 查询视频分类是否有关联视频
	*@author (shx@sxw100.com)
	* @param @param mediaTypeId
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/queryHasMediaTypeRel")
	public void  queryHasMediaTypeRel(
			@RequestParam(value = "mediaTypeId", required = false) Integer mediaTypeId,
			HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = 0;
		try {
			MediaRelType record = new MediaRelType();
			record.setMediaTypeId(mediaTypeId);
			 rst = iMediaRelTypeService.queryCount(record);
		} catch (Exception e) {
			rst  = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSON(response, returnMap);
		
	}
	
	
	/**
	 * 
	* @Title: delMediaTypeRel 
	* @Description: 删除视频关联分类
	*@author (shx@sxw100.com)
	* @param @param mediaRelType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/delMediaTypeRel")
	public void  delMediaTypeRel(
			MediaRelType mediaRelType,
			HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = 0;
		try {
			iMediaRelTypeService.delete(mediaRelType );
		} catch (Exception e) {
			rst  = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSON(response, returnMap);
	}
	
	/**
	 * 
	* @Title: addMediaTypeRels 
	* @Description: 添加视频与类型关系
	*@author (shx@sxw100.com)
	* @param @param mediaIds
	* @param @param mediaTypeIds
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/addMediaTypeRels")
	public void  addMediaTypeRels(
			@RequestParam(value = "mediaIds", required = false) String mediaIds,
			@RequestParam(value = "mediaTypeIds", required = false) String mediaTypeIds,
			HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = 0;
		try {
			if(StringUtils.isNotBlank(mediaIds) && StringUtils.isNotBlank(mediaTypeIds)){
			      String [] mIds = mediaIds.split(",");
			      String [] mTypeIds = mediaTypeIds.split(",");
			      for(String mId : mIds){
			    	  //去除原有关系
			    	  MediaRelType mrt = new MediaRelType();
			    	  mrt.setMediaId(Integer.parseInt(mId));
			    	  iMediaRelTypeService.delete(mrt );
			    	  //添加新关系
			    	  for(String mTypeId : mTypeIds){
			    		  MediaRelType mediaRelType = new MediaRelType();
				    	  mediaRelType.setMediaId(Integer.parseInt(mId));
				    	  mediaRelType.setMediaTypeId(Integer.parseInt(mTypeId));
				    	  iMediaRelTypeService.addSelective(mediaRelType );
				      }
			      }
			     
			}else{
				rst  = -1;
			}
		} catch (Exception e) {
			rst  = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSON(response, returnMap);
		
	}
	/*******************************************视频与关联关系相关结束************************************************/

}
