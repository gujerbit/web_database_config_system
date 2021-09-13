package com.gujerbit.main.service;

import java.util.Map;

import com.gujerbit.main.vo.UserVO;

public interface SessionService {
	
	String create(UserVO user);
	
	void checkValid(String jwt);
	
	Map<String, Object> get(String jwt);
	
}
