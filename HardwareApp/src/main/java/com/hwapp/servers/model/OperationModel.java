package com.hwapp.servers.model;

import org.springframework.validation.BindingResult;

public class OperationModel {
	
	private boolean sucess;
	private String message;
	BindingResult result;
	
	public BindingResult getResult() {
		return result;
	}

	public void setResult(BindingResult result) {
		this.result = result;
	}

	public OperationModel() {
		
	}
	
	public OperationModel(BindingResult result) {
		this.result = result;
		this.sucess = true;
	}
	
	public OperationModel(boolean success, String message) {
		this.sucess = success;
		this.message = message;
	}
	
	public OperationModel(boolean success) {
		this.sucess = success;
		this.message = "";
	}
	
	public boolean isSucess() {
		if(result!=null) {
			if(result.hasErrors()) {
				String initialMessage = "Invalid Field: ";
				StringBuilder sb = new StringBuilder(initialMessage);
				sb.append(result.getFieldError().getField());
				this.message = sb.toString();
				this.sucess = false;
			}
		}
		return sucess;
	}
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
