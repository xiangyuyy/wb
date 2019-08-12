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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.balancerecord.entity.Balancerecord;
import com.example.demo.cgcenter.web.CgcenterController;
import com.example.demo.common.DingdianResult;
import com.example.demo.common.PageVo;
import com.example.demo.common.Result;
import com.example.demo.common.XianDaiResult;
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
import com.example.demo.hopeorder.modelvo.XDWbdianzanCanshu;
import com.example.demo.hopeorder.service.IOrderService;
import com.example.demo.hopeorder.service.impl.DingdianService;
import com.example.demo.hopeorder.service.impl.XianDaiService;
import com.example.demo.orderimplrecord.service.IOrderimplrecordService;
import com.example.demo.systempara.entity.Systempara;
import com.example.demo.systempara.service.ISystemparaService;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.GenerateSequenceUtil;

import lombok.experimental.var;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-17
 */
@Controller
public class XDOrderController {
	private static final Logger logger = LoggerFactory.getLogger(XDOrderController.class);
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

	@RequestMapping("/xdorder/addswbdianzan")
	@ResponseBody
	public Result addswbdianzan(Integer goodid, Integer kind, String facekind, String remark, String url,
			String urlrid) {
		List<WbdianzanVo> list = new LinkedList<WbdianzanVo>();
		Result result = new Result();
		if (kind == 2) {// 评论赞
			var urlrids = urlrid.split("\\n");
			for (String item : urlrids) {
				var content = item.split(",");
				if (!content[0].matches("(.*).weibo.(.*)")) {
					result.setSucess(false);
					result.setMsg("微博 地址不正确");
					return result;
				}
				if (Integer.parseInt(content[2]) <= 0) {
					result.setSucess(false);
					result.setMsg("数量不能小于0");
					return result;
				}
				WbdianzanVo model = new WbdianzanVo();
				model.setAllnum(new BigDecimal(content[2]));
				model.setGoodid(goodid);
				model.setKind(kind);
				model.setMid(content[1]);
				model.setRemark(remark);
				model.setOrderurl(content[0]);
				list.add(model);
			}
		} else {
			var urls = url.split("\\n");
			for (String item : urls) {
				var content = item.split(",");
				if (!content[0].matches("(.*).weibo.(.*)")) {
					result.setSucess(false);
					result.setMsg("微博 地址不正确");
					return result;
				}
				if (Integer.parseInt(content[1]) <= 0) {
					result.setSucess(false);
					result.setMsg("数量不能小于0");
					return result;
				}
				WbdianzanVo model = new WbdianzanVo();
				model.setAllnum(new BigDecimal(content[1]));
				model.setGoodid(goodid);
				model.setFacekind(facekind);
				model.setKind(kind);
				model.setRemark(remark);
				model.setOrderurl(content[0]);
				list.add(model);
			}
		}
		Boolean ifsucess = false;
		String msString = "";
		for (WbdianzanVo wbdianzanVo : list) {
			Result r = addwbdianzan(wbdianzanVo);
			if (r.isSucess()) {
				ifsucess = true;
				msString += wbdianzanVo.getOrderurl() + "提交成功" + "\n" + "</br>";
			} else {
				msString += wbdianzanVo.getOrderurl() + "提交失败，失败原因" + r.getMsg() + "\n" + "</br>";
			}
		}
		result.setMsg(msString);
		result.setSucess(ifsucess);
		return result;
	}

	@RequestMapping("/xdorder/addswbshuafen")
	@ResponseBody
	public Result addswbshuafen(Integer goodid, String remark, String url) {
		List<WbshuafenVo> list = new LinkedList<WbshuafenVo>();
		Result result = new Result();
		var urlrids = url.split("\\n");
		for (String item : urlrids) {
			var content = item.split(",");
			if (content[0].matches("[0-9]{1,}")) {
				result.setSucess(false);
				result.setMsg("微博id格式不正确，应该全是数字");
				return result;
			}
			if (Integer.parseInt(content[1]) <= 0) {
				result.setSucess(false);
				result.setMsg("数量不能小于0");
				return result;
			}
			WbshuafenVo model = new WbshuafenVo();
			model.setAllnum(new BigDecimal(content[1]));
			model.setGoodid(goodid);
			model.setRemark(remark);

			model.setOrderurl(content[0]);
			list.add(model);
		}
		Boolean ifsucess = false;
		String msString = "";
		for (WbshuafenVo wbdianzanVo : list) {
			Result r = addwbshuafen(wbdianzanVo);
			if (r.isSucess()) {
				ifsucess = true;
				msString += wbdianzanVo.getOrderurl() + "提交成功" + "\n" + "</br>";
			} else {
				msString += wbdianzanVo.getOrderurl() + "提交失败，失败原因" + r.getMsg() + "\n" + "</br>";
			}
		}
		result.setMsg(msString);
		result.setSucess(ifsucess);
		return result;
	}
	
