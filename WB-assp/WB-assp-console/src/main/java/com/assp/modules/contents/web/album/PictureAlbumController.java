package com.assp.modules.contents.web.album;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.ResponseJsonData;
import com.assp.common.utils.DateUtils;
import com.assp.common.web.BaseController;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.entity.PictureAlbum;
import com.assp.modules.commondata.entity.PictureAlbumRelType;
import com.assp.modules.commondata.entity.PictureAlbumType;
import com.assp.modules.commondata.service.IPictureAlbumRelTypeService;
import com.assp.modules.commondata.service.IPictureAlbumService;
import com.assp.modules.commondata.service.IPictureAlbumTypeService;
import com.assp.modules.commondata.service.IPictureService;
import com.assp.modules.sys.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;

/**
 * 
 * 
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (panlinlin@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月29日 上午10:27:05
 */
@Controller
@RequestMapping(value = "/contents/album")
public class PictureAlbumController extends BaseController{

	@Autowired
	private IPictureAlbumService iPictureAlbumService;
	@Autowired
	private IPictureAlbumTypeService iPictureAlbumTypeService;
	@Autowired
	private IPictureAlbumRelTypeService iPictureAlbumRelTypeService;
	
	@Autowired
	IPictureService iPictureService;
	/**
	 * 
	* @Title: addAlbumAndGetprimaryKey 
	* @Description: 添加相册并返回主键
	* @author (panlinlin@sxw100.com)
	* @param @param albumName
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return PictureAlbum    返回类型 
	* @throws
	 */
	@RequestMapping("/addAlbumAndGetprimaryKey")
	@ResponseBody
	public PictureAlbum addAlbumAndGetprimaryKey(@RequestParam(value = "albumName", required = false) String albumName,HttpServletResponse response,HttpServletRequest request,HttpSession session) {
		
		PictureAlbum pictureAlbum = new PictureAlbum();
		pictureAlbum.setAlbumName(albumName); 
		
		//获取当前登录用户
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null){
			pictureAlbum.setOperatorId(1);
			pictureAlbum.setOperatorName("admin");
		}else{
			pictureAlbum.setOperatorId(loginUser.getUserId());
			pictureAlbum.setOperatorName(loginUser.getUserName());
		}
		pictureAlbum.setAlbumStatus(0);
		pictureAlbum.setCreateTime(DateUtils.getCurrentDate());
		iPictureAlbumService.addAlbumAndGetprimaryKey(pictureAlbum);
		
