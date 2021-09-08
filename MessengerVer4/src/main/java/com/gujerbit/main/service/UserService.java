package com.gujerbit.main.service;

import java.util.Map;

public interface UserService {

	int login(Map<String, String> map);
	
	String selectSalt(String id);
	
}