	@RequestMapping("/xdorder/addswbzhuanfa")
	@ResponseBody
	public Result addswbzhuanfa(WbzhuanfanVo model, String url) {
		List<WbzhuanfanVo> list = new LinkedList<WbzhuanfanVo>();
		Result result = new Result();

			var urls = url.split("\\n");
			for (String item : urls) {
				var content = item.split(",");
				if (!content[0].matches("(.*).weibo.(.*)")) {
					result.setSucess(false);
					result.setMsg("微博 地址不正确");
					return result;
				}
				if (Integer.parseInt(content[1]) <= 0) {
					result.setSucess(false);
					result.setMsg("数量不能小于0");
					return result;
				}
/*				对象引用等于慎用 虽然表面是新建的对象 vo 
				但是== 之后是同一个对象的引用 导致list 对象都是一个 必须 clone 对象 存在引用 必须深度克隆对象*/
				WbzhuanfanVo vo = new WbzhuanfanVo();
				try {
					vo = (WbzhuanfanVo) model.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vo.setOrderurl(content[0]);
				vo.setAllnum(new Integer(content[1]));
				list.add(vo);
			}
		
		Boolean ifsucess = false;
		String msString = "";
		for (WbzhuanfanVo wbdianzanVo : list) {
			Result r = addwbzhuanfa(wbdianzanVo);
			if (r.isSucess()) {
				ifsucess = true;
				msString += wbdianzanVo.getOrderurl() + "提交成功" + "\n" + "</br>";
			} else {
				msString += wbdianzanVo.getOrderurl() + "提交失败，失败原因" + r.getMsg() + "\n" + "</br>";
			}
		}
		result.setMsg(msString);
		result.setSucess(ifsucess);
		return result;
	}



//抖音批量接口
	@RequestMapping("/xdorder/addsdyshuafen")
	@ResponseBody
	public Result addsdyshuafen(Integer goodid, String remark, String url) {
		List<WbshuafenVo> list = new LinkedList<WbshuafenVo>();
		Result result = new Result();
		var urls = url.split("\\n");
		for (String item : urls) {
			var content = item.split(",");
			if (!content[0].matches("(.*).douyin.(.*)")) {
				result.setSucess(false);
				result.setMsg("地址不正确");
				return result;
			}
			if (Integer.parseInt(content[1]) <= 0) {
				result.setSucess(false);
				result.setMsg("数量不能小于0");
				return result;
			}
			WbshuafenVo model = new WbshuafenVo();
			model.setAllnum(new BigDecimal(content[1]));
			model.setGoodid(goodid);
			model.setRemark(remark);

			model.setOrderurl(content[0]);
			list.add(model);
		}
		Boolean ifsucess = false;
		String msString = "";
		for (WbshuafenVo wbdianzanVo : list) {
			Result r = adddyshuafen(wbdianzanVo);
			if (r.isSucess()) {
				ifsucess = true;
				msString += wbdianzanVo.getOrderurl() + "提交成功" + "\n" + "</br>";
			} else {
				msString += wbdianzanVo.getOrderurl() + "提交失败，失败原因" + r.getMsg() + "\n" + "</br>";
			}
		}
		result.setMsg(msString);
		result.setSucess(ifsucess);
		return result;
	}

	@RequestMapping("/xdorder/addsdyshuazan")
	@ResponseBody
	public Result addsdyshuazan(Integer goodid, String remark, String url) {
		List<WbshuafenVo> list = new LinkedList<WbshuafenVo>();
		Result result = new Result();
		var urls = url.split("\\n");
		for (String item : urls) {
			var content = item.split(",");
			if (!content[0].matches("(.*).douyin.(.*)")) {
				result.setSucess(false);
				result.setMsg("地址不正确");
				return result;
			}
			if (Integer.parseInt(content[1]) <= 0) {
				result.setSucess(false);
				result.setMsg("数量不能小于0");
				return result;
			}
			WbshuafenVo model = new WbshuafenVo();
			model.setAllnum(new BigDecimal(content[1]));
			model.setGoodid(goodid);
			model.setRemark(remark);

			model.setOrderurl(content[0]);
			list.add(model);
		}
		Boolean ifsucess = false;
		String msString = "";
		for (WbshuafenVo wbdianzanVo : list) {
			Result r = adddyshuazan(wbdianzanVo);
			if (r.isSucess()) {
				ifsucess = true;
				msString += wbdianzanVo.getOrderurl() + "提交成功" + "\n" + "</br>";
			} else {
				msString += wbdianzanVo.getOrderurl() + "提交失败，失败原因" + r.getMsg() + "\n" + "</br>";
			}
		}
		result.setMsg(msString);
		result.setSucess(ifsucess);
		return result;
	}

	@RequestMapping("/xdorder/addwbdianzan")
	@ResponseBody
	public Result addwbdianzan(WbdianzanVo model) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer customer = customerService.selectOne(ew);

		Result result = new Result();
		Good good = goodService.selectById(model.getGoodid());
		Hopeorder order = new Hopeorder();
		if (Integer.parseInt(model.getAllnum().toString()) < good.getMinnumber()) {// 数量不能小于100
			model.setAllnum(new BigDecimal(good.getMinnumber()));
		}

		order.setAllnum(Integer.parseInt(model.getAllnum().toString()));
		order.setCreatetime(new Date());
		order.setUpdatetime(new Date());
		order.setCustomerid(account.getCustomerid());
		order.setGoodclassname(good.getGoodclassname());
		order.setGoodid(good.getId());
		order.setGoodtypename(good.getGoodtypename());
		order.setGoodname(good.getGoodname());
		order.setPositionstart(model.getPositionstart());
		order.setThirdname(good.getThirdname());
		order.setThirdtypename(good.getThirdtypename());

		order.setRemark(model.getRemark());
		order.setOrderurl(model.getOrderurl());
		order.setOrderusername(model.getOrderusername());
		order.setOrderkind(model.getKind());
		order.setOrdernum(GenerateSequenceUtil.generateSequenceNoByDate());// 订单号
		BigDecimal zhek = getZhek(customer);// 不同等级给的折扣信息

		BigDecimal money = good.getFastprice().multiply(model.getAllnum()).multiply(zhek);
		BigDecimal thirdmoney = good.getThirdfastprice().multiply(model.getAllnum());
		order.setPrice(good.getFastprice());
		order.setThirdprice(good.getThirdfastprice());
		order.setMoney(money);
		order.setThirdmoney(thirdmoney);
		order.setHopemoney(money.subtract(thirdmoney));// 利润
		if (customer.getBalance().compareTo(money) == -1) {
			result.setSucess(false);
			result.setMsg("提交失败，余额不足请充值");
			return result;
		}

		order.setStatus(OrderStatusEunm.FAILED.getValue());

		if (good.getThirdname().contains("现代")) {
			return xiandaiwbdianzan(model, order, good, customer);
		}
		if (good.getThirdname().contains("慢速")) {
			return slowOrder(order, customer);
		}
		return result;

	}

	@RequestMapping("/xdorder/addwbshuafen")
	@ResponseBody
	public Result addwbshuafen(WbshuafenVo model) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer customer = customerService.selectOne(ew);

		Result result = new Result();
		Good good = goodService.selectById(model.getGoodid());
		Hopeorder order = new Hopeorder();
		if (Integer.parseInt(model.getAllnum().toString()) < good.getMinnumber()) {// 数量不能小于500
			model.setAllnum(new BigDecimal(good.getMinnumber()));
		}

