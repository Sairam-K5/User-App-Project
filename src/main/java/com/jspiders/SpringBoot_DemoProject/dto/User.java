package com.jspiders.SpringBoot_DemoProject.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotBlank
	@Pattern(regexp = "^[a-z A-Z]*$")
	@Size(min = 3,max = 12)
	private String name;
//	@Email
	private String email;
//	@Pattern
	private String password;
//	@Pattern
	private String contact;
//	@Max(value = 60)
//	@Min(value = 18)
//	private int age;
//	@Positive
//	private double sallary;
}
