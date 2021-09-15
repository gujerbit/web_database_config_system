package com.gujerbit.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.main.service.TableServiceImpl;
import com.gujerbit.main.template.TableTemplate;

@CrossOrigin("*")
@Controller
public class TableController {

	@Autowired
	private TableServiceImpl service;
	
	@Autowired
	private TableTemplate template;
	
	private String db;
	
	private String JSONParser(String str) {
		str = str.replaceAll("[\"\"]", "");
		str = str.replaceAll("[{}]", "");
		str = str.split(":")[1];
		
		return str;
	}
	
	@PostMapping("/view/view_table")
	public @ResponseBody List<String> getViewTable(@RequestBody String db) {
		this.db = JSONParser(db);
		List<String> list = service.selectViewTable(this.db);
		
		return list;
	}
	
	@PostMapping("/view/view_table_content")
	public @ResponseBody List<Map<String, Object>> getTableContent(@RequestBody String table) {
		String query = "SELECT * FROM " + JSONParser(table);
		List<Map<String, Object>> map = template.selectTableContent(query);
		
		return map;
	}
	
	@PostMapping("/view/use_database")
	public @ResponseBody List<Object> useDB() {
		boolean result = template.useDB("USE " + this.db);
		String currentDB = template.currentDB();
		List<Object> list = new ArrayList<>();
		list.add(result);
		list.add(currentDB);
		
		return list;
	}
	
}
