package com.gujerbit.main.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MessageVO {

	@JsonProperty
	private int idx;
	
	@JsonProperty
	private int room_id;
	
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String message;
	
}
