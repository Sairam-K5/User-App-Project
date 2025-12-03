package com.jspiders.SpringBoot_DemoProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.SpringBoot_DemoProject.dto.User;
import com.jspiders.SpringBoot_DemoProject.repository.UserRepository;
import com.jspiders.SpringBoot_DemoProject.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseStructure<User> registerUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(userRepository.save(user));
		structure.setStatusCode(201);
		structure.setMessage("User registered successfully");
		return structure;
	}

	public ResponseStructure<List<User>> getAllUser() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(userRepository.findAll());
		structure.setStatusCode(302);
		structure.setMessage("All users fetched successfully");
		return structure;
	}

	public ResponseStructure<Optional<User>> getUserById(int userId) {
		ResponseStructure<Optional<User>> structure = new ResponseStructure<>();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			structure.setData(user);
			structure.setStatusCode(302);
			structure.setMessage("User found successfully");
		} else {
			structure.setStatusCode(404);
			structure.setMessage("User not found");
		}
		return structure;
	}

	public ResponseStructure<User> updateUser(int userId, User updatedUser) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			existingUser.setName(updatedUser.getName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setContact(updatedUser.getContact());

			structure.setData(userRepository.save(existingUser));
			structure.setStatusCode(200);
			structure.setMessage("User updated successfully");
		} else {
			structure.setStatusCode(404);
			structure.setMessage("User not found");
		}
		return structure;
	}

	public ResponseStructure<String> deleteUserById(int userId) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			userRepository.deleteById(userId);
			structure.setData("User deleted");
			structure.setStatusCode(200);
			structure.setMessage("User deleted successfully");
		} else {
			structure.setData("User not found");
			structure.setStatusCode(404);
			structure.setMessage("User not found");
		}
		return structure;
	}
}
