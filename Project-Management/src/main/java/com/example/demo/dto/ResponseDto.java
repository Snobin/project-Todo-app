package com.example.demo.dto;

import org.json.simple.JSONObject;

public class ResponseDto {
	
	private String message;
	private JSONObject response;
	private boolean status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JSONObject getResponse() {
		return response;
	}

	public void setResponse(JSONObject response) {
		this.response = response;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ResponseDto(String message, JSONObject response, boolean status) {
		super();
		this.message = message;
		this.response = response;
		this.status = status;
	}

	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
