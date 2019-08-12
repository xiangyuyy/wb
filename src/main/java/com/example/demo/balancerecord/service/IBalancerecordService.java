package com.example.demo.balancerecord.service;

import com.example.demo.addbalance.entity.Addbalance;
import com.example.demo.balancerecord.entity.Balancerecord;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.customer.entity.Customer;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 余额变更记录 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-23
 */
public interface IBalancerecordService extends IService<Balancerecord> {
	boolean insertOrder(Balancerecord balancerecord,Addbalance addbalance,Customer customer);
	boolean insertCommCashOrder(Balancerecord balancerecord,Customer customer);
	public PageVo<Balancerecord> GetPageList(Balancerecord balancerecord,PagePara pagePara);
}
