package com.gujerbit.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gujerbit.main.service.MessageServiceImpl;
import com.gujerbit.main.vo.MessageVO;

@CrossOrigin("*")
@Controller
public class MessageController {

	@Autowired
	private MessageServiceImpl service;
	
	@GetMapping("/view")
	public String init() {
		return "";
	}
	
	@GetMapping("/view/view_message/{limit}")
	public @ResponseBody List<MessageVO> getMessageList(@PathVariable int limit) {
		List<MessageVO> list = service.selectMessageList(limit);
		
		return list;
	}
	
	@GetMapping("/view/view_message_size")
	public @ResponseBody int getMessageSize() {
		int size = service.selectMessageSize();
		
		return size;
	}
	
	@GetMapping("/view/view_search_message/{searchInfo}")
	public @ResponseBody List<MessageVO> getSearchMessage(@PathVariable String[] searchInfo) {
		Map<String, String> map = new HashMap<>();
		map.put("searchData", searchInfo[0]);
		map.put("limit", searchInfo[1]);
		List<MessageVO> list = service.selectSearchMessage(map);
		
		return list;
	}
	
	@GetMapping("/view/view_search_message_size/{searchData}")
	public @ResponseBody int getSearchMessageSize(@PathVariable String searchData) {
		int size = service.selectSearchMessageSize(searchData);
		
		return size;
	}
	
}
