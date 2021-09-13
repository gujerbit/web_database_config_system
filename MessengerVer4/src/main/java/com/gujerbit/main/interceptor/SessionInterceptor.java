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
		//option 요청은 바로 통과
		if(request.getMethod().equals("OPTION")) {
			return true;
		}
		else {
			//request parameter에서 auth_token으로 넘어온 것을 찾는다
			String token = request.getHeader("jwt-auth-token");
			
			if(token != null && token.length() > 0) {
				//유효한 token이면 진행 아니면 예외 처리
				service.checkValid(token);
				log.trace("token enable: {}", token);
				
				return true;
			} else {
				throw new RuntimeException("no have auth-token");
			}
		}
	}
	
}
