package com.java.spring.api.exception;

import lombok.*;

@Getter
@Setter
@Builder
public class ErrorResponse {
	private String code;
	private String message;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ErrorResponse() {
		super();
	}
	
	
}
