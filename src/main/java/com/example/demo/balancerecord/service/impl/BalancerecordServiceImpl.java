package com.example.demo.balancerecord.service.impl;

import com.example.demo.addbalance.entity.Addbalance;
import com.example.demo.addbalance.service.IAddbalanceService;
import com.example.demo.balancerecord.entity.Balancerecord;
import com.example.demo.balancerecord.mapper.BalancerecordMapper;
import com.example.demo.balancerecord.service.IBalancerecordService;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.service.ICustomerService;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 余额变更记录 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-23
 */
@Service
public class BalancerecordServiceImpl extends ServiceImpl<BalancerecordMapper, Balancerecord> implements IBalancerecordService {
	@Autowired
	private IAddbalanceService addbalanceService;
	
	@Autowired
	private ICustomerService customerService;
	@Transactional
	@Override
	public boolean insertOrder(Balancerecord balancerecord, Addbalance addbalance,Customer customer) {
		try {
			this.insert(balancerecord);
			addbalanceService.insert(addbalance);
			customerService.updateById(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean insertCommCashOrder(Balancerecord balancerecord,Customer customer) {
		try {
			this.insert(balancerecord);
			customerService.updateById(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public PageVo<Balancerecord> GetPageList(Balancerecord balancerecord, PagePara pagePara) {
	    EntityWrapper<Balancerecord> ew=new EntityWrapper<Balancerecord>();
		PageVo<Balancerecord> pageVo = new PageVo<Balancerecord>();
	    if (!StringUtils.isEmpty(balancerecord.getType())) {
	    	ew.andNew().eq("type", balancerecord.getType());
		}
	    if (!StringUtils.isEmpty(balancerecord.getDetailordernum())) {
	    	ew.andNew().like("detailordernum", balancerecord.getDetailordernum());
		}
	    if (!StringUtils.isEmpty(balancerecord.getCreatetime())) {
	    	ew.andNew().eq("createtime", balancerecord.getCreatetime());
		}	  
	    if (!StringUtils.isEmpty(balancerecord.getCustomerid())) {
	    	ew.andNew().eq("customerid", balancerecord.getCustomerid());
		}
	    ew.orderBy("createtime", false);
	    Page<Balancerecord> pageEw = new Page<Balancerecord>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Balancerecord> hopeorderPage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(hopeorderPage);
		return pageVo;	
	}

}
