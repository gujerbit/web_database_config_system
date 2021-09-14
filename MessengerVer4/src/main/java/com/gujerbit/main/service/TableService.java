package com.gujerbit.main.service;

import java.util.List;

public interface TableService {

	List<String> selectViewTable(String db);
	
	List<String> selectTableContent(String table);
	
}
