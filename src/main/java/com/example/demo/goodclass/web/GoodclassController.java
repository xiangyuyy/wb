package com.example.demo.goodclass.web;


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
import com.example.demo.goodtype.entity.Goodtype;
import com.example.demo.goodtype.service.IGoodtypeService;

/**
 * <p>
 * 商品分类 (刷粉 点赞 博文转评。。) 前端控制器
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Controller
public class GoodclassController {
	@Autowired
	private IGoodclassService goodClassService;

	@RequestMapping("/goodclass/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/goodclass/list");
		return mav;
	}
	@RequestMapping("/goodclass/listform")
	public ModelAndView listform(String id) {
		ModelAndView mav = new ModelAndView();
		Goodclass goodclass = new Goodclass();
		goodclass.setStatus(1);//默认为上架
		Goodclass result = goodClassService.selectById(id);
		if (result!=null) {
			goodclass = result;
		}
		mav.addObject("goodclass",goodclass);
		mav.setViewName("webui/goodclass/listform");
		return mav; 
	}
	
	@RequestMapping("/goodclass/getlist")
	@ResponseBody
	public PageVo<Goodclass> getlist(Goodclass goodclass,PagePara pagePara) {
		return goodClassService.GetPageList(goodclass, pagePara);
	}
	
	@RequestMapping("/goodclass/update")
	@ResponseBody
	public Result update(Goodclass goodclass) {
		Result result = new Result();
		boolean b;
		if (goodclass.getId() ==null || goodclass.getId() == 0) {
			goodclass.setCreatetime(new Date());
			goodclass.setUpdatetime(new Date());
			b = goodClassService.insert(goodclass);
		}
		else {
			goodclass.setUpdatetime(new Date());
			b = goodClassService.updateById(goodclass);
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
