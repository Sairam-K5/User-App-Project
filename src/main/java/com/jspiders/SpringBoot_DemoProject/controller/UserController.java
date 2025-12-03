package com.jspiders.SpringBoot_DemoProject.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.SpringBoot_DemoProject.dto.User;
import com.jspiders.SpringBoot_DemoProject.service.UserService;
import com.jspiders.SpringBoot_DemoProject.util.ResponseStructure;

@RestController
@RequestMapping("/rest-user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		ResponseStructure<User> structure = userService.registerUser(user);
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}
}
