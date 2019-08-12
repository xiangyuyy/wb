package com.example.demo.notice.service.impl;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.goodclass.entity.Goodclass;
import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.mapper.NoticeMapper;
import com.example.demo.notice.service.INoticeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 最新公告 服务实现类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-30
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

	@Override
	public PageVo<Notice> GetPageList(Notice notice, PagePara pagePara) {
	    EntityWrapper<Notice> ew=new EntityWrapper<Notice>();
		PageVo<Notice> pageVo = new PageVo<Notice>();
	    if (!StringUtils.isEmpty(notice.getId())) {
	    	ew.like("id", notice.getId().toString());
		}
	    if (!StringUtils.isEmpty(notice.getName())) {
	    	ew.andNew().like("name", notice.getName());
		}
	    ew.orderBy("updatetime", false);
	    Page<Notice> pageEw = new Page<Notice>(pagePara.getPage(), pagePara.getLimit());		    
	    Page<Notice> noticePage= this.selectPage(pageEw,ew);
	    pageVo.changeToPageVo(noticePage);
		return pageVo;	
	}

}
