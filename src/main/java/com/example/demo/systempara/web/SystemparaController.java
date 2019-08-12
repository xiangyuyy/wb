package com.example.demo.systempara.web;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.demo.common.Result;
import com.example.demo.cshowvo.GoodCenerVo;
import com.example.demo.cshowvo.GoodClassVo;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.service.ICustomerService;
import com.example.demo.good.entity.Good;
import com.example.demo.good.service.IGoodService;
import com.example.demo.goodclass.entity.Goodclass;
import com.example.demo.goodclass.service.IGoodclassService;
import com.example.demo.goodtype.entity.Goodtype;
import com.example.demo.goodtype.service.IGoodtypeService;
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
 * 系统参数表 前端控制器
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-23
 */
@Controller
/* @RequestMapping("/") */
public class SystemparaController {
	private static final Logger logger = LoggerFactory.getLogger(SystemparaController.class);
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

	@RequestMapping("/")
	public ModelAndView index() {
		logger.info("index");
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account =(Customer)(session.getAttribute("account"));
		}	
		ModelAndView mav = new ModelAndView();
		mav.addObject("nickname", account.getNickname());
		
		List<GoodCenerVo> goodCenerVos = new LinkedList<GoodCenerVo>();
		var typelist = goodtypeService.selectList(new EntityWrapper<Goodtype>());
	    for (Goodtype goodtype : typelist) {
	    	GoodCenerVo vo = new GoodCenerVo();
	    	vo.setGoodtypename(goodtype.getName());	    	
	    	vo.setRank(goodtype.getRank());
	    
			List<Goodclass> goodclasslist = goodclassService.selectList(new EntityWrapper<Goodclass>());
					
	    	var ew = new EntityWrapper<Good>().where("goodtypename = {0}", goodtype.getName());
	    	ew.andNew().eq("status", 1);//上架的
			List<Good> goodlist = goodService.selectList(ew);
			HashSet<String> classlist = new HashSet<>();
			for (Good good : goodlist) {
				classlist.add(good.getGoodclassname());
			};
			List<GoodClassVo> goodClassVoslist = new LinkedList<GoodClassVo>();
			for (String classtype : classlist) {
				GoodClassVo goodClassVo = new GoodClassVo();
				goodClassVo.setGoodclassname(classtype);
				var rank = goodclasslist.stream().filter(a -> a.getName().equals(classtype)).collect(Collectors.toList()).get(0).getRank();
				goodClassVo.setRank(rank);
				var goodslist = goodlist.stream().filter(a -> a.getGoodclassname().equals(classtype)).collect(Collectors.toList());
				goodslist = goodslist.stream().sorted(Comparator.comparing(Good::getRank)).collect(Collectors.toList());;
				goodClassVo.setGoods(goodslist);
				goodClassVoslist.add(goodClassVo);
			};
			goodClassVoslist = goodClassVoslist.stream().sorted(Comparator.comparing(GoodClassVo::getRank)).collect(Collectors.toList());
			vo.setGoodclasses(goodClassVoslist);
			goodCenerVos.add(vo);
		}
	    goodCenerVos = goodCenerVos.stream().sorted(Comparator.comparing(GoodCenerVo::getRank)).collect(Collectors.toList());;
		mav.addObject("url", "/console");
		mav.addObject("goodCenerVos", goodCenerVos);
		mav.setViewName("webui/index");
		return mav;
	}
	
	@RequestMapping("/first")
	public ModelAndView first() {
		logger.info("first");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/first");
		return mav;
	}


	@RequestMapping("/console")
	public ModelAndView console() {
		ModelAndView mav = new ModelAndView();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account =(Customer)(session.getAttribute("account"));
		}
		BigDecimal zhek = BigDecimal.ONE;// 不同等级给的折扣信息
		EntityWrapper<Systempara> ews = new EntityWrapper<Systempara>();
		Systempara systempara = systemparaService.selectOne(ews);
		if (account.getLevel() == 1) {
			zhek = systempara.getLevelonediscount();
		}
		if (account.getLevel() == 2) {
			zhek = systempara.getLeveltwodiscount();
		}
		if (account.getLevel() == 3) {
			zhek = systempara.getLevelthreediscount();
		}
		if (account.getLevel() == 4) {
			zhek = systempara.getLevelfourdiscount();
		}
		String levelstr = "";
		
		if (account.getLevel() == 0 ) {
			levelstr = "普通";
		}
		if (account.getLevel() == 1 ) {
			levelstr = "黄金"/*+"("+ zhek +"折)"*/;
		}
		if (account.getLevel() == 2 ) {
			levelstr = "铂金"/*+"("+ zhek +"折)"*/;
		}
		if (account.getLevel() == 3 ) {
			levelstr = "钻石"/*+"("+ zhek +"折)"*/;
		}
		if (account.getLevel() == 4 ) {
			levelstr = "至尊"/*+"("+ zhek +"折)"*/;
		}
		mav.addObject("account", account);
		mav.addObject("levelstr", levelstr);
		mav.setViewName("webui/console");
		return mav;
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/login");
		return mav;
	}
	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/register");
		return mav;
	}

	@RequestMapping("/syspara/index")
	public ModelAndView sysParaIndex() {
		EntityWrapper<Systempara> ew = new EntityWrapper<Systempara>();
		Systempara systempara = systemparaService.selectOne(ew);
		ModelAndView mav = new ModelAndView();
		mav.addObject("systempara", systempara);
		mav.setViewName("webui/syspara/index");
		return mav;
	}

	@RequestMapping("/registerin")
	@ResponseBody
	public Result registerIn(String vercode,Customer customer) {
		Result result = new Result();
		String account = customer.getAccount();
		if (StringUtils.isEmpty(account)) {
			result.setSucess(false);
			result.setMsg("用户名不能为空");
			return result;
		}
		String phone = customer.getPhone();
		if (StringUtils.isEmpty(phone)) {
			result.setSucess(false);
			result.setMsg("电话号码不能为空");
			return result;
		}
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String imageCode = session.getAttribute("imageCode").toString();
		if (!imageCode.toLowerCase().equals(vercode.toLowerCase())) {
			result.setSucess(false);
			result.setMsg("图形验证码不正确");
			return result;
		}
		Integer byinvitatecod = customer.getByinvitatecod();
		if (!StringUtils.isEmpty(byinvitatecod)) {
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			ew.where("customerid = {0}", byinvitatecod);//邀请码为用户ID
			List<Customer> list = customerService.selectList(ew);
			if (list.size() == 0) {
				result.setSucess(false);
				result.setMsg("邀请码不存在");
				return result;
			}
		}	
		if (!StringUtils.isEmpty(account)) {
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			ew.where("account = {0}", account);
			List<Customer> list = customerService.selectList(ew);
			if (list.size() >= 1) {
				result.setSucess(false);
				result.setMsg("用户名已存在");
				return result;
			}
		}	
		if (!StringUtils.isEmpty(phone)) {
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			ew.where("phone = {0}", phone);
			List<Customer> list = customerService.selectList(ew);
			if (list.size() >= 1) {
				result.setSucess(false);
				result.setMsg("电话号码已存在");
				return result;
			}
		}				
		String ip = CustomerUtil.getIpAddress(request);
		if (!StringUtils.isEmpty(ip)) {
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			ew.where("ip = {0}", ip);
			List<Customer> list = customerService.selectList(ew);
			if (list.size() >= 3) {
				result.setSucess(false);
				result.setMsg("一个ip最多注册3个账号");
				return result;
			}
		}		
		customer.setPassword(MD5Encrypt.MD5Encode(customer.getPassword()));
		customer.setIp(ip);
		customer.setNickname("lucyfor"+customer.getAccount());
		customer.setUpdatetime(new Date());
		customer.setCreatetime(new Date());
		boolean b;
		b = customerService.insert(customer);
		if (b) {
			result.setMsg("注册成功");
		} else {
			result.setSucess(false);
			result.setMsg("注册失败");
		}
		return result;
	}
	@RequestMapping("/loginin")
	@ResponseBody
	public Result loginIn(String account, String password,String vercode) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();		
		Result result = new Result();
		String imageCode = session.getAttribute("imageCode").toString();
		if (!imageCode.toLowerCase().equals(vercode.toLowerCase())) {
			result.setSucess(false);
			result.setMsg("图形验证码不正确");
			return result;
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		password = MD5Encrypt.MD5Encode(password);
		ew.where("account = {0}", account).andNew("password = {0}", password);
		List<Customer> list = customerService.selectList(ew);
		if (list.size() == 1) {
			result.setMsg("登录成功");	
			session.setAttribute("account", list.get(0));
		} else {
			result.setSucess(false);
			result.setMsg("登录失败");
		}
		return result;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Result logout() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("account", null);
		Result result = new Result();
		result.setMsg("退出成功");
		return result;
	}
	
	/** 
	  * @author ZZC 
	  * @date 2017年11月6日 
	  * @param 
	  * @desc 图形验证码生成方法 
	  * 
	  */
	 @RequestMapping("/valicode") 
	 public void valicode(HttpServletResponse response,HttpSession session) throws Exception{ 
	  //利用图片工具生成图片 
	  //第一个参数是生成的验证码，第二个参数是生成的图片 
	  Object[] objs = VerifyUtil.createImage(); 
	  //将验证码存入Session 
	  session.setAttribute("imageCode",objs[0]); 
	    
	  //将图片输出给浏览器 
	  BufferedImage image = (BufferedImage) objs[1]; 
	  response.setContentType("image/png"); 
	  OutputStream os = response.getOutputStream(); 
	  ImageIO.write(image, "png", os); 
	 } 
}
