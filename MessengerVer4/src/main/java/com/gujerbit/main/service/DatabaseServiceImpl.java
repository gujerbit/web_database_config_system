package com.gujerbit.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.main.dao.DatabaseDAO;

@Service
public class DatabaseServiceImpl implements DatabaseService {

	@Autowired
	private DatabaseDAO dao;
	
	@Override
	public List<String> selectViewDatabase() {
		return dao.selectViewDatabase();
	}
	
}
