package com.jspiders.SpringBoot_DemoProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.SpringBoot_DemoProject.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
}
