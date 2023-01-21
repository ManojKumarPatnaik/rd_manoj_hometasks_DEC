package com.epam;

public class Message {

	private String msg;
	private String datetime;

	public Message(String msg, String datetime) {
		super();
		this.msg = msg;
		this.datetime = datetime;
	}

	public String getMsg() {
		return msg;
	}

	public String getDatetime() {
		return datetime;
	}

}
