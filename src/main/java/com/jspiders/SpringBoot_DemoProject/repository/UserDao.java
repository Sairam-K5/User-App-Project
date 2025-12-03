package com.jspiders.SpringBoot_DemoProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.SpringBoot_DemoProject.dto.User;
import com.jspiders.SpringBoot_DemoProject.exception.InvalidUserId;

@Repository
public class UserDao {
	@Autowired
	UserRepository userRepository;

	public User registerUser(User user) {
		User user2 = userRepository.save(user);
		return user2;
	}

	public List<User> getAllUser() {
		List<User> listUsers = userRepository.findAll();
		return listUsers;
	}

	public Optional<User> getUserById(int userId) {
		Optional<User> optional = userRepository.findById(userId);
		if(optional.isPresent()) {
			return optional;
		}else {
			throw new InvalidUserId();
		}
	}

	public User updateUser(int userId, User user) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			user.setUserId(userId);
			return userRepository.save(user);
		} else {
			throw new IllegalArgumentException("Invalid User Id");
		}
	}

	public String deleteUserById(int userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			userRepository.delete(user);
			return "User Data Deleted SucessFully";
		} else {
			throw new IllegalArgumentException("Invalid User Id");
		}
	}
}
