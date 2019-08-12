package com.example.demo.commisionorder.service.impl;

import com.example.demo.commisionorder.entity.Commisionorder;
import com.example.demo.commisionorder.mapper.CommisionorderMapper;
import com.example.demo.commisionorder.service.ICommisionorderService;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 佣金变更订单 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-23
 */
@Service
public class CommisionorderServiceImpl extends ServiceImpl<CommisionorderMapper, Commisionorder> implements ICommisionorderService {
	@Override
	public PageVo<Commisionorder> GetPageList(Commisionorder commisionorder, PagePara pagePara) {
	    EntityWrapper<Commisionorder> ew=new EntityWrapper<Commisionorder>();
		PageVo<Commisionorder> pageVo = new PageVo<Commisionorder>();
	    if (!StringUtils.isEmpty(commisionorder.getBycustomerid())) {
	    	ew.in("bycustomerid", commisionorder.getBycustomerid().toString());
		}
	    if (!StringUtils.isEmpty(commisionorder.getForgoodcustomerid())) {
	    	ew.andNew().in("forgoodcustomerid", commisionorder.getForgoodcustomerid().toString());
		}
	    if (!StringUtils.isEmpty(commisionorder.getByordernum())) {
	    	ew.andNew().like("byordernum", commisionorder.getByordernum());
		}
	    if (!StringUtils.isEmpty(commisionorder.getOnecustomerid())) {
	    	ew.andNew().like("onecustomerid", commisionorder.getOnecustomerid());
		}
	    if (!StringUtils.isEmpty(commisionorder.getLevel())) {
	    	ew.andNew().eq("level", commisionorder.getLevel());
		}
	    ew.orderBy("createtime", false);
	    Page<Commisionorder> pageEw = new Page<Commisionorder>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Commisionorder> hopeorderPage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(hopeorderPage);
		return pageVo;	
	}

	@Override
	public List<Commisionorder> GetList(Commisionorder commisionorder) {
	    EntityWrapper<Commisionorder> ew=new EntityWrapper<Commisionorder>();
	    if (!StringUtils.isEmpty(commisionorder.getBycustomerid())) {
			ew.where("bycustomerid in {0}", commisionorder.getBycustomerid());
		}
	    if (!StringUtils.isEmpty(commisionorder.getForgoodcustomerid())) {
	    	ew.andNew().in("forgoodcustomerid", commisionorder.getForgoodcustomerid().toString());
		}
	    if (!StringUtils.isEmpty(commisionorder.getByordernum())) {
	    	ew.andNew().like("byordernum", commisionorder.getByordernum());
		}
	    List<Commisionorder> list = this.selectList(ew);
	    return list;
	}	
}
