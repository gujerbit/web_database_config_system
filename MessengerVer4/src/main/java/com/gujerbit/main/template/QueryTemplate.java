package com.gujerbit.main.template;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueryTemplate {

	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource src) {
		template = new JdbcTemplate(src);
	}
	
	public List<Map<String, Object>> selectTableContent(String query) {
		List<Map<String, Object>> list = template.queryForList(query);
		return list;
	}
	
	public int dataManipulation(String query) {
		return template.update(query);
	}
	
	public boolean dataDefinition(String query) {
		try {
			template.execute(query);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
}
