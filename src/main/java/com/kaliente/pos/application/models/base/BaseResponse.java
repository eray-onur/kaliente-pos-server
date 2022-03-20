package com.kaliente.pos.application.models.base;


public class BaseResponse<T> {
	private String message;
	private T data;
	
	public BaseResponse(T data) {
		super();
		this.data = data;
	}
	
	public BaseResponse(T data, String message) {
		super();
		this.data = data;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
