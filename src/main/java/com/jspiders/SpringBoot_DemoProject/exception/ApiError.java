package com.jspiders.SpringBoot_DemoProject.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiError {

	private String message;
	private LocalDateTime timeStamp;
	private Class className;
	private String path;

}
