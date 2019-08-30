package com.example.demo.utils;

import java.lang.reflect.Method;
import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELParser {

	private EvaluationContext context;

	private ExpressionParser parser;
	private LocalVariableTableParameterNameDiscoverer discoverer;

	public SpELParser(JoinPoint jp) throws Exception {
		discoverer = new LocalVariableTableParameterNameDiscoverer();
		parser = new SpelExpressionParser();
		getContext(jp);
	}

	public SpELParser(ProceedingJoinPoint pjp) throws Exception {
		discoverer = new LocalVariableTableParameterNameDiscoverer();
		parser = new SpelExpressionParser();
		getContext(pjp);
	}

	public SpELParser(ProceedingJoinPoint pjp, Object result) throws Exception {
		discoverer = new LocalVariableTableParameterNameDiscoverer();
		parser = new SpelExpressionParser();
		getContext(pjp, result);
	}

	public <T> T parseExpression(String expression, Class<T> clazz) {
		return parser.parseExpression(expression).getValue(context, clazz);
	}

	private void getContext(JoinPoint jp, Object result) throws Exception {
		Object[] args = jp.getArgs();
		Method method = ((MethodSignature) jp.getSignature()).getMethod();
		getContext(method, args, result);
	}

	private void getContext(JoinPoint jp) throws Exception {
		Object[] args = jp.getArgs();
		Method method = ((MethodSignature) jp.getSignature()).getMethod();
		getContext(method, args);
	}

	private void getContext(ProceedingJoinPoint pjp) throws Exception {
		Object[] args = pjp.getArgs();
		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		getContext(method, args);
	}

	private void getContext(Method method, Object[] args) throws Exception {
		context = new StandardEvaluationContext();
		String[] names = discoverer.getParameterNames(method);
		for (int i = 0; i < args.length; i++) {
			context.setVariable(names[i], args[i]);
		}
	}

	private void getContext(Method method, Object[] args, Object result) throws Exception {
		context = new StandardEvaluationContext();
		String[] names = discoverer.getParameterNames(method);
		if (result != null) {
			context.setVariable("result", result);
		}
		for (int i = 0; i < args.length; i++) {
			context.setVariable(names[i], args[i]);
		}
	}

	public static void main(String[] args) {
		EvaluationContext context = new StandardEvaluationContext();
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression("'classId='+ #classId").getValue(context, String.class));
		System.out.println(parser.parseExpression("#homeworks[0].userDetaillDto.mobile").getValue(context, String.class));
		System.out.println(parser.parseExpression("#homeworks.?[classId>7]").getValue(context, Collection.class));
		System.out.println(parser.parseExpression("#homeworks.!['classId='+classId]").getValue(context, Collection.class));
		System.out.println(parser.parseExpression("#ids").getValue(context, Collection.class));
	}
}