		order.setAllnum(Integer.parseInt(model.getAllnum().toString()));
		order.setCreatetime(new Date());
		order.setUpdatetime(new Date());
		order.setCustomerid(account.getCustomerid());
		order.setGoodclassname(good.getGoodclassname());
		order.setGoodid(good.getId());
		order.setGoodtypename(good.getGoodtypename());
		order.setGoodname(good.getGoodname());
		order.setThirdname(good.getThirdname());
		order.setThirdtypename(good.getThirdtypename());
		order.setRemark(model.getRemark());
		order.setOrderurl(model.getOrderurl());
		order.setOrderusername(model.getOrderusername());
		order.setPositionstart(model.getPositionstart());
		/* order.setOrderkind(model.getKind()); */
		order.setOrdernum(GenerateSequenceUtil.generateSequenceNoByDate());// 订单号
		BigDecimal zhek = getZhek(customer);// 不同等级给的折扣信息

		BigDecimal money = good.getFastprice().multiply(model.getAllnum()).multiply(zhek);
		BigDecimal thirdmoney = good.getThirdfastprice().multiply(model.getAllnum());
		order.setPrice(good.getFastprice());
		order.setThirdprice(good.getThirdfastprice());
		order.setMoney(money);
		order.setThirdmoney(thirdmoney);
		order.setHopemoney(money.subtract(thirdmoney));// 利润
		if (customer.getBalance().compareTo(money) == -1) {
			result.setSucess(false);
			result.setMsg("提交失败，余额不足请充值");
			return result;
		}
		order.setStatus(OrderStatusEunm.FAILED.getValue());

		if (good.getThirdname().contains("现代")) {
			return xiandaiwbshuafen(model, order, good, customer);
		}
		if (good.getThirdname().contains("慢速")) {
			return slowOrder(order, customer);
		}
		return result;

	}
	
	@RequestMapping("/xdorder/addwbzhuanfa")
	@ResponseBody
	public Result addwbzhuanfa(WbzhuanfanVo model) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer customer = customerService.selectOne(ew);

		Result result = new Result();
		Good good = goodService.selectById(model.getGoodid());
		Hopeorder order = new Hopeorder();
		if (Integer.parseInt(model.getAllnum().toString()) < good.getMinnumber()) {// 数量不能小于500
			model.setAllnum(good.getMinnumber());
		}

		order.setAllnum(Integer.parseInt(model.getAllnum().toString()));
		order.setCreatetime(new Date());
		order.setUpdatetime(new Date());
		order.setCustomerid(account.getCustomerid());
		order.setGoodclassname(good.getGoodclassname());
		order.setGoodid(good.getId());
		order.setGoodtypename(good.getGoodtypename());
		order.setGoodname(good.getGoodname());
		order.setThirdname(good.getThirdname());
		order.setThirdtypename(good.getThirdtypename());
		order.setRemark(model.getRemark());
		order.setOrderurl(model.getOrderurl());
		order.setOrderusername(model.getOrdername());
		order.setWbcontent(model.getOrdercontent());
		//order.setPositionstart(model.getPositionstart());
		/* order.setOrderkind(model.getKind()); */
		order.setOrdernum(GenerateSequenceUtil.generateSequenceNoByDate());// 订单号
		BigDecimal zhek = getZhek(customer);// 不同等级给的折扣信息

		BigDecimal money = good.getFastprice().multiply(new BigDecimal(model.getAllnum())).multiply(zhek);
		BigDecimal thirdmoney = good.getThirdfastprice().multiply(new BigDecimal(model.getAllnum()));
		order.setPrice(good.getFastprice());
		order.setThirdprice(good.getThirdfastprice());
		order.setMoney(money);
		order.setThirdmoney(thirdmoney);
		order.setHopemoney(money.subtract(thirdmoney));// 利润
		if (customer.getBalance().compareTo(money) == -1) {
			result.setSucess(false);
			result.setMsg("提交失败，余额不足请充值");
			return result;
		}
		order.setStatus(OrderStatusEunm.FAILED.getValue());

		if (good.getThirdname().contains("现代")) {
			return xiandaiwbzhuanfa(model, order, good, customer);
		}
		if (good.getThirdname().contains("慢速")) {
			return slowOrder(order, customer);
		}
		return result;

	}


