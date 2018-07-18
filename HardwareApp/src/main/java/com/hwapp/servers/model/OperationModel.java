package com.hwapp.servers.model;

public class OperationModel {
	
	private boolean sucess;
	private String message;
	
	public OperationModel() {
		
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
