package com.example.demo.customer.service;

import com.example.demo.commisionorder.entity.Commisionorder;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.customer.entity.Customer;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 会员信息表 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-12
 */
public interface ICustomerService extends IService<Customer> {
	PageVo<Customer> GetPageList(Customer customer, PagePara pagePara);
	List<Customer> GetList(Customer customer);
}
