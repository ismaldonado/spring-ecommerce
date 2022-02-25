package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.user.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@GetMapping("/login")
	public String create() {
		return "user/login";
	}

	@PostMapping("/save")
	public String save(User user) {
		user.setType("user");
		this.userService.save(user);
		return "redirect:/";
	}
}
