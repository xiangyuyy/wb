package com.example.demo.customer.web;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.concurrent.DistributedLock;
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
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.service.ICustomerService;
import com.example.demo.systempara.entity.Systempara;
import com.example.demo.systempara.web.SystemparaController;
import com.example.demo.utils.CustomerUtil;
import com.example.demo.utils.MD5Encrypt;

/**
 * <p>
 * 会员信息表 前端控制器
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-12
 */
@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(SystemparaController.class);
	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/customercenter")
	public ModelAndView customercenter() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer model = customerService.selectOne(ew);
		ModelAndView mav = new ModelAndView();
		mav.addObject("customer", model);
		mav.setViewName("webui/customer/customercenter");
		return mav;
	}

	@RequestMapping("/customerinfor")
	public ModelAndView customerinfor() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer model = customerService.selectOne(ew);
		ModelAndView mav = new ModelAndView();
		mav.addObject("customer", model);
		mav.setViewName("webui/customer/customerinfor");
		return mav;
	}

	@RequestMapping("/customer/cpass")
	public ModelAndView cpass() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("webui/customer/cpass");
		return mav;
	}

	@RequestMapping("/customer/update")
	@ResponseBody
	public Result update(Customer customer) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		Result result = new Result();
		String nickname = customer.getNickname();

		if (StringUtils.isEmpty(nickname)) {
			result.setSucess(false);
			result.setMsg("昵称不能为空");
			return result;
		}
		if (!StringUtils.isEmpty(nickname) && !nickname.equals(account.getNickname())) {
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
			ew.where("nickname = {0}", nickname);
			List<Customer> list = customerService.selectList(ew);
			if (list.size() >= 1) {
				result.setSucess(false);
				result.setMsg("昵称已存在");
				return result;
			}
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", customer.getCustomerid());
		List<Customer> list = customerService.selectList(ew);
		if (list == null) {
			result.setSucess(false);
			result.setMsg("用户不存在，请重新登录");
			return result;
		}
		Customer update = list.get(0);
		update.setUpdatetime(new Date());
		update.setNickname(nickname);
		update.setQq(customer.getQq());
		boolean b;
		b = customerService.updateById(update);
		if (b) {
			session.setAttribute("account", update);
			result.setMsg("操作成功");
		} else {
			result.setSucess(false);
			result.setMsg("操作失败");
		}
		return result;
	}

	@RequestMapping("/customer/updatepass")
	@ResponseBody
	public Result updatepass(Customer customer) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Customer account = null;
		if (session.getAttribute("account") != null) {
			account = (Customer) (session.getAttribute("account"));
		}
		Result result = new Result();
		String phone = customer.getPhone();

		if (!StringUtils.isEmpty(phone)) {
			if (!phone.equals(account.getPhone())) {
				result.setSucess(false);
				result.setMsg("密保手机不正确,忘记手机请联系管理员");
				return result;
			}
		}
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", account.getCustomerid());
		Customer model = customerService.selectOne(ew);
/*		if (model == null) {
			result.setSucess(false);
			result.setMsg("用户不存在，请重新登录");
			return result;
		}*/
		Customer update = model;
		update.setUpdatetime(new Date());
		update.setPassword(MD5Encrypt.MD5Encode(customer.getPassword()));
		boolean b;
		b = customerService.updateById(update);
		if (b) {
			session.setAttribute("account", update);
			result.setMsg("操作成功");
		} else {
			result.setSucess(false);
			result.setMsg("操作失败");
		}
		return result;
	}

	@DistributedLock(lockKey = "'testDistributedLock'+#userId")
	@RequestMapping("/customer/testDistributedLock")
	@ResponseBody
	public Result testDistributedLock(String userId) {
		Result result = new Result();
		EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
		ew.where("customerid = {0}", userId);
		Customer model = customerService.selectOne(ew);
		if (model.getBalance().compareTo(new BigDecimal(10)) >= 0){
			model.setBalance(model.getBalance().subtract(new BigDecimal(1)));
			boolean b;
			b = customerService.updateById(model);
			if (b) {
				result.setMsg("操作成功");
			} else {
				result.setSucess(false);
				result.setMsg("操作失败");
			}
		}
		else{
			result.setSucess(false);
			result.setMsg("余额不足");
		}
		return  result;
	}
}
