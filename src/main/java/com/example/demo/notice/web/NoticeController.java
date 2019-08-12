package com.example.demo.notice.web;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.common.Result;
import com.example.demo.goodclass.entity.Goodclass;
import com.example.demo.goodclass.service.IGoodclassService;
import com.example.demo.notice.entity.Notice;
import com.example.demo.notice.service.INoticeService;

/**
 * <p>
 * 最新公告 前端控制器
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-30
 */
@Controller
public class NoticeController {
	@Autowired
	private INoticeService noticeService;

	@RequestMapping("/notice/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/notice/list");
		return mav;
	}
	@RequestMapping("/notice/listform")
	public ModelAndView listform(String id) {
		ModelAndView mav = new ModelAndView();
		Notice notice = new Notice();
		notice.setStatus(1);//默认为上架
		Notice result = noticeService.selectById(id);
		if (result!=null) {
			notice = result;
		}
		mav.addObject("notice",notice);
		mav.setViewName("webui/notice/listform");
		return mav; 
	}
	
	@RequestMapping("/notice/getlist")
	@ResponseBody
	public PageVo<Notice> getlist(Notice notice,PagePara pagePara) {
		return noticeService.GetPageList(notice, pagePara);
	}
	
	@RequestMapping("/notice/update")
	@ResponseBody
	public Result update(Notice notice) {
		Result result = new Result();
		boolean b;
		if (notice.getId() ==null || notice.getId() == 0) {
			notice.setCreatetime(new Date());
			notice.setUpdatetime(new Date());
			b = noticeService.insert(notice);
		}
		else {
			notice.setUpdatetime(new Date());
			b = noticeService.updateById(notice);
		}
		if (b) {
			result.setMsg("提交成功");
		}
		else {
			result.setSucess(false);
			result.setMsg("提交失败");
		}
		return result; 
	}
}
