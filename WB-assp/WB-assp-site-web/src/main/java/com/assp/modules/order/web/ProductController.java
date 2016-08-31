package com.assp.modules.order.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assp.common.web.BaseController;
import com.assp.modules.commondata.entity.Area;
import com.assp.modules.commondata.entity.City;
import com.assp.modules.commondata.entity.Province;
import com.assp.modules.commondata.service.IAreaService;
import com.assp.modules.commondata.service.ICityService;
import com.assp.modules.commondata.service.IProvinceService;
import com.assp.modules.order.entity.Product;
import com.assp.modules.order.service.IProductService;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.service.ITemplateService;

/**
 * 
 * 描述：产品相关控制层
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 上午9:41:33
 * @version
 */
@Controller
@RequestMapping("/wms/product")
public class ProductController extends BaseController{
	
	@Autowired
	private ITemplateService tempService;
	
	@Autowired
	private IProvinceService provinceService;
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private IAreaService areaService;
	
	@Autowired
	private IProductService productService;

	/**
	 * 
	 * 描述：跳转至产品选购页面
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 上午9:37:58
	 * @version
	 */
	@RequestMapping("/toProductView")
	public String toProductView(
			@RequestParam(value = "templateId", required = false) String templateId,
			HttpServletRequest request ,
			HttpServletResponse response,
			ModelMap model) {
		createTempList(model,templateId);
		createProductList(model);
		return "/order/productView";
	}
	
	/**
	 * 
	 * 描述：创建产品数据
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午3:38:10
	 * @version
	 */
	private void createProductList(ModelMap model){
		Product product = new Product();
		product.setIsDelete(0);
		product.setIsUse(1);
		List<Product> queryAll = productService.queryAll(product);
		if(null!=queryAll && queryAll.size()>0){
			model.put("proList", queryAll);
		}
	}
	
	/**
	 * 
	 * 描述：创建模板数据
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午1:16:19
	 * @version
	 */
	private void createTempList(ModelMap model,String tmplId){
		Template template = new Template();
		template.setIsEffect(Integer.valueOf(0));
		template.setDataStatus(Integer.valueOf(0));
		template.setTemplateId(Integer.valueOf(tmplId));
		//List<Template> queryAll = tempService.queryAll(template);
		Template queryOne = tempService.queryOne(template);
		if(null!=queryOne){
			model.put("temp", queryOne);
		}
	}
	
	/**
	 * 
	 * 描述：获取省份
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午1:26:17
	 * @version
	 */
	@RequestMapping("/getProvince.do")
	public void getProvince(HttpServletResponse response){
		List<Province> queryAll = provinceService.queryAll(null);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		if(null!=queryAll && queryAll.size()>0){
			resultMap.put("province",queryAll);
		}
		printJSON(response, resultMap);
	}
	
	/**
	 * 
	 * 描述：获取城市
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午1:28:14
	 * @version
	 */
	@RequestMapping("/getCityByProvinceId.do")
	public void getCityByProvinceId(HttpServletResponse response,String provinceId){
		City city = new City();
		city.setProvinceid(provinceId);
		List<City> queryAll = cityService.queryAll(city);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		if(null!=queryAll && queryAll.size()>0){
			resultMap.put("city",queryAll);
		}else{
			resultMap.put("city","");
		}
		printJSON(response, resultMap);
	}
	
	/**
	 * 
	 * 描述：获取区域
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午1:29:56
	 * @version
	 */
	@RequestMapping("/getAreaByCityId.do")
	public void getAreaByCityId(String cityId,HttpServletResponse response){
		Area area = new Area();
		area.setCityid(cityId);
		List<Area> queryAll = areaService.queryAll(area);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		if(null!=queryAll && queryAll.size()>0){
			resultMap.put("area",queryAll);
		}else{
			resultMap.put("area","");
		}
		printJSON(response, resultMap);
	}
	
}
