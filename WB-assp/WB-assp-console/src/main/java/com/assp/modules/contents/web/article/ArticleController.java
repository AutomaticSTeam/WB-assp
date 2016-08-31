package com.assp.modules.contents.web.article;   

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assp.common.utils.DateUtils;
import com.assp.common.web.BaseController;
import com.assp.modules.commondata.entity.Article;
import com.assp.modules.commondata.entity.ArticleRelType;
import com.assp.modules.commondata.entity.ArticleType;
import com.assp.modules.commondata.service.IArticleRelTypeService;
import com.assp.modules.commondata.service.IArticleService;
import com.assp.modules.commondata.service.IArticleTypeService;
import com.assp.modules.sys.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 类简述
 * <p>
 * 类说明、详细描述(optional)
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (wangkang@sxw100.com)
 * @version 1.0
 * @CreateDate 2016-5-31 下午2:57:04 
 */
@Controller
@RequestMapping("/contents/article")
public class ArticleController extends BaseController{

	private static final Logger logger = Logger.getLogger(ArticleController.class);
	
	@Autowired
	private IArticleService iArticleService;
	
	@Autowired
	private IArticleTypeService iArticleTypeService;
	
	@Autowired
	private IArticleRelTypeService iArticleRelTypeService;
	
	/**
	 * 
	* @Title: addArticleType 
	* @Description: TODO(添加或者修改文章文类) 
	*@author (wangkang@sxw100.com)
	* @param @param articleType
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/addArticleType")
	public void addArticleType(
			ArticleType articleType,
			HttpServletResponse response, 
			HttpServletRequest request,HttpSession session) {
		logger.debug("--进入addArticleType方法--");
		Map<String, Object> returMap = new HashMap<String,Object>();
		String reasult = "success";
		User loginUser = (User) session.getAttribute("loginUser");
		try {
			if(articleType.getArticleTypeId() !=null){
				iArticleTypeService.updateByPrimaryKeySelective(articleType);
			}else{
				articleType.setDataStatus(Integer.valueOf(0));
				articleType.setCreateTime(new Date());
				articleType.setOperatorId(loginUser.getUserId());
				articleType.setOperatorName(loginUser.getUserName());
				iArticleTypeService.add(articleType);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reasult = "fail";
			e.printStackTrace();
		}
		returMap.put("reasult", reasult);
		printJSON(response, returMap);
	}
	
	
	/**
	 * 
	* @Title: toAddArticle 
	* @Description: TODO(取文章分类) 
	*@author (wangkang@sxw100.com)
	* @param @param modelMap
	* @param @return    /webconsole/article/addArticle
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/toAddArticle")
	public String toAddArticle(ModelMap modelMap) {
		logger.debug("--进入toAddArticle 方法");
		ArticleType articleType = new ArticleType();
		articleType.setDataStatus(Integer.valueOf(0));
		List<ArticleType> queryAll = iArticleTypeService.queryAll(articleType);
		if (queryAll.size() > 0 && null != queryAll) {
			modelMap.put("queryAll", queryAll);
		}
		return "/webconsole/article/addArticle";
	}

	/**
	 * 
	* @Title: addArticle 
	* @Description: TODO(添加文章相关内容) 
	*@author (wangkang@sxw100.com)
	* @param @param article
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/addArticle")
	public Map<String, Object> addArticle(
			Article article,
			@RequestParam(value = "articleTypeId", required = false) List<String> articleTypeIds,
			HttpServletResponse response, 
			HttpServletRequest request,
			Model model,HttpSession session) {
		logger.debug("--进入addArticle 方法");
		Map<String, Object> map = new HashMap<String, Object>();
		String reasult = "success";
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null){
			article.setOperatorId(1);
			article.setOperatorName("admin");
		}else{
			article.setOperatorId(loginUser.getUserId());
			article.setOperatorName(loginUser.getUserName());
		}
		try {
		if(article.getArticleId() == null){
			article.setArticleSize(Integer.valueOf(0));//文章大小默认为0
			article.setDataStatus(Integer.valueOf(0));
			article.setArticleTop(Integer.valueOf(0));
			article.setArticleReview(Integer.valueOf(0));
			article.setCreateTime(new Date());
			article.setUpdateTime(new Date());
			
			if(article.getArticleWordNum()!=null&&article.getArticleWordNum()>=7){
				article.setArticleWordNum(article.getArticleWordNum()-Integer.valueOf(7));
			}else{
				article.setArticleWordNum(Integer.valueOf(0));
			}
			//添加文章
			
			//回归未审核状态
			article.setArticleReview(0);
			iArticleService.addArticle(article);
		}else{
			article.setUpdateTime(new Date());
			if(article.getArticleWordNum() != null && article.getArticleWordNum()>=7){
				article.setArticleWordNum(article.getArticleWordNum()-Integer.valueOf(7));
			}else{
				article.setArticleWordNum(Integer.valueOf(0));
			}

			//回归未审核状态
			article.setArticleReview(0);
			
			iArticleService.updateByPrimaryKeySelective(article);
			//删除原有关系
			ArticleRelType art = new ArticleRelType();
			art.setArticleId(article.getArticleId());
			iArticleRelTypeService.delete(art);
		}
		//添加文章关联类型
			if(article.getArticleId() !=null && articleTypeIds !=null && articleTypeIds.size() > 0){
				ArticleRelType articleRelType=null;
				for (String articleTypeId : articleTypeIds) {
					articleRelType =new ArticleRelType();
					articleRelType.setArticleId(article.getArticleId());
					articleRelType.setArticleTypeId(Integer.valueOf(articleTypeId));
					iArticleRelTypeService.addSelective(articleRelType);
				}
			}
		} catch (Exception e) {
			reasult = "fail";
			e.printStackTrace();
		}
		model.addAttribute("reasult", reasult);
		map.put("reasult", reasult);
		//return "/webconsole/article/articleManage";
		return map;
	}
	
	
	/**
	 * 
	* @Title: queryArticleTypes 
	* @Description:  查询文章分类 
	*@author (wangkang@sxw100.com)
	* @param @param articleType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/queryArticleTypes")
	public void queryArticleTypes(
			ArticleType articleType,
			HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("--进入queryArticleTypes 方法");
		List<ArticleType> articleTypes = iArticleTypeService.queryAll(articleType);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("articleTypes", articleTypes);
		printJSON(response, returnMap);
	}
	/**
	 * 
	* @Title: deleteArticleTypes 
	* @Description: TODO(删除文章分类 其实就是改变状态  将0该成1) 
	*@author (wangkang@sxw100.com)
	* @param @param articleTypeId
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/deleteArticleType")
	public void deleteArticleTypes(
			ArticleType articleType,
			HttpServletResponse response,
			HttpServletRequest request,
			String arrayArticleTypeId){
		logger.debug("--进入deleteArticleType方法");
		//TD
		if(arrayArticleTypeId.length()>0 && null !=arrayArticleTypeId){
			String arrayArticleTypeValues[]=arrayArticleTypeId.split(",");
			for (int i = 0; i < arrayArticleTypeValues.length; i++) {
				ArticleType articleType1 = new ArticleType();
				articleType1.setArticleTypeId(Integer.valueOf(arrayArticleTypeValues[i]));
				articleType1.setDataStatus(Integer.valueOf(1));
				iArticleTypeService.updateByPrimaryKeySelective(articleType1);
				ArticleRelType articleRelType = new ArticleRelType();
				articleRelType.setArticleTypeId(Integer.valueOf(arrayArticleTypeValues[i]));
				iArticleRelTypeService.delete(articleRelType);
			}
		}
	}
	
	/**
	 * 
	* @Title: queryArticleByPage 
	* @Description: TODO(文章查询分页) 
	*@author (wangkang@sxw100.com)
	* @param @param articleTypeId
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/queryArticleByPage")
	public void queryArticleByPage(
			@RequestParam(value = "page", required = false) Integer currentPage, 
			@RequestParam(value = "rows", required = false) Integer pageSize,
			@RequestParam(value="typeId",required=false) Integer articleTypeId,
			Article article,
			HttpServletResponse response,
			HttpServletRequest request){
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		PageHelper.startPage(currentPage, pageSize,true);//设置分页
		if(articleTypeId != null ){
			List<Integer> articleTypes = new ArrayList<Integer>();
			articleTypes.add(articleTypeId);
			article.setArticleTypes(articleTypes);
		}
		List<Article> articles = iArticleService.queryByArticleCodition(article);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
		returnMap.put("currentPage", currentPage);
		returnMap.put("rows", articles);
		returnMap.put("pages", pageInfo.getPages());
		returnMap.put("total", pageInfo.getTotal());
		printJSONWithDateFormat(response, returnMap);
		
	}
	
	/**
	 * 
	* @Title: setArticleRelTypes 
	* @Description: TODO(存放新文章类型关系) 
	*@author (wangkang@sxw100.com)
	* @param @param articleIds
	* @param @param articleTypeIds
	* @param @param request
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/setArticleRelTypes")
	public void setArticleRelTypes(
			@RequestParam(value = "articleIds" , required = false) String articleIds,
			@RequestParam(value = "articleTypeIds",required= false) String articleTypeIds,
			HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int srt = 0;
		try {
			if(StringUtils.isNotBlank(articleIds) && StringUtils.isNotBlank(articleTypeIds)){
				String [] artIds = articleIds.split(",");
				String [] artTypeIds = articleTypeIds.split(",");
				for(String artId :artIds ){
					//去除原有关系
					ArticleRelType art =new ArticleRelType();
					art.setArticleId(Integer.parseInt(artId));
					iArticleRelTypeService.delete(art);
					//添加新关系
					for(String artTypeId :artTypeIds ){
						ArticleRelType artype =new ArticleRelType();
						artype.setArticleId(Integer.parseInt(artId));
						artype.setArticleTypeId(Integer.parseInt(artTypeId));
						iArticleRelTypeService.addSelective(artype);
					}
					
				}
			}else{
				srt = -1;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			srt = -1;
			e.printStackTrace();
		}
		returnMap.put("srt", srt);
		printJSON(response, returnMap);
	}
	/**
	 * 
	* @Title: deleteArticleRelTypes 
	* @Description: TODO(删除文章类型关系) 
	*@author (wangkang@sxw100.com)
	* @param @param articleRelType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/deleteArticleRelTypes")
	public void deleteArticleRelTypes(
			ArticleRelType articleRelType,
			HttpServletResponse response,
			HttpServletRequest request){
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		int rst = 0;
		try {
			iArticleRelTypeService.delete(articleRelType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rst = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSON(response, returnMap);
	}
	/**
	 * 
	* @Title: editUpdateArticle 
	* @Description: TODO(批量修改文章) 
	*@author (wangkang@sxw100.com)
	* @param @param article
	* @param @param articleIdsStr
	* @param @param request
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/editUpdateArticle")
	public void editUpdateArticle(
			Article article,
			@RequestParam(value="articleIdsStr",required=false) String  articleIdsStr,
			@RequestParam(value = "editTime", required = false) String editTime,
			HttpServletRequest request,
			HttpServletResponse response){
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		int rst = 0;
		
		try {
			if(StringUtils.isNotBlank(editTime)){article.setCreateTime(DateUtils.parseDate(editTime, "yyyy-MM-dd"));}
			List<Integer> articleIds = new ArrayList<Integer>();
			String[] str = articleIdsStr.split(",");
			for (String st : str) {
				if(StringUtils.isNoneBlank(st)){
					articleIds.add(Integer.parseInt(st));
				}
			}
			article.setArticleIds(articleIds);
			
//			//不是审核操作就回到未审核装填
//			if(article.getArticleReview()==null||article.getArticleReview()!=1){
//				article.setArticleReview(0);
//			}
			article.setUpdateTime(new Date());
			iArticleService.bathUpdateArticle(article);
			
			//若果是删除操作，article。dataStatus为1即删除文章关联
			if(article!=null&&article.getDataStatus()!=null&&article.getDataStatus()==1){
				iArticleRelTypeService.bathDelByArticleIds(articleIds);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rst = -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSON(response, returnMap);
	}
	
	/**
	 * 
	* @Title: toArticleEditOrView 
	* @Description: TODO(查询编辑文章） 
	*@author (wangkang@sxw100.com)
	* @param @param articles
	* @param @param flag
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/toArticleEditOrView")
	public String toArticleEditOrView(
			Article articles,
			@RequestParam(value="flag",required=false) Integer flag,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model ){
		Article article = iArticleService.queryOne(articles);
		model.addAttribute("article", article);
		if(flag.intValue() == 1){
			return "/webconsole/article/addArticle";
		}else{
			return "/webconsole/article/articleView";
		}
		}
	/**
	 * 
	* @Title: queryHasArticleTypes 
	* @Description: TODO(查询添加的文章分类是否为重) 
	*@author (wangkang@sxw100.com)
	* @param @param articleType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/queryHasArticleTypes")
	public void queryHasArticleTypes(
			ArticleType articleType,
			HttpServletResponse response,
			HttpServletRequest request){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst=iArticleTypeService.queryCount(articleType);
		returnMap.put("rst", rst);
		printJSONWithDateFormat(response, returnMap);
	}
	
	
	/**
	 * 
	* @Title: addOrEditArticleType 
	* @Description: TODO(文章分类的添加 删除 修改) 
	*@author (wangkang@sxw100.com)
	* @param @param articleType
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/addOrEditArticleType")
	public void addOrEditArticleType(
			ArticleType articleType,
			HttpServletResponse response,
			HttpServletRequest request,HttpSession session){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String reasult = "success";
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null){
			articleType.setOperatorId(1);
			articleType.setOperatorName("admin");
		}else{
			articleType.setOperatorId(loginUser.getUserId());
			articleType.setOperatorName(loginUser.getUserName());
		}
		try {
			if(articleType.getArticleTypeId() != null){
				iArticleTypeService.updateByPrimaryKeySelective(articleType);
				if(articleType.getDataStatus() != null && articleType.getDataStatus().intValue() == 1 ){
					  ArticleRelType art = new  ArticleRelType();
					  art.setArticleTypeId(articleType.getArticleTypeId());
					  iArticleRelTypeService.delete(art);
				}
			}else{
				iArticleTypeService.addSelective(articleType);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			reasult = "fail";
			e.printStackTrace();
		}
		returnMap.put("reasult", reasult);
		printJSON(response, returnMap);
	}
	
	/**
	 * 
	* @Title: queryHasArticleTypeRel 
	* @Description: TODO(查询删除文章分类是否有关联的数据) 
	*@author (wangkang@sxw100.com)
	* @param @param articleTypeId
	* @param @param response
	* @param @param request    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/queryHasArticleTypeRel")
	public void queryHasArticleTypeRel(
			@RequestParam(value="articleTypeId",required=false) Integer articleTypeId,
			HttpServletResponse response,
			HttpServletRequest request){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int rst = 0;
		try {
			ArticleRelType art = new ArticleRelType();
			art.setArticleTypeId(articleTypeId);
			rst=iArticleRelTypeService.queryCount(art);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rst= -1;
			e.printStackTrace();
		}
		returnMap.put("rst", rst);
		printJSON(response, returnMap);
	}
	
	
	/**
	 * 
	* @Title: queryArticleTypeRels 
	* @Description: TODO(查询文章分类关联) 
	*@author (wangkang@sxw100.com)
	* @param @param articleRelType
	* @param @param request
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping(value="/queryArticleTypeRels")
	public void queryArticleTypeRels(
			ArticleRelType articleRelType,
			HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<ArticleRelType> articleRelTypes = iArticleRelTypeService.queryAll(articleRelType);
		returnMap.put("articleRelTypes", articleRelTypes);
		printJSON(response, returnMap);
	}
}
  