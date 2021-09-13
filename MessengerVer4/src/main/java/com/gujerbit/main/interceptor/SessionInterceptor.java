package com.gujerbit.main.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gujerbit.main.service.SessionServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {

	@Autowired
	private SessionServiceImpl service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getMethod() + " : " + request.getServletPath());
		//option ��û�� �ٷ� ���
		if(request.getMethod().equals("OPTION")) {
			return true;
		}
		else {
			//request parameter���� auth_token���� �Ѿ�� ���� ã�´�
			String token = request.getHeader("jwt-auth-token");
			
			if(token != null && token.length() > 0) {
				//��ȿ�� token�̸� ���� �ƴϸ� ���� ó��
				service.checkValid(token);
				log.trace("token enable: {}", token);
				
				return true;
			} else {
				throw new RuntimeException("no have auth-token");
			}
		}
	}
	
}
