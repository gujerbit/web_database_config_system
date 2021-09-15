package com.gujerbit.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.main.template.QueryTemplate;

@CrossOrigin("*")
@Controller
public class QueryController {

	@Autowired
	private QueryTemplate template;
	
	@PostMapping("/query/select")
	public @ResponseBody List<Map<String, Object>> getSelectQueryResult(@RequestBody String query) {
		return template.selectTableContent(JSONParser(query));
	}
	
	@PostMapping("/query/manipulation")
	public @ResponseBody int getManipulationQueryResult(@RequestBody String query) {
		return template.dataManipulation(JSONParser(query));
	}
	
	@PostMapping("/query/definition")
	public @ResponseBody boolean getDefinitionQueryResult(@RequestBody String query) {
		return template.dataDefinition(JSONParser(query));
	}
	
	private String JSONParser(String str) {
		str = str.replaceAll("[\"\"]", "");
		str = str.replaceAll("[{}]", "");
		str = str.split(":")[1];
		
		return str;
	}
	
}
