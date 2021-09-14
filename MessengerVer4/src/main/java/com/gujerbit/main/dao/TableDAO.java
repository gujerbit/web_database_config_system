package com.gujerbit.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableDAO {

	public List<String> selectViewTable(String db);
	
	public List<String> selectTableContent(String table);
	
}
