package com.lwj.springbootexample.pulsar;

import java.io.Serializable;
import java.util.UUID;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 基础消息对象
 * 
 * @author 杜群
 *
 */
@Setter
@Getter
@ToString
public class Msg implements Serializable {

	private static final long serialVersionUID = -1011199916429054249L;

	/** 消息ID */
	private String msgId = UUID.randomUUID().toString();

}
