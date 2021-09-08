package com.gujerbit.main.service;

import java.util.List;

import com.gujerbit.main.vo.MessageVO;

public interface MessageService {

	List<MessageVO> selectMessageList(int limit);
	
	int selectMessageSize();
	
}
