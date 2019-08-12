package com.example.demo.goodtype.service.impl;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.goodtype.entity.Goodtype;
import com.example.demo.goodtype.mapper.GoodtypeMapper;
import com.example.demo.goodtype.service.IGoodtypeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 商品类型 微博 抖音 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Service
public class GoodtypeServiceImpl extends ServiceImpl<GoodtypeMapper, Goodtype> implements IGoodtypeService {

	@Override
	public PageVo<Goodtype> GetPageList(Goodtype goodtype, PagePara pagePara) {
	    EntityWrapper<Goodtype> ew=new EntityWrapper<Goodtype>();
		PageVo<Goodtype> pageVo = new PageVo<Goodtype>();
	    if (!StringUtils.isEmpty(goodtype.getId())) {
	    	ew.like("id", goodtype.getId().toString());
		}
	    if (!StringUtils.isEmpty(goodtype.getName())) {
	    	ew.andNew().like("name", goodtype.getName());
		}
	    ew.orderBy("updatetime", false);
	    Page<Goodtype> pageEw = new Page<Goodtype>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Goodtype> goodTypePage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(goodTypePage);
		return pageVo;		
	}

}
