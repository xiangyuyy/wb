package com.example.demo.customer.service.impl;

import com.example.demo.commisionorder.entity.Commisionorder;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.mapper.CustomerMapper;
import com.example.demo.customer.service.ICustomerService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员信息表 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-12
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

	@Override
	public PageVo<Customer> GetPageList(Customer customer, PagePara pagePara) {
	    EntityWrapper<Customer> ew=new EntityWrapper<Customer>();
		PageVo<Customer> pageVo = new PageVo<Customer>();
	    if (!StringUtils.isEmpty(customer.getCustomerid())) {
			ew.where("customerid = {0}", customer.getCustomerid());
		}
	    if (!StringUtils.isEmpty(customer.getByinvitatecod())) {
	    	ew.andNew().eq("byinvitatecod", customer.getByinvitatecod());
		}
	    ew.orderBy("createtime", false);
	    Page<Customer> pageEw = new Page<Customer>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Customer> hopeorderPage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(hopeorderPage);
		return pageVo;
	}

	@Override
	public List<Customer> GetList(Customer customer) {
	    EntityWrapper<Customer> ew=new EntityWrapper<Customer>();
	    if (!StringUtils.isEmpty(customer.getCustomerid())) {
			ew.where("customerid = {0}", customer.getCustomerid());
		}
	    if (!StringUtils.isEmpty(customer.getByinvitatecod())) {
	    	ew.andNew().eq("byinvitatecod", customer.getByinvitatecod());
		}
	    ew.orderBy("createtime", false);		    
	    List<Customer> list= this.selectList(ew);
	    return list;
	}

}
