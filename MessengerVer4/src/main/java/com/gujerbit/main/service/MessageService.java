package com.gujerbit.main.service;

import java.util.List;
import java.util.Map;

import com.gujerbit.main.vo.MessageVO;

public interface MessageService {

	List<MessageVO> selectMessageList(int limit);
	
	int selectMessageSize();
	
	List<MessageVO> selectSearchMessage(Map<String, String> map);
	
	int selectSearchMessageSize(String searchData);
	
}
