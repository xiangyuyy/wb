package com.example.demo.goodclass.service.impl;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.goodclass.entity.Goodclass;
import com.example.demo.goodclass.mapper.GoodclassMapper;
import com.example.demo.goodclass.service.IGoodclassService;
import com.example.demo.goodtype.entity.Goodtype;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 商品分类 (刷粉 点赞 博文转评。。) 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Service
public class GoodclassServiceImpl extends ServiceImpl<GoodclassMapper, Goodclass> implements IGoodclassService {

	@Override
	public PageVo<Goodclass> GetPageList(Goodclass goodclass, PagePara pagePara) {
	    EntityWrapper<Goodclass> ew=new EntityWrapper<Goodclass>();
		PageVo<Goodclass> pageVo = new PageVo<Goodclass>();
	    if (!StringUtils.isEmpty(goodclass.getId())) {
	    	ew.like("id", goodclass.getId().toString());
		}
	    if (!StringUtils.isEmpty(goodclass.getName())) {
	    	ew.andNew().like("name", goodclass.getName());
		}
	    ew.orderBy("updatetime", false);
	    Page<Goodclass> pageEw = new Page<Goodclass>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Goodclass> goodClassPage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(goodClassPage);
		return pageVo;	
	}

}
