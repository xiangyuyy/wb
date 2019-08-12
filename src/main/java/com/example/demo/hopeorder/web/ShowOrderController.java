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
 * 前端展示控制器 客户端订单展示页面
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-17
 */
@Controller
public class ShowOrderController {
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
	
	@RequestMapping("/showorder/orderlist")
	public ModelAndView orderlist() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerid", account.getCustomerid());
		mav.setViewName("webui/showorder/orderlist");
		return mav;
	}
	
	@RequestMapping("/showorder/orderinfor")
	public ModelAndView orderinfor(Integer id) {
		logger.info("orderinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/showorder/orderinfor");
		return mav;
	}	
	
	@RequestMapping("/showorder/addbalancelist")
	public ModelAndView addbalancelist() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerid", account.getCustomerid());
		mav.setViewName("webui/showorder/addbalancelist");
		return mav;
	}
	@RequestMapping("/showorder/balancerecordlist")
	public ModelAndView balancerecordlist() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerid", account.getCustomerid());
		mav.setViewName("webui/showorder/balancerecordlist");
		return mav;
	}
		
	@RequestMapping("/showorder/onecommorderlist")
	public ModelAndView onecommorderlist() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerid", account.getCustomerid());
		mav.setViewName("webui/showorder/onecommorderlist");
		return mav;
	}
	
	@RequestMapping("/showorder/twocommorderlist")
	public ModelAndView twocommorderlist() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerid", account.getCustomerid());
		mav.setViewName("webui/showorder/twocommorderlist");
		return mav;
	}
	
	@RequestMapping("/showorder/onecommcustomerlist")
	public ModelAndView onecommcustomerlist() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerid", account.getCustomerid());
		mav.setViewName("webui/showorder/onecommcustomerlist");
		return mav;
	}
	
	@RequestMapping("/showorder/twocommcustomerlist")
	public ModelAndView twocommcustomerlist(String  bytwocustomerid ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("bytwocustomerid", bytwocustomerid);
		mav.setViewName("webui/showorder/twocommcustomerlist");
		return mav;
	}
		
	@RequestMapping("/showorder/addbalance")
	public ModelAndView addbalance() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerid", account.getCustomerid());
		mav.setViewName("webui/showorder/addbalance");
		return mav;
	}
	
	@RequestMapping("/showorder/addcommcash")
	public ModelAndView addcommcash() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("account", account);
		mav.setViewName("webui/showorder/addcommcash");
		return mav;
	}
	
	@RequestMapping("/showorder/customerupgrade")
	public ModelAndView customerupgrade() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		EntityWrapper<Systempara> ews = new EntityWrapper<Systempara>();
		Systempara systempara = systemparaService.selectOne(ews);
		ModelAndView mav = new ModelAndView();
		mav.addObject("account", account);
		mav.addObject("systempara", systempara);
		mav.setViewName("webui/showorder/customerupgrade");
		return mav;
	}
	
	@RequestMapping("/showorder/ajaxorderlist")
	@ResponseBody
	public PageVo<Hopeorder> getlist(Hopeorder hopeorder,PagePara pagePara) {
		return orderService.GetWbdianzanPageList(hopeorder, pagePara);
	}
	
	@RequestMapping("/showorder/ajaxaddbalancelist")
	@ResponseBody
	public PageVo<Addbalance> ajaxaddbalancelist(Addbalance addbalance,PagePara pagePara) {
		return addbalanceService.GetPageList(addbalance, pagePara);
	}
	
	@RequestMapping("/showorder/ajaxbalancerecordlist")
	@ResponseBody
	public PageVo<Balancerecord> ajaxbalancerecordlist(Balancerecord addbalance,PagePara pagePara) {
		return balancerecordService.GetPageList(addbalance, pagePara);
	}
	
	@RequestMapping("/showorder/ajaxaddcommcashlist")
	@ResponseBody
	public PageVo<Balancerecord> ajaxaddcommcashlist(Balancerecord addbalance,PagePara pagePara) {
	/*	addbalance.setType(1);*/
		return balancerecordService.GetPageList(addbalance, pagePara);
	}
	
	@RequestMapping("/showorder/ajaxonecommorderlist")
	@ResponseBody
	public PageVo<Commisionorder> ajaxonecommorderlist(Commisionorder commisionorder,PagePara pagePara) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		commisionorder.setLevel(1);
		commisionorder.setForgoodcustomerid(account.getCustomerid().toString());
		return commisionorderService.GetPageList(commisionorder, pagePara);
	}
	
	@RequestMapping("/showorder/ajaxtwocommorderlist")
	@ResponseBody
	public PageVo<Commisionorder> ajaxtwocommorderlist(Commisionorder commisionorder,PagePara pagePara) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		commisionorder.setLevel(2);
		commisionorder.setForgoodcustomerid(account.getCustomerid().toString());
		//commisionorder.setOnecustomerid(account.getCustomerid().toString());
		return commisionorderService.GetPageList(commisionorder, pagePara);
	}
	
	@RequestMapping("/showorder/ajaxonecommcustomerlist")
	@ResponseBody
	public PageVo<Customer> ajaxonecommcustomerlist(Customer customer,PagePara pagePara) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		Customer newcustomer   = new Customer();
		newcustomer.setByinvitatecod(account.getCustomerid());
		return customerService.GetPageList(newcustomer, pagePara);
	}
	
	@RequestMapping("/showorder/ajaxtwocommcustomerlist")
	@ResponseBody
	public PageVo<Customer> ajaxtwocommcustomerlist(Integer bytwocustomerid,PagePara pagePara) {
		Customer newcustomer   = new Customer();
		newcustomer.setByinvitatecod(bytwocustomerid);
		return customerService.GetPageList(newcustomer, pagePara);
	}
	
	@RequestMapping("/showorder/ajaxaddbalance")
	@ResponseBody
	public Result ajaxaddbalance(String customerid,String thirdnumber) {
		Result result = new Result();
		if (StringUtils.isEmpty(customerid)) {
			result.setSucess(false);
			result.setMsg("充值失败" +"用户id不能为空" );
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", customerid);
		Customer customer = customerService.selectOne(ew);
		if (customer == null) {
			result.setSucess(false);
			result.setMsg("充值失败" +"用户不存在" );
			return result;
		}
		if (StringUtils.isEmpty(thirdnumber)) {
			result.setSucess(false);
			result.setMsg("充值失败" +"交易流水号不能为空" );
			return result;
		}
		Addbalance addbalance = new Addbalance();
		addbalance.setBalanceorderid(GenerateSequenceUtil.generateSequenceNoByDate());
		addbalance.setCustomerid(customerid);
		addbalance.setBeforbalance(customer.getBalance());
		BigDecimal bigDecimal = new BigDecimal(10);		//接口获得充值参数
		addbalance.setBalance(bigDecimal);
		addbalance.setAfterbalance(customer.getBalance().add(bigDecimal));
		addbalance.setCreatetime(new Date());
		addbalance.setStatus(1);
		addbalance.setPayway("支付宝");
		addbalance.setThirdnumber(thirdnumber);
		// 余额记录表
		Balancerecord balancerecord = new Balancerecord();
		balancerecord.setBeforbalance(customer.getBalance());
		balancerecord.setAfterbalance(customer.getBalance().add(bigDecimal));
		balancerecord.setBalance((bigDecimal));
		balancerecord.setCreatetime(new Date());
		balancerecord.setCustomerid(customer.getCustomerid());
		balancerecord.setDetailordernum(addbalance.getBalanceorderid());
		balancerecord.setType(0); // 0充值 1佣金提现 2下单消费 升级冻结3 冻结解除4
		
		customer.setBalance(customer.getBalance().add(bigDecimal));
		
		boolean b = balancerecordService.insertOrder(balancerecord,addbalance,customer);	
		if (b) {
			result.setSucess(true);
			result.setMsg("");
			result.setData("");
		} else {
			result.setSucess(false);
			result.setMsg("充值失败");
		}
		return result;
	}	
	
	@RequestMapping("/showorder/ajaxaddcommcash")
	@ResponseBody
	public Result ajaxaddcommcash(String customerid,String money) {
		Result result = new Result();
		if (StringUtils.isEmpty(customerid)) {
			result.setSucess(false);
			result.setMsg("用户id不能为空" );
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", customerid);
		Customer customer = customerService.selectOne(ew);
		if (customer == null) {
			result.setSucess(false);
			result.setMsg("用户不存在");
			return result;
		}
		if (StringUtils.isEmpty(money)) {
			result.setSucess(false);
			result.setMsg("提现金额不能为空" );
			return result;
		}
		BigDecimal monenys = new BigDecimal(money);
		// 余额记录表
		Balancerecord balancerecord = new Balancerecord();
		balancerecord.setBeforbalance(customer.getBalance());
		balancerecord.setAfterbalance(customer.getBalance().add(monenys));
		balancerecord.setBalance((monenys));
		balancerecord.setCreatetime(new Date());
		balancerecord.setCustomerid(customer.getCustomerid());
		//佣金提现  升级冻结 冻结解除 特殊情况 外联号没有 随机生成
		balancerecord.setDetailordernum(GenerateSequenceUtil.generateSequenceNoByDate()); 
		balancerecord.setType(1); // 0充值 1佣金提现 2下单消费 升级冻结3 冻结解除4
		
		customer.setBalance(customer.getBalance().add(monenys));
		customer.setNocashcommision(customer.getNocashcommision().subtract(monenys));//待提现-
		customer.setCashcommision(customer.getCashcommision().add(monenys));//已经提现+
		
		boolean b = balancerecordService.insertCommCashOrder(balancerecord,customer);
		if (b) {
			result.setSucess(true);
			result.setMsg("");
			result.setData("");
		} else {
			result.setSucess(false);
			result.setMsg("充值失败");
		}
		return result;
	}
	@RequestMapping("/showorder/ajaxcustomerupgrade")
	@ResponseBody
	public Result ajaxcustomerupgrade(String customerid,String stype) {
		Result result = new Result();
		if (StringUtils.isEmpty(customerid)) {
			result.setSucess(false);
			result.setMsg("用户id不能为空" );
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", customerid);
		Customer customer = customerService.selectOne(ew);
		if (customer == null) {
			result.setSucess(false);
			result.setMsg("用户不存在");
			return result;
		}
		EntityWrapper<Systempara> ews = new EntityWrapper<Systempara>();
		Systempara systempara = systemparaService.selectOne(ews);
		var needmoney = new BigDecimal(0);
		if (stype.equals("1")) {
			needmoney = systempara.getLevelone();
		}
		else if (stype.equals("2")) {
			needmoney = systempara.getLeveltwo();
		}
		else if (stype.equals("3")) {
			needmoney = systempara.getLevelthree();
		}
		else if (stype.equals("4")) {
			needmoney = systempara.getLevelfour();
		}
		else {
			result.setSucess(false);
			result.setMsg("参数类型错误" );
			return result;
		}
		if (customer.getBalance().compareTo(needmoney) == -1) {
			result.setSucess(false);
			result.setMsg("余额不足"+needmoney+"请先充值余额");
			return result;
		}
		
		// 余额记录表
		Balancerecord balancerecord = new Balancerecord();
		balancerecord.setBeforbalance(customer.getBalance());
		balancerecord.setAfterbalance(customer.getBalance().subtract(needmoney));
		balancerecord.setBalance((needmoney).multiply(new BigDecimal(-1)));
		balancerecord.setCreatetime(new Date());
		balancerecord.setCustomerid(customer.getCustomerid());
		//佣金提现  升级冻结 冻结解除 特殊情况 外联号没有 随机生成
		balancerecord.setDetailordernum(GenerateSequenceUtil.generateSequenceNoByDate()); 
		balancerecord.setType(3); // 0充值 1佣金提现 2下单消费 升级冻结3 冻结解除4
		
		customer.setBalance(customer.getBalance().subtract(needmoney));
		customer.setLevel(Integer.parseInt(stype));
		customer.setLevelmony(customer.getLevelmony().add(needmoney));
		
		boolean b = balancerecordService.insertCommCashOrder(balancerecord,customer);
		if (b) {
			result.setSucess(true);
			result.setMsg("");
			result.setData("");
		} else {
			result.setSucess(false);
			result.setMsg("升级失败");
		}
		return result;
	}	
	//解除冻结
	@RequestMapping("/showorder/ajaxoutcustomerupgrade")
	@ResponseBody
	public Result ajaxoutcustomerupgrade() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		Result result = new Result();
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer customer = customerService.selectOne(ew);
		if (customer.getLevel() == 0) {
			result.setSucess(false);
			result.setMsg("普通会员没有冻结金额");
			return result;
		}
		EntityWrapper<Systempara> ews = new EntityWrapper<Systempara>();
		Systempara systempara = systemparaService.selectOne(ews);
		var needmoney = new BigDecimal(0);
		String stype = customer.getLevel().toString();
		if (stype.equals("1")) {
			needmoney = systempara.getLevelone();
		}
		else if (stype.equals("2")) {
			needmoney = systempara.getLeveltwo();
		}
		else if (stype.equals("3")) {
			needmoney = systempara.getLevelthree();
		}
		else if (stype.equals("4")) {
			needmoney = systempara.getLevelfour();
		}
		else {
			result.setSucess(false);
			result.setMsg("参数类型错误" );
			return result;
		}
/*		if (customer.getLevelmony().compareTo(needmoney) == -1) {
			result.setSucess(false);
			result.setMsg("冻结余额"+needmoney+"和会员等级不匹配，请联系管理员");
			return result;
		}*/
		
		// 余额记录表
		Balancerecord balancerecord = new Balancerecord();
		balancerecord.setBeforbalance(customer.getBalance());
		balancerecord.setAfterbalance(customer.getBalance().add(customer.getLevelmony()));//转到余额
		balancerecord.setBalance((customer.getLevelmony()));
		balancerecord.setCreatetime(new Date());
		balancerecord.setCustomerid(customer.getCustomerid());
		//佣金提现  升级冻结 冻结解除 特殊情况 外联号没有 随机生成
		balancerecord.setDetailordernum(GenerateSequenceUtil.generateSequenceNoByDate()); 
		balancerecord.setType(4); // 0充值 1佣金提现 2下单消费 升级冻结3 冻结解除4 
		
		customer.setBalance(customer.getBalance().add(customer.getLevelmony()));//转到余额
		customer.setLevel(0);//恢复等级
		customer.setLevelmony(new BigDecimal("0"));//清空冻结金额
		
		boolean b = balancerecordService.insertCommCashOrder(balancerecord,customer);
		if (b) {
			result.setSucess(true);
			result.setMsg("保证金返回余额，恢复普通会员");
			result.setData("");
		} else {
			result.setSucess(false);
			result.setMsg("操作失败，请联系管理员");
		}
		return result;
	}
	
	//解除冻结
	@RequestMapping("/showorder/updateorder")
	@ResponseBody
	public Result updateorder(Hopeorder order,String editestartime,String editeendtime) {
		Result result = new Result();
		Hopeorder model = orderService.selectById(order.getId());
		if (model == null) {
			result.setSucess(false);
			result.setMsg("操作失败，订单号没有找到");
			return result;
		}
		model.setAllok(order.getAllnum());
		model.setStatus(order.getStatus());
		model.setOrderusername(order.getOrderusername());
		model.setPositionstart(order.getPositionstart());
		
		if (!StringUtils.isEmpty(editestartime)) {
			try {
				Date dates = DateUtil.convertStringToDatOne(editestartime);
				model.setStartime(dates);
			} catch (ParseException e) {
				e.printStackTrace();
				result.setSucess(false);
				result.setMsg("操作失败,系统错误");
				return result;
			}
		}
		if (!StringUtils.isEmpty(editeendtime)) {
			try {
				Date datee = DateUtil.convertStringToDatOne(editeendtime);
				model.setEndtime(datee);
			} catch (ParseException e) {
				e.printStackTrace();
				result.setSucess(false);
				result.setMsg("操作失败,系统错误");
				return result;
			}
		}
		Balancerecord balancerecord = null;
		Customer customer = null;
		if (order.getStatus().intValue() == OrderStatusEunm.RETURNF.getValue()) {//退款处理
			if (model.getReturnmoney().compareTo(new BigDecimal(0)) != -1) {
				
				balancerecord = new Balancerecord();
				balancerecord.setStatus(3);//成功
				balancerecord.setCustomerid(model.getCustomerid());
				balancerecord.setDetailordernum(model.getOrdernum());
				balancerecord.setCreatetime(new Date());
				balancerecord.setType(6);//退单返回
				balancerecord.setBalance(order.getReturnmoney());		
				customer = customerService.selectById(model.getCustomerid());//不可能为空				
				balancerecord.setBeforbalance(customer.getBalance());
				balancerecord.setAfterbalance(customer.getBalance().add(order.getReturnmoney()));
			
				customer.setBalance(customer.getBalance().add(order.getReturnmoney()));//余额加回去
															
			}
		}
		boolean b = orderService.dealReturnMoney(order, balancerecord, customer);
		if (b) {
			result.setSucess(true);
			result.setMsg("操作成功");
		}
		else {
			result.setSucess(false);
			result.setMsg("操作失败，更新失败");
		}
		return result;
	}
}