		//分类持久化 添加
		ArrayList<PictureAlbumRelType> pps = new ArrayList<PictureAlbumRelType>();
		String relIDs = request.getParameter("relTypeIDS");
		PictureAlbumRelType pp;
		if(!StringUtils.isNullOrEmpty(relIDs)){
			for(String relID:relIDs.split(",")){
				pp = new PictureAlbumRelType();
				pp.setAlbumId(pictureAlbum.getAlbumId());
				pp.setAlbumTypeId(Integer.valueOf(relID));
				pps.add(pp);
			}
			iPictureAlbumRelTypeService.addBatch(pps);
		}
		return pictureAlbum;
	}
	/**
	 * 
	* @Title: queryAlbumAll 
	* @Description: 查询全部相册
	* @author (panlinlin@sxw100.com)
	* @param @param albumName
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return List<PictureAlbum>    返回类型 
	* @throws
	 */
	@RequestMapping("/queryAlbumAll")
	public void queryAlbumAll(
			PictureAlbum pictureAlbum,
			@RequestParam(value = "albumName", required = false) String albumName,
			@RequestParam(value = "page", required = false) Integer currentPage, 
			@RequestParam(value = "rows", required = false) Integer pageSize,
			HttpServletResponse response,
			HttpServletRequest request) {
		List<PictureAlbum> AlbumAll=null;
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		PageHelper.startPage(currentPage, pageSize,true);//设置分页
		if(pictureAlbum.getAlbumName()!=null || pictureAlbum.getAlbumTypeId() !=null){
			AlbumAll = iPictureAlbumService.queryAlbumAllAndReviewInRelType(pictureAlbum);
		}else{
			AlbumAll = iPictureAlbumService.queryAlbumAllAndReviewInRelType(null);
		}
		//return  AlbumAll;
		PageInfo<PictureAlbum> pageInfo = new PageInfo<PictureAlbum>(AlbumAll);
		returnMap.put("currentPage", currentPage);
		returnMap.put("rows", AlbumAll);
		returnMap.put("pages", pageInfo.getPages());
		returnMap.put("total", pageInfo.getTotal());
		printJSONWithDateFormat(response, returnMap);
		}
		
	
	/**
	 * 
	* @Title: editAlbunById 
	* @Description: 编辑相册
	* @author (panlinlin@sxw100.com)
	* @param @param PictureAlbum
	* @param @param response
	* @param @param request
	* @param @return    设定文件  
	* @return int    返回类型 
	* @throws
	 */
	@RequestMapping("/editAlbumById")
	@ResponseBody
	public PictureAlbum editAlbumById(PictureAlbum pictureAlbum,HttpServletResponse response,HttpServletRequest request,HttpSession session) {

		User loginUser = (User) session.getAttribute("loginUser");
		if(pictureAlbum.getAlbumId()!=null){
			pictureAlbum.setOperatorId(loginUser.getUserId());
			pictureAlbum.setOperatorName(loginUser.getUserName());
			iPictureAlbumService.updataAlbunById(pictureAlbum);
		}
		//分类持久化1先删除，2添加
		PictureAlbumRelType part = new PictureAlbumRelType();
		part.setAlbumId(pictureAlbum.getAlbumId());
		iPictureAlbumRelTypeService.delete(part);
		List<PictureAlbumRelType> pps = new ArrayList<PictureAlbumRelType>();
		//TODO
		String relIDs = request.getParameter("relTypeIDS");
		PictureAlbumRelType pp;
		if(!StringUtils.isNullOrEmpty(relIDs)){
			for(String relID : relIDs.split(",")){
				pp = new PictureAlbumRelType();
				pp.setAlbumId(pictureAlbum.getAlbumId());
				pp.setAlbumTypeId(Integer.valueOf(relID));
				pps.add(pp);
			}
			iPictureAlbumRelTypeService.addBatch(pps);
		}
		return pictureAlbum;
	}
	
	/**
	 * 
	* @Title: deletAlbunById 
	* @Description:删除相册
	* @author (panlinlin@sxw100.com)
	* @param @param ablumIds
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/deletAlbumById")
	@ResponseBody
	public void deletAlbumById(@RequestParam(value = "ablumId", required = false) String ablumId,HttpServletResponse response,HttpServletRequest request) {
	
		iPictureAlbumService.deleteByPrimaryKey(Integer.valueOf(ablumId));
		//修改关联图片
		iPictureService.updatePictureforDelete(Integer.valueOf(ablumId));
		//删除图册关联关系
		if(ablumId!=null){
			PictureAlbumRelType part = new PictureAlbumRelType();
			part.setAlbumId(Integer.valueOf(ablumId));
			iPictureAlbumRelTypeService.delete(part);
		}
	}
	
	/**
	 * 
	* @Title: queryPicByAlbumId 
	* @Description: 根据相册id查询图片 
	* @author (panlinlin@sxw100.com)
	* @param @param albumId
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return List<Picture>    返回类型 
	* @throws
	 */
	@RequestMapping("/queryPicByAlbumId")
	@ResponseBody
	public List<Picture> queryPicByAlbumId(@RequestParam(value = "albumId", required = false) String albumId,HttpServletResponse response,HttpServletRequest request) {
		
		return  iPictureAlbumService.queryPicByAlbumId(Integer.valueOf(albumId));
		
	}
	
	/**
	 * 
	* @Title: queryAlbumById 
	* @Description: 根据id查询相册
	* @author (panlinlin@sxw100.com)
	* @param @param albumId
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return PictureAlbum    返回类型 
	* @throws
	 */
	@RequestMapping("/queryAlbumById")
	@ResponseBody
	public PictureAlbum queryAlbumById(@RequestParam(value = "albumId", required = false) String albumId,HttpServletResponse response,HttpServletRequest request) {
		
		return  iPictureAlbumService.queryByPrimaryKey(Integer.valueOf(albumId));
		
	}
	
	/**
	 * 
	* @Title: queryPicByAlbumIdToReview 
	* @Description: 根据图册id查询待审查图片
	* @author (panlinlin@sxw100.com)
	* @param @param albumId
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return List<Picture>    返回类型 
	* @throws
	 */
	@RequestMapping("/queryPicByAlbumIdToReview")
	@ResponseBody
	public List<Picture> queryPicByAlbumIdToReview(@RequestParam(value = "albumId", required = false) String albumId,HttpServletResponse response,HttpServletRequest request) {
		
		List<Picture> data=iPictureAlbumService.queryPicByAlbumIdToReview(Integer.valueOf(albumId));
		
		return data;
	}
	/**
	 * 
	 * @Title: delRelPic 
	 * @Description: 根据图册id,图册分类id删除先关记录
	 * @author (wangzhipeng@sxw100.com)
	 * @param @param albumId
	 * @param @param response
	 * @param @param request
	 * @param @return    设定文件 
	 * @return List<Picture>    返回类型 
	 * @throws
	 */
	@RequestMapping("/delRelPic")
	@ResponseBody
	public String delRelPic(Integer albumId,Integer ablumTypeId,HttpServletResponse response,HttpServletRequest request) {
		
		logger.debug("--进入delRelPic方法");
		
		if(albumId == null || ablumTypeId == null){
			
			//log日志。。。
			logger.debug("非法操作");
			return "非法操作";
			
		}
		
		//做删除
		PictureAlbumRelType part = new PictureAlbumRelType();
		part.setAlbumId(Integer.valueOf(albumId));
		part.setAlbumTypeId(Integer.valueOf(ablumTypeId));
		
		int i = iPictureAlbumRelTypeService.delete(part);
		
		return i > 0 ? "success" : "操作失败，请重试" ;
	}
	
	/*********************图册分类********************************************/
	/**
	 * 
	* @Title: queryAlbumTypes 
	* @Description:  查询图册分类 
	*@author (wangzhipeng@sxw100.com)
	* @param @param albumType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/queryAlbumTypes")
	public void queryAlbumTypes(HttpServletResponse response,
			HttpServletRequest request) {
		
		logger.debug("--进入queryAlbumTypes 方法");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		PictureAlbumType pamt = new PictureAlbumType();
		pamt.setAlbumStatus(Integer.valueOf(0));
		List<PictureAlbumType> pictureAlbumTypes = iPictureAlbumTypeService.queryAll(pamt);
		returnMap.put("albumTypes", pictureAlbumTypes);
		printJSON(response, returnMap);
	}
	
	/**
	 * 
	* @Title: queryHasAlbumTypes 
	* @Description: TODO(查询添加的图册分类是否为重) 
	*@author (wangzhipeng@sxw100.com)
	* @param @param albumType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/queryHasAlbumTypes")
	public void queryHasAlbumTypes(
			PictureAlbumType pictureAlbumType,
			HttpServletResponse response,
			HttpServletRequest request){
		
		logger.debug("--进入queryHasAlbumTypes 方法");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = iPictureAlbumTypeService.queryCount(pictureAlbumType);
		returnMap.put("rst", rst);
		printJSONWithDateFormat(response, returnMap);
	}
	/**
	 * 
	* @Title: addOrEditAlbumType
	* @Description: TODO(相册分类的添加 修改) 
	*@author (wangzhipeng@sxw100.com)
	* @param @param albumType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/addOrEditAlbumType")
	public void addOrEditAlbumType(
			PictureAlbumType pictureAlbumType,
			HttpServletResponse response,
			HttpServletRequest request){
		
		logger.debug("--进入addOrEditAlbumType 方法");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String reasult = "success";
		try {
			
			if(pictureAlbumType.getAlbumTypeId() != null){
				
				iPictureAlbumTypeService.updateByPrimaryKeySelective(pictureAlbumType);
				
			}else{
				
				iPictureAlbumTypeService.addSelective(pictureAlbumType);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reasult = "fail";
			logger.error(e);
		}
		returnMap.put("reasult", reasult);
		printJSON(response, returnMap);
	}
	/**
	 * 
	 * @Title: addOrEditAlbumType
	 * @Description: TODO(查询相关分类集合) 
	 *@author (wangzhipeng@sxw100.com)
	 * @param @param pictureAlbumId
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value="/queryAlbumTypeRels")
	public void queryAlbumTypeRels(
			Integer pictureAlbumId,
			HttpServletResponse response,
			HttpServletRequest request){
		
		logger.debug("--进入queryAlbumTypeRels 方法");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try {
			
			PictureAlbumRelType part = new PictureAlbumRelType();
			part.setAlbumId(pictureAlbumId);
			List<PictureAlbumRelType> parts = iPictureAlbumRelTypeService.queryAll(part);
			returnMap.put("datas", parts);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		
		printJSON(response, returnMap);
	}
	/**
	 * 
	 * @Title: delAlbumType
	 * @Description: TODO(相册分类的删除) 
	 *@author (wangzhipeng@sxw100.com)
	 * @param @param albumTypeId
	 * @param @param status 当为1时，直接删除不进行校验
	 * @param @param response
	 * @param @param request    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping(value="/delAlbumType")
	@ResponseBody
	public ResponseJsonData delAlbumType(
			Integer albumTypeId,
			Integer delStatus,
			HttpServletResponse response,
			HttpServletRequest request){
		//TODO
		logger.debug("--进入delAlbumType 方法");
		
		ResponseJsonData  responseJsonData = new ResponseJsonData();
		
		try {
			
			PictureAlbumRelType part = new PictureAlbumRelType();
			part.setAlbumTypeId(Integer.valueOf(albumTypeId));
			// 当status为1时，不进行校验直接删除
			if((delStatus == null || delStatus != 1) && iPictureAlbumRelTypeService.queryCount(part) > 0){
				
				responseJsonData.setResult("tip");
				responseJsonData.setMesssage("该类下面有相关图册，确定还要进行该操作吗？");
				
			}else{
				
				//删除图册相关,有一坑，没进行事务性删除，待改进...
				iPictureAlbumRelTypeService.delete(part);
				
				PictureAlbumType rap = iPictureAlbumTypeService.queryByPrimaryKey(albumTypeId);
				rap.setAlbumStatus(Integer.valueOf(1));
				iPictureAlbumTypeService.updateByPrimaryKey(rap);
				
				responseJsonData.setResult("success");
			}
			
			
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			responseJsonData.setResult("fail");
			responseJsonData.setMesssage("系统异常，请联系管理员");
			logger.error(e);
			
		}
		
		return responseJsonData;
	}
	/****************************************图册分类 end********************************************************/
}
 