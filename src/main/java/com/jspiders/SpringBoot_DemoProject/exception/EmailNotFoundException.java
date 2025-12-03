package com.jspiders.SpringBoot_DemoProject.exception;

public class EmailNotFoundException extends RuntimeException {
	
	@Override
	public String getMessage() {
		return "Email Not Found Try Again With Rgistered Email!!";
	}

}
