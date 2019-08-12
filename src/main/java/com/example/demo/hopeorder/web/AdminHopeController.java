package com.example.demo.hopeorder.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.addbalance.entity.Addbalance;
import com.example.demo.addbalance.service.IAddbalanceService;
import com.example.demo.balancerecord.entity.Balancerecord;
import com.example.demo.balancerecord.service.IBalancerecordService;
import com.example.demo.cgcenter.web.CgcenterController;
import com.example.demo.commisionorder.entity.Commisionorder;
import com.example.demo.commisionorder.service.ICommisionorderService;
import com.example.demo.common.DingdianResult;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.common.Result;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.service.ICustomerService;
import com.example.demo.good.entity.Good;
import com.example.demo.good.service.IGoodService;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.example.demo.hopeorder.modelvo.OrderStatusEunm;
import com.example.demo.hopeorder.modelvo.WbdianzanCanshu;
import com.example.demo.hopeorder.modelvo.WbdianzanInfor;
import com.example.demo.hopeorder.modelvo.WbdianzanVo;
import com.example.demo.hopeorder.modelvo.WbshuafenVo;
import com.example.demo.hopeorder.modelvo.WbzhuanfanVo;
import com.example.demo.hopeorder.service.IOrderService;
import com.example.demo.hopeorder.service.impl.DingdianService;
import com.example.demo.orderimplrecord.service.IOrderimplrecordService;
import com.example.demo.systempara.entity.Systempara;
import com.example.demo.systempara.service.ISystemparaService;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.GenerateSequenceUtil;

import lombok.experimental.var;

/**
 * <p>
 * 前端展示控制器 后台管理员订单相关管理页面
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-17
 */
@Controller
public class AdminHopeController {
	private static final Logger logger = LoggerFactory.getLogger(CgcenterController.class);
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IGoodService goodService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ISystemparaService systemparaService;
	@Autowired
	private IOrderimplrecordService orderimplrecordService;
	@Autowired
	private IAddbalanceService addbalanceService;
	@Autowired
	private ICommisionorderService commisionorderService;
	@Autowired
	private IBalancerecordService balancerecordService;
	
