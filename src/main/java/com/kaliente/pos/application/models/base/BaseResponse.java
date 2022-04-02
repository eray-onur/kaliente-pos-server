package com.kaliente.pos.application.models.base;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
	String message;
	T payload;
	
	public BaseResponse(T payload) {
		super();
		this.payload = payload;
	}
	
	public BaseResponse(T payload, String message) {
		super();
		this.payload = payload;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	
	
}
