package com.example.demo.good.service.impl;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.good.entity.Good;
import com.example.demo.good.mapper.GoodMapper;
import com.example.demo.good.service.IGoodService;
import com.example.demo.goodclass.entity.Goodclass;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements IGoodService {

	@Override
	public PageVo<Good> GetPageList(Good good, PagePara pagePara) {
	    EntityWrapper<Good> ew=new EntityWrapper<Good>();
		PageVo<Good> pageVo = new PageVo<Good>();
	    if (!StringUtils.isEmpty(good.getId())) {
	    	ew.like("id", good.getId().toString());
		}
	    if (!StringUtils.isEmpty(good.getGoodname())) {
	    	ew.andNew().like("goodname", good.getGoodname());
		}
	    if (!StringUtils.isEmpty(good.getGoodclassname())) {
	    	ew.andNew().like("goodclassname", good.getGoodclassname());
		}
	    if (!StringUtils.isEmpty(good.getGoodtypename())) {
	    	ew.andNew().like("goodtypename", good.getGoodtypename());
		}
	    ew.orderBy("updatetime", false);
	    Page<Good> pageEw = new Page<Good>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Good> goodPage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(goodPage);
		return pageVo;	
	}
}