	@RequestMapping("/adminhope/allorderlist")
	public ModelAndView allorderlist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/adminhope/allorderlist");
		return mav;
	}
	
	@RequestMapping("/adminhope/allcustomerlist")
	public ModelAndView allcustomerlist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/adminhope/allcustomerlist");
		return mav;
	}
	
	@RequestMapping("/adminhope/allonecommcustomerlist")
	public ModelAndView allonecommcustomerlist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/adminhope/allonecommcustomerlist");
		return mav;
	}
	
	@RequestMapping("/adminhope/alltwocommcustomerlist")
	public ModelAndView alltwocommcustomerlist(String  bytwocustomerid ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("bytwocustomerid", bytwocustomerid);
		mav.setViewName("webui/adminhope/alltwocommcustomerlist");
		return mav;
	}
	
	@RequestMapping("/adminhope/alladdbalancelist")
	public ModelAndView addbalancelist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/adminhope/alladdbalancelist");
		return mav;
	}
	
	@RequestMapping("/adminhope/allbalancerecordlist")
	public ModelAndView allbalancerecordlist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/adminhope/allbalancerecordlist");
		return mav;
	}
	
	@RequestMapping("/adminhope/allcommorderlist")
	public ModelAndView allcommorderlist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/adminhope/allcommorderlist");
		return mav;
	}
	
	@RequestMapping("/adminhope/orderinfor")
	public ModelAndView orderinfor(Integer id) {
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/adminhope/orderinfor");
		return mav;
	}
	
	@RequestMapping("/adminhope/customerinfor")
	public ModelAndView customerinfor(Integer id) {
		ModelAndView mav = new ModelAndView();
		Customer customer = customerService.selectById(id);
		mav.addObject("customer", customer);
		mav.setViewName("webui/adminhope/customerinfor");
		return mav;
	}
	
	@RequestMapping("/adminhope/ajaxupdateorder")
	@ResponseBody
	public Result ajaxupdateorder(Hopeorder hopeorder) {
		Result result = new Result();
		boolean b;
		Hopeorder model = orderService.selectById(hopeorder.getId());
		model.setOrderusername(hopeorder.getOrderusername());
		model.setPositionstart(hopeorder.getPositionstart());
		model.setAllnum(hopeorder.getAllnum());
		model.setAllok(hopeorder.getAllok());
		model.setOrderurl(hopeorder.getOrderurl());
		if (hopeorder.getStatus()!= -2) {
			model.setStatus(hopeorder.getStatus());
		}
		b = orderService.updateById(model);		
		if (b) {
			result.setMsg("提交成功");
		}
		else {
			result.setSucess(false);
			result.setMsg("提交失败");
		}
		return result; 
	}
	@RequestMapping("/adminhope/ajaxupdatecustomer")
	@ResponseBody
	public Result ajaxupdatecustomer(Customer customer) {
		Result result = new Result();
		boolean b;
		Customer model = customerService.selectById(customer.getCustomerid());
		
		model.setBalance(customer.getBalance());
		model.setCashcommision(customer.getCashcommision());
		model.setCommision(customer.getCommision());
		model.setNocashcommision(customer.getNocashcommision());
		model.setLevelmony(customer.getLevelmony());
		model.setMonetary(customer.getMonetary());
		model.setPhone(customer.getPhone());
		model.setQq(customer.getQq());
		if (customer.getStatus() != -1) {
			model.setStatus(customer.getStatus());
		}
		if (customer.getLevel() != -1) {
			model.setLevel(customer.getLevel());
		}
		model.setNickname(customer.getNickname());
		
		b = customerService.updateById(model);		
		if (b) {
			result.setMsg("提交成功");
		}
		else {
			result.setSucess(false);
			result.setMsg("提交失败");
		}
		return result; 
	}
	
	@RequestMapping("/adminhope/ajaxallorderlist")
	@ResponseBody
	public PageVo<Hopeorder> getlist(Hopeorder hopeorder,PagePara pagePara) {
		return orderService.GetWbdianzanPageList(hopeorder, pagePara);
	}
	
	@RequestMapping("/adminhope/ajaxallcustomerlist")
	@ResponseBody
	public PageVo<Customer> ajaxallcustomerlist(Customer customer,PagePara pagePara) {
		return customerService.GetPageList(customer, pagePara);
	}
	
	@RequestMapping("/adminhope/ajaxallonecommcustomerlist")
	@ResponseBody
	public PageVo<Customer> ajaxallonecommcustomerlist(Customer customer,PagePara pagePara) {
		return customerService.GetPageList(customer, pagePara);
	}
	
	@RequestMapping("/adminhope/ajaxalltwocommcustomerlist")
	@ResponseBody
	public PageVo<Customer> ajaxalltwocommcustomerlist(Integer bytwocustomerid,Integer customerid,PagePara pagePara) {
		Customer newcustomer   = new Customer();
		newcustomer.setByinvitatecod(bytwocustomerid);
		newcustomer.setCustomerid(customerid);
		return customerService.GetPageList(newcustomer, pagePara);
	}
	
	@RequestMapping("/adminhope/ajaxalladdbalancelist")
	@ResponseBody
	public PageVo<Addbalance> ajaxalladdbalancelist(Addbalance addbalance,PagePara pagePara) {
		return addbalanceService.GetPageList(addbalance, pagePara);
	}
	
	@RequestMapping("/adminhope/ajaxallbalancerecordlist")
	@ResponseBody
	public PageVo<Balancerecord> ajaxallbalancerecordlist(Balancerecord addbalance,PagePara pagePara) {
		return balancerecordService.GetPageList(addbalance, pagePara);
	}
	
	@RequestMapping("/adminhope/ajaxallcommorderlist")
	@ResponseBody
	public PageVo<Commisionorder> ajaxallcommorderlist(Commisionorder commisionorder,PagePara pagePara) {
		return commisionorderService.GetPageList(commisionorder, pagePara);
	}
	
}