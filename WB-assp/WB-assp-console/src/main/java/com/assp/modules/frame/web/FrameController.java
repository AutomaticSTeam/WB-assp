package com.assp.modules.frame.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.constant.SystemConstants;
import com.assp.common.web.BaseController;
import com.assp.modules.frame.entity.Frame;
import com.assp.modules.frame.entity.Frames;
import com.assp.modules.frame.entity.framesRefFrame;
import com.assp.modules.frame.service.IFrameService;
import com.assp.modules.frame.service.IFramesRefFrameService;
import com.assp.modules.frame.service.IFramesService;

/**
 * 
  * 类简述
  * <p>
  * 编辑板式类
  * </p>
  * @Company 动力威视
  * @Copyright
  * @author (sundageng@sxw100.com)
  * @version 2.0
  * @CreateDate 2016年7月21日
 */
@Controller
@RequestMapping(value = "/console/frame")
public class FrameController extends BaseController{

	private static final Logger logger = Logger.getLogger(FrameController.class);
	
	@Autowired
	private IFrameService frameService;
	
	@Autowired
	private IFramesService framesService;
	
	@Autowired
	private IFramesRefFrameService framesRefFrameService;
	
	/**
	 * 
	* @Title: ManagerFrame 
	* @Description: 查询模块模板 进入模块模板管理页面 
	* @author (sundageng@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @return    设定文件 
	* @return List<TemplateModuleTmpl>    返回类型 
	* @throws
	 */
	@RequestMapping("/ManagerFrame")
	@ResponseBody
	public Map<String, Object> ManagerFrame(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Frame frame)  {
		logger.debug("--进入ManagerFrame方法--");
		try {
		frame.setFrameName(new String(frame.getFrameName().getBytes("iso-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Frame> FrameList = frameService.queryByExample(frame);
		for(Frame frameOne : FrameList){
			if(frameOne.getFrameAttachmentImg() != null){
				//frameOne.setFrameAttachmentImg("<img src='"+frameOne.getFrameAttachmentImg()+"' alt='' />");
				frameOne.setFrameAttachmentImg("<span onmouseover='over(this);' onmouseout='out(this);' class='pic'><img style='width:20px;height:20px;' src='"+frameOne.getFrameAttachmentImg()+"' /><span class='picImg' style='display:none;position:absolute;margin-left:15px;margin-top:-28px;width:100px;height:76px;background:#fff;'><img style='width:100px;height:76px;' src='"+frameOne.getFrameAttachmentImg()+"' alt='' /></span></span>");
			}
		}
		int count = frameService.queryCountByExample(frame);
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("total", count);
		data.put("rows", FrameList);
		return data;
	}
	
	/**
	 * 
	* @Title: ManagerFrame 
	* @Description: 查询模块模板 进入模块模板管理页面 
	* @author (sundageng@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @return    设定文件 
	* @return List<TemplateModuleTmpl>    返回类型 
	* @throws
	 */
	@RequestMapping("/ManagerFrames")
	@ResponseBody
	public Map<String, Object> ManagerFrames(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Frames frames)  {
		logger.debug("--进入ManagerFrame方法--");
		List<Frames> FramesList = framesService.queryByExample(frames);
		for(Frames framesOne : FramesList){
			if(framesOne.getFramesAttachmentImg() != null){
				//framesOne.setFramesAttachmentImg("<img src='"+framesOne.getFramesAttachmentImg()+"' alt='' />");
				framesOne.setFramesAttachmentImg("<span onmouseover='over(this);' onmouseout='out(this);' class='pic'><img style='width:20px;height:20px;' src='"+framesOne.getFramesAttachmentImg()+"' /><span class='picImg' style='display:none;position:absolute;margin-left:15px;margin-top:-28px;width:100px;height:76px;background:#fff;'><img style='width:100px;height:76px;' src='"+framesOne.getFramesAttachmentImg()+"' alt='' /></span></span>");
			}
		}
		int count = framesService.queryCountByExample(frames);
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("total", count);
		data.put("rows", FramesList);
		return data;
	}
	
	/**
	 * 
	* @Title: getFramesByType 
	* @Description: 查询模块模板 进入模块模板管理页面 
	* @author (sundageng@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @return    设定文件 
	* @return List<TemplateModuleTmpl>    返回类型 
	* @throws
	 */
	@RequestMapping("/getFramesByType")
	@ResponseBody
	public Map<String, Object> getFramesByType(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Frames frames)  {
		logger.debug("--进入ManagerFrame方法--");
		try {
			frames.setFramesName(new String(frames.getFramesName().getBytes("iso-8859-1"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		List<Frames> FramesList = framesService.queryAll(frames);
		for(Frames framesOne : FramesList){
			if(framesOne.getFramesAttachmentImg() != null){
				//framesOne.setFramesAttachmentImg("<img src='"+framesOne.getFramesAttachmentImg()+"' alt='' />");
				framesOne.setFramesAttachmentImg("<span onmouseover='over(this);' onmouseout='out(this);' class='pic'><img style='width:20px;height:20px;' src='"+framesOne.getFramesAttachmentImg()+"' /><span class='picImg' style='display:none;position:absolute;margin-left:15px;margin-top:-28px;width:100px;height:76px;background:#fff;'><img style='width:100px;height:76px;' src='"+framesOne.getFramesAttachmentImg()+"' alt='' /></span></span>");
			}
		}
		int count = framesService.queryCount(frames);
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("total", count);
		data.put("rows", FramesList);
		return data;
	}
	
	/**
	 * 
	* @Title: getFramesByType 
	* @Description: 查询模块模板 进入模块模板管理页面 
	* @author (sundageng@sxw100.com)
	* @param @param response
	* @param @param request
	* @param @param session
	* @param @return    设定文件 
	* @return List<TemplateModuleTmpl>    返回类型 
	* @throws
	 */
	@RequestMapping("/saveFramesRefFrame")
	@ResponseBody
	public Map<String, Object> saveFramesRefFrame(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			framesRefFrame framesRefFrame)  {
		logger.debug("--进入saveFramesRefFrame方法--");
		framesRefFrame.setRelStatus(0);
		Map<String, Object> data = new HashMap<String,Object>();
		framesRefFrame framesRefFrameOne = framesRefFrameService.queryOne(framesRefFrame);
		if(framesRefFrameOne != null){
			data.put("tip", SystemConstants.FRAMESREFFRAME);
			data.put("code", 2);
		}else{
			int i;
			try {
				framesRefFrame frf = framesRefFrameService.querySortNumMaxObj(framesRefFrame.getFramesId());
				framesRefFrame.setSortNum(frf.getSortNum()+1);
				i = framesRefFrameService.addSelective(framesRefFrame);
				if(i > 0){
					data.put("tip", SystemConstants.TIP_SUCCESS);
					data.put("code", 1);
				}
			} catch (Exception e) {
				data.put("tip", SystemConstants.TIP_FAIL);
				data.put("code", 0);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
	
	@RequestMapping("/editFrame")
	public String editFrame(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Frame frame,
			Model model)  {
		
		logger.debug("--进入editModuleTmpl方法--");
		Frame data = frameService.queryOne(frame);
			model.addAttribute("reasult", data);
		return "/frame/editFrame";
	}
	
	@RequestMapping("/editFrames")
	public String editFrames(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			Frames frames,
			Model model)  {
		
		logger.debug("--进入editModuleTmpl方法--");
		Frames data = framesService.queryOne(frames);
			model.addAttribute("reasult", data);
		return "/frame/editFrames";
	}
	
	@ResponseBody
	@RequestMapping("/editFrameOne")
	public Map<String, Object> editFrameOne(HttpServletResponse response,HttpServletRequest request,Frame frame,HttpSession session)  {
		
		logger.debug("--进入addFrame方法--");
		Map<String, Object> data = new HashMap<String,Object>();
		//User loginUser = (User) session.getAttribute("loginUser");
		try {
			Date date = new Date();
			frame.setUpdateTime(date);
			if(frame.getFrameId()==null){
				frameService.addSelective(frame);
			}else{
				//moduleTmpl.setDataStatus(Integer.valueOf(0));
				frameService.updateByPrimaryKeySelective(frame);
			}
			data.put("tip", SystemConstants.TIP_SUCCESS);
			data.put("code", 1);
		} catch (Exception e) {
			data.put("tip", SystemConstants.TIP_FAIL);
			data.put("code", 0);
			e.printStackTrace();
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/editFramesOne")
	public Map<String, Object> editFramesOne(HttpServletResponse response,HttpServletRequest request,Frames frames,HttpSession session)  {
		
		logger.debug("--进入addFrame方法--");
		Map<String, Object> data = new HashMap<String,Object>();
		//User loginUser = (User) session.getAttribute("loginUser");
		try {
			Date date = new Date();
			frames.setUpdateTime(date);
			if(frames.getFramesId()==null){
				framesService.addSelective(frames);
			}else{
				//moduleTmpl.setDataStatus(Integer.valueOf(0));
				framesService.updateByPrimaryKeySelective(frames);
			}
			data.put("tip", SystemConstants.TIP_SUCCESS);
			data.put("code", 1);
		} catch (Exception e) {
			data.put("tip", SystemConstants.TIP_FAIL);
			data.put("code", 0);
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping("/addFrame")
	@ResponseBody
	public Map<String, Object> addFrame(HttpServletResponse response,HttpServletRequest request,Frame frame,HttpSession session)  {
		
		logger.debug("--进入addFrame方法--");
		Map<String, Object> data = new HashMap<String,Object>();
		//User loginUser = (User) session.getAttribute("loginUser");
		try {
			Date date = new Date();
			frame.setCreateTime(date);
			frame.setUpdateTime(date);
			frame.setDataStatus(Integer.valueOf(0));
			frameService.addSelective(frame);
			data.put("tip", SystemConstants.TIP_SUCCESS);
			data.put("code", 1);
		} catch (Exception e) {
			data.put("tip", SystemConstants.TIP_FAIL);
			data.put("code", 0);
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping("/addFrames")
	@ResponseBody
	public Map<String, Object> addFrames(HttpServletResponse response,HttpServletRequest request,Frames frames,HttpSession session)  {
		
		logger.debug("--进入addFrames方法--");
		Map<String, Object> data = new HashMap<String,Object>();
		//User loginUser = (User) session.getAttribute("loginUser");
		try {
			Date date = new Date();
			frames.setCreateTime(date);
			frames.setUpdateTime(date);
			frames.setDataStatus(Integer.valueOf(0));
			framesService.addSelective(frames);
			data.put("tip", SystemConstants.TIP_SUCCESS);
			data.put("code", 1);
		} catch (Exception e) {
			data.put("tip", SystemConstants.TIP_FAIL);
			data.put("code", 0);
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping("/deleteFrame")
	@ResponseBody
	public Map<String, Object> deleteFrame(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "frameIds", required = false) String frameIds,
			Model model)  {
		logger.debug("--进入deletModuleTmpl方法--");
		Map<String, Object> data = new HashMap<String,Object>();
		if(frameIds!=null&&frameIds.length()>0){
			Frame frame = new Frame();
			frame.setDataStatus(1);
			String[] frameIdArray = frameIds.split(",");
			for (int i = 0; i < frameIdArray.length; i++) {
				frame.setFrameId(Integer.valueOf(frameIdArray[i]));
				frameService.updateByPrimaryKeySelective(frame);
			}
			data.put("tip", SystemConstants.TIP_SUCCESS);
			data.put("code", 1);
		}else{
			data.put("tip", SystemConstants.TIP_FAIL);
			data.put("code", 0);
		}
		return data;
	}
	
	@RequestMapping("/deleteFrames")
	@ResponseBody
	public Map<String, Object> deleteFrames(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "framesIds", required = false) String framesIds,
			Model model)  {
		logger.debug("--进入deletModuleTmpl方法--");
		Map<String, Object> data = new HashMap<String,Object>();
		if(framesIds!=null&&framesIds.length()>0){
			Frames frames = new Frames();
			frames.setDataStatus(1);
			String[] framesIdArray = framesIds.split(",");
			for (int i = 0; i < framesIdArray.length; i++) {
				frames.setFramesId(Integer.valueOf(framesIdArray[i]));
				framesService.updateByPrimaryKeySelective(frames);
			}
			data.put("tip", SystemConstants.TIP_SUCCESS);
			data.put("code", 1);
		}else{
			data.put("tip", SystemConstants.TIP_FAIL);
			data.put("code", 0);
		}
		return data;
	}
	
	@RequestMapping("/framesPreview")
	public String framesPreview(HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session,
			framesRefFrame framesRefFrame,
			Model model)  {
		logger.debug("--进入deletModuleTmpl方法--");
		List<framesRefFrame> framesRefFrameList = framesRefFrameService.queryByExample(framesRefFrame);
		List<Frame> frames = new ArrayList<Frame>();
		for(framesRefFrame framesFrame : framesRefFrameList){
			Frame record = new Frame();
			record.setFrameId(framesFrame.getFrameId());
			Frame frame = frameService.queryOne(record);
			frames.add(frame);
		}
		model.addAttribute("reasult", frames);
		return "/frame/framesPreview";
	}
	
}


