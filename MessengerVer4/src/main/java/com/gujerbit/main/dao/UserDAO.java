package com.gujerbit.main.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

	public int login(Map<String, String> map);
	
	public String selectSalt(String id);
	
}