//抖音
	@RequestMapping("/xdorder/adddyshuafen")
	@ResponseBody
	public Result adddyshuafen(WbshuafenVo model) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer customer = customerService.selectOne(ew);

		Result result = new Result();
		Good good = goodService.selectById(model.getGoodid());
		Hopeorder order = new Hopeorder();
		if (Integer.parseInt(model.getAllnum().toString()) < 100) {// 数量不能小于100
			model.setAllnum(new BigDecimal(100));
		}
		order.setAllnum(Integer.parseInt(model.getAllnum().toString()));
		order.setCreatetime(new Date());
		order.setUpdatetime(new Date());
		order.setCustomerid(account.getCustomerid());
		order.setGoodclassname(good.getGoodclassname());
		order.setGoodid(good.getId());
		order.setGoodtypename(good.getGoodtypename());
		order.setGoodname(good.getGoodname());
		order.setThirdname(good.getThirdname());
		order.setThirdtypename(good.getThirdtypename());
		order.setRemark(model.getRemark());
		order.setOrderurl(model.getOrderurl());
		/* order.setOrderkind(model.getKind()); */
		order.setOrdernum(GenerateSequenceUtil.generateSequenceNoByDate());// 订单号
		BigDecimal zhek = getZhek(customer);// 不同等级给的折扣信息

		BigDecimal money = good.getFastprice().multiply(model.getAllnum()).multiply(zhek);
		BigDecimal thirdmoney = good.getThirdfastprice().multiply(model.getAllnum());
		order.setPrice(good.getFastprice());
		order.setThirdprice(good.getThirdfastprice());
		order.setMoney(money);
		order.setThirdmoney(thirdmoney);
		order.setHopemoney(money.subtract(thirdmoney));// 利润
		if (customer.getBalance().compareTo(money) == -1) {
			result.setSucess(false);
			result.setMsg("提交失败，余额不足请充值");
			return result;
		}

		order.setStatus(OrderStatusEunm.FAILED.getValue());

		Map<String, String> map = new HashMap<String, String>();
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加抖音刷粉订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "dysz";
		String proid = "60";// 产品id 60为抖音关注 66为抖音低价关注
		map.put("token", token);
		map.put("addtype", addtype);
		map.put("type", type);
		map.put("proid", proid);
		// 封装json
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wburl", model.getOrderurl());
		jsonObject.put("remark", model.getRemark());
		jsonObject.put("allnum", model.getAllnum());
		String info = jsonObject.toJSONString();
		map.put("info", info);
		order.setOther(JSONArray.toJSONString(map));

		DingdianResult dingdianResult = DingdianService.postDingdian(url, map, customer.getCustomerid(), order.getOrdernum(),
				good.getGoodname(), orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			order.setSreturn(dingdianResult.getData());// 获得返回data
			String returnmsg = dingdianResult.getData();
			JSONObject json = JSON.parseObject(returnmsg);
			Integer status = json.getInteger("status");
			Integer position_end = json.getInteger("position_now") + json.getInteger("allnum");
			Integer position_now = json.getInteger("position_now");
			Integer position_start = json.getInteger("position_start");
			Integer allok = json.getInteger("allok");
			/* String wbcontent = json.getString("wbcontent"); */
			String wbname = json.getString("wbname");
			String taskid = json.getString("taskid");
			BigDecimal returnmoney = json.getBigDecimal("fee_base");

			order.setReturnmoney(returnmoney);
			order.setOrderusername(wbname);
			order.setStatus(status);
			order.setAllok(allok);
			order.setPositionstart(position_start);
			order.setPositionend(position_end);
			order.setPositionnow(position_now);
			/* order.setWbcontent(wbcontent); */
			order.setThridtaskid(taskid);
			boolean b = orderService.insertOrder(order, customer);
			if (b) {
				result.setMsg("提交成功");
			} else {
				result.setMsg("提交失败400，请联系管理员");
			}
		}

		else if (dingdianResult == null) {
			result.setSucess(false);
			result.setMsg("提交失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("提交失败" + dingdianResult.getMsg() + "，请联系管理员");
		}
		return result;

	}

	@RequestMapping("/xdorder/adddyshuazan")
	@ResponseBody
	public Result adddyshuazan(WbshuafenVo model) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer customer = customerService.selectOne(ew);

		Result result = new Result();
		Good good = goodService.selectById(model.getGoodid());
		Hopeorder order = new Hopeorder();
		if (Integer.parseInt(model.getAllnum().toString()) < 100) {// 数量不能小于100
			model.setAllnum(new BigDecimal(100));
		}
		order.setAllnum(Integer.parseInt(model.getAllnum().toString()));
		order.setCreatetime(new Date());
		order.setUpdatetime(new Date());
		order.setCustomerid(account.getCustomerid());
		order.setGoodclassname(good.getGoodclassname());
		order.setGoodid(good.getId());
		order.setGoodtypename(good.getGoodtypename());
		order.setGoodname(good.getGoodname());
		order.setThirdname(good.getThirdname());
		order.setThirdtypename(good.getThirdtypename());
		order.setRemark(model.getRemark());
		order.setOrderurl(model.getOrderurl());
		/* order.setOrderkind(model.getKind()); */
		order.setOrdernum(GenerateSequenceUtil.generateSequenceNoByDate());// 订单号
		BigDecimal zhek = getZhek(customer);// 不同等级给的折扣信息

		BigDecimal money = good.getFastprice().multiply(model.getAllnum()).multiply(zhek);
		BigDecimal thirdmoney = good.getThirdfastprice().multiply(model.getAllnum());
		order.setPrice(good.getFastprice());
		order.setThirdprice(good.getThirdfastprice());
		order.setMoney(money);
		order.setThirdmoney(thirdmoney);
		order.setHopemoney(money.subtract(thirdmoney));// 利润
		if (customer.getBalance().compareTo(money) == -1) {
			result.setSucess(false);
			result.setMsg("提交失败，余额不足请充值");
			return result;
		}

		order.setStatus(OrderStatusEunm.FAILED.getValue());

		Map<String, String> map = new HashMap<String, String>();
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加抖音点赞订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "dysz";
		String proid = "59";// 产品id 59抖音视频点赞 67 为抖音低价视频点赞
		map.put("token", token);
		map.put("addtype", addtype);
		map.put("type", type);
		map.put("proid", proid);
		// 封装json
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wburl", model.getOrderurl());
		jsonObject.put("remark", model.getRemark());
		jsonObject.put("allnum", model.getAllnum());
		String info = jsonObject.toJSONString();
		map.put("info", info);
		order.setOther(JSONArray.toJSONString(map));

		DingdianResult dingdianResult = DingdianService.postDingdian(url, map, customer.getCustomerid(), order.getOrdernum(),
				good.getGoodname(), orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			order.setSreturn(dingdianResult.getData());// 获得返回data
			String returnmsg = dingdianResult.getData();
			JSONObject json = JSON.parseObject(returnmsg);
			Integer status = json.getInteger("status");
			Integer position_end = json.getInteger("position_now") + json.getInteger("allnum");
			Integer position_now = json.getInteger("position_now");
			Integer position_start = json.getInteger("position_start");
			Integer allok = json.getInteger("allok");
			String wbcontent = json.getString("wbcontent");
			String wbname = json.getString("wbname");
			String taskid = json.getString("taskid");
			BigDecimal returnmoney = json.getBigDecimal("fee_base");

			order.setReturnmoney(returnmoney);
			order.setOrderusername(wbname);
			order.setStatus(status);
			order.setAllok(allok);
			order.setPositionstart(position_start);
			order.setPositionend(position_end);
			order.setPositionnow(position_now);
			order.setWbcontent(wbcontent);
			order.setThridtaskid(taskid);
			boolean b = orderService.insertOrder(order, customer);
			if (b) {
				result.setMsg("提交成功");
			} else {
				result.setMsg("提交失败400，请联系管理员");
			}
		}

		else if (dingdianResult == null) {
			result.setSucess(false);
			result.setMsg("提交失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("提交失败" + dingdianResult.getMsg() + "，请联系管理员");
		}
		return result;

	}

