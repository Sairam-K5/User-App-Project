package com.jspiders.SpringBoot_DemoProject.exception;

public class InvalidUserId extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "Invalid Id Try Again !!";
	}

}
