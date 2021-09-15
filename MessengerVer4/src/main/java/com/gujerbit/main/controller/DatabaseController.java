package com.gujerbit.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.main.service.DatabaseServiceImpl;

@CrossOrigin("*")
@Controller
public class DatabaseController {
	
	@Autowired
	private DatabaseServiceImpl service;
	
	@PostMapping("/view/view_database")
	public @ResponseBody List<String> getViewDatabase() {
		List<String> list = service.selectViewDatabase();
		
		return list;
	}
	
}
