package com.company.CompanyWebService.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.company.CompanyWebService.entity.CompanyEntity;



public class GenericResponse implements Serializable {
	
	public enum STATUS {
		SUCCESS,
		FAILED
	}
	
	private ArrayList<String> messages;
	private STATUS status;
	private Object body;
	//private String message;
	
	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	/*
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
/**
	 * 
	 */
	public GenericResponse (Object body, String message) {
		
		
	//	this.message=message;
		this.status= STATUS.SUCCESS;
		System.out.println("body: "+body);
		System.out.println("body: "+body);
	}
	public GenericResponse(String status, Object body) {

		 this.body=body;
		 if ("SUCCESS".equals(status)) {
			 this.status=STATUS.SUCCESS;
		 }else if ("FAILED".equals(status)) {
			 this.status=STATUS.FAILED;
		 }
	
/*
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;


*/
	
	
		 
	}
	

	@Override
	public String toString() {
		String str=" messages: "+messages+" status: "+status;
		return str;
		
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}
	

}
