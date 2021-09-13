package com.gujerbit.main.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gujerbit.main.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	@Override
	public int login(Map<String, String> map) {
		return dao.login(map);
	}
	
	@Override
	public String selectSalt(String id) {
		return dao.selectSalt(id);
	}
	
}
