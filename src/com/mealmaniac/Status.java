package com.mealmaniac;

public class Status {

	public boolean status;
	public boolean success = false;
	public Error error;
	
	public Status() {
		
	}
	
	public Status(boolean status, boolean success, Error error) {
		this.status = status;
		this.success = success;
		this.error = error;
	}

	public class Error {
		public String message;
		public int statusCode;
		
		public Error(String message, int statusCode) {
			this.message = message;
			this.statusCode = statusCode;
		}
	}

}
