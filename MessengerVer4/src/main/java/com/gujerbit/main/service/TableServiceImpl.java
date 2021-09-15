package com.gujerbit.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.main.dao.TableDAO;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableDAO dao;

	@Override
	public List<String> selectViewTable(String db) {
		return dao.selectViewTable(db);
	}

}
