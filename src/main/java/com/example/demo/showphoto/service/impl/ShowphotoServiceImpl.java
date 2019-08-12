package com.example.demo.showphoto.service.impl;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.good.entity.Good;
import com.example.demo.showphoto.entity.Showphoto;
import com.example.demo.showphoto.mapper.ShowphotoMapper;
import com.example.demo.showphoto.service.IShowphotoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 展示页面图片 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-30
 */
@Service
public class ShowphotoServiceImpl extends ServiceImpl<ShowphotoMapper, Showphoto> implements IShowphotoService {

	@Override
	public PageVo<Showphoto> GetPageList(Showphoto showphoto, PagePara pagePara) {
	    EntityWrapper<Showphoto> ew=new EntityWrapper<Showphoto>();
		PageVo<Showphoto> pageVo = new PageVo<Showphoto>();
	    if (!StringUtils.isEmpty(showphoto.getId())) {
	    	ew.like("id", showphoto.getId().toString());
		}
	    if (!StringUtils.isEmpty(showphoto.getType())) {
	    	ew.andNew().like("type", showphoto.getType());
		}
	    ew.orderBy("updatetime", false);
	    Page<Showphoto> pageEw = new Page<Showphoto>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Showphoto> showphotoPage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(showphotoPage);
		return pageVo;	
	}

}
