package com.example.demo.hopeorder.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.commisionorder.service.ICommisionorderService;
import com.example.demo.common.DingdianResult;
import com.example.demo.common.Result;
import com.example.demo.customer.entity.Customer;
import com.example.demo.good.entity.Good;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.example.demo.hopeorder.modelvo.WbdianzanVo;
import com.example.demo.hopeorder.service.IOrderService;
import com.example.demo.orderimplrecord.entity.Orderimplrecord;
import com.example.demo.orderimplrecord.service.IOrderimplrecordService;
import com.example.demo.utils.HttpHelper;

import lombok.experimental.var;

public class DingdianService {
	public static  DingdianResult postDingdian(String url, Map<String, String> map,Integer customerid,String orderid,String productname,IOrderimplrecordService orderimplrecordService) {
		String result = HttpHelper.postMap(url, map);
		DingdianResult dResult = (DingdianResult) JSONObject.parseObject(result, DingdianResult.class);
		if (customerid != null) { //查询不写日志
		Orderimplrecord orderimplrecord = new Orderimplrecord();
		orderimplrecord.setUrl(url);
		orderimplrecord.setType("顶点");
		orderimplrecord.setMap(JSONArray.toJSONString(map));
		orderimplrecord.setOrderid(orderid);
		orderimplrecord.setCustomerid(customerid);
		orderimplrecord.setCreatetime(new Date());
		orderimplrecord.setProductname(productname);
		if (dResult == null) {
			orderimplrecord.setIssucess(0);
			orderimplrecord.setMsg("提交失败远程内部错误4000，请联系管理员,接口返回对象为空");
		}
		if (dResult != null && dResult.getCode().equals("1000")) {
			orderimplrecord.setIssucess(1);
			orderimplrecord.setMsg(dResult.getMsg());
			orderimplrecord.setData(dResult.getData());
			orderimplrecord.setCode(dResult.getCode());
		}
		else {
			orderimplrecord.setIssucess(0);
			orderimplrecord.setMsg(dResult.getMsg());
			//orderimplrecord.setData(dResult.getData());
			orderimplrecord.setCode(dResult.getCode());
		}
		orderimplrecordService.insert(orderimplrecord);
		}
		return dResult;
	}

}
