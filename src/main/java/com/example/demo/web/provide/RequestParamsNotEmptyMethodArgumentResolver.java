package com.example.demo.web.provide;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.example.demo.exception.meta.SkyParamNullException;
import com.example.demo.exception.meta.SkyParamsValueNullException;
import com.example.demo.web.annotation.RequestParamsNotEmpty;

@Component
public class RequestParamsNotEmptyMethodArgumentResolver extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			
			
			RequestParamsNotEmpty annotation = method.getAnnotation(RequestParamsNotEmpty.class);
			if (annotation != null) {
				RequestParamsNotEmpty requestParams = ((HandlerMethod) handler).getMethodAnnotation(RequestParamsNotEmpty.class);
				String[] params = requestParams.names();
				String value = null;
				if (params != null && params.length > 0) {
					for (String paramName : params) {
						if (StringUtils.isEmpty(paramName)) {
							throw new SkyParamNullException("names数组中存在空值");
						}
						value = request.getParameter(paramName);
						if (StringUtils.isEmpty(value)) {
							throw new SkyParamsValueNullException("参数" + paramName + "不能为空");
						}
					}
				}
			}

/*			RequestSelect requestSelect = method.getAnnotation(RequestSelect.class);
			if (requestSelect != null) {
				request.getSession().setAttribute("ifQuery", ((HandlerMethod) handlerMethod).getMethodAnnotation(RequestSelect.class).ifQuery());
			}*/
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
