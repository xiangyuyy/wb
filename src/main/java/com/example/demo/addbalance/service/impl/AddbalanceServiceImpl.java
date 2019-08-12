package com.example.demo.addbalance.service.impl;

import com.example.demo.addbalance.entity.Addbalance;
import com.example.demo.addbalance.mapper.AddbalanceMapper;
import com.example.demo.addbalance.service.IAddbalanceService;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 充值订单 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-04-07
 */
@Service
public class AddbalanceServiceImpl extends ServiceImpl<AddbalanceMapper, Addbalance> implements IAddbalanceService {

	@Override
	public PageVo<Addbalance> GetPageList(Addbalance addbalance, PagePara pagePara) {
	    EntityWrapper<Addbalance> ew=new EntityWrapper<Addbalance>();
		PageVo<Addbalance> pageVo = new PageVo<Addbalance>();
	    if (!StringUtils.isEmpty(addbalance.getCustomerid())) {
			ew.where("customerid = {0}", addbalance.getCustomerid());
		}
	    if (!StringUtils.isEmpty(addbalance.getBalanceorderid())) {
	    	ew.andNew().like("balanceorderid", addbalance.getBalanceorderid());
		}
	    if (!StringUtils.isEmpty(addbalance.getThirdnumber())) {
	    	ew.andNew().like("thirdnumber",addbalance.getThirdnumber());
		}
	    ew.orderBy("createtime", false);
	    Page<Addbalance> pageEw = new Page<Addbalance>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Addbalance> hopeorderPage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(hopeorderPage);
		return pageVo;	
	}
}
