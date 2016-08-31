package com.assp.modules.order.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assp.common.utils.DateUtils;
import com.assp.common.web.BaseController;
import com.assp.modules.common.SessionUtils;
import com.assp.modules.order.entity.Order;
import com.assp.modules.order.entity.OrderProduct;
import com.assp.modules.order.entity.OrderVo;
import com.assp.modules.order.service.IOrderProductService;
import com.assp.modules.order.service.IOrderService;
import com.assp.modules.sys.entity.User;
import com.assp.modules.template.entity.Template;
import com.assp.modules.template.service.ITemplateService;

/**
 * 
 * 描述：order控制层
 * 创建人： 魏泽超
 * 创建时间: 2016年7月20日 上午9:36:38
 * @version
 */
@Controller
@RequestMapping("/wms/order")
public class OrderController extends BaseController{

	@Autowired
	private ITemplateService tempService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IOrderProductService opService;
	
	/*@RequestMapping("/ceshi.do")
	public void ceshi(){
		System.out.println(createOrderNo("1"));
	}*/
	
	
	/**
	 * 
	 * 描述：添加订单
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 下午7:43:18
	 * @version
	 */
	@RequestMapping("/addOrder.do")
	public void addOrder(Order order,OrderProduct op,HttpServletRequest request,HttpServletResponse response,String provinceName,String cityName){
		User user=SessionUtils.getloginUser(request);
		order.setCreateTime(DateUtils.getCurrentDate());
		order.setOrderNo(createOrderNo(op.getTmplId().toString()));
		order.setUserAccount(user.getUserName());
		order.setProvince(provinceName);
		order.setCity(cityName);
		order.setOrderStatus(0);
		boolean flag = orderService.addOrderProduct(order, op);
		//boolean flag=true;
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("status", flag);
		resultMap.put("orderId", order.getOrderId());
		printJSON(response, resultMap);
	}
	
	/**
	 * 
	 * 描述：获取订单列表
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月21日 下午2:24:26
	 * @version
	 */
	@RequestMapping("/orderList.do")
	public String orderList(ModelMap model,HttpServletRequest request){
		User user = SessionUtils.getloginUser(request);
		if(null!=user){
			OrderVo orderVo = new OrderVo();
			orderVo.setUserAccount(user.getUserName());
			List<OrderVo> queryOrderByParam = orderService.queryOrderByParam(orderVo);
			if(null!=queryOrderByParam && queryOrderByParam.size()>0){
				model.put("orderList", queryOrderByParam);
			}
		}
		return "/order/orderList";
	}
	
	
	/**
	 * 
	 * 描述：查看订单详情
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月21日 下午2:57:38
	 * @version
	 */
	@RequestMapping("/getOrderByOrderId.do")
	public String getOrderByOrderId(String orderId,ModelMap model,HttpServletRequest request){
		OrderVo orderVo = new OrderVo();
		if(StringUtils.isNotEmpty(orderId) && !("").equals(orderId)){
			orderVo.setOrderId(Integer.valueOf(orderId));
		}
		User user = SessionUtils.getloginUser(request);
		if(user!=null){
			orderVo.setUserAccount(user.getUserName());
		}
		List<OrderVo> queryOrderByParam = orderService.queryOrderByParam(orderVo);
		if(null!=queryOrderByParam && queryOrderByParam.size()>0){
			model.put("order", queryOrderByParam.get(0));
			OrderVo o=queryOrderByParam.get(0);
			model.put("overTime", formatOverDate(o.getRentedUnit(),o.getRentedTime(),o.getCheckTime()));
		}
		return "/order/orderdetail";
	}
	
	
	
	/**
	 * 
	 * 描述：去支付页面
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月27日 下午2:26:44
	 * @version
	 */
	@RequestMapping("/toPayOrder.do")
	public String toPayOrder(Integer orderId,ModelMap model,HttpServletRequest request){
		OrderVo orderVo = new OrderVo();
		if(!("").equals(orderId)){
			orderVo.setOrderId(Integer.valueOf(orderId));
		}
		User user = SessionUtils.getloginUser(request);
		if(user!=null){
			orderVo.setUserAccount(user.getUserName());
		}
		List<OrderVo> queryOrderByParam = orderService.queryOrderByParam(orderVo);
		if(null!=queryOrderByParam && queryOrderByParam.size()>0){
			model.put("order", queryOrderByParam.get(0));
		}
		return "/order/pay";
	}
	
	
	
	/**
	 * 
	 * 描述：支付
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月27日 下午2:44:05
	 * @version
	 */
	@RequestMapping("/payOrder.do")
	public String payOrder(Integer orderId,ModelMap model,HttpServletRequest request){
		Order order=new Order();
		order.setOrderId(orderId);
		order.setOrderStatus(1);
		//TO - DO 支付逻辑
		//TO - DO 支付逻辑
		OrderProduct op = new OrderProduct();
		op.setOrderId(orderId);
		op.setCheckTime(DateUtils.getCurrentDate());
		op.setIsUse(1);
		if(!("").equals(orderId) && null!=orderId){
			orderService.updateByPrimaryKeySelective(order);
			opService.upCheckTimeByOrderId(op);
		}
		return "redirect:/wms/order/orderList.do";
	}
	
	/**
	 * 
	 * 描述：生成订单编号
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月20日 上午10:56:19
	 * @version
	 */
	private String createOrderNo(String tempId){
		StringBuffer sb=new StringBuffer();
		//W 固定前缀
		sb.append("W");
		//4位时间 例如  0728  月份和日期
		sb.append(DateUtils.getCurrentDateStrByFormat("MMdd"));
		//查询模板code
		Template template = new Template();
		template.setTemplateId(Integer.valueOf(tempId));
		Template tempEntity = tempService.queryOne(template);
		if(null!=tempEntity){
			sb.append(tempEntity.getTemplateCode());
		}
		//时间戳
		sb.append(((Long)DateUtils.getCurrentDate().getTime()).toString().substring(0, 9));
		return sb.toString();
	}
	
	/**
	 * 
	 * 描述：计算结束时间
	 * 创建人： 魏泽超
	 * 创建时间: 2016年7月21日 下午3:33:10
	 * @version
	 */
	private String formatOverDate(String rentedUnit,Integer rentedTime,Date date){
		if(date == null) return "";
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		switch (rentedUnit) {
			case "月": {
				c.add(Calendar.MONTH, rentedTime); // 目前時間加1個月    
				break;
			}
			case "年": {
				c.add(Calendar.YEAR, rentedTime); // 目前時間減2年    
				break;
			}
		}
		return df.format(c.getTime());
	}
}
