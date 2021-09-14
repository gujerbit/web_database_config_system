package com.gujerbit.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.main.service.TableServiceImpl;

@CrossOrigin("*")
@Controller
public class TableController {

	@Autowired
	private TableServiceImpl service;
	
	@PostMapping("/view/view_table")
	public @ResponseBody List<String> getViewTable(@RequestBody String db) {
		db = db.replaceAll("[\"\"]", "");
		db = db.replaceAll("[{}]", "");
		List<String> list = service.selectViewTable(db.split(":")[1]);
		
		return list;
	}
	
	@PostMapping("/view/view_table_content")
	public @ResponseBody List<String> getTableContent(@RequestBody String table) {
		table = table.replaceAll("[\"\"]", "");
		table = table.replaceAll("[{}]", "");
		List<String> list = service.selectTableContent(table.split(":")[1]);
		
		return list;
	}
	
}
