package com.example.demo.cgcenter.web;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.common.Result;
import com.example.demo.cshowvo.GoodCenerVo;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.service.ICustomerService;
import com.example.demo.good.entity.Good;
import com.example.demo.good.service.IGoodService;
import com.example.demo.goodclass.entity.Goodclass;
import com.example.demo.goodclass.service.IGoodclassService;
import com.example.demo.goodtype.entity.Goodtype;
import com.example.demo.goodtype.service.IGoodtypeService;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.example.demo.hopeorder.service.IOrderService;
import com.example.demo.hopeorder.service.impl.DingdianService;
import com.example.demo.systempara.entity.Systempara;
import com.example.demo.systempara.service.ISystemparaService;
import com.example.demo.teacher.entity.Teacher;
import com.example.demo.teacher.service.ITeacherService;
import com.example.demo.utils.CustomerUtil;
import com.example.demo.utils.MD5Encrypt;
import com.example.demo.utils.VerifyUtil;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.experimental.var;

/**
 * <p>
 * 抖音 前端控制器 顶点业务页面 --ordercontroller业务处理
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-23
 */
@Controller
public class DycenterController {
	private static final Logger logger = LoggerFactory.getLogger(DycenterController.class);
	@Autowired
	private ISystemparaService systemparaService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IGoodclassService goodclassService;
	@Autowired
	private IGoodService goodService;
	@Autowired
	private IGoodtypeService goodtypeService;
	@Autowired
	private IOrderService orderService;
	
	
	@RequestMapping("/dycenter/dyshuafenlist")
	public ModelAndView dyshuafenlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/dycenter/dyshuafenlist");
		return mav;
	}
	@RequestMapping("/dycenter/dyshuafen")
	public ModelAndView dyshuafen(Integer id) {
		logger.info("dyshuafen");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("good", good);
		mav.addObject("zhek", zhek);
		mav.setViewName("webui/dycenter/dyshuafen");
		return mav;
	}
	@RequestMapping("/dycenter/dyshuafenpl")
	public ModelAndView dyshuafenpl(Integer id) {
		logger.info("dyshuafenpl");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/dycenter/dyshuafenpl");
		return mav;
	}
	@RequestMapping("/dycenter/dyshuafeninfor")
	public ModelAndView dyshuafeninfor(Integer id) {
		logger.info("dyshuafeninfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/dycenter/dyshuafeninfor");
		return mav;
	}	
	
	
	@RequestMapping("/dycenter/dyshuazanlist")
	public ModelAndView dyshuazanlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/dycenter/dyshuazanlist");
		return mav;
	}
	
	@RequestMapping("/dycenter/dyshuazan")
	public ModelAndView dyshuazan(Integer id) {
		logger.info("dyshuazan");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("good", good);
		mav.addObject("zhek", zhek);
		mav.setViewName("webui/dycenter/dyshuazan");
		return mav;
	}
	@RequestMapping("/dycenter/dyshuazanpl")
	public ModelAndView dyshuazanpl(Integer id) {
		logger.info("dyshuazanpl");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/dycenter/dyshuazanpl");
		return mav;
	}
	@RequestMapping("/dycenter/dyshuazaninfor")
	public ModelAndView dyshuazaninfor(Integer id) {
		logger.info("dyshuazaninfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/dycenter/dyshuazaninfor");
		return mav;
	}	
	private BigDecimal setCustomerPrice(Good good) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		BigDecimal zhek = BigDecimal.ONE;// 不同等级给的折扣信息
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer customer = customerService.selectOne(ew);
		EntityWrapper<Systempara> ews = new EntityWrapper<Systempara>();
		Systempara systempara = systemparaService.selectOne(ews);
		if (customer.getLevel() == 1) {
			zhek = systempara.getLevelonediscount();
		}
		if (customer.getLevel() == 2) {
			zhek = systempara.getLeveltwodiscount();
		}
		if (customer.getLevel() == 3) {
			zhek = systempara.getLevelthreediscount();
		}
		if (customer.getLevel() == 4) {
			zhek = systempara.getLevelfourdiscount();
		}
		good.setFastprice(good.getFastprice().multiply(zhek));
		//good.setSlowprice(good.getSlowprice().multiply(zhek));
		return zhek;
	}
	
}
