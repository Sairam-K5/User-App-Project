package com.jspiders.SpringBoot_DemoProject.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private T data;
	private String message;
	private LocalDateTime timeStamp;
	private int statusCode;

}
