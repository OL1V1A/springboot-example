package com.lwj.springbootexample.msg;

import java.io.Serializable;
import java.util.UUID;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Msg implements Serializable {

	private static final long serialVersionUID = -1011199916429054249L;

	/** 消息ID */
	private String msgId = UUID.randomUUID().toString();

}
