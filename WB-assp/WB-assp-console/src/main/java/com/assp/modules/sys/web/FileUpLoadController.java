package com.assp.modules.sys.web;   

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assp.common.upload.Upload;
import com.assp.common.upload.UploadVo;
import com.assp.common.utils.PropertiesUtil;
import com.assp.common.web.BaseController;

/**
 * 类简述
 * <p>
 *      文件上传处理
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月30日 下午3:19:42 
 */
@Controller
@RequestMapping("/fileUpLoad")
public class FileUpLoadController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(FileUpLoadController.class);
	
	
	
	/**
	 * 
	* @Title: upLoadVedios 
	* @Description: ajax 上传文件
	*@author (shx@sxw100.com)
	* @param @param files
	* @param @param request
	* @param @param response
	* @param @throws UnsupportedEncodingException  
	* @return void    
	* @throws
	 */
	@RequestMapping("/upLoad")
	public void upLoad(
			@RequestParam(value = "templateCode", required = false) String templateCode,
			@RequestParam(value = "dirFolderName", required = false) String dirFolderName,
			@RequestParam(value = "file", required = false) MultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		logger.debug("进入upLoadVedios方法");
		//设置字符编码防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<UploadVo> uploadFileInfos  = new ArrayList<UploadVo>();
		try {
			Map<String, String>  properties = PropertiesUtil.getInstance("/upload.properties");
			String dir = properties.get("dir");
			String uploadFileTypes = properties.get("uploadFileType");
			String remotePath = properties.get("remotePath");
			String dirPath = properties.get("dirPath");
			logger.debug("file------->:" + files);
			for (MultipartFile file : files) {
				if (file != null && file.getOriginalFilename() != null &&  !"".equals(file.getOriginalFilename())) {
					UploadVo uploadVo = new UploadVo();
					String fileName = file.getOriginalFilename().trim(); 
					uploadVo.setOldName(fileName);
					uploadVo.setFileSize(file.getSize());
					logger.debug("fileName:" +fileName);
					//任意文件上传  当文件是符合要求的文件，则上传
					if (checkFileType(fileName , uploadFileTypes)) {
						if (StringUtils.isNotEmpty(fileName)) {
							String prefix = fileName.substring(fileName.indexOf(".") + 1);
							uploadVo.setFileType(prefix);
							String uuid = UUID.randomUUID().toString().replace("-", ""); //获取到唯一的名称
							fileName = uuid + "." + prefix;  //上传服务器名
						}
						String dirFile = remotePath+ templateCode +"/" + dirFolderName  +"/" ;
						uploadVo.setFileServerPath(dirPath+templateCode +"/" + dirFolderName  +"/"+fileName);
						try {
							FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir+ fileName));// 复制临时文件到指定目录下
							Upload.ftpFile(dir + fileName, dirFile);//上传到服务器
						} catch (IOException e) {
							uploadVo.setReturnStatus(Integer.valueOf(1));
							e.printStackTrace();
						}
					}
					uploadFileInfos.add(uploadVo);
				}
			}
			returnMap.put("uploadRst", uploadFileInfos);
			printJSON(response, returnMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//检查文件类型是否符合规范
	private boolean checkFileType(String fileName , String uploadFileTypes) throws Exception{
		boolean flag = false;
		String[] uploadFileType = uploadFileTypes.split("[|]");
		//检查上传的文件类型是否符合要求的文件
		for (int i = 0; i < uploadFileType.length; i++) {
			if (fileName.toLowerCase().contains(uploadFileType[i])) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
}
  