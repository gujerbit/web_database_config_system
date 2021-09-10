package com.gujerbit.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gujerbit.main.dao.MessageDAO;
import com.gujerbit.main.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO dao;
	
	@Override
	public List<MessageVO> selectMessageList(int limit) {
		return dao.selectMessageList(limit);
	}
	
	@Override
	public int selectMessageSize() {
		return dao.selectMessageSize();
	}
	
	@Override
	public List<MessageVO> selectSearchMessage(Map<String, String> map) {
		return dao.selectSearchMessage(map);
	}
	
	@Override
	public int selectSearchMessageSize(String searchData) {
		return dao.selectSearchMessageSize(searchData);
	}
	
}
