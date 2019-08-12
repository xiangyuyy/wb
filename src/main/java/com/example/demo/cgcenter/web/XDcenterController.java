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
 * 微博 前端控制器 现代业务页面 --dyordercontroller业务处理
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-23
 */
@Controller
public class XDcenterController {
	private static final Logger logger = LoggerFactory.getLogger(XDcenterController.class);
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
	
	
	@RequestMapping("/xdcenter/wbdianzanlist")
	public ModelAndView wbdianzanlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/xdcenter/wbdianzanlist");
		return mav;
	}
	@RequestMapping("/xdcenter/wbdianzanforridlist")
	public ModelAndView wbdianzanforridlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/xdcenter/wbdianzanforridlist");
		return mav;
	}
	
	@RequestMapping("/xdcenter/wbdianzan")
	public ModelAndView wbdianzan(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("good", good);
		mav.addObject("zhek", zhek);
		mav.setViewName("webui/xdcenter/wbdianzan");
		return mav;
	}

	@RequestMapping("/xdcenter/wbdianzanforrid")
	public ModelAndView wbdianzanforrid(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("good", good);
		mav.addObject("zhek", zhek);
		mav.setViewName("webui/xdcenter/wbdianzanforrid");
		return mav;
	}
	@RequestMapping("/xdcenter/wbdianzanpl")
	public ModelAndView wbdianzanpl(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		int kind = 1;
/*		if (good.getGoodname().contains("微博赞")) {
			kind = 1;
		}*/
		if (good.getGoodname().contains("评论赞")) {
			kind = 2;
		}
/*		if (good.getGoodname().contains("表情赞")) {
			kind = 3;
		}*/
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.addObject("kind", kind);
		mav.setViewName("webui/xdcenter/wbdianzanpl");
		return mav;
	}
	@RequestMapping("/xdcenter/wbdianzaninfor")
	public ModelAndView wbdianzaninfor(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/xdcenter/wbdianzaninfor");
		return mav;
	}	
	
	
	
	
	@RequestMapping("/xdcenter/wbshuafenlist")
	public ModelAndView wbshuafenlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/xdcenter/wbshuafenlist");
		return mav;
	}
	@RequestMapping("/xdcenter/wbshuafen")
	public ModelAndView wbshuafen(Integer id) {
		logger.info("wbshuafen");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbshuafen");
		return mav;
	}
	@RequestMapping("/xdcenter/wbshuafenpl")
	public ModelAndView wbshuafenpl(Integer id) {
		logger.info("wbshuafenpl");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbshuafenpl");
		return mav;
	}
	
	@RequestMapping("/xdcenter/wbtouplist")
	public ModelAndView wbtouplist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/xdcenter/wbtouplist");
		return mav;
	}
	@RequestMapping("/xdcenter/wbtoup")
	public ModelAndView wbtoup(Integer id) {
		logger.info("wbtoup");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbtoup");
		return mav;
	}
	@RequestMapping("/xdcenter/wbtoupinfor")
	public ModelAndView wbtoupinfor(Integer id) {
		logger.info("wbtoupinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/xdcenter/wbtoupinfor");
		return mav;
	}	
	
	
	@RequestMapping("/xdcenter/wbshouyydlist")
	public ModelAndView wbshouyydlist(Integer goodid,String ifshowadd ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/xdcenter/wbshouyydlist");
		return mav;
	}
	@RequestMapping("/xdcenter/wbshouyyd")
	public ModelAndView wbshouyyd(Integer id) {
		logger.info("wbshouyyd");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbshouyyd");
		return mav;
	}
	@RequestMapping("/xdcenter/wbshouyydinfor")
	public ModelAndView wbshouyydinfor(Integer id) {
		logger.info("wbshouyydinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/xdcenter/wbshouyydinfor");
		return mav;
	}
	
	@RequestMapping("/xdcenter/wbhuatgzlist")
	public ModelAndView wbhuatgzlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/xdcenter/wbhuatgzlist");
		return mav;
	}
	@RequestMapping("/xdcenter/wbhuatgz")
	public ModelAndView wbhuatgz(Integer id) {
		logger.info("wbhuatgz");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbhuatgz");
		return mav;
	}
	@RequestMapping("/xdcenter/wbhuatgzinfor")
	public ModelAndView wbhuatgzinfor(Integer id) {
		logger.info("wbhuatgzinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/xdcenter/wbhuatgzinfor");
		return mav;
	}
	
	
	@RequestMapping("/xdcenter/wbshipbflist")
	public ModelAndView wbshipbflist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/xdcenter/wbshipbflist");
		return mav;
	}
	@RequestMapping("/xdcenter/wbshipbf")
	public ModelAndView wbshipbf(Integer id) {
		logger.info("wbhuatgz");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbshipbf");
		return mav;
	}
	@RequestMapping("/xdcenter/wbshipbfinfor")
	public ModelAndView wbshipbfinfor(Integer id) {
		logger.info("wbshipbfinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/xdcenter/wbshipbfinfor");
		return mav;
	}
	@RequestMapping("/xdcenter/wbshipbfpl")
	public ModelAndView wbshipbfpl(Integer id) {
		logger.info("wbshipbfpl");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbshipbfpl");
		return mav;
	}
	
	
	@RequestMapping("/xdcenter/wbshuafeninfor")
	public ModelAndView wbshuafeninfor(Integer id) {
		logger.info("wbshuafenfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/xdcenter/wbshuafeninfor");
		return mav;
	}	
	
	@RequestMapping("/xdcenter/wbzhuanfalist")
	public ModelAndView wbzhuanfalist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/xdcenter/wbzhuanfalist");
		return mav;
	}
	@RequestMapping("/xdcenter/wbzhuanfa")
	public ModelAndView wbzhuanfa(Integer id) {
		logger.info("wbzhuanfa");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbzhuanfa");
		return mav;
	}
	@RequestMapping("/xdcenter/wbzhuanfapl")
	public ModelAndView wbzhuanfapl(Integer id) {
		logger.info("wbzhuanfapl");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));


		BigDecimal zhek = setCustomerPrice(good);

		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/xdcenter/wbzhuanfapl");
		return mav;
	}
	@RequestMapping("/xdcenter/wbzhuanfainfor")
	public ModelAndView wbzhuanfainfor(Integer id) {
		logger.info("wbzhuanfainfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/xdcenter/wbzhuanfainfor");
		return mav;
	}	
	@RequestMapping("/xdcenter/ajaxwbdianzanlist")
	@ResponseBody
	public PageVo<Hopeorder> getlist(Hopeorder hopeorder,PagePara pagePara) {
		return orderService.GetWbdianzanPageList(hopeorder, pagePara);
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
