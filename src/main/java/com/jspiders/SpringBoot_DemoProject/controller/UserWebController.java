
package com.jspiders.SpringBoot_DemoProject.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jspiders.SpringBoot_DemoProject.dto.User;
import com.jspiders.SpringBoot_DemoProject.service.UserService;
import com.jspiders.SpringBoot_DemoProject.util.ResponseStructure;

@Controller
@RequestMapping("/user")
public class UserWebController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String home() {
        return "index";
    }
    
    @GetMapping("/list")
    public String listUsers(Model model) {
        ResponseStructure<List<User>> response = userService.getAllUser();
        model.addAttribute("users", response.getData());
        return "user-list";
    }
    
    @GetMapping("/register-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user-registration";
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        ResponseStructure<User> response = userService.registerUser(user);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/user/list";
    }
    
    @GetMapping("/edit/{userId}")
    public String showEditForm(@PathVariable int userId, Model model, RedirectAttributes redirectAttributes) {
        ResponseStructure<Optional<User>> response = userService.getUserById(userId);
        
        if (response.getData().isPresent()) {
            model.addAttribute("user", response.getData().get());
            return "user-edit";
        } else {
            redirectAttributes.addFlashAttribute("message", "User not found");
            return "redirect:/user/list";
        }
    }
    
    @PostMapping("/update/{userId}")
    public String updateUser(@PathVariable int userId, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        ResponseStructure<User> response = userService.updateUser(userId, user);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/user/list";
    }
    
    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable int userId, RedirectAttributes redirectAttributes) {
        ResponseStructure<String> response = userService.deleteUserById(userId);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/user/list";
    }
    
    @GetMapping("/view/{userId}")
    public String viewUser(@PathVariable int userId, Model model, RedirectAttributes redirectAttributes) {
        ResponseStructure<Optional<User>> response = userService.getUserById(userId);
        
        if (response.getData().isPresent()) {
            model.addAttribute("user", response.getData().get());
            return "user-view";
        } else {
            redirectAttributes.addFlashAttribute("message", "User not found");
            return "redirect:/user/list";
        }
    }
}