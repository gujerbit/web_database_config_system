package com.gujerbit.main.controller;

import java.util.List;

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
	
	@GetMapping("/view_message/{limit}")
	public @ResponseBody List<MessageVO> getMessageList(@PathVariable int limit) {
		List<MessageVO> list = service.selectMessageList(limit);
		System.out.println("safsadf");
		
		return list;
	}
	
	@GetMapping("/view_message_size")
	public @ResponseBody int getMessageSize() {
		int size = service.selectMessageSize();
		
		return size;
	}
	
}
