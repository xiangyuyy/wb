package com.example.demo.goodtype.web;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.common.Result;
import com.example.demo.goodtype.entity.Goodtype;
import com.example.demo.goodtype.service.IGoodtypeService;
import com.example.demo.systempara.entity.Systempara;
import com.example.demo.teacher.entity.Teacher;
import com.example.demo.teacher.service.ITeacherService;
import com.example.demo.utils.MD5Encrypt;

/**
 * <p>
 * 商品类型 微博 抖音 前端控制器
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Controller
public class GoodtypeController {
	
	@Autowired
	private IGoodtypeService goodtyperService;

	@RequestMapping("/goodtype/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/goodtype/list");
		return mav;
	}
	@RequestMapping("/goodtype/listform")
	public ModelAndView listform(String id) {
		ModelAndView mav = new ModelAndView();
		Goodtype goodtype = new Goodtype();
		goodtype.setStatus(1);//默认为上架
		Goodtype result = goodtyperService.selectById(id);
		if (result!=null) {
			goodtype = result;
		}
		mav.addObject("goodtype",goodtype);
		mav.setViewName("webui/goodtype/listform");
		return mav;
	}
	
	@RequestMapping("/goodtype/getlist")
	@ResponseBody
	public PageVo<Goodtype> getlist(Goodtype goodtype,PagePara pagePara) {
		return goodtyperService.GetPageList(goodtype, pagePara);
	}
	
	@RequestMapping("/goodtype/update")
	@ResponseBody
	public Result update(Goodtype goodtype) {
		Result result = new Result();
		boolean b;
		if (goodtype.getId() ==null || goodtype.getId() == 0) {
			goodtype.setCreatetime(new Date());
			goodtype.setUpdatetime(new Date());
			b = goodtyperService.insert(goodtype);
		}
		else {
			goodtype.setUpdatetime(new Date());
			b = goodtyperService.updateById(goodtype);
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
