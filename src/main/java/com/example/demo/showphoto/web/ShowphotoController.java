package com.example.demo.showphoto.web;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.common.DataBaseUtils;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.common.Result;
import com.example.demo.good.entity.Good;
import com.example.demo.good.service.IGoodService;
import com.example.demo.showphoto.entity.Showphoto;
import com.example.demo.showphoto.service.IShowphotoService;

/**
 * <p>
 * 展示页面图片 前端控制器
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-30
 */
@Controller
public class ShowphotoController {
	@Autowired
	private IShowphotoService showphotoService;
	@Autowired
	private DataBaseUtils utils;
	@RequestMapping("/showphoto/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/showphoto/list");
		return mav;
	}
	@RequestMapping("/showphoto/listform")
	public ModelAndView listform(String id) {
		ModelAndView mav = new ModelAndView();
		Showphoto showphoto = new Showphoto();		
		Showphoto result = showphotoService.selectById(id);
		String[] typeList = utils.getShowPhotoType();
		if (result!=null) {
			showphoto = result;
		}
		else {
			showphoto.setStatus(1);//默认为上架
			showphoto.setType(typeList[0]);
			showphoto.setUrl("");
		}
		mav.addObject("typeList",typeList);
		mav.addObject("showphoto",showphoto);
		mav.setViewName("webui/showphoto/listform");
		return mav; 
	}
	
	@RequestMapping("/showphoto/getlist")
	@ResponseBody
	public PageVo<Showphoto> getlist(Showphoto showphoto,PagePara pagePara) {
		return showphotoService.GetPageList(showphoto, pagePara);
	}
	
	@RequestMapping("/showphoto/update")
	@ResponseBody
	public Result update(Showphoto showphoto) {
		Result result = new Result();
		boolean b;
		if (showphoto.getId() ==null || showphoto.getId() == 0) {
			showphoto.setCreatetime(new Date());
			showphoto.setUpdatetime(new Date());
			b = showphotoService.insert(showphoto);
		}
		else {
			showphoto.setUpdatetime(new Date());
			b = showphotoService.updateById(showphoto);
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
