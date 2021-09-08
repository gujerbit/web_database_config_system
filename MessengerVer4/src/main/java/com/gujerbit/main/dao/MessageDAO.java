package com.gujerbit.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gujerbit.main.vo.MessageVO;

@Mapper
public interface MessageDAO {

	public List<MessageVO> selectMessageList(int limit);
	
	public int selectMessageSize();
	
}