//接口查询
	@RequestMapping("/xdorder/synOrderInforList")
	@ResponseBody
	public Result synOrderInforList(String goodid) {
		Result result = new Result();
		EntityWrapper<Hopeorder> ew = new EntityWrapper<Hopeorder>();
		ew.where("goodid = {0}", goodid);
		ew.andNew().eq("isfast", 1);
		ew.andNew().notIn("status", 9); // 已完成
		ew.andNew().notIn("status", 10);// 已退款
		var list = orderService.selectList(ew);
		for (Hopeorder hopeorder : list) {
			if (hopeorder == null) {
				continue;
			}
			String taskid = hopeorder.getThridtaskid();
			WbdianzanCanshu wbdianzanCanshu = (WbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
					WbdianzanCanshu.class);
			String proid = wbdianzanCanshu.getProid();
			String type = wbdianzanCanshu.getType();
			String token = wbdianzanCanshu.getToken();
			String url = "http://wb.dd107.com:81/taskapi/gettaskstatus.do";// 查询订单
			Map<String, String> map = new HashMap<String, String>();
			map.put("proid", proid);
			map.put("type", type);
			map.put("taskids", taskid);
			map.put("token", token);
			DingdianResult dingdianResult = DingdianService.postDingdian(url, map, hopeorder.getCustomerid(),
					hopeorder.getOrdernum(), hopeorder.getGoodname(), orderimplrecordService);
			if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
				String returnmsg = dingdianResult.getData();
				JSONObject jsonFrist = JSON.parseObject(returnmsg);
				JSONObject json = JSON.parseObject(jsonFrist.getString(taskid));
				Integer status = json.getInteger("status");
				Integer allok = json.getInteger("allok");
				String time_start = json.getString("time_start");
				String time_end = json.getString("time_end");
				// BigDecimal fee_all = json.getBigDecimal("fee_all");

				hopeorder.setStatus(status);
				hopeorder.setAllok(allok);
				hopeorder.setPositionnow(hopeorder.getPositionstart() + allok);
				hopeorder.setStartime(DateUtil.stampTenToDate(time_start));
				hopeorder.setEndtime(DateUtil.stampTenToDate(time_end));
				var b = orderService.updateById(hopeorder);
				result.setSucess(true);
				result.setMsg("同步订单成功");
			}
		}
		return result;
	}

	@RequestMapping("/xdorder/synOrderInfor")
	@ResponseBody
	public Result synOrderInfor(String id) {
		Result result = new Result();
		Hopeorder hopeorder = orderService.selectById(id);
		if (hopeorder == null) {
			result.setSucess(false);
			result.setMsg("获取订单信息失败400，请联系管理员");
		}
		if (hopeorder.getIsfast() == 0) {
			result.setSucess(true);
			result.setMsg("慢速订单不同步");
		}
		String taskid = hopeorder.getThridtaskid();
		WbdianzanCanshu wbdianzanCanshu = (WbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				WbdianzanCanshu.class);
		String proid = wbdianzanCanshu.getProid();
		String type = wbdianzanCanshu.getType();
		String token = wbdianzanCanshu.getToken();
		String url = "http://wb.dd107.com:81/taskapi/gettaskstatus.do";// 查询订单
		Map<String, String> map = new HashMap<String, String>();
		map.put("proid", proid);
		map.put("type", type);
		map.put("taskids", taskid);
		map.put("token", token);
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map, hopeorder.getCustomerid(),
				hopeorder.getOrdernum(), hopeorder.getGoodname(), orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			String returnmsg = dingdianResult.getData();
			JSONObject jsonFrist = JSON.parseObject(returnmsg);
			JSONObject json = JSON.parseObject(jsonFrist.getString(taskid));
			Integer status = json.getInteger("status");
			Integer allok = json.getInteger("allok");
			String time_start = json.getString("time_start");
			String time_end = json.getString("time_end");
			// BigDecimal fee_all = json.getBigDecimal("fee_all");
			Integer position_start = json.getInteger("position_start");
			if (position_start > 0) {
				hopeorder.setPositionstart(position_start);
			}
			hopeorder.setStatus(status);
			hopeorder.setAllok(allok);
			hopeorder.setPositionnow(hopeorder.getPositionstart() + allok);
			hopeorder.setStartime(DateUtil.stampTenToDate(time_start));
			hopeorder.setEndtime(DateUtil.stampTenToDate(time_end));
			orderService.updateById(hopeorder);
			result.setSucess(true);
			result.setMsg("同步订单成功");
		} else if (dingdianResult == null) {
			result.setSucess(false);
			result.setMsg("同步订单失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("同步订单失败" + dingdianResult.getMsg() + "，请联系管理员");
		}
		return result;

	}

	@RequestMapping("/xdorder/synStartOrder")
	@ResponseBody
	public Result synStartOrder(String id) {
		Result result = new Result();
		Hopeorder hopeorder = orderService.selectById(id);
		if (hopeorder == null) {
			result.setSucess(false);
			result.setMsg("获取订单信息失败400，请联系管理员");
			return result;
		}
		if(hopeorder.getThirdname().equals("慢速")) {		
			hopeorder.setStatus(OrderStatusEunm.RUN.getValue());
			boolean b = orderService.updateById(hopeorder);
			if (b) {
				result.setSucess(true);
				result.setMsg("慢速订单开启成功，请等待更新相关信息");//待处理
			}
			else {
				result.setSucess(false);
				result.setMsg("慢速订单开启失败，请联系管理员");//待处理
			}
			return result;
		}
		
		String taskid = hopeorder.getThridtaskid();
		XDWbdianzanCanshu wbdianzanCanshu = (XDWbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				XDWbdianzanCanshu.class);
		if (wbdianzanCanshu == null) {
			result.setSucess(false);
			result.setMsg("操作失败，请联系管理员");//待处理
			return result;
		}
		String userId = wbdianzanCanshu.getUserId();
		String url = wbdianzanCanshu.getStopUrl();
		String state = "continue";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("state", state);
		map.put("id", taskid);
		XianDaiResult xiandaiResult = XianDaiService.postDingdian(url, map, hopeorder.getCustomerid(),
				hopeorder.getOrdernum(), hopeorder.getGoodname(), orderimplrecordService);
		if (xiandaiResult != null && xiandaiResult.isSuccess()) {
			hopeorder.setStatus(OrderStatusEunm.RUN.getValue());
			orderService.updateById(hopeorder);
			result.setSucess(true);
			result.setMsg("开启订单成功");
		} else if (xiandaiResult == null) {
			result.setSucess(false);
			result.setMsg("开启订单失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("开启订单失败" + xiandaiResult.getMessage()+ "，请联系管理员");
		}
		return result;

	}

	@RequestMapping("/xdorder/synStopOrder")
	@ResponseBody
	public Result synStopOrder(String id) {
		Result result = new Result();
		Hopeorder hopeorder = orderService.selectById(id);
		if (hopeorder == null) {
			result.setSucess(false);
			result.setMsg("获取订单信息失败400，请联系管理员");
		}
		if(hopeorder.getThirdname().equals("慢速")) {		
			hopeorder.setStatus(OrderStatusEunm.STOP.getValue());
			boolean b = orderService.updateById(hopeorder);
			if (b) {
				result.setSucess(true);
				result.setMsg("慢速订单停止成功，请等待更新相关信息");// 待处理
			} else {
				result.setSucess(false);
				result.setMsg("慢速订单停止失败，请联系管理员");// 待处理
			}
			return result;
		}
		String taskid = hopeorder.getThridtaskid();
		XDWbdianzanCanshu wbdianzanCanshu = (XDWbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				XDWbdianzanCanshu.class);
		if (wbdianzanCanshu == null) {
			result.setSucess(false);
			result.setMsg("操作失败，请联系管理员");//待处理
			return result;
		}
		String userId = wbdianzanCanshu.getUserId();
		String url = wbdianzanCanshu.getStopUrl();
		String state = "stop";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("state", state);
		map.put("id", taskid);
		XianDaiResult xiandaiResult = XianDaiService.postDingdian(url, map, hopeorder.getCustomerid(),
				hopeorder.getOrdernum(), hopeorder.getGoodname(), orderimplrecordService);
		if (xiandaiResult != null && xiandaiResult.isSuccess()) {
			hopeorder.setStatus(OrderStatusEunm.STOP.getValue());
			orderService.updateById(hopeorder);
			result.setSucess(true);
			result.setMsg("停止订单成功");
		} else if (xiandaiResult == null) {
			result.setSucess(false);
			result.setMsg("停止订单失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("停止订单失败" + xiandaiResult.getMessage()+ "，请联系管理员");
		}
		return result;
	}

	@RequestMapping("/xdorder/synRefundOrder")
	@ResponseBody
	public Result synRefundOrder(String id) {
		Result result = new Result();
		Hopeorder hopeorder = orderService.selectById(id);
		hopeorder.setStatus(OrderStatusEunm.RETURN.getValue());
		orderService.updateById(hopeorder);
		result.setSucess(true);
		result.setMsg("退单申请成功，请等待退款");
/*		if (hopeorder == null) {
			result.setSucess(false);
			result.setMsg("获取订单信息失败400，请联系管理员");
		}
		if (hopeorder.getIsfast() == 0) {
			result.setSucess(true);
			result.setMsg("慢速订单不同步");// 待处理
		}
		String taskid = hopeorder.getThridtaskid();
		XDWbdianzanCanshu wbdianzanCanshu = (XDWbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				XDWbdianzanCanshu.class);
		if (wbdianzanCanshu == null) {
			result.setSucess(false);
			result.setMsg("操作失败，请联系管理员");//待处理
			return result;
		}
		String userId = wbdianzanCanshu.getUserId();
		String url = wbdianzanCanshu.getStopUrl();
		String state = "finish";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("state", state);
		map.put("id", taskid);
		XianDaiResult xiandaiResult = XianDaiService.postDingdian(url, map, hopeorder.getCustomerid(),
				hopeorder.getOrdernum(), hopeorder.getGoodname(), orderimplrecordService);
		if (xiandaiResult != null && xiandaiResult.isSuccess()) {

			Balancerecord balancerecord = new Balancerecord();
			balancerecord.setStatus(3);// 成功
			balancerecord.setCustomerid(hopeorder.getCustomerid());
			balancerecord.setDetailordernum(hopeorder.getOrdernum());
			balancerecord.setCreatetime(new Date());
			balancerecord.setType(6);// 退单返回

			hopeorder.setStatus(OrderStatusEunm.STOP.getValue());

			result.setSucess(true);
			result.setMsg("退订单成功");
		} else if (xiandaiResult == null) {
			result.setSucess(false);
			result.setMsg("退订单失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("退订单失败" + xiandaiResult.getMessage());
		}*/
		return result;
	}

	@RequestMapping("/xdorder/getwbInfor")
	@ResponseBody
	public Result getwbInfor(String wburl) {
		Result result = new Result();
		String url = "http://wb.dd107.com:81/taskapi/getwbinfo.do";
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String type = "sf";
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", wburl);
		map.put("type", type);
		map.put("token", token);
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map, null, null, null,
				orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			result.setSucess(true);
			result.setMsg(dingdianResult.getMsg());
			result.setData(dingdianResult.getData());
		} else {
			result.setSucess(false);
			result.setMsg("查询失败" + dingdianResult.getMsg());
		}
		return result;
	}


	@RequestMapping("/xdorder/getwbuserInfor")
	@ResponseBody
	public Result getwbuserInfor(String wburl) {
		Result result = new Result();
		String url = "http://wb.dd107.com:81//taskapi/getwbuserinfo.do";
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String type = "sf";
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", wburl);
		map.put("type", type);
		map.put("token", token);
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map, null, null, null,
				orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			result.setSucess(true);
			result.setMsg(dingdianResult.getMsg());
			JSONObject jsondata = JSON.parseObject(dingdianResult.getData());
			JSONObject jsondata1 = jsondata.getJSONObject(wburl);
			result.setData(jsondata1);

		} else {
			result.setSucess(false);
			result.setMsg("查询失败" + dingdianResult.getMsg());
		}
		return result;
	}



	private BigDecimal getZhek(Customer customer) {
		EntityWrapper<Systempara> ews = new EntityWrapper<Systempara>();
		Systempara systempara = systemparaService.selectOne(ews);
		BigDecimal zhek = BigDecimal.ONE;// 不同等级给的折扣信息
		if (customer.getLevel() == 1) {
			zhek = systempara.getLevelonediscount();
		}
		if (customer.getLevel() == 2) {
			zhek = systempara.getLeveltwodiscount();
		}
		if (customer.getLevel() == 3) {
			zhek = systempara.getLevelthreediscount();
		}
		if (customer.getLevel() == 4) {
			zhek = systempara.getLevelfourdiscount();
		}
		return zhek;
	}

	private Result slowOrder(Hopeorder order, Customer customer) {
		Result result = new Result();
		order.setStatus(OrderStatusEunm.CHECKED.getValue());
		boolean b = orderService.insertOrder(order, customer);
		if (b) {
			result.setMsg("提交成功");
		} else {
			result.setSucess(false);
			result.setMsg("提交失败400，请联系管理员");
		}
		return result;
	}

	// 现代接口

	// 微博点赞
	private Result xiandaiwbdianzan(WbdianzanVo model, Hopeorder order, Good good, Customer customer) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>();
		String url = "";// 不同的地址注意
		String userId = "dcbdd60d61efd67d";
		String type = "";// 查询接口用
		String ourl = "";
		String stopUrl = "";//暂停url
		/*
		 * --------type值-------- forward【初级转发，极速转发】 fans【初级粉丝】 like【极速赞】 comment【极速评论】
		 * commentlike【初级评论赞】 forward-senior【高级转发】 fans-senior【高级粉丝】 like-senior【高级赞】
		 * comment-senior【高级评论】 --------type值--------
		 */
		String id = ""; // 产品3(刷量点赞速度会慢点) 21(优速赞速度快)
		if (good.getThirdtypename().contains("初级赞")) {
			type = "like";
			url = "http://wb.xiandai6.com/xiandai/api/orderLike";
			stopUrl = "http://wb.xiandai6.com/xiandai/api/updateOrderLikeState";
			if (good.getThirdtypename().contains("刷量点赞")) {
				id = "3";
			} else {
				id = "21";
			}
			ourl = model.getOrderurl();
		}
		if (good.getThirdtypename().contains("初级评论赞")) {
			type = "commentlike";
			url = "http://wb.xiandai6.com/xiandai/api/orderCommentLike";
			stopUrl = "http://wb.xiandai6.com/xiandai/api/updateOrderCommentLikeState";
			id = "18";
			ourl = model.getMid();
		}
		if (good.getThirdtypename().contains("高级赞")) {
			type = "like-senior";
			url = "http://wb.xiandai6.com/senior/xiandai/api/orderLike";
			stopUrl = "http://wb.xiandai6.com/senior/xiandai/api/updateOrderCommentLikeState";
			id = "6";
			ourl = model.getOrderurl();
		}
		/*
		 * if (good.getThirdtypename().contains("高级评论赞")) { type = "commentlike"; url =
		 * "http://wb.xiandai6.com/senior/xiandai/api/orderCommentLike"; id = "19"; ourl
		 * = model.getMid(); }
		 */

		map.put("userId", userId);
		map.put("type", type);
		map.put("id", id);
		map.put("ourl", ourl);
		map.put("onum", model.getAllnum().toString());


		XianDaiResult xiandaiResult = XianDaiService.postDingdian(url, map, customer.getCustomerid(),
				order.getOrdernum(), good.getGoodname(), orderimplrecordService);
		if (xiandaiResult != null && xiandaiResult.isSuccess()) {
			/*
			 * order.setSreturn(dingdianResult.getData());// 获得返回data String returnmsg =
			 * dingdianResult.getData(); JSONObject json = JSON.parseObject(returnmsg);
			 * Integer status = json.getInteger("status"); Integer position_end =
			 * json.getInteger("position_now") + json.getInteger("allnum"); Integer
			 * position_now = json.getInteger("position_now"); Integer position_start =
			 * json.getInteger("position_start"); Integer allok = json.getInteger("allok");
			 * String wbcontent = json.getString("wbcontent"); String wbname =
			 * json.getString("wbname"); BigDecimal returnmoney =
			 * json.getBigDecimal("fee_base");
			 * 
			 * order.setReturnmoney(returnmoney); order.setOrderusername(wbname);
			 * order.setStatus(status); order.setAllok(allok);
			 * order.setPositionstart(position_start); order.setPositionend(position_end);
			 * order.setPositionnow(position_now); order.setWbcontent(wbcontent);
			 */
			map.put("stopUrl", stopUrl);
			order.setOther(JSONArray.toJSONString(map));
			String taskid = xiandaiResult.getData();
			order.setStatus(OrderStatusEunm.RUN.getValue());
			order.setThridtaskid(taskid);
			boolean b = orderService.insertOrder(order, customer);
			if (b) {
				result.setMsg("提交成功");
			} else {
				result.setMsg("提交失败400，请联系管理员");
			}
		}

		else if (xiandaiResult == null) {
			result.setSucess(false);
			result.setMsg("提交失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("提交失败x" + xiandaiResult.getMessage() + "，请联系管理员");
		}
		return result;
	}

	// 微博刷粉
	private Result xiandaiwbshuafen(WbshuafenVo model, Hopeorder order, Good good, Customer customer) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>();
		String url = "";// 不同的地址注意
		String userId = "dcbdd60d61efd67d";
		String type = "";// 查询接口用
		String ourl = "";
		String stopUrl = "";		
		ourl = model.getOrderurl();
		/*
		 * --------type值-------- forward【初级转发，极速转发】 fans【初级粉丝】 like【极速赞】 comment【极速评论】
		 * commentlike【初级评论赞】 forward-senior【高级转发】 fans-senior【高级粉丝】 like-senior【高级赞】
		 * comment-senior【高级评论】 --------type值--------
		 */
		String id = ""; // 产品3(刷量点赞速度会慢点) 21(优速赞速度快)
		if (good.getThirdtypename().contains("初级粉丝")) {
			type = "fans";
			id = "1";
			url = "http://wb.xiandai6.com/xiandai/api/orderFansApi";
			stopUrl = "http://wb.xiandai6.com/xiandai/api/updateOrderFansState";
			map.put("speedNum", "0");
			if (good.getThirdtypename().contains("掉粉不补")) {
				map.put("guarantee", "0");
			} else {
				map.put("guarantee", "1");
			}
		}
		if (good.getThirdtypename().contains("高级粉丝")) {
			type = "fans-senior";
			url = "http://wb.xiandai6.com/senior/xiandai/api/orderFans";
			stopUrl = "http://wb.xiandai6.com/senior/xiandai/api/updateOrderFansState";
			id = "4";
		}

		map.put("userId", userId);
		map.put("type", type);
		map.put("id", id);
		map.put("ourl", ourl);
		map.put("onum", model.getAllnum().toString());

		XianDaiResult xiandaiResult = XianDaiService.postDingdian(url, map, customer.getCustomerid(),
				order.getOrdernum(), good.getGoodname(), orderimplrecordService);
		if (xiandaiResult != null && xiandaiResult.isSuccess()) {

			map.put("stopUrl", stopUrl);
			order.setOther(JSONArray.toJSONString(map));
			String taskid = xiandaiResult.getData();
			order.setThridtaskid(taskid);
			order.setStatus(OrderStatusEunm.RUN.getValue());
			boolean b = orderService.insertOrder(order, customer);
			if (b) {
				result.setMsg("提交成功");
			} else {
				result.setMsg("提交失败400，请联系管理员");
			}
		}

		else if (xiandaiResult == null) {
			result.setSucess(false);
			result.setMsg("提交失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("提交失败x" + xiandaiResult.getMessage() + "，请联系管理员");
		}
		return result;
	}

	// 微博转发
	private Result xiandaiwbzhuanfa(WbzhuanfanVo model, Hopeorder order, Good good, Customer customer) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>();
		String url = "";// 不同的地址注意
		String userId = "dcbdd60d61efd67d";
		String type = "";// 查询接口用
		String ourl = "";
		String stopUrl = "";
		ourl = model.getOrderurl();
		/*
		 * --------type值-------- forward【初级转发，极速转发】 fans【初级粉丝】 like【极速赞】 comment【极速评论】
		 * commentlike【初级评论赞】 forward-senior【高级转发】 fans-senior【高级粉丝】 like-senior【高级赞】
		 * comment-senior【高级评论】 --------type值--------
		 */
		String id = ""; 
		if (good.getThirdtypename().contains("极速转发")) {
			type = "forward";
			id = "13";// 13 极速转发 22 优速转发
			url = "http://wb.xiandai6.com/xiandai/api/orderForward";
			stopUrl = "http://wb.xiandai6.com/xiandai/api/updateOrderForwardState";
			if (good.getThirdtypename().contains("极速转发")) {
				id = "13";
			} else {
				id = "22";
			}
/*			comment：转发语（可空，空则使用默认转发语，用\n分割）
			isComment：true（同时评论給原作者）false（不评论）
			commentType：1 无内容 2 系统默认内容 3自定义内容*/
			map.put("comment", model.getZfcomments()); 
			if (model.getIscomment_onoff() == 0) {
				map.put("isComment", "false");
			}
			else {
				map.put("isComment", "true");
			}
			map.put("commentType", model.getIscomment().toString());//1 无内容 2 系统默认内容 3自定义内容
			order.setPositionstart(model.getOrderzfnum());
			
		}
		if (good.getThirdtypename().contains("极速评论")) {
			type = "comment";
			id = "14";
			url = "http://wb.xiandai6.com/xiandai/api/orderComment";
			stopUrl = "http://wb.xiandai6.com/xiandai/api/updateOrderCommentState";
			map.put("comment", model.getZfcomments()); 
			map.put("commentType", model.getIscomment().toString());// 2 系统默认内容 3自定义内容		
			order.setPositionstart(model.getOrderplnum());
		}
		if (good.getThirdtypename().contains("高级转发")) {
			type = "forward-senior";
			id = "5";
			url = "http://wb.xiandai6.com/senior/xiandai/api/orderForward";	
			stopUrl = "http://wb.xiandai6.com/senior/xiandai/api/updateOrderForwardState";
			order.setPositionstart(model.getOrderplnum());
		}
		if (good.getThirdtypename().contains("高级转发带评论语")) {
			type = "forward-senior";
			id = "8";
			url = "http://wb.xiandai6.com/senior/xiandai/api/orderForward";	
			stopUrl = "http://wb.xiandai6.com/senior/xiandai/api/updateOrderForwardState";
			map.put("comment", model.getZfcomments()); 
			order.setPositionstart(model.getOrderzfnum());
		}
		map.put("userId", userId);
		map.put("type", type);
		map.put("id", id);
		map.put("ourl", ourl);
		map.put("onum", model.getAllnum().toString());

		XianDaiResult xiandaiResult = XianDaiService.postDingdian(url, map, customer.getCustomerid(),
				order.getOrdernum(), good.getGoodname(), orderimplrecordService);
		if (xiandaiResult != null && xiandaiResult.isSuccess()) {

			map.put("stopUrl", stopUrl);
			order.setOther(JSONArray.toJSONString(map));
			String taskid = xiandaiResult.getData();
			order.setThridtaskid(taskid);
			order.setStatus(OrderStatusEunm.RUN.getValue());
			boolean b = orderService.insertOrder(order, customer);
			if (b) {
				result.setMsg("提交成功");
			} else {
				result.setMsg("提交失败400，请联系管理员");
			}
		}

		else if (xiandaiResult == null) {
			result.setSucess(false);
			result.setMsg("提交失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("提交失败x" + xiandaiResult.getMessage() + "，请联系管理员");
		}
		return result;
	}
}
