package com.gujerbit.main.template;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableTemplate {

	private JdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource src) {
		template = new JdbcTemplate(src);
	}

	public boolean useDB(String query) {
		try {
			template.execute(query);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String currentDB() {
		return template.queryForObject("SELECT DATABASE()", String.class);
	}
	
	public List<Map<String, Object>> selectTableContent(String query) {
		List<Map<String, Object>> list = template.queryForList(query);
		return list;
	}

}
