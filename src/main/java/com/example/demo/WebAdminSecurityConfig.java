package com.example.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.service.ICustomerService;

/**
 * 登录配置
 */
@Configuration

public class WebAdminSecurityConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private ICustomerService customerService;
	public final static String SESSION_KEY = "account";

	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//	    addInterceptor.excludePathPatterns("/error");
		addInterceptor.addPathPatterns("/**");
		addInterceptor.excludePathPatterns("/login");
		addInterceptor.excludePathPatterns("/login**");
		addInterceptor.excludePathPatterns("/register");
		addInterceptor.excludePathPatterns("/register**");
		addInterceptor.excludePathPatterns("/layuiadmin/**");
	}

	private class SecurityInterceptor extends HandlerInterceptorAdapter {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws IOException {
			HttpSession session = request.getSession();
			EntityWrapper<Customer> ew = new EntityWrapper<Customer>();
            session.setAttribute(SESSION_KEY, customerService.selectList(ew).get(0));
            return true;
/*//            判断是否已有该用户登录的session
			if (session.getAttribute(SESSION_KEY) != null) {
				return true;
			}
//            跳转到登录页
			String url = "/login";
			response.sendRedirect(url);
			return false;*/
		}
	}
}
