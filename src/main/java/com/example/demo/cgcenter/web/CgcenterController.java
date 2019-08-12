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
 * 微博 前端控制器 顶点业务页面 --ordercontroller业务处理
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-23
 */
@Controller
public class CgcenterController {
	private static final Logger logger = LoggerFactory.getLogger(CgcenterController.class);
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
	
	
	@RequestMapping("/cgcenter/wbdianzanlist")
	public ModelAndView wbdianzanlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbdianzanlist");
		return mav;
	}
	@RequestMapping("/cgcenter/wbdianzanforkindlist")
	public ModelAndView wbdianzanforkindlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbdianzanforkindlist");
		return mav;
	}
	@RequestMapping("/cgcenter/wbdianzanforridlist")
	public ModelAndView wbdianzanforridlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbdianzanforridlist");
		return mav;
	}
	
	@RequestMapping("/cgcenter/wbdianzan")
	public ModelAndView wbdianzan(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("good", good);
		mav.addObject("zhek", zhek);
		mav.setViewName("webui/cgcenter/wbdianzan");
		return mav;
	}
	@RequestMapping("/cgcenter/wbdianzanforkind")
	public ModelAndView wbdianzanforkind(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("good", good);
		mav.addObject("zhek", zhek);
		mav.setViewName("webui/cgcenter/wbdianzanforkind");
		return mav;
	}
	@RequestMapping("/cgcenter/wbdianzanforrid")
	public ModelAndView wbdianzanforrid(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("good", good);
		mav.addObject("zhek", zhek);
		mav.setViewName("webui/cgcenter/wbdianzanforrid");
		return mav;
	}
	@RequestMapping("/cgcenter/wbdianzanpl")
	public ModelAndView wbdianzanpl(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		int kind = 1;
		if (good.getGoodname().contains("微博赞")) {
			kind = 1;
		}
		if (good.getGoodname().contains("评论赞")) {
			kind = 2;
		}
		if (good.getGoodname().contains("表情赞")) {
			kind = 3;
		}
		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.addObject("kind", kind);
		mav.setViewName("webui/cgcenter/wbdianzanpl");
		return mav;
	}
	@RequestMapping("/cgcenter/wbdianzaninfor")
	public ModelAndView wbdianzaninfor(Integer id) {
		logger.info("wbdianzan");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/cgcenter/wbdianzaninfor");
		return mav;
	}	
	
	
	@RequestMapping("/cgcenter/wbshuafenlist")
	public ModelAndView wbshuafenlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbshuafenlist");
		return mav;
	}
	@RequestMapping("/cgcenter/wbshuafen")
	public ModelAndView wbshuafen(Integer id) {
		logger.info("wbshuafen");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbshuafen");
		return mav;
	}
	@RequestMapping("/cgcenter/wbshuafenpl")
	public ModelAndView wbshuafenpl(Integer id) {
		logger.info("wbshuafenpl");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbshuafenpl");
		return mav;
	}
	
	@RequestMapping("/cgcenter/wbtouplist")
	public ModelAndView wbtouplist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbtouplist");
		return mav;
	}
	@RequestMapping("/cgcenter/wbtoup")
	public ModelAndView wbtoup(Integer id) {
		logger.info("wbtoup");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbtoup");
		return mav;
	}
	@RequestMapping("/cgcenter/wbtoupinfor")
	public ModelAndView wbtoupinfor(Integer id) {
		logger.info("wbtoupinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/cgcenter/wbtoupinfor");
		return mav;
	}	
	
	
	@RequestMapping("/cgcenter/wbshouyydlist")
	public ModelAndView wbshouyydlist(Integer goodid,String ifshowadd ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbshouyydlist");
		return mav;
	}
	@RequestMapping("/cgcenter/wbshouyyd")
	public ModelAndView wbshouyyd(Integer id) {
		logger.info("wbshouyyd");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbshouyyd");
		return mav;
	}
	@RequestMapping("/cgcenter/wbshouyydinfor")
	public ModelAndView wbshouyydinfor(Integer id) {
		logger.info("wbshouyydinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/cgcenter/wbshouyydinfor");
		return mav;
	}
	
	@RequestMapping("/cgcenter/wbhuatgzlist")
	public ModelAndView wbhuatgzlist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbhuatgzlist");
		return mav;
	}
	@RequestMapping("/cgcenter/wbhuatgz")
	public ModelAndView wbhuatgz(Integer id) {
		logger.info("wbhuatgz");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbhuatgz");
		return mav;
	}
	@RequestMapping("/cgcenter/wbhuatgzinfor")
	public ModelAndView wbhuatgzinfor(Integer id) {
		logger.info("wbhuatgzinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/cgcenter/wbhuatgzinfor");
		return mav;
	}
	
	
	@RequestMapping("/cgcenter/wbshipbflist")
	public ModelAndView wbshipbflist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbshipbflist");
		return mav;
	}
	@RequestMapping("/cgcenter/wbshipbf")
	public ModelAndView wbshipbf(Integer id) {
		logger.info("wbhuatgz");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbshipbf");
		return mav;
	}
	@RequestMapping("/cgcenter/wbshipbfinfor")
	public ModelAndView wbshipbfinfor(Integer id) {
		logger.info("wbshipbfinfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/cgcenter/wbshipbfinfor");
		return mav;
	}
	@RequestMapping("/cgcenter/wbshipbfpl")
	public ModelAndView wbshipbfpl(Integer id) {
		logger.info("wbshipbfpl");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));

		BigDecimal zhek = setCustomerPrice(good);
		mav.addObject("zhek", zhek);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbshipbfpl");
		return mav;
	}
	
	
	@RequestMapping("/cgcenter/wbshuafeninfor")
	public ModelAndView wbshuafeninfor(Integer id) {
		logger.info("wbshuafenfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/cgcenter/wbshuafeninfor");
		return mav;
	}	
	
	@RequestMapping("/cgcenter/wbzhuanfalist")
	public ModelAndView wbzhuanfalist(Integer goodid,String ifshowadd) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodid", goodid);
		if(ifshowadd == null) {
			ifshowadd = "0";
		}
		mav.addObject("ifshowadd", ifshowadd);
		mav.setViewName("webui/cgcenter/wbzhuanfalist");
		return mav;
	}
	@RequestMapping("/cgcenter/wbzhuanfa")
	public ModelAndView wbzhuanfa(Integer id) {
		logger.info("wbzhuanfa");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		Good kxzgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%空闲赞%"));
		Good cjzgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%初级赞%"));
		Good gszgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%高速赞%"));

		BigDecimal zhek = setCustomerPrice(good);
		setCustomerPrice(kxzgood);
		setCustomerPrice(cjzgood);
		setCustomerPrice(gszgood);
		mav.addObject("zhek", zhek);
		mav.addObject("kxzgood", kxzgood);
		mav.addObject("cjzgood", cjzgood);
		mav.addObject("gszgood", gszgood);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbzhuanfa");
		return mav;
	}
	@RequestMapping("/cgcenter/wbzhuanfapl")
	public ModelAndView wbzhuanfapl(Integer id) {
		logger.info("wbzhuanfapl");
		ModelAndView mav = new ModelAndView();
		Good good = goodService.selectOne(new EntityWrapper<Good>().where("id = {0}", id));
		Good kxzgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%空闲赞%"));
		Good cjzgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%初级赞%"));
		Good gszgood = goodService.selectOne(new EntityWrapper<Good>().where("goodname like {0}", "%高速赞%"));

		BigDecimal zhek = setCustomerPrice(good);
		setCustomerPrice(kxzgood);
		setCustomerPrice(cjzgood);
		setCustomerPrice(gszgood);
		mav.addObject("zhek", zhek);
		mav.addObject("kxzgood", kxzgood);
		mav.addObject("cjzgood", cjzgood);
		mav.addObject("gszgood", gszgood);
		mav.addObject("kxzgood", kxzgood);
		mav.addObject("cjzgood", cjzgood);
		mav.addObject("gszgood", gszgood);
		mav.addObject("good", good);
		mav.setViewName("webui/cgcenter/wbzhuanfapl");
		return mav;
	}
	@RequestMapping("/cgcenter/wbzhuanfainfor")
	public ModelAndView wbzhuanfainfor(Integer id) {
		logger.info("wbzhuanfainfor");
		ModelAndView mav = new ModelAndView();
		Hopeorder hopeorder = orderService.selectById(id);
		mav.addObject("order", hopeorder);
		mav.setViewName("webui/cgcenter/wbzhuanfainfor");
		return mav;
	}	
	@RequestMapping("/cgcenter/ajaxwbdianzanlist")
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
