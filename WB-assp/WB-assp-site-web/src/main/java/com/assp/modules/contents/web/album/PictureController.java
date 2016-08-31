package com.assp.modules.contents.web.album;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.assp.common.utils.PictureUtils;
import com.assp.common.utils.PropertiesUtil;
import com.assp.common.web.BaseController;
import com.assp.modules.common.SessionUtils;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.service.IPictureService;
import com.assp.modules.sys.entity.User;
import com.assp.modules.website.entity.Website;

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
  * @CreateDate 2016年6月2日 下午4:32:05 
  */
@Controller
@RequestMapping(value = "/contents/picture")
public class PictureController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(PictureController.class);
	
	@Autowired
	IPictureService iPictureService;
	
	/**
	 */
	/** 
	* @Title: handlePicture 
	* @Description: 处理图片，完善信息
	* @author (panlinlin@sxw100.com)
	* @param @param picUrls
	* @param @param pNames
	* @param @param request
	* @param @param session
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<Picture>    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/handlePicture")
	@ResponseBody
	public  List<Picture> handlePicture(
			@RequestParam(value = "pUrls", required = false) String picUrls,
			@RequestParam(value = "pNames", required = false) String pNames,
			HttpServletRequest request,
			HttpSession session) throws Exception{
		//加载配置文件，获取=路径信息
		Map<String, String>  properties = PropertiesUtil.getInstance("/upload.properties");
		String dir = properties.get("dir");
		
		if(picUrls!=null&&picUrls!=""&&pNames!=null&&pNames!=""){
			String[] pictureUrls = picUrls.split(",");
			String[] pictureNames = pNames.split(",");
			List<Picture> data=new ArrayList<>();
			for (int i = 0; i < pictureUrls.length; i++) {
				String pUrl=pictureUrls[i];
				//截取文件名
				int index = pUrl.lastIndexOf("/");
				String fileName = pUrl.substring(index+1, pUrl.length());
				File picFile = new File(dir+fileName);
				String oldNmae=PictureUtils.getfirstName(pictureNames[i]);
				 //获取图片信息
				BufferedImage sourceImg =ImageIO.read(new FileInputStream(picFile));
                Picture picture = new Picture();
                if(oldNmae.length()>30){
                	oldNmae=oldNmae.trim().substring(0, 30);
                }
                picture.setPictureName(oldNmae);
                picture.setImgSize(PictureUtils.getImageSize(picFile));
                picture.setImgPostfix(PictureUtils.getExtension(fileName, ""));
                picture.setPictureUrl(pUrl);
                picture.setBannerFormatHeight(PictureUtils.getImageHeight(sourceImg));
                picture.setBannerFormatWidth(PictureUtils.getImageWidth(sourceImg));
                picture.setDataStatus(0);
				//将图片信息存入待返回列表
				data.add(picture);
			}
			return data;
		}else{
			return null;
		}
			
	}
	
	/**
	 * 
	* @Title: deletePicture 
	* @Description: 删除图片（改状态为1）
	* @author (panlinlin@sxw100.com)
	* @param @param picId
	* @param @param pName
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/deletePicture")
	@ResponseBody
	public  int  deletePicture(@RequestParam(value = "pId", required = false) String picId,
							   @RequestParam(value = "pName", required = false) String pName,
							   HttpSession session){

		//获取website
						Website website = SessionUtils.getWebsite(session);
						Integer siteId = website.getSiteId();
		Picture picture = new Picture();
		picture.setSiteId(siteId);
		picture.setPictureId(Integer.valueOf(picId));
		picture.setPictureName(pName);
		picture.setDataStatus(1);
		int i = iPictureService.updatePictureStatus(picture);
		return i;
		
	}
	
	/**
	 * @return 
	 * 
	* @Title: updatePictureResult 
	* @Description: 提交页面的最终结果 
	* @author (panlinlin@sxw100.com)
	* @param @param picture
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/updatePictureResult")
	@ResponseBody
	public int updatePictureResult(
			String  picListJson,
			@RequestParam(value = "saveClient", required = false) String saveClient,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session) {
			List<Picture> picList = JSON.parseArray(picListJson, Picture.class);
			//获取website
			Website website = SessionUtils.getWebsite(session);
			Integer siteId = website.getSiteId();
			//获取当前登录用户
			User loginUser = SessionUtils.getCurrentLoginUser(request);
			//编辑状态的数据拆分
			ArrayList<Picture> addPicArray = new ArrayList<Picture>();
			ArrayList<Picture> updatePicArray = new ArrayList<Picture>();
			for (Picture pic : picList) {
				if(loginUser!=null){
					if(siteId != null){
						pic.setSiteId(siteId);
					}
					pic.setOperatorId(loginUser.getUserId());
					pic.setOperatorName(loginUser.getUserName());
					//设置成未审核状态,如果是站点编辑 直接reviewed，否则 未审核
					if("1".equals(saveClient)){
						pic.setIsReview(1);
					}else{
						pic.setIsReview(0);
					}
					Date date = new Date();
					pic.setUpdateTime(date);
					if(pic.getPictureId()==null){
						//添加数据状态
						pic.setDataStatus(0);
						pic.setCreateTime(date);
						addPicArray.add(pic);
					}else{
						updatePicArray.add(pic);
					}
				}
			}
		int i=0;
		if(addPicArray.size()>0){
			i+=iPictureService.batchAddPic(addPicArray);
		}
		if(updatePicArray.size()>0){
			i+=iPictureService.batchUpdataPic(updatePicArray);
		}
		
		return i;
	}
	
	/**
	 * 
	* @Title: review_yesById 
	* @Description: 审核通过
	* @author (panlinlin@sxw100.com)
	* @param @param picId
	* @param @param response
	* @param @param request
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	@RequestMapping("/review_yesById")
	@ResponseBody
	public int review_yesById(
			@RequestParam(value = "picId", required = false) String picId,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		return iPictureService.review_yesById(Integer.valueOf(picId));
	}
	
	@RequestMapping("/queryPicById")
	@ResponseBody
	public Picture queryPicById(
			@RequestParam(value = "picId", required = false) String picId,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		return iPictureService.queryByPrimaryKey(Integer.valueOf(picId));
	}
}
  