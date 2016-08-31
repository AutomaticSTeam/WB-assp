package com.assp.modules.contents.web.album;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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

import com.assp.common.utils.PictureUtils;
import com.assp.common.utils.PropertiesUtil;
import com.assp.modules.commondata.entity.Picture;
import com.assp.modules.commondata.service.IPictureService;
import com.assp.modules.sys.entity.User;

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
public class PictureController {
	
	private static final Logger logger = Logger.getLogger(PictureController.class);
	
	@Autowired
	IPictureService iPictureService;
	
	@RequestMapping(value = "/addPicture")
	@ResponseBody
	public  List<Picture> addPicture(@RequestParam(value = "pUrls", required = false) String picUrls,
									 @RequestParam(value = "pNames", required = false) String pNames,HttpSession session) throws Exception{
		
		Map<String, String>  properties = PropertiesUtil.getInstance("/upload.properties");
		String dir = properties.get("dir");
		User loginUser = (User) session.getAttribute("loginUser");	
		if(picUrls!=null&&picUrls!=""&&pNames!=null&&pNames!=""){
			String[] pictureUrls = picUrls.split(",");
			String[] pictureNames = pNames.substring(0,pNames.length()-1).split(",");
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
                if(loginUser != null){
                	 picture.setOperatorId(loginUser.getUserId());
                     picture.setOperatorName(loginUser.getUserName());
                }
				//将图片信息存入数据库
                iPictureService.addPicture(picture);
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
							   @RequestParam(value = "pName", required = false) String pName){
		
		Picture picture = new Picture();
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
	public int updatePictureResult(Picture picture,HttpServletResponse response,HttpServletRequest request) {
		//获取当前登录用户
		//TOOD
		picture.setOperatorId(null);
		picture.setOperatorName(null);
		//射设置成未审核状态
		picture.setIsReview(0);
		int i = iPictureService.updatePictureResult(picture);
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
	public int review_yesById(@RequestParam(value = "picId", required = false) String picId,HttpServletResponse response,HttpServletRequest request) {
		
		return iPictureService.review_yesById(Integer.valueOf(picId));
	}
	
	@RequestMapping("/queryPicById")
	@ResponseBody
	public Picture queryPicById(@RequestParam(value = "picId", required = false) String picId,HttpServletResponse response,HttpServletRequest request) {
		
		return iPictureService.queryByPrimaryKey(Integer.valueOf(picId));
	}
}
  