package com.example.demo.hopeorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.balancerecord.entity.Balancerecord;
import com.example.demo.balancerecord.service.IBalancerecordService;
import com.example.demo.commisionorder.entity.Commisionorder;
import com.example.demo.commisionorder.service.ICommisionorderService;
import com.example.demo.common.DingdianResult;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.common.XianDaiResult;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.modelvo.CustomerLevelEnum;
import com.example.demo.customer.service.ICustomerService;
import com.example.demo.good.entity.Good;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.example.demo.hopeorder.mapper.OrderMapper;
import com.example.demo.hopeorder.modelvo.WbdianzanCanshu;
import com.example.demo.hopeorder.modelvo.XDWbdianzanCanshu;
import com.example.demo.hopeorder.service.IOrderService;
import com.example.demo.orderimplrecord.service.IOrderimplrecordService;
import com.example.demo.systempara.entity.Systempara;
import com.example.demo.systempara.service.ISystemparaService;
import com.example.demo.utils.DateUtil;

import lombok.experimental.var;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Hopeorder> implements IOrderService {
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ISystemparaService systemparaService;
	@Autowired
	private ICommisionorderService commisionorderService;
	@Autowired
	private IBalancerecordService balancerecordService;
	@Autowired
	private IOrderimplrecordService orderimplrecordService;

	@Override
	public boolean insertOrder(Hopeorder order, Customer customer) {
		try {

			EntityWrapper<Systempara> ew = new EntityWrapper<Systempara>();
			Systempara systempara = systemparaService.selectOne(ew);

			customer.setMonetary(customer.getMonetary().add(order.getMoney()));// 更新消费总额

			if (customer.getMonetary().compareTo(systempara.getLevelone()) >= 0) {// 更新等级
				customer.setLevel(CustomerLevelEnum.GOLD.getValue());
			}
			if (customer.getMonetary().compareTo(systempara.getLeveltwo()) >= 0) {
				customer.setLevel(CustomerLevelEnum.PLATINUM.getValue());
			}
			if (customer.getMonetary().compareTo(systempara.getLevelthree()) >= 0) {
				customer.setLevel(CustomerLevelEnum.DIAMONDS.getValue());
			}
			if (customer.getMonetary().compareTo(systempara.getLevelfour()) >= 0) {
				customer.setLevel(CustomerLevelEnum.SUPER.getValue());
			}

			// 余额记录表
			Balancerecord balancerecord = new Balancerecord();
			balancerecord.setBeforbalance(customer.getBalance());
			balancerecord.setAfterbalance(customer.getBalance().subtract(order.getMoney()));
			balancerecord.setBalance(order.getMoney().multiply(new BigDecimal(-1)));
			balancerecord.setCreatetime(new Date());
			balancerecord.setCustomerid(customer.getCustomerid());
			balancerecord.setDetailordernum(order.getOrdernum());
			balancerecord.setType(2); // 0充值 1佣金提现 2下单消费 升级冻结3 冻结解除4
			// balancerecordService.insert(balancerecord);

			// 更新会员信息

			customer.setBalance(customer.getBalance().subtract(order.getMoney()));// 更新余额
			// customerService.updateById(customer);

			// 添加订单信息
			// this.insert(order);

			if (Transactionaorder(customer, order, balancerecord)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean Transactionaorder(Customer customer, Hopeorder order, Balancerecord balancerecord) {
		try {
			customerService.updateById(customer);
			this.insert(order);
			balancerecordService.insert(balancerecord);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean dealCommisionorder(Hopeorder order, Customer customer) {
		try {
			EntityWrapper<Systempara> ew = new EntityWrapper<Systempara>();
			Systempara systempara = systemparaService.selectOne(ew);
			// 返佣计算
			EntityWrapper<Customer> ewc = new EntityWrapper<Customer>();
			ewc.where("customerid = {0}", customer.getByinvitatecod());
			var oneGoodForCustomer = customerService.selectOne(ewc);
			Customer twoGoodForCustomer = null;
			Commisionorder commisionorderone = null;
			Commisionorder commisionordertwo = null;
			if (oneGoodForCustomer != null) { // 存在一级受邀人
				commisionorderone = new Commisionorder();
				BigDecimal money = order.getMoney().multiply(systempara.getCommisionone());
				commisionorderone.setProportion(systempara.getCommisionone());// 返佣比例
				commisionorderone.setBycustomermoney(order.getMoney());// 订单金额
				commisionorderone.setHopemoney(money);// 返佣金额
				commisionorderone.setBeforcommision(oneGoodForCustomer.getNocashcommision());// 待提现的佣金
				commisionorderone.setAftercommision(oneGoodForCustomer.getNocashcommision().add(money));// 待提现的佣金+拥金
				commisionorderone.setBycustomerid(customer.getCustomerid().toString());// 下单人id
				commisionorderone.setBygoodclassname(order.getGoodclassname());
				commisionorderone.setBygoodtypename(order.getGoodtypename());
				commisionorderone.setBygoodid(order.getGoodid());
				commisionorderone.setByordernum(order.getOrdernum());
				commisionorderone.setCreatetime(new Date());
				commisionorderone.setForgoodcustomerid(oneGoodForCustomer.getCustomerid().toString());
				commisionorderone.setLevel(1);
				commisionorderone.setProportion(systempara.getCommisionone());
				// 更新一级收益人总佣金
				oneGoodForCustomer.setCommision(oneGoodForCustomer.getCommision().add(money));// 更新总佣金

				EntityWrapper<Customer> ewct = new EntityWrapper<Customer>();
				ewct.where("customerid = {0}", oneGoodForCustomer.getByinvitatecod());
				twoGoodForCustomer = customerService.selectOne(ewct);
				if (twoGoodForCustomer != null) {// 存在二级受邀人
					commisionordertwo = new Commisionorder();
					BigDecimal moneytwo = order.getMoney().multiply(systempara.getCommisiontwo());
					commisionordertwo.setProportion(systempara.getCommisiontwo());// 返佣比例
					commisionordertwo.setBycustomermoney(order.getMoney());// 订单金额
					commisionordertwo.setHopemoney(money);// 返佣金额
					commisionordertwo.setBeforcommision(twoGoodForCustomer.getNocashcommision());// 待提现的佣金
					commisionordertwo.setAftercommision(twoGoodForCustomer.getNocashcommision().add(moneytwo));// 待提现的佣金+拥金
					commisionordertwo.setBycustomerid(customer.getCustomerid().toString());// 下单人id
					commisionordertwo.setBygoodclassname(order.getGoodclassname());
					commisionordertwo.setBygoodtypename(order.getGoodtypename());
					commisionordertwo.setBygoodid(order.getGoodid());
					commisionordertwo.setByordernum(order.getOrdernum());
					commisionordertwo.setCreatetime(new Date());
					commisionordertwo.setOnecustomerid(oneGoodForCustomer.getCustomerid().toString());// 中间级人
					commisionordertwo.setForgoodcustomerid(twoGoodForCustomer.getCustomerid().toString());
					commisionordertwo.setLevel(2);
					commisionordertwo.setProportion(systempara.getCommisiontwo());
					// 更新二级级收益人总佣金
					twoGoodForCustomer.setCommision(twoGoodForCustomer.getCommision().add(moneytwo));// 更新总佣金
				}
			}

			if (TransactionalForCommisionorder(oneGoodForCustomer, twoGoodForCustomer, order, commisionorderone,
					commisionordertwo)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean TransactionalForCommisionorder(Customer oneGoodForCustomer, Customer twoGoodForCustomer,
			Hopeorder order, Commisionorder commisionorderone, Commisionorder commisionordertwo) {
		try {
			if (oneGoodForCustomer != null) {
				customerService.updateById(oneGoodForCustomer);
			}
			if (twoGoodForCustomer != null) {
				customerService.updateById(twoGoodForCustomer);
			}
			if (commisionorderone != null) {
				commisionorderService.insert(commisionorderone);
			}
			if (commisionordertwo != null) {
				commisionorderService.insert(commisionordertwo);
			}
			this.updateById(order);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean RefundOrder(Hopeorder hopeorder, Balancerecord balancerecord) {
		try {
			this.updateById(hopeorder);
			balancerecordService.insert(balancerecord);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public PageVo<Hopeorder> GetWbdianzanPageList(Hopeorder order, PagePara pagePara) {
		EntityWrapper<Hopeorder> ew = new EntityWrapper<Hopeorder>();
		PageVo<Hopeorder> pageVo = new PageVo<Hopeorder>();
		if (!StringUtils.isEmpty(order.getGoodid())) {
			ew.where("goodid = {0}", order.getGoodid());
		}
		if (!StringUtils.isEmpty(order.getOrderusername())) {
			ew.andNew().like("orderusername", order.getOrderusername());
		}
		if (!StringUtils.isEmpty(order.getOrderurl())) {
			ew.andNew().like("orderurl", order.getOrderurl());
		}
		if (!StringUtils.isEmpty(order.getOrderkind())) {
			ew.andNew().eq("orderkind", order.getOrderkind());
		}
		if (!StringUtils.isEmpty(order.getIsfast())) {
			ew.andNew().eq("isfast", order.getIsfast());
		}
		if (!StringUtils.isEmpty(order.getOrdernum())) {
			ew.andNew().like("ordernum", order.getOrdernum());
		}
		if (!StringUtils.isEmpty(order.getCustomerid())) {
			ew.andNew().eq("customerid", order.getCustomerid());
		}
		if (!StringUtils.isEmpty(order.getStatus())) {
			ew.andNew().eq("status", order.getStatus());
		}

		ew.orderBy("createtime", false);
		Page<Hopeorder> pageEw = new Page<Hopeorder>(pagePara.getPage(), pagePara.getLimit());
		Page<Hopeorder> hopeorderPage = this.selectPage(pageEw, ew);
		for (Hopeorder hopeorder : hopeorderPage.getRecords()) {
			if (hopeorder.getStatus() != null) {
				if (hopeorder.getThirdname().equals("顶点")) {
					if ((hopeorder.getStatus() != 9 && hopeorder.getStatus() != 10)) {
						SycOrderDingDian(hopeorder);
					}
				}
				if (hopeorder.getThirdname().equals("现代")) {
					if ((hopeorder.getStatus() != 9 && hopeorder.getStatus() != 11 && hopeorder.getStatus() != 10)) {
						SycOrderXianDai(hopeorder);
					}
				}
			}
		}
		pageVo.changeToPageVo(hopeorderPage);
		return pageVo;
	}

	private void SycOrderDingDian(Hopeorder hopeorder) {
		String taskid = hopeorder.getThridtaskid();
		WbdianzanCanshu wbdianzanCanshu = (WbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				WbdianzanCanshu.class);
		if (wbdianzanCanshu == null) {
			return;
		}
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
			Integer position_start = json.getInteger("position_start");
			if (position_start > 0) {
				hopeorder.setPositionstart(position_start);
			}
			String time_start = json.getString("time_start");
			String time_end = json.getString("time_end");
			// BigDecimal fee_all = json.getBigDecimal("fee_all");
			hopeorder.setStatus(status);
			hopeorder.setAllok(allok);
			hopeorder.setPositionnow(hopeorder.getPositionstart() + allok);
			if (!StringUtils.isEmpty(time_start)) {
				hopeorder.setStartime(DateUtil.stampTenToDate(time_start));
			}
			if (!StringUtils.isEmpty(time_end)) {
				hopeorder.setEndtime(DateUtil.stampTenToDate(time_end));
			}	
			this.updateById(hopeorder);
		}
	}
	
	private void SycOrderXianDai(Hopeorder hopeorder) {
		String ids = hopeorder.getThridtaskid();
		XDWbdianzanCanshu wbdianzanCanshu = (XDWbdianzanCanshu) JSONObject.parseObject(hopeorder.getOther(),
				XDWbdianzanCanshu.class);
		if (wbdianzanCanshu == null) {
			return;
		}
		String type = wbdianzanCanshu.getType();
		String userId = wbdianzanCanshu.getUserId();
		String url = "http://wb.xiandai6.com/api/thirdorder/findOrderByIds";// 查询订单
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("ids", ids);
		map.put("userId", userId);
		XianDaiResult dingdianResult = XianDaiService.postDingdian(url, map, hopeorder.getCustomerid(),
				hopeorder.getOrdernum(), hopeorder.getGoodname(), orderimplrecordService);
		if (dingdianResult != null && dingdianResult.isSuccess()) {
			String returnmsg = dingdianResult.getData();
			returnmsg = returnmsg.substring(1, returnmsg.length()-1);
			//JSONObject jsonFrist = JSON.parseObject(returnmsg);
			//JSONObject json = JSON.parseObject(jsonFrist.getString(taskid));
			JSONObject json = JSON.parseObject(returnmsg);
			Integer status = changeXianDaiStatusToInterger(json.getString("amountState"));
			Integer allok = json.getInteger("schedule");
			Integer position_start = json.getInteger("followersCount");
			if (position_start == null) {
				position_start = hopeorder.getPositionstart();
				if (position_start == null) {
					position_start = 0;
				}
			}
			String urls = json.getString("url");
			Integer number = json.getInteger("number");
			BigDecimal amount = json.getBigDecimal("amount");

			
			String createTime = json.getString("createTime");
			String stopTime = json.getString("stopTime");
			String finishTime = json.getString("finishTime");
			// BigDecimal fee_all = json.getBigDecimal("fee_all");
			hopeorder.setPositionstart(position_start);
			hopeorder.setOrderurl(urls);
			hopeorder.setPositionend(hopeorder.getPositionstart() + number);
			hopeorder.setReturnmoney(amount);
			hopeorder.setStatus(status);
			hopeorder.setAllok(allok);
			hopeorder.setPositionnow(hopeorder.getPositionstart() + allok);
			if (!StringUtils.isEmpty(createTime)) {
				hopeorder.setStartime(DateUtil.stampTenAddThreeToDate(createTime));
			}
			if (!StringUtils.isEmpty(finishTime)) {
				hopeorder.setEndtime(DateUtil.stampTenAddThreeToDate(finishTime));
			}
			this.updateById(hopeorder);
		}
	}
	@Override
	public boolean dealReturnMoney(Hopeorder order, Balancerecord balancerecord, Customer customer) {
		return transactionalDealReturnMoney(order,balancerecord,customer);
	}
	@Transactional
	public boolean transactionalDealReturnMoney(Hopeorder order, Balancerecord balancerecord, Customer customer) {
		try {
			if (customer != null) {
				customerService.updateById(customer);
			}
			if (balancerecord != null) {
				balancerecordService.insert(balancerecord);
			}
			this.updateById(order);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Integer changeXianDaiStatusToInterger(String status) {
		Integer integer = 2;
		switch (status) {
		case "执行中":
			integer = 2;
			break;
		case "已暂停":
			integer = 5;
			break;
		case "完成":
			integer = 9;
			break;
		case "已完成":
			integer = 9;
			break;
		case "已取消":
			integer = 11;
			break;
		case "异常":
			integer = 3;
			break;
		default:
			break;
		}
		return integer;
	}
}
