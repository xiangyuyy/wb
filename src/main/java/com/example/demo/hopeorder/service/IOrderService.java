package com.example.demo.hopeorder.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.balancerecord.entity.Balancerecord;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.customer.entity.Customer;
import com.example.demo.good.entity.Good;
import com.example.demo.hopeorder.entity.Hopeorder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-17
 */
public interface IOrderService extends IService<Hopeorder> {
	boolean insertOrder(Hopeorder order,Customer customer);
	boolean dealCommisionorder(Hopeorder order, Customer customer);
	boolean dealReturnMoney(Hopeorder order,Balancerecord balancerecord, Customer customer);
	public PageVo<Hopeorder> GetWbdianzanPageList(Hopeorder order,PagePara pagePara);
}
