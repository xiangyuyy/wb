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
 * 前端控制器   顶点业务逻辑处理
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-17
 */
@Controller
public class OrderController {
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
	@RequestMapping("/order/addswbdianzan")
	@ResponseBody
	public Result addswbdianzan(Integer goodid, Integer kind, String facekind, String remark,
			String url, String urlrid) {
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

	@RequestMapping("/order/addswbshuafen")
	@ResponseBody
	public Result addswbshuafen(Integer goodid,  String remark, String url) {
		List<WbshuafenVo> list = new LinkedList<WbshuafenVo>();
		Result result = new Result();
		var urlrids = url.split("\\n");
		for (String item : urlrids) {
			var content = item.split(",");
			if (content[0].matches("[0-9]{1,}")) {
				;
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

	@RequestMapping("/order/addswbshipbf")
	@ResponseBody
	public Result addswbshipbf(Integer goodid, String remark, String url) {
		List<WbshuafenVo> list = new LinkedList<WbshuafenVo>();
		Result result = new Result();
		var urls = url.split("\\n");
		for (String item : urls) {
			var content = item.split(",");
			if (!content[0].matches("(.*).weibo.(.*)")) {
				result.setSucess(false);
				result.setMsg("视频地址不正确");
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
			Result r = addwbshipbf(wbdianzanVo);
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
	@RequestMapping("/order/addsdyshuafen")
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

	@RequestMapping("/order/addsdyshuazan")
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
	@RequestMapping("/order/addwbdianzan")
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
        if (Integer.parseInt(model.getAllnum().toString()) < good.getMinnumber()) {// 数量不能小于设置
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
		
		if (good.getThirdname().contains("顶点")) {
			return dingdianwbdianzan(model, order, good, customer);
		}
		if (good.getThirdname().contains("慢速")) {
			return slowOrder(order, customer);
		}
		return result;

	}

	@RequestMapping("/order/addwbzhuanfa")
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
		if (customer.getBalance().compareTo(model.getMoney()) == -1) {// 先验证总价
			result.setSucess(false);
			result.setMsg("提交失败，余额不足请充值");
			return result;
		}
		Good good = goodService.selectById(model.getGoodid());
		Hopeorder order = new Hopeorder();// 主订单
		order.setAllnum(Integer.parseInt(model.getAllnum().toString()));
		if (model.getAllnum() < 100) {// 数量不能小于100
			order.setAllnum(100);
		}
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
		order.setOrderurl(model.getOrderurl());
		order.setOrderkind(model.getPlzftype());// 0转发 1评论
		order.setOrdernum(GenerateSequenceUtil.generateSequenceNoByDate());// 订单号
		Hopeorder orderchild = null;// 子订单
		if (model.getIscomment_onoff() == 1) {// 同时评论
			orderchild = new Hopeorder();
			if (model.getZfiscomment() == 1) { // 指定内容
				orderchild.setAllnum(model.getZfcurrentnumber());
				if (model.getZfcurrentnumber() < 100) { // 转发评论的数量不能小于100
					orderchild.setAllnum(100);
				}
			} else {// 同时发语
				orderchild.setAllnum(model.getZftspycurrentnumber());
				if (model.getZftspycurrentnumber() < 100) { // 转发评论的数量不能小于100
					orderchild.setAllnum(100);
				}
			}
			orderchild.setCreatetime(new Date());
			orderchild.setUpdatetime(new Date());
			orderchild.setCustomerid(account.getCustomerid());
			orderchild.setGoodclassname(good.getGoodclassname());
			orderchild.setGoodid(good.getId());
			orderchild.setGoodtypename(good.getGoodtypename());
			orderchild.setGoodname(good.getGoodname());
			order.setThirdname(good.getThirdname());
			order.setThirdtypename(good.getThirdtypename());
			orderchild.setRemark(model.getRemark());
			orderchild.setOrderurl(model.getOrderurl());
			order.setOrderkind(1);// 0转发 1评论
			orderchild.setOrdernum(GenerateSequenceUtil.generateSequenceNoByDate());// 订单号
		}
		Good dzgood = null;// 商品赞
		Hopeorder ordershuazan = null;// 刷赞订单
		if (model.getSztype() != 0) {//
			ordershuazan = new Hopeorder();
			ordershuazan.setAllnum(model.getSzallnum());
			if (model.getAllnum() < 100) { // 刷赞数量不能小于100
				ordershuazan.setAllnum(100);
			}
			if (model.getSztype() == 1) {
				dzgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%空闲赞%"));
			}
			if (model.getSztype() == 2) {
				dzgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%初级赞%"));
			}
			if (model.getSztype() == 3) {
				dzgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%高速赞%"));
			}
			ordershuazan.setCreatetime(new Date());
			ordershuazan.setUpdatetime(new Date());
			ordershuazan.setCustomerid(account.getCustomerid());
			ordershuazan.setGoodclassname(dzgood.getGoodclassname());
			ordershuazan.setGoodid(dzgood.getId());
			ordershuazan.setGoodtypename(dzgood.getGoodtypename());
			ordershuazan.setGoodname(dzgood.getGoodname());
			order.setThirdname(good.getThirdname());
			order.setThirdtypename(good.getThirdtypename());
			ordershuazan.setRemark(model.getRemark());
			ordershuazan.setOrderurl(model.getOrderurl());
			ordershuazan.setOrderkind(1);// （刷赞 有表情赞3 微博赞1 评论赞2）
			ordershuazan.setOrdernum(GenerateSequenceUtil.generateSequenceNoByDate());// 订单号
		}
		 BigDecimal zhek = getZhek(customer);// 不同等级给的折扣信息
			BigDecimal allmoney = new BigDecimal(0);
			BigDecimal money = good.getFastprice().multiply(new BigDecimal(order.getAllnum())).multiply(zhek);
			BigDecimal thirdmoney = good.getThirdfastprice().multiply(new BigDecimal(order.getAllnum()));
			order.setPrice(good.getFastprice());
			order.setThirdprice(good.getThirdfastprice());
			order.setMoney(money);
			order.setThirdmoney(thirdmoney);
			order.setHopemoney(money.subtract(thirdmoney));// 利润
			allmoney = allmoney.add(money);
			if (orderchild != null) {
				BigDecimal moneychild = good.getFastprice().multiply(new BigDecimal(orderchild.getAllnum()))
						.multiply(zhek);
				BigDecimal thirdmoneychild = good.getThirdfastprice().multiply(new BigDecimal(orderchild.getAllnum()));
				orderchild.setPrice(good.getFastprice());
				orderchild.setThirdprice(good.getThirdfastprice());
				orderchild.setMoney(moneychild);
				orderchild.setThirdmoney(thirdmoneychild);
				orderchild.setHopemoney(moneychild.subtract(thirdmoneychild));// 利润
				allmoney = allmoney.add(moneychild);
			}
			if (ordershuazan != null) {
				BigDecimal moneyshuazan = dzgood.getFastprice().multiply(new BigDecimal(ordershuazan.getAllnum()))
						.multiply(zhek);
				BigDecimal thirdmoneyshuazan = dzgood.getThirdfastprice()
						.multiply(new BigDecimal(ordershuazan.getAllnum()));
				ordershuazan.setPrice(dzgood.getFastprice());
				ordershuazan.setThirdprice(dzgood.getThirdfastprice());
				ordershuazan.setMoney(moneyshuazan);
				ordershuazan.setThirdmoney(thirdmoneyshuazan);
				ordershuazan.setHopemoney(moneyshuazan.subtract(thirdmoneyshuazan));// 利润
				allmoney = allmoney.add(moneyshuazan);
			}
			if (customer.getBalance().compareTo(allmoney) == -1) {
				result.setSucess(false);
				result.setMsg("提交失败，余额不足请充值");
				return result;
			}

		order.setStatus(OrderStatusEunm.FAILED.getValue());
		Map<String, String> map = new HashMap<String, String>();
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加刷赞订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "zfpl";
		String proid = "3"; // 产品id 3 刷量转发, 22 转评达人20%, 23 转评达人80%, 39 博文转评
		if (good.getGoodname().contains("刷量转发")) {
			proid = "3";
		}
		if (good.getGoodname().contains("转评达人20%")) {
			proid = "22";
		}
		if (good.getGoodname().contains("转评达人80%")) {
			proid = "23";
		}
		if (good.getGoodname().contains("博文转评")) {
			proid = "39";
		}
		map.put("token", token);
		map.put("addtype", addtype);
		map.put("type", type);
		map.put("proid", proid);
		// 封装json
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wburl", model.getOrderurl());
		if (model.getPlzftype() == 0) { // 是否评论转发0 1评论 （0，0 是转发 1，0是评论）
			jsonObject.put("plzftype", "0,0");
		} else {
			jsonObject.put("plzftype", "0,1");
		}
		jsonObject.put("allnum", model.getAllnum());
		jsonObject.put("iscomment", model.getIscomment());
		jsonObject.put("countnumber", model.getCountnumber());// 指定内容数量
		jsonObject.put("comments", model.getComments());// 指定内容
		jsonObject.put("comment_keyword", model.getComment_keyword());
		jsonObject.put("comment_keyword_nums", model.getComment_keyword_nums());
		jsonObject.put("iscomment_onoff", model.getIscomment_onoff());
		if (model.getSztype() != 0) {
			String sztype = "24"; // 产品id ，24 空闲赞， 41 高速赞， 42 优速赞
			if (model.getSztype() == 1) {
				proid = "24";
			}
			if (model.getSztype() == 2) {
				proid = "42";
			}
			if (model.getSztype() == 3) {
				proid = "41";
			}
			jsonObject.put("sztype", sztype);
			jsonObject.put("szallnum", model.getSzallnum());
		}
		jsonObject.put("remark", model.getRemark());
		String info = jsonObject.toJSONString();
		map.put("info", info);

		if (model.getIscomment_onoff() == 1) { // 同时评论
			JSONObject jsonObjectpl = new JSONObject(); // 评论语： 0，同转发语（只有info中iscomment为2，5时才有效） 2，指定内容 5，人工内容
			if (model.getZfiscomment() == 0) { // 同时发语
				jsonObjectpl.put("iscomment", 0);
			} else {// 指定内容
				jsonObjectpl.put("iscomment", 2);
				jsonObjectpl.put("countnumber", model.getZfcurrentnumber());
				jsonObjectpl.put("comments", model.getZfcomments());
			}
			String infopl = jsonObjectpl.toJSONString();
			map.put("infopl", infopl);
		}
		map.put("details", "yes");
		
		order.setOther(JSONArray.toJSONString(map));
		
			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
			if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {

				JSONObject jsonObjectdatazf = null;
				JSONObject jsonObjectdatapl = null;
				JSONObject jsonObjectdatasz = null;
				order.setSreturn(dingdianResult.getData());// 获得返回data
				String returnmsg = dingdianResult.getData();
				var iftrue = false;//是否全部生成成功
				var  iftruemsg ="";
				JSONArray jsondata = JSON.parseArray(returnmsg);
				for (int i = 0; i < jsondata.size(); i++) {
					JSONObject jsonObjectdata = jsondata.getJSONObject(i);
					if (jsonObjectdata.getInteger("zf")!=null && jsonObjectdata.getInteger("zf") == 1 ) {
						jsonObjectdatazf = jsonObjectdata;
					}
					if (jsonObjectdata.getInteger("pl")!=null &&jsonObjectdata.getInteger("pl") == 1) {
						jsonObjectdatapl = jsonObjectdata;
					}
					if (jsonObjectdata.getInteger("sz")!=null &&jsonObjectdata.getInteger("sz") == 1) {
						jsonObjectdatasz = jsonObjectdata;
					}
				}
				JSONObject json = null;
				if (model.getPlzftype() == 0) {// 0转发 1评论
					json = jsonObjectdatazf;
				} else {
					json = jsonObjectdatapl;
				}
				if (json != null) {
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
						iftrue = true;
						iftruemsg = "转发评论订单生产成功!";
					}
				}
				if (model.getIscomment_onoff() == 1) {// 同时评论
					JSONObject childjson = jsonObjectdatapl;
					if (childjson != null) {
						Integer childstatus = childjson.getInteger("status");
						Integer childposition_end = childjson.getInteger("position_now") + json.getInteger("allnum");
						Integer childposition_now = childjson.getInteger("position_now");
						Integer childposition_start = childjson.getInteger("position_start");
						Integer childallok = childjson.getInteger("allok");
						String childwbcontent = childjson.getString("wbcontent");
						String childwbname = childjson.getString("wbname");
						String childtaskid = childjson.getString("taskid");
						BigDecimal childreturnmoney = childjson.getBigDecimal("fee_base");

						orderchild.setReturnmoney(childreturnmoney);
						orderchild.setOrderusername(childwbname);
						orderchild.setStatus(childstatus);
						orderchild.setAllok(childallok);
						orderchild.setPositionstart(childposition_start);
						orderchild.setPositionend(childposition_end);
						orderchild.setPositionnow(childposition_now);
						orderchild.setWbcontent(childwbcontent);
						orderchild.setThridtaskid(childtaskid);
						boolean orderchildresult = orderService.insertOrder(orderchild, customer);	
						if (orderchildresult) {
							iftrue = true;
							iftruemsg += "同时评论订单生产成功!";
						}
					}
				}
				if (model.getSztype() != 0) {
					if (jsonObjectdatapl != null) {
						JSONObject szjson = jsonObjectdatapl;
						Integer szstatus = szjson.getInteger("status");
						Integer szposition_end = szjson.getInteger("position_now") + json.getInteger("allnum");
						Integer szposition_now = szjson.getInteger("position_now");
						Integer szposition_start = szjson.getInteger("position_start");
						Integer szallok = szjson.getInteger("allok");
						String szwbcontent = szjson.getString("wbcontent");
						String szwbname = szjson.getString("wbname");
						String sztaskid = szjson.getString("taskid");
						BigDecimal szreturnmoney = szjson.getBigDecimal("fee_base");

						ordershuazan.setReturnmoney(szreturnmoney);
						ordershuazan.setOrderusername(szwbname);
						ordershuazan.setStatus(szstatus);
						ordershuazan.setAllok(szallok);
						ordershuazan.setPositionstart(szposition_start);
						ordershuazan.setPositionend(szposition_end);
						ordershuazan.setPositionnow(szposition_now);
						ordershuazan.setWbcontent(szwbcontent);
						ordershuazan.setThridtaskid(sztaskid);
						boolean ordershuazanresult = orderService.insertOrder(ordershuazan, customer);//并发问题
						if (ordershuazanresult) {
							iftrue = true;
							iftruemsg += "同时刷赞订单生产成功!";
						}
					}
				}
				if (iftrue) {
					result.setMsg("提交成功" + iftruemsg);
				} else {
					result.setMsg("提交失败内部400，请联系管理员");
				}
			}

			else if (dingdianResult == null) {
				result.setSucess(false);
				result.setMsg("提交失败远程内部错误4000，请联系管理员");
			} else {
				result.setSucess(false);
				result.setMsg("提交失败" + dingdianResult.getMsg() + "，请联系管理员");
			}
			return result;
	}

	@RequestMapping("/order/addwbshuafen")
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
		if (good.getThirdtypename().contains("初级粉")) {
			if (Integer.parseInt(model.getAllnum().toString()) < 500) {// 数量不能小于500
				model.setAllnum(new BigDecimal(500));
			}
		}
		if (good.getThirdtypename().contains("超级粉")) {
			if (Integer.parseInt(model.getAllnum().toString()) < 100) {// 数量不能小于100
				order.setAllnum(100);
			}
		}
		if (good.getThirdtypename().contains("顶级粉")) {
			if (Integer.parseInt(model.getAllnum().toString()) < 100) {// 数量不能小于100
				model.setAllnum(new BigDecimal(100));
			}
		}
		if (good.getThirdtypename().contains("高级粉")) {
			if (Integer.parseInt(model.getAllnum().toString()) < 200) {// 数量不能小于200
				model.setAllnum(new BigDecimal(200));
			}
		}
		if (good.getThirdtypename().contains("超级粉-大人20%")) {
			if (Integer.parseInt(model.getAllnum().toString()) < 100) {// 数量不能小于100
				model.setAllnum(new BigDecimal(100));
			}
		}
		if (good.getThirdtypename().contains("超级粉-达人50%")) {
			if (Integer.parseInt(model.getAllnum().toString()) < 100) {// 数量不能小于100
				model.setAllnum(new BigDecimal(100));
			}
		}
		if (good.getThirdtypename().contains("等级粉10+")) {
			if (Integer.parseInt(model.getAllnum().toString()) < 100) {// 数量不能小于100
				model.setAllnum(new BigDecimal(100));
			}
		}
		if (good.getThirdtypename().contains("等级粉20+")) {
			if (Integer.parseInt(model.getAllnum().toString()) < 100) {// 数量不能小于100
				model.setAllnum(new BigDecimal(100));
			}
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
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加刷粉订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "sf";
		String proid = "1"; // 1 初级粉， 6 超级粉， 7 顶级粉，19 高级粉， 20 超级粉-大人20%， 21 超级粉-达人50%， 62 等级粉10+， 63 等级粉20+
		if (good.getThirdtypename().contains("初级粉")) {
			proid = "1";
		}
		if (good.getThirdtypename().contains("超级粉")) {
			proid = "6";
		}
		if (good.getThirdtypename().contains("顶级粉")) {
			proid = "7";
		}
		if (good.getThirdtypename().contains("高级粉")) {
			proid = "19";
		}
		if (good.getThirdtypename().contains("超级粉-大人20%")) {
			proid = "20";
		}
		if (good.getThirdtypename().contains("超级粉-达人50%")) {
			proid = "21";
		}
		if (good.getThirdtypename().contains("等级粉10+")) {
			proid = "62";
		}
		if (good.getThirdtypename().contains("等级粉20+")) {
			proid = "63";
		}
		map.put("token", token);
		map.put("addtype", addtype);
		map.put("type", type);
		map.put("proid", proid);
		// 封装json
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wbid", model.getOrderurl());
		jsonObject.put("speed", model.getSpeed());
		jsonObject.put("remark", model.getRemark());
		jsonObject.put("allnum", model.getAllnum());
		jsonObject.put("iftiming", model.getIftiming());
		if (good.getGoodname().contains("包补")) {
			jsonObject.put("isbf", 1);// 1掉粉包补 2掉粉自担
		} else {
			jsonObject.put("isbf", 2);// 1掉粉包补 2掉粉自担
		}
		if (model.getIftiming() == 1) {
			JSONObject iftimingjsonObject = new JSONObject();
			iftimingjsonObject.put("startdate", model.getStartdate());
			iftimingjsonObject.put("perhour", model.getPerhour());
			iftimingjsonObject.put("pernum", model.getPernum());
			jsonObject.put("config", iftimingjsonObject.toJSONString());
		}
		String info = jsonObject.toJSONString();
		map.put("info", info);
		order.setOther(JSONArray.toJSONString(map));
		
			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
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

	@RequestMapping("/order/addwbshouyyd")
	@ResponseBody
	public Result addwbshouyyd(WbshuafenVo model) {
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
		if (Integer.parseInt(model.getAllnum().toString()) < 10000) {// 数量不能小于10000
			model.setAllnum(new BigDecimal(10000));
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
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加刷粉订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "yd";
		String proid = "32";
		map.put("token", token);
		map.put("addtype", addtype);
		map.put("type", type);
		map.put("proid", proid);
		// 封装json
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wburl", model.getOrderurl());
		jsonObject.put("speed", 2);
		jsonObject.put("remark", model.getRemark());
		jsonObject.put("allnum", model.getAllnum());
		jsonObject.put("iftiming", model.getIftiming());
		if (model.getIftiming() == 1) {
			JSONObject iftimingjsonObject = new JSONObject();
			iftimingjsonObject.put("startdate", model.getStartdate());
			iftimingjsonObject.put("perhour", model.getPerhour());
			iftimingjsonObject.put("pernum", model.getPernum());
			jsonObject.put("config", iftimingjsonObject.toJSONString());
		}
		String info = jsonObject.toJSONString();
		map.put("info", info);
		order.setOther(JSONArray.toJSONString(map));

			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
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

	@RequestMapping("/order/addwbshipbf")
	@ResponseBody
	public Result addwbshipbf(WbshuafenVo model) {
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
		if (Integer.parseInt(model.getAllnum().toString()) < 10000) {// 数量不能小于10000
			model.setAllnum(new BigDecimal(10000));
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
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加刷粉订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "sp";
		String proid = "46";
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
		
			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
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

	@RequestMapping("/order/addwbhuatgz")
	@ResponseBody
	public Result addwbhuatgz(WbshuafenVo model) {
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
		if (Integer.parseInt(model.getAllnum().toString()) < 50) {// 数量不能小于50
			model.setAllnum(new BigDecimal(50));
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
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加刷粉订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "ht";
		String proid = "38";
		map.put("token", token);
		map.put("addtype", addtype);
		map.put("type", type);
		map.put("proid", proid);
		// 封装json
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wbid", model.getOrderurl());
		jsonObject.put("speed", model.getSpeed());
		jsonObject.put("remark", model.getRemark());
		jsonObject.put("allnum", model.getAllnum());
		jsonObject.put("iftiming", model.getIftiming());
		if (model.getIftiming() == 1) {
			JSONObject iftimingjsonObject = new JSONObject();
			iftimingjsonObject.put("startdate", model.getStartdate());
			iftimingjsonObject.put("perhour", model.getPerhour());
			iftimingjsonObject.put("pernum", model.getPernum());
			jsonObject.put("config", iftimingjsonObject.toJSONString());
		}
		String info = jsonObject.toJSONString();
		map.put("info", info);
		order.setOther(JSONArray.toJSONString(map));

			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
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
    //投票暂时不做 没有例子
	@RequestMapping("/order/addwbtoup")
	@ResponseBody
	public Result addwbtoup(WbshuafenVo model) {
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
		if (Integer.parseInt(model.getAllnum().toString()) < 50) {// 数量不能小于50
			model.setAllnum(new BigDecimal(50));
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
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加刷粉订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "tp";
		String proid = "24";
		map.put("token", token);
		map.put("addtype", addtype);
		map.put("type", type);
		map.put("proid", proid);
		// 封装json
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("vid", model.getOrderurl());
		jsonObject.put("speed", 2);
		jsonObject.put("item", model.getRemark());//投票选项
		jsonObject.put("allnum", model.getAllnum());
		jsonObject.put("iftiming", model.getIftiming());
		String info = jsonObject.toJSONString();
		map.put("info", info);
		order.setOther(JSONArray.toJSONString(map));

			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
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
//抖音
	@RequestMapping("/order/adddyshuafen")
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
		String proid = "60";//产品id 60为抖音关注 66为抖音低价关注
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

			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
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
	@RequestMapping("/order/adddyshuazan")
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
		String proid = "59";//产品id 59抖音视频点赞 67 为抖音低价视频点赞
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

			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
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
	@RequestMapping("/order/synOrderInforList")
	@ResponseBody
	public Result synOrderInforList(String goodid) {
		Result result = new Result();
	    EntityWrapper<Hopeorder> ew=new EntityWrapper<Hopeorder>();
	    ew.where("goodid = {0}", goodid);
	    ew.andNew().eq("isfast", 1);	
	    ew.andNew().notIn("status", 9);	//已完成
	    ew.andNew().notIn("status", 10);//已退款
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
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,hopeorder.getCustomerid(),hopeorder.getOrdernum(),hopeorder.getGoodname(),orderimplrecordService);
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
	
	@RequestMapping("/order/synOrderInfor")
	@ResponseBody
	public Result synOrderInfor(String id) {
		Result result = new Result();
		Hopeorder hopeorder = orderService.selectById(id);
		if (hopeorder == null) {
			result.setSucess(false);
			result.setMsg("获取订单信息失败400，请联系管理员");
		}
		if(hopeorder.getIsfast() == 0) {
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
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,hopeorder.getCustomerid(),hopeorder.getOrdernum(),hopeorder.getGoodname(),orderimplrecordService);
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
			if (position_start >0) {
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

	@RequestMapping("/order/synStartOrder")
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
		WbdianzanCanshu wbdianzanCanshu = (WbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				WbdianzanCanshu.class);
		String proid = wbdianzanCanshu.getProid();
		String type = wbdianzanCanshu.getType();
		String token = wbdianzanCanshu.getToken();
		String url = "http://wb.dd107.com:81/taskapi/start.do";// 开启订单
		Map<String, String> map = new HashMap<String, String>();
		map.put("proid", proid);
		map.put("type", type);
		map.put("id", taskid);
		map.put("token", token);
		map.put("mode", "0");// 订单多个开关0 是单个 1是多个
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,hopeorder.getCustomerid(),hopeorder.getOrdernum(),hopeorder.getGoodname(),orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			hopeorder.setStatus(OrderStatusEunm.RUN.getValue());
			orderService.updateById(hopeorder);
			result.setSucess(true);
			result.setMsg("开启订单成功");
		} else if (dingdianResult == null) {
			result.setSucess(false);
			result.setMsg("开启订单失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("开启订单失败" + dingdianResult.getMsg());
		}
		return result;

	}

	@RequestMapping("/order/synStopOrder")
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
				result.setMsg("慢速订单停止成功，请等待更新相关信息");//待处理
			}
			else {
				result.setSucess(false);
				result.setMsg("慢速订单停止失败，请联系管理员");//待处理
			}
			return result;
		}
		String taskid = hopeorder.getThridtaskid();
		WbdianzanCanshu wbdianzanCanshu = (WbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				WbdianzanCanshu.class);
		String proid = wbdianzanCanshu.getProid();
		String type = wbdianzanCanshu.getType();
		String token = wbdianzanCanshu.getToken();
		String url = "http://wb.dd107.com:81/taskapi/stop.do";// 停止订单
		Map<String, String> map = new HashMap<String, String>();
		map.put("proid", proid);
		map.put("type", type);
		map.put("id", taskid);
		map.put("token", token);
		map.put("mode", "0");// 订单多个开关0 是单个 1是多个
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,hopeorder.getCustomerid(),hopeorder.getOrdernum(),hopeorder.getGoodname(),orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			hopeorder.setStatus(OrderStatusEunm.STOP.getValue());
			orderService.updateById(hopeorder);
			result.setSucess(true);
			result.setMsg("停止订单成功");
		} else if (dingdianResult == null) {
			result.setSucess(false);
			result.setMsg("停止订单失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("停止订单失败" + dingdianResult.getMsg());
		}
		return result;
	}

	@RequestMapping("/order/synRefundOrder")
	@ResponseBody
	public Result synRefundOrder(String id) {
		Result result = new Result();
		Hopeorder hopeorder = orderService.selectById(id);
		if (hopeorder == null) {
			result.setSucess(false);
			result.setMsg("获取订单信息失败400，请联系管理员");
		}
		hopeorder.setStatus(OrderStatusEunm.RETURN.getValue());
		orderService.updateById(hopeorder);
		result.setSucess(true);
		result.setMsg("退单申请成功，请等待退款");
		
/*		if(hopeorder.getThirdname().equals("慢速")) {		
			result.setSucess(true);
			result.setMsg("慢速订单不同步");//待处理
		}
		String taskid = hopeorder.getThridtaskid();
		WbdianzanCanshu wbdianzanCanshu = (WbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				WbdianzanCanshu.class);
		String proid = wbdianzanCanshu.getProid();
		String type = wbdianzanCanshu.getType();
		String token = wbdianzanCanshu.getToken();
		String url = "http://wb.dd107.com:81/taskapi/refund.do";// 退订单
		Map<String, String> map = new HashMap<String, String>();
		map.put("proid", proid);
		map.put("type", type);
		map.put("id", taskid);
		map.put("token", token);
		map.put("mode", "0");// 订单多个开关0 是单个 1是多个
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,hopeorder.getCustomerid(),hopeorder.getOrdernum(),hopeorder.getGoodname(),orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			
			Balancerecord balancerecord = new Balancerecord();
			balancerecord.setStatus(3);//成功
			balancerecord.setCustomerid(hopeorder.getCustomerid());
			balancerecord.setDetailordernum(hopeorder.getOrdernum());
			balancerecord.setCreatetime(new Date());
			balancerecord.setType(6);//退单返回
			
			hopeorder.setStatus(OrderStatusEunm.STOP.getValue());
		
			result.setSucess(true);
			result.setMsg("退订单成功");
		} else if (dingdianResult == null) {
			result.setSucess(false);
			result.setMsg("退订单失败4000，请联系管理员");
		} else {
			result.setSucess(false);
			result.setMsg("退订单失败" + dingdianResult.getMsg());
		}*/
		return result;
	}

	@RequestMapping("/order/getwbInfor")
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
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,null,null,null,orderimplrecordService);
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
	
	@RequestMapping("/order/getshipinInfor")
	@ResponseBody
	public Result getshipinInfor(String wburl) {
		Result result = new Result();
		String url = "http://wb.dd107.com:81/taskapi/getwbinfo.do";
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String type = "sf";
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", wburl);
		map.put("type", type);
		map.put("token", token);
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,null,null,null,orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			result.setSucess(true);
			result.setMsg(dingdianResult.getMsg());
			JSONObject  jsondata = JSON.parseObject(dingdianResult.getData());
			JSONObject jsondata1 = jsondata.getJSONObject("play");
			result.setData(jsondata1);
		} else {
			result.setSucess(false);
			result.setMsg("查询失败" + dingdianResult.getMsg());
		}
		return result;
	}

	@RequestMapping("/order/getwbuserInfor")
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
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,null,null,null,orderimplrecordService);
		if (dingdianResult != null && dingdianResult.getCode().equals("1000")) {
			result.setSucess(true);
			result.setMsg(dingdianResult.getMsg());
			JSONObject  jsondata = JSON.parseObject(dingdianResult.getData());
			JSONObject jsondata1 = jsondata.getJSONObject(wburl);
			result.setData(jsondata1);

		} else {
			result.setSucess(false);
			result.setMsg("查询失败" + dingdianResult.getMsg());
		}
		return result;
	}

	@RequestMapping("/order/getwbvoteInfor")
	@ResponseBody
	public Result getwbvoteInfor(String wburl) {
		Result result = new Result();
		String url = "http://wb.dd107.com:81/taskapi/getwbvoteinfo.do";
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String type = "sf";
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", wburl);
		map.put("type", type);
		map.put("token", token);
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,null,null,null,orderimplrecordService);
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

	@RequestMapping("/order/getwbhuatInfor")
	@ResponseBody
	public Result getwbhuatInfor(String wburl) {
		Result result = new Result();
		String url = "http://wb.dd107.com:81/taskapi/getwbtopic.do";
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String type = "sf";
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", wburl);
		map.put("type", type);
		map.put("token", token);
		DingdianResult dingdianResult = DingdianService.postDingdian(url, map,null,null,null,orderimplrecordService);
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
	
	
	private Result slowOrder(Hopeorder order,Customer customer) {
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
	
	//顶点接口
	
	//微博点赞
	private  Result dingdianwbdianzan (WbdianzanVo model,Hopeorder order,Good good,Customer customer ) {
		Result result = new Result();
		Map<String, String> map = new HashMap<String, String>();
		String url = "http://wb.dd107.com:81/taskapi.do";// 添加刷赞订单
		String token = "b5a1cc30f542ccab24104a0890c59283";
		String addtype = "add";
		String type = "sz";
		String proid = "24"; // 产品id ，24 空闲赞， 41 高速赞， 42 优速赞
		if (good.getThirdtypename().contains("空闲赞")) {
			proid = "24";
		}
		if (good.getThirdtypename().contains("高速赞")) {
			proid = "41";
		}
		if (good.getThirdtypename().contains("初级赞")) {
			proid = "42";
		}
		map.put("token", token);
		map.put("addtype", addtype);
		map.put("type", type);
		map.put("proid", proid);
		// 封装json
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("wburl", model.getOrderurl());
		jsonObject.put("expression", model.getFacekind());
		jsonObject.put("speed", 2);
		jsonObject.put("ztype", model.getKind());
		jsonObject.put("mid", model.getMid());
		jsonObject.put("remark", model.getRemark());
		jsonObject.put("allnum", model.getAllnum());
		String info = jsonObject.toJSONString();
		map.put("info", info);
		order.setOther(JSONArray.toJSONString(map));
			DingdianResult dingdianResult = DingdianService.postDingdian(url, map,customer.getCustomerid(),order.getOrdernum(),good.getGoodname(),orderimplrecordService);
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
}